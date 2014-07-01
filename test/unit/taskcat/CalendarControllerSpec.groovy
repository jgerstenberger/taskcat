package taskcat

import grails.plugin.jodatime.simpledatastore.SimpleMapJodaTimeMarshaller;
import grails.test.mixin.TestFor

import org.joda.time.LocalDate

import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CalendarController)
@Mock([Task, User])
class CalendarControllerSpec extends Specification {

	def user
	def today = new LocalDate()
	
    def setup() {
    	user = new User(username: 'bob', password: 'pass', firstName: 'bob').save()
		def cat = new Category(name: 'cat')
		def cat2 = new Category(name: 'categoryFoo')
		new Task(user:user, description: 'Task', dueDate: today, category: cat).save()
		new Task(user:user, description: 'Task', dueDate: today, category: cat2).save()
		new Task(user:user, description: 'Task', dueDate: today).save()
		
		grails.plugin.jodatime.converters.JodaConverters.registerJsonAndXmlMarshallers()
	}

    def cleanup() {
    }

    void "Get Calendar Data"() {
		when:
		params.userId = user.id
		params.start = 1300721778
		params.end = 1500721778
		def model = controller.data()
		then:
		response.text == '[{"title":"cate:Task","start":"' + today + '","color":"#00f"},{"title":"cate:Task","start":"2014-05-22","color":"#00f"},{"title":"cate:Task","start":"2014-05-22","color":"#00f"}]'
    }
	
	void "Get Calendar"() {
		when:
		params.userId = user.id
		def model = controller.index()
		then:
		user == model.user
	}
}
