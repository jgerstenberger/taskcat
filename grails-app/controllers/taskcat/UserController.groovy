package taskcat

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_USER'])
class UserController {

	def TaskService taskService
	
    def index() {
		[users: User.all]
	}
	
	def show() {
		def user = User.get(params.id)
		
		def task = new Task(userId: params.id)
		task.user = user

		def dailyTask = new DailyTask(userId: params.id)
		dailyTask.user = User.get(params.id)

		[user: User.get(params.id), users: User.all, 
			task: task, dailyTask: dailyTask, categories: Category.all]
	}
}
