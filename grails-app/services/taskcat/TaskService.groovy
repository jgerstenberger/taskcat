package taskcat

import org.joda.time.LocalDate
import taskcat.TaskStatus;

class TaskService {
	static transactional = false

    def currentTasksForUser(User theUser) {
		def tasks = Task.findAll {
			user == theUser &&
			status == TaskStatus.NOT_DONE
		}
		
		def dailyTasks = theUser.dailyTasks
		def today = new LocalDate()
		def dayOfWeekToday = today.dayOfWeek
		
		tasks.addAll(dailyTasks.findAll {
			!it.instancesThru && !it.excludedDays.contains(dayOfWeekToday)
		}.collect{
			new Task(user: theUser, dailyTask: it, dueDate: today,
				description: it.description, category: it.category)
		})
		
		tasks.addAll(dailyTasks.findAll {
			it.instancesThru != null && it.instancesThru < today
		}.collect { dt ->
			(dt.instancesThru.plusDays(1)..today).collect() { date ->
				if (!dt.excludedDays.contains(date.dayOfWeek))
					new Task(user: theUser, dailyTask: dt, dueDate: date,
						description: dt.description, category: dt.category)
			}
		}.flatten() - null)
		
		tasks.sort{ it.dueDate }
    }
	
	def recentTasksInCategories(User theUser, int days) {
		def tasks = Task.findAll(sort:'dueDate') {
			user == theUser &&
			dueDate > new LocalDate().minusDays(days)
		}
		
		def categoryMap = [:].withDefault {[]}
		tasks.each { task ->
			categoryMap[task.category] << task
		}
		
		categoryMap
	}
}
