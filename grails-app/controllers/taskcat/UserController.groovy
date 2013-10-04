package taskcat

import grails.plugins.springsecurity.Secured;

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

		def tasks = taskService.currentTasksForUser(user)
		
		[user: User.get(params.id), tasks: tasks, task: task, dailyTask: dailyTask]
	}
}
