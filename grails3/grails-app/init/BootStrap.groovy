import grails.core.GrailsApplication

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
    }
    def destroy = {
    }
}
