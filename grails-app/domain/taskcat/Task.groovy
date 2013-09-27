package taskcat

class Task {
	String description
	
	static belongsTo = [user: User, dailyTask : DailyTask]
	
    static constraints = {
		dailyTask(nullable: true)
    }
}
