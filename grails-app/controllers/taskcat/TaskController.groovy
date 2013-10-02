package taskcat

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class TaskController {

	def TaskService taskService
	
    def index() {
		print TaskStatus.NOT_DONE == TaskStatus.valueOf(params.status)
		if (TaskStatus.valueOf(params.status) == TaskStatus.NOT_DONE)
			render(template: 'index', model: [tasks: taskService.currentTasksForUser(User.get(params.userId))])	
		else
			render 'ok'	
	}
	
	def save() {
		print params
		Task task = new Task(params)
		task.user = User.get(params.userId)
		task.save()
		print task.user
//		render(view: '/user/show', model: [user: task.user, task: task])
//		User u = User.get(params.user_id).addToTasks(task)
//		u.save()
		print task.errors
		redirect uri: '/'
	}
	
	def updateStatus() {
		print params
		
		if (params.id) {
			Task task = Task.get(params.id)
			task.status = params.status
			task.save()
			print task.errors
		} else {
		}
		
		render 'ok'
	}
}
