package taskcat

import org.joda.time.LocalDate

class DailyTask {
	String description = ''
	LocalDate instancesThru
	
	static belongsTo = [user: User]
	static hasMany = [tasks: Task]
		
    static constraints = {
		instancesThru(nullable: true)
    }
}
