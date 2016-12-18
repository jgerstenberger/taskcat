import grails.core.GrailsApplication
import tasksquire.Role

class BootStrap {

	GrailsApplication grailsApplication

	def checkForConfig(key) {
		def value = grailsApplication.config.getProperty(key)
		if (value == null || value ==~ /\$\{.*\}/) {
			throw new RuntimeException("${key} config not found")
		}
	}

    def init = { servletContext ->
		checkForConfig('grails.plugin.springsecurity.oauth2.providers.google.api_key')
		checkForConfig('grails.plugin.springsecurity.oauth2.providers.google.api_secret')
		
		environments {
			test {
				def adminRole = new Role(authority: 'ROLE_ADMIN').save()
				def userRole = new Role(authority: 'ROLE_USER').save()

				Role.withSession {
					it.flush()
					it.clear()
				}
			}
		}
    }
    def destroy = {
    }
}
