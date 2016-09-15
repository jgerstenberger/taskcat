package tasksquire


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
import java.time.LocalDate

@Integration
@Rollback
class FirstIntTestSpec extends Specification {

    void "test something"() {
		given:
		def user = new User(username: 'fred', password: 'irrelevant', firstName: 'Fred').save()
		def task = new Task(description: 'walk the cat', user: user, dueDate: LocalDate.now(user.timeZone()))
		println task

		when:
		task.save(flush: true)
		
        then:"there is one task"
		!task.hasErrors()
        Task.count() == 1
    }
}
