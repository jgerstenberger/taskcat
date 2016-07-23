//import org.springframework.security.openid.OpenIDAuthenticationFilter
import taskcat.Role
import taskcat.User
import taskcat.UserRole

class BootStrap {

    def init = { servletContext ->
		environments {
			development {
//				def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
//				def roleUser = new Role(authority: 'ROLE_USER').save()
			}
		}
    }
    def destroy = {
    }
}
