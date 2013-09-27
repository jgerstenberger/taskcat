package taskcat

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class UserController {

    def index() {
		render User.all
	}
	
	def show() {
		def task = new Task(userId: params.id)
		task.user = User.get(params.id)
		print "${task.user}/${task.user.id}/${task.userId}"
		[user: User.get(params.id), task: task]
	}
}
