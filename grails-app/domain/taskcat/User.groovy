package taskcat

class User {

	transient springSecurityService

	String username
	String password
	String firstName
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	static hasMany = [openIds: OpenID, tasks: Task]

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

//	protected void encodePassword() {
//		password = springSecurityService.encodePassword(password)
//	}
}
