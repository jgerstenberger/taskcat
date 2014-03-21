package taskcat

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class DailyTaskController {

    def index() { }
	
	def edit(int id) {
		def dailyTask = DailyTask.get(id)
		println "ed=" + dailyTask.excludedDays
		[dailyTask: dailyTask, categories: Category.all, user: dailyTask.user]
	}
	
	def update() {
		def dt = DailyTask.get(params.id)
		dt.properties = params
		if (!dt.save())
			log.info("DailyTask ${dt.properties} not saved because of:\n${dt.errors}")
		redirect(controller: 'user', action: 'show', id: dt.user.id)
	}
	
	def save() {
		DailyTask dTask = new DailyTask(params)
		dTask.user = User.get(params.userId)
		if (dTask.save())
			log.info("Daily Task ${dTask.properties} not saved because of:\n${dTask.errors}")
		redirect(controller: 'user', action: 'show', id: params.userId)
	}
	
	def create() {
		render(template:'create', model:[dailyTask:new DailyTask()])
	}
}
