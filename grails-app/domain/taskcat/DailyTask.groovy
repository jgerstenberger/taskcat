package taskcat

import org.joda.time.LocalDate
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;

class DailyTask {
	String description = ''
	LocalDate instancesThru
	
	static belongsTo = [user: User]
	static hasMany = [tasks: Task]
		
    static constraints = {
		instancesThru(nullable: true)
    }
	
	static mapping = {
//		instancesThru type:PersistentLocalDate
	}
}
