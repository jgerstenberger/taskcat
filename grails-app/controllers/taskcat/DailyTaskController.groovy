package taskcat

class DailyTaskController {

    def index() { }
	
	def save() {
		DailyTask dTask = new DailyTask(params)
		dTask.user = User.get(params.userId)
		dTask.save()
		render 'ok'
	}
}
