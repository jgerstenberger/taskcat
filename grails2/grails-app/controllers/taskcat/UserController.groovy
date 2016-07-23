package taskcat

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_USER'])
class UserController {
	
    def index() {
		[users: User.list()]
	}
	
	def show() {
		def user = User.get(params.id)
		
		def dailyTask = new DailyTask(userId: params.id)
		dailyTask.user = user

		[user: user, dailyTask: dailyTask]
	}
}
