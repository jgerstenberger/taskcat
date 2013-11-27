class UrlMappings {

	static mappings = {
		'/login/openIdCreateAccount' {
			controller = 'openId'
			action = 'createAccount'
		}

        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller = 'main')
        "500"(view:'/error')
	}
}
