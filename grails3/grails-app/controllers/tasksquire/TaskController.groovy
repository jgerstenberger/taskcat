package tasksquire

import grails.plugin.springsecurity.SpringSecurityUtils;
import grails.plugin.springsecurity.annotation.Secured

import java.time.LocalDate

import tasksquire.TaskStatus

@Secured(['ROLE_USER'])
class TaskController {

	def TaskService taskService
	
	def index(int userId, String status) {
		[(TaskStatus.NOT_DONE.name): this.&indexNotDone, 
		(TaskStatus.DONE.name): this.&indexDone][status](userId)
	}
	
	def dailyTaskTrend(user) {
		def statMap = [(TaskStatus.MISSED): -1, (TaskStatus.NOT_DONE): 0, (TaskStatus.DONE): 1,
			(TaskStatus.NOT_CONFIRMED): 0]
		user.dailyTasks.collectEntries { dt ->
			[(dt.id): Task.findAllByDailyTask(dt, [sort: 'dueDate', order:'desc', max:14])*.status.
				collect{statMap[it]}.reverse()]
		}
	}
	
	def indexNotDone(int userId) {
		def user = User.get(userId)
		
		render(template: 'index', model: [tasks: taskService.currentTasksForUser(user),
			tasksType: 'currentTasks', user: user, dtTrend: dailyTaskTrend(user)])
	}
	
	def indexDone(int userId) {
		def user = User.get(userId)
		render(template: 'index', model: [tasks: Task.completed.recent(3, user).forUser(user).list(),
			tasksType: 'completedTasks', user: user, dtTrend: dailyTaskTrend(user)])
	}
	
	def indexCategory(int userId, int categoryId) {
		def user = User.get(userId)
		render(template: 'index', model: [user: user, showCompletedGreen: true, dtTrend: dailyTaskTrend(user),
			tasksType: 'categoryTasks', tasks: Task.forUser(user).inCategory(Category.get(categoryId)).list(
				[sort: 'dueDate', order: 'desc', max:20])])
	}	
	
	@Secured(['ROLE_ADMIN'])
	def edit() {
		def task = Task.get(params.id)
		def otherUsers = User.findAllByIdNotEqual(task.user.id)
		[user: task.user, task: task, categories: Category.list(), otherUsers: otherUsers]
	}
	
	@Secured(['ROLE_ADMIN'])
	def update() {
		def task = Task.get(params.id)
		task.properties = params
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
		redirect(controller: 'user', action: 'show', id: task.user.id)
	}
	
	@Secured(['ROLE_ADMIN'])
	def save() {
		Task task = new Task(params)
		if (!task.save())
			log.info("Task ${task.properties} not saved because of:\n${task.errors}")
			
		def referer =  request.getHeader('referer')
		if (referer)
			redirect(url: referer)
		else	
			redirect(controller: 'user', action: 'show', id: params.user)
	}
	
	@Secured(['ROLE_ADMIN'])
	def delete(int id) {
		Task task = Task.get(id)
		User user = task.user
		task.delete()
		redirect(controller: 'user', action: 'show', id: user.id)
	}
	
	@Secured(['ROLE_ADMIN'])
	def delay(int id) {
		Task task = Task.get(id)
		User user = task.user
		
		if (task.category) {
			def tasks = Task.forUser(user).inCategory(task.category).findAllByDueDateGreaterThanEquals(task.dueDate)
			tasks.each {
				it.dueDate = it.dueDate.plusDays(1)
				it.save()
			}
		}

		redirect(controller: 'user', action: 'show', id: user.id)
	}
	
	def updateStatus(Integer id) {
		if (id) {
			Task task = Task.get(id)
			if (SpringSecurityUtils.ifNotGranted("ROLE_ADMIN") && task.requiresConfirmation &&
				task.status == TaskStatus.NOT_CONFIRMED && status == TaskStatus.DONE) {
				throw AccessDeniedException("Can't confirm this task")
			}
			task.status = params.status
			if (!task.save())
				log.warn("Task ${task.properties} not saved because of:\n${task.errors}")
		} else {
			taskService.persistDailyTaskInstanceWithStatus(
				params.dailyTaskId, params.dueDate, params.status)
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
			list(sort: 'id', order: 'desc', max: 6)
		
		render(template:'recentForCategory', model:	[recent: tasks])
	}
	
	def create(int userId) {
		def task = new Task()
		task.user = request.getAttribute("user")
		
		def category = request.getAttribute("category")
		if (category)
			task.category = category
		
		render(template:'create', model:[categories: Category.list(), task: task])
	}
}