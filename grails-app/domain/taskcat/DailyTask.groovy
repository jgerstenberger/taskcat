package taskcat

class DailyTask {
	String description
	static belongsTo = [user: User]
	static hasMany = [tasks: Task]
		
    static constraints = {
    }
}
