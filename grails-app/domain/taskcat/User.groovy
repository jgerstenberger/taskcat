package taskcat

import org.joda.time.DateTimeZone;

class User {

	transient springSecurityService

	String username
	String password
	String firstName
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	protected String timeZone = "America/New_York"
	
	static hasMany = [openIds: OpenID, tasks: Task, dailyTasks: DailyTask]

	static constraints = {
		username blank: false, unique: true
		password blank: false
		firstName blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
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
//		password = springSecurityService.encodePassword(password)
//	}
}
