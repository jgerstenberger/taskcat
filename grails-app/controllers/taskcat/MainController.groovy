package taskcat

import grails.plugin.springsecurity.annotation.Secured

class MainController {

	def springSecurityService
	
	@Secured(['ROLE_USER'])
    def index() {
//		render springSecurityService.getPrincipal().id
		redirect(controller: 'user', action: 'show', id: springSecurityService.getPrincipal().id)
	}
}
