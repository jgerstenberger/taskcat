package taskcat

import org.joda.time.DateTimeZone
import tasksquire.OAuthID

class User {
	
	transient springSecurityService

	String username
	String password
	String firstName
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	protected String timeZone = "America/New_York"

	static hasMany = [oAuthIDs: OAuthID, tasks: Task, dailyTasks: DailyTask]

	static transients = ['springSecurityService']
			
	static constraints = {
		username blank: false, unique: true
		password blank: false
		firstName blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
//		encodePassword()
	}

	def beforeUpdate() {
//		if (isDirty('password')) {
//			encodePassword()
//		}
	}
	
	DateTimeZone timeZone() {
		DateTimeZone.forID(timeZone);
	}

//	protected void encodePassword() {
//		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
//	}
}
