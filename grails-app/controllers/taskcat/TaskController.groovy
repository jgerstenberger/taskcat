package taskcat

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate
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
				[retrieval: {theUser -> Task.completed.recent(3).forUser(user).list()},
				 tasksType: 'completedTasks']]
		
		def status = statusMap[TaskStatus.valueOf(params.status)]
		
		render(template: 'index', model: [tasks: status.retrieval(user),
			tasksType: status.tasksType, user: user])	
	}
	
	def edit() {
		def task = Task.get(params.id)
		def otherUsers = User.findAllByIdNotEqual(task.user.id)
		[user: task.user, task: task, categories: Category.all, otherUsers: otherUsers]
	}
	
	def update() {
		def task = Task.get(params.id)
		task.properties = params
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		redirect(controller: 'user', action: 'show', id: task.user.id)
	}
	
	def save() {
		Task task = new Task(params)
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		redirect(controller: 'user', action: 'show', id: params.user)
	}
	
	def delete(int id) {
		println 'delete'
		Task task = Task.get(id)
		User user = task.user
		task.delete()
		redirect(controller: 'user', action: 'show', id: user.id)
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
			task.category = task.dailyTask.category
			task.statusChangeDate = new LocalDate()
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
	
	def copy(int id, int destUserId) {
		def task = new Task(Task.get(id).properties)
		task.user = User.get(destUserId)
		if (!task.save()) {
			render task.errors	
		} else {
			redirect(controller: 'user', action: 'show', id: task.user.id)
		}
	}
	
	def recentForCategory(int userId, int categoryId) {
		def tasks = Task.inCategory(Category.get(categoryId)).forUser(User.get(userId)).
			list(sort: 'id', order: 'desc', max: 3)
		
		render(template:'recentForCategory', model:	[recent: tasks])
	}
	
//	def create() {
//		render(template:'create', model:[])
//	}
}