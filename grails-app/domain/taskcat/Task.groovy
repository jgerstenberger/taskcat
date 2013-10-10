package taskcat

import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.joda.time.LocalDate;

enum TaskStatus {
	NOT_DONE, DONE, MISSED
}

class Task {
	String description = ''
	LocalDate dueDate
	TaskStatus status = TaskStatus.NOT_DONE
	LocalDate statusChangeDate
	
	static belongsTo = [user: User, dailyTask : DailyTask]
	
    static constraints = {
		dailyTask(nullable: true)
		statusChangeDate(nullable: true)
    }
	
	static mapping = {
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
	
	boolean isNotDone() {
		status == TaskStatus.NOT_DONE
	}
	
	boolean isCompletedLate() {
		status == TaskStatus.DONE && statusChangeDate > dueDate
	}
}
