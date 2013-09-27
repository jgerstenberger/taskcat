package taskcat

class TaskController {

    def index() { }
	
	def save() {
		Task task = new Task(params)
		User.get(params.user_id).addToTasks(task).save()
		render 'ok'
	}
}
