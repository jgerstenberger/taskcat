package taskcat

import grails.test.mixin.Mock;
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CategoryController)
@Mock([Category, User])
class CategoryControllerSpec extends Specification {

	def cat, user
	
    def setup() {
		user = new User(username: 'bob', password: 'pass', firstName: 'bob').save()
		cat = new Category(name: 'foo', user: user).save()
    }

    def cleanup() {
    }

    void "Show a category"() {
		when: "Show a category"
		def model = controller.show(user.id, cat.id)
		
		then: "Correct category and user are supplied"
		model.category == cat
		model.user == user
    }
}
