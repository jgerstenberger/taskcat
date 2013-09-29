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

		def dailyTask = new DailyTask(userId: params.id)
		dailyTask.user = User.get(params.id)

		[user: User.get(params.id), task: task, dailyTask: dailyTask]
	}
}
