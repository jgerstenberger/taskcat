package taskcat

import grails.plugin.springsecurity.SpringSecurityUtils;

import org.joda.time.LocalDate
import org.springframework.security.access.AccessDeniedException;

import taskcat.TaskStatus;

class TaskService {
	static transactional = false

    def currentTasksForUser(User theUser) {
		def tasks = Task.findAll {
			user == theUser &&
			(status == TaskStatus.NOT_DONE || status == TaskStatus.NOT_CONFIRMED)
		}
		
		def dailyTasks = theUser.dailyTasks
		def today = new LocalDate()
		def dayOfWeekToday = today.dayOfWeek
		
		tasks.addAll(dailyTasks.findAll {
			!it.instancesThru && !it.excludedDays.contains(dayOfWeekToday)
		}.collect{
			newTaskFromDailyTask(it, dueDate: today)
		})
		
		tasks.addAll(dailyTasks.findAll {
			it.instancesThru != null && it.instancesThru < today
		}.collect { dt ->
			(dt.instancesThru.plusDays(1)..today).collect() { date ->
				if (!dt.excludedDays.contains(date.dayOfWeek))
					newTaskFromDailyTask(dailyTask: dt, dueDate: date)
			}
		}.flatten() - null)
		
		tasks.sort{ it.dueDate }
    }
	
	def recentTasksInCategories(User theUser, int days) {
		def tasks = Task.findAll(sort:'dueDate') {
			user == theUser &&
			dueDate > new LocalDate(theUser.timeZone()).minusDays(days)
		}
		
		def categoryMap = [:].withDefault {[]}
		tasks.each { task ->
			categoryMap[task.category] << task
		}
		
		categoryMap
	}

	def newTaskFromDailyTask(params) {
		new Task(user: params.dailyTask.user, 
			dailyTask: params.dailyTask,
			dueDate: params.dueDate ?: params.dailyTask.date,
			description: params.dailyTask.description,
			category: params.dailyTask.category,
			requiresConfirmation: params.dailyTask.requiresConfirmation)
	}
	
	def persistDailyTaskInstanceWithStatus(dailyTaskId, dueDate, status) {
		def dailyTask = DailyTask.get(dailyTaskId)
		if (SpringSecurityUtils.ifNotGranted("ROLE_ADMIN") && dailyTask.requiresConfirmation &&
			status == TaskStatus.DONE) {
			throw AccessDeniedException("Can't confirm this task")
		}
		
		Task task = newTaskFromDailyTask(dailyTask: dailyTask, dueDate: dueDate)
		task.status = status
		task.statusChangeDate = new LocalDate()
		if (!task.save())
			log.warn("Task ${task.properties} not saved because of:\n${task.errors}")
			
		if (task.dailyTask.instancesThru) {
			(task.dailyTask.instancesThru.plusDays(1)..<task.dueDate).each { day ->
				if (!task.dailyTask.excludedDays.contains(day.dayOfWeek)) {
					Task fillInTask = new Task(task.properties)
					fillInTask.status = TaskStatus.NOT_DONE
					fillInTask.dueDate = day
					if (!fillInTask.save())
						log.warn("Task ${fillInTask.properties} not saved because of:\n${fillInTask.errors}")
				}
			}
		}
		
		if (!task.dailyTask.instancesThru || task.dailyTask.instancesThru < task.dueDate) {
			task.dailyTask.instancesThru = task.dueDate
			if (!task.dailyTask.save())
				log.warn("Task ${task.dailyTask.properties} not saved because of:\n${task.dailyTask.errors}")
		}
	}
}
