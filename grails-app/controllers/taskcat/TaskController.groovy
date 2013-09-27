package taskcat

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class TaskController {

    def index() {
	}
	
	def save() {
		print params
		Task task = new Task(params)
		task.user = User.get(params.userId)
		print task.user
		render(view: '/user/show', model: [user: task.user, task: task])
//		User u = User.get(params.user_id).addToTasks(task)
//		u.save()
//		print u.errors
//		render 'ok'
	}
}
