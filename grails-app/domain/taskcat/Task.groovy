package taskcat

import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.joda.time.LocalDate;

enum TaskStatus {
	NOT_DONE, DONE
}

class Task {
	String description = ''
	LocalDate dueDate = new LocalDate()
	TaskStatus status = TaskStatus.NOT_DONE
	
	static belongsTo = [user: User, dailyTask : DailyTask]
	
    static constraints = {
		dailyTask(nullable: true)
    }
	
	static mapping = {
		dueDate type:PersistentLocalDate
	}
}
