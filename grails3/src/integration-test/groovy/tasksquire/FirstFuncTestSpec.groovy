package tasksquire

import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.springframework.beans.factory.annotation.Value

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class FirstFuncTestSpec extends GebSpec {
	
	def grailsApplication

    def setup() {
/*
		defineBeans {
			clock(MockClock) {
				dateTime = "1/1/2001 12:00:00"
			}
		}*/
		
		def user = new User(username: 'u', password: 'p').save()
		User.withSession {
			it.flush()
			it.clear()
		}
    }

    def cleanup() {
    }
	
    void "test something"() {
        when:"The home page is visited"
			to LoginPage
			sleep(1000)
			username = 'u'
			password = 'p'
			sleep(1000)
			loginButton.click()
			sleep(1000)
			
		/*
			go '/'
			sleep(200)
			$('form').Email = grailsApplication.config.test.authUser
			sleep(200)
			$('#next').click()
			sleep(200)
			$('form').Passwd = grailsApplication.config.test.authPassword
			sleep(200)
			$('#signIn').click()
			waitFor {
				$('#submit_approve_access').firstElement().isEnabled()
			}
			$('#submit_approve_access').click()
			sleep(200)
		*/
		
/*            go '/'
			report 'auth1'
			$('form').Email = grailsApplication.config.test.authUser
			$('#next').click()
			report 'auth2'
			waitFor {
			}
			$('form').Passwd = grailsApplication.config.test.authPassword
			$('#signIn').click()
*/
/*			to GoogleLoginPage*/
/*			email = grailsApplication.config.test.authUser*/
			
/*			$('#next').click()*/
/*			next.click()*/
			
/*			sleep(10000)*/
			
/*			setPasswordField(grailsApplication.config.test.authPassword)*/
/*			signIn.click()*/

/*			sleep(10000)*/
						
/*			println title*/
/*			submitApproveAccess.click()*/
/*			println $('#submit_approve_access')*/
						
/*			if (title == 'Request for Permission') {*/
/*				println 'submitApproveAccess'*/
/*				submitApproveAccess()*/
/*				sleep(10000)*/
/*			}*/
			
/*			println grailsApplication.config.getProperty('grails.serverURL')*/
			
/*			println currentUrl*/
			
        then:"The title is correct"
        	title == "Login"	
			
		cleanup:
			report 'auth'
    }
}
