grails.plugin.springsecurity.userLookup.userDomainClassName = 'tasksquire.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'tasksquire.UserRole'
grails.plugin.springsecurity.authority.className = 'tasksquire.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',                     access: ['permitAll']],
	[pattern: '/**/css/**',                    access: ['permitAll']],
	[pattern: '/**/images/**',                 access: ['permitAll']],
	[pattern: '/**/favicon.ico',               access: ['permitAll']],
	[pattern: '/dbconsole/**',                 access: ['permitAll']],
	[pattern: '/tasksquireOAuth2/createAccount',           access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

/*grails.plugin.springsecurity.roleHierarchy = '''
   ROLE_ADMIN > ROLE_USER
'''
*/

// Added by the Spring Security OAuth2 Google Plugin:
grails.plugin.springsecurity.oauth2.domainClass = 'tasksquire.OAuthID'

grails.plugin.springsecurity.oauth2.registration.askToLinkOrCreateAccountUri = '/tasksquireOAuth2/createAccount'

grails.plugin.springsecurity.auth.loginFormUrl =
	"/springSecurityOAuth2/authenticate?provider=google"