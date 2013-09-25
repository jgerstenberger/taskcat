class UrlMappings {

	static mappings = {
//		'/login/auth'(uri: '/j_spring_openid_security_check?openid_identifier=' + URLEncoder.encode('https://www.google.com/accounts/o8/id', 'UTF-8'))
		
		'/login/openIdCreateAccount' {
			controller = 'openId'
			action = 'createAccount'
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
