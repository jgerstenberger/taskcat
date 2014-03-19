package taskcat

import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.joda.time.LocalDate;

class Task {
	String description = ''
	LocalDate dueDate
	TaskStatus status = TaskStatus.NOT_DONE
	LocalDate statusChangeDate
	
	static belongsTo = [user: User, dailyTask : DailyTask, category: Category]
	
    static constraints = {
		dailyTask(nullable: true)
		statusChangeDate(nullable: true)
		category(nullable: true)
    }
	
	static mapping = {
	}
	
	static namedQueries = {
		completed {	eq 'status', TaskStatus.DONE }
		forUser { user -> eq 'user', user }
		inCategory { category -> eq 'category', category }
		recent { days ->
			gt 'dueDate', new LocalDate().minusDays(days)
		}		
	}
	
	def beforeUpdate() {
		if (isDirty('status'))
			statusChangeDate = new LocalDate()
	}
	
	boolean isPastDailyTask() {
		dailyTask && isPastDue()
	}
	
	boolean isPastDue() {
		status == TaskStatus.NOT_DONE && dueDate < new LocalDate()
	}
	
	boolean isDueTodayAndNotDone() {
		status == TaskStatus.NOT_DONE && dueDate == new LocalDate()
	}
	
	boolean isNotDone() {
		status == TaskStatus.NOT_DONE
	}
	
	boolean isCompletedLate() {
		status == TaskStatus.DONE && statusChangeDate > dueDate
	}
	
	boolean isCompleted() {
		status == TaskStatus.DONE
	}
	
	boolean isDailyTaskType() {
		dailyTask != null
	}
}
