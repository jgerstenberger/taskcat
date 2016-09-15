package tasksquire

import java.time.LocalDate
//import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
//import org.joda.time.LocalDate;

class Task {
	String description = ''
	LocalDate dueDate
	TaskStatus status = TaskStatus.NOT_DONE
	LocalDate statusChangeDate
	boolean requiresConfirmation
	
	static belongsTo = [user: User, dailyTask : DailyTask, category: Category]
	
    static constraints = {
		dailyTask(nullable: true, unique: 'dueDate')
		statusChangeDate(nullable: true)
		category(nullable: true)
    }

/*	static namedQueries = {
		completed {	eq 'status', TaskStatus.DONE }

		ofUser { user -> eq 'user', user }

		inCategory { category -> eq 'category', category }

		recent { days, user ->
			gt 'dueDate', LocalDate.now(user.timeZone()).atStartOfDay().minusDays(days)
		}
	}
*/		
	def beforeUpdate() {
		if (isDirty('status'))
			statusChangeDate = LocalDate.now(user.timeZone())
	}
		
	boolean isPastDue() {
		(status == TaskStatus.NOT_DONE || status == TaskStatus.NOT_CONFIRMED) && 
			dueDate < LocalDate.now(user.timeZone())
	}
	
	boolean isPastDailyTask()	{dailyTask && isPastDue()}
	boolean isNotConfirmed()	{status == TaskStatus.NOT_CONFIRMED}
	boolean isDueTodayAndNotDone() {status == TaskStatus.NOT_DONE && dueDate == LocalDate.now(user.timeZone())}
	boolean isNotDone()			{status == TaskStatus.NOT_DONE}
	boolean isCompletedLate()	{status == TaskStatus.DONE && statusChangeDate > dueDate}
	boolean isCompleted()		{status == TaskStatus.DONE}
	boolean isDailyTaskType()	{dailyTask != null}
}
