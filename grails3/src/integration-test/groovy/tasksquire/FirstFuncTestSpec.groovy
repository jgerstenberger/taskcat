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
	
	@Value('${local.server.port}')
	Integer serverPort
		
	def grailsApplication

    def setup() {
		grailsApplication.config.grails.serverURL = "http://localhost:${grailsApplication.getMainContext().embeddedServletContainer.port}"
    }

    def cleanup() {
    }
	
    void "test something"() {
        when:"The home page is visited"
			go '/'
			$('form').Email = grailsApplication.config.test.authUser
			$('#next').click()
			waitFor {
				$("input", name: "Passwd")
			}
			$('form').Passwd = grailsApplication.config.test.authPassword
			$('#signIn').click()
/*			for (int i = 0; i < 50; i++) {
				println $('#submit_approve_access').firstElement().isEnabled()
				sleep(100)
			}*/
			waitFor {
				$('#submit_approve_access').firstElement().isEnabled()
			}
			
			$('#submit_approve_access').click()
			sleep(2000)
		
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
        	title == "Sign in - Google Accounts"	
			
		cleanup:
			report 'auth'
    }
}
