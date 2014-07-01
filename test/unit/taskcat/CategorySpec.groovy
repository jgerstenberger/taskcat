package taskcat

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Category)
//@Mock(Task)
class CategorySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Create some categories and check their properties"() {
		given: "A category"
		def cat = new Category(name: 'Name', requiresConfirmation: true)
		
		expect: "Save is OK and requiresConfirmation is true"
		cat.save() && cat.requiresConfirmation
		
		when: "Another category is added"
		def cat2 = new Category(name: 'Another Category')
		cat2.save()
		
		then: "There are now 2 categories and requiresConfirmation is false for cat2"
		Category.list().size == 2
		!cat2.requiresConfirmation
		
//		when: "Tasks are added"
//		cat.tasks = tasks
//		
//		then: "Category has the right number of tasks in the right order"
//		cat.tasks.size() == expected
		
//		where:
//		name	|	tasks								|	expected
//		'Name'	|	[]									|	0
//		'Other'	|	[new Task(description: 'Desc 1'), new Task(description)]	|	1
		
    }
}
