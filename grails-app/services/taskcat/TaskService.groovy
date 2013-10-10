package taskcat

import org.joda.time.LocalDate
import taskcat.TaskStatus;

class TaskService {

    def currentTasksForUser(User theUser) {
		def tasks = Task.findAll {
			user == theUser &&
			status == TaskStatus.NOT_DONE
		}
		
		def dailyTasks = theUser.dailyTasks
		
		tasks.addAll(dailyTasks.findAll {
			!it.instancesThru
		}.collect{
			new Task(user: theUser, dailyTask: it, dueDate: new LocalDate(),
				description: it.description)
		})
		
		tasks.addAll(dailyTasks.findAll {
			it.instancesThru != null && it.instancesThru < new LocalDate()
		}.collect { dt ->
			(dt.instancesThru.plusDays(1)..new LocalDate()).collect() { date ->
				new Task(user: theUser, dailyTask: dt, dueDate: date,
					description: dt.description)
			}
		}.flatten())
		
		tasks
    }
	
	def recentlyCompletedTasksForUser(User theUser) {
		def tasks = Task.findAll {
			user == theUser &&
			status == TaskStatus.DONE &&
			statusChangeDate > new LocalDate().minusDays(3)
		}

	}
}
