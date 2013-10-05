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
		Task task = new Task(params)
		task.user = User.get(params.userId)
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		redirect uri: '/'
	}
	
	def updateStatus() {
		if (!params.id.isEmpty()) {
			Task task = Task.get(params.id)
			task.status = params.status
			task.save()
		} else {
			Task task = new Task(params)
			task.dailyTask = DailyTask.get(params.dailyTaskId)
			task.description = task.dailyTask.description
			task.user = User.get(params.userId)
			task.save()
			
			if (task.dailyTask.instancesThru) {
				(task.dailyTask.instancesThru.plusDays(1)..<task.dueDate).each {
					Task fillInTask = new Task(task.properties)
					fillInTask.status = TaskStatus.NOT_DONE
					fillInTask.dueDate = it
					fillInTask.save()
				}
			}
			
			if (!task.dailyTask.instancesThru || task.dailyTask.instancesThru < task.dueDate) {
				task.dailyTask.instancesThru = task.dueDate
				task.dailyTask.save()
			}
		}
		
		render 'ok'
	}
}
