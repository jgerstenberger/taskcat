package taskcat

class Category {

	String name
	
	static hasMany = [tasks: Task]
	
    static mapping = {
		sort 'name'
    }
}
