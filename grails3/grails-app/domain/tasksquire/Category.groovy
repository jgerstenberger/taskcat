package tasksquire

class Category {

	String name
	boolean requiresConfirmation
	
	static hasMany = [tasks: Task]
	
    static mapping = {
		sort 'name'
    }
}
