package taskcat

import grails.plugins.springsecurity.Secured
import taskcat.TaskStatus

@Secured(['ROLE_USER'])
class TaskController {

	def TaskService taskService
	
    def index() {
		def user = User.get(params.userId)
		def statusMap = [(TaskStatus.NOT_DONE): 
				[retrieval: {theUser -> taskService.currentTasksForUser(theUser)},
					tasksType: 'currentTasks'],
			(TaskStatus.DONE): 
				[retrieval: {theUser -> taskService.recentlyCompletedTasksForUser(theUser)},
					tasksType: 'completedTasks']]
		
		def status = statusMap[TaskStatus.valueOf(params.status)]
		
		render(template: 'index', model: [tasks: status.retrieval(user),
			tasksType: status.tasksType])	
	}
	
	def save() {
		Task task = new Task(params)
		task.user = User.get(params.userId)
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		redirect(controller: 'user', action: 'show', id: params.userId)
	}
	
	def updateStatus() {
		if (!params.id.isEmpty()) {
			Task task = Task.get(params.id)
			task.status = params.status
			if (!task.save())
				log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		} else {
			Task task = new Task(params)
			task.dailyTask = DailyTask.get(params.dailyTaskId)
			task.description = task.dailyTask.description
			task.user = User.get(params.userId)
			if (!task.save())
				log.info("Task ${task.properties} not saved because of:\n${task.errors}")
			
			if (task.dailyTask.instancesThru) {
				(task.dailyTask.instancesThru.plusDays(1)..<task.dueDate).each {
					Task fillInTask = new Task(task.properties)
					fillInTask.status = TaskStatus.NOT_DONE
					fillInTask.dueDate = it
					if (!fillInTask.save())
						log.info("Task ${fillInTask.properties} not saved because of:\n${fillInTask.errors}")
				}
			}
			
			if (!task.dailyTask.instancesThru || task.dailyTask.instancesThru < task.dueDate) {
				task.dailyTask.instancesThru = task.dueDate
				if (!task.dailyTask.save())
					log.info("Task ${task.dailyTask.properties} not saved because of:\n${task.dailyTask.errors}")
			}
		}
		
		render 'ok'
	}
}