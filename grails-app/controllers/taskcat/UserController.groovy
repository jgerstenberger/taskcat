package taskcat

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class UserController {

    def index() {
		render User.all
	}
	
	def show() {
		print User.get(params.id).tasks
		
//		render User.get(params.id)
		[user: User.get(params.id)]
	}
}
