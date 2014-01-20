package taskcat

import org.joda.time.LocalDate
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;

class DailyTask {
	String description = ''
	LocalDate instancesThru
	
	static belongsTo = [user: User, category: Category]
	static hasMany = [tasks: Task, excludedDays: Integer]
		
    static constraints = {
		instancesThru(nullable: true)
		category(nullable: true)
    }
	
	static mapping = {
//		instancesThru type:PersistentLocalDate
	}
}
