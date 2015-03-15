package taskcat

import grails.plugin.springsecurity.SpringSecurityUtils;
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.oauth.OAuthToken;

@Secured(['ROLE_USER'])
class MainController {

	def springSecurityService
	def oauthService
	
	public static final String SPRING_SECURITY_OAUTH_TOKEN = 'springSecurityOAuthToken'
	
    def index() {
		redirect(controller: 'user', action: 'show', id: springSecurityService.getPrincipal().id)
	}
	
	def taskbar() {
		def users = SpringSecurityUtils.ifAllGranted("ROLE_ADMIN") ? User.list() : null
		
		render template:'taskbar', model:[user:request.getAttribute("user"), users: users, categories: Category.list()]
	}
	
	def linkAccount() {
		OAuthToken oAuthToken = session[SPRING_SECURITY_OAUTH_TOKEN]

		def googleResource = oauthService.getGoogleResource(oAuthToken.accessToken,
			"https://www.googleapis.com/oauth2/v1/userinfo")
		def googleResponse = JSON.parse(googleResource?.getBody())
		
		println googleResponse.verified_email
		println googleResponse.given_name

		if (googleResponse.verified_email == 'false') {
			render 'must have verified email'
			return
		}

		boolean linked = User.withTransaction { status ->
			//def user = User.findByUsernameAndPassword(command.username, springSecurityService.encodePassword(command.password))
			def user = User.findByUsername(oAuthToken.principal)
			if (user) {
				user.addToOAuthIDs(provider: oAuthToken.providerName, accessToken: oAuthToken.socialId, user: user)
				if (user.validate() && user.save()) {
					oAuthToken = springSecurityOAuthService.updateOAuthToken(oAuthToken, user)
					return true
				} else {
					return false
				}
			}
			status.setRollbackOnly()
			return false
		}
		if (linked) {
			session.removeAttribute SPRING_SECURITY_OAUTH_TOKEN
			SecurityContextHolder.context.authentication = oAuthToken
			redirect(uri: '/')
			return
		}
		
		/* User creation turned off
		boolean created = User.withTransaction { status ->
			def user = User.newInstance()
			//User user = new User(username: command.username, password: command.password1, enabled: true)
			user.firstName = googleResponse.given_name
			user.username = oAuthToken.principal
			user.password = 'foo'
			user.enabled = true
			user.addToOAuthIDs(provider: oAuthToken.providerName, accessToken: oAuthToken.socialId, user: user)
			// updateUser(user, oAuthToken)
			if (!user.validate() || !user.save()) {
				 user.errors.each {
					println it
				}
				status.setRollbackOnly()
				return false
			}
			def roles = springSecurityOAuthService.getRoleNames()
			for (roleName in roles) {
				UserRole.create user, Role.findByAuthority(roleName)
			}
			oAuthToken = springSecurityOAuthService.updateOAuthToken(oAuthToken, user)
			true
		}
		if (created) {
			session.removeAttribute SPRING_SECURITY_OAUTH_TOKEN
			SecurityContextHolder.context.authentication = oAuthToken
			redirect(uri: '/')
			return
		}
		*/
		
		render 'uh oh'
	}
}
