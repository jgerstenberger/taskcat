package taskcat

class DailyTaskController {

    def index() { }
	
	def save() {
		DailyTask dTask = new DailyTask(params)
		dTask.user = User.get(params.userId)
		if (dTask.save())
			log.info("Daily Task ${dTask.properties} not saved because of:\n${dTask.errors}")
		redirect(controller: 'user', action: 'show', id: params.userId)
	}
}
