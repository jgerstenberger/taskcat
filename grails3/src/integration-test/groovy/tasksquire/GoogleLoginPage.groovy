import geb.Page
     
class GoogleLoginPage extends Page {
    static url = "/"
    static at = { title == "Sign in - Google Accounts" }
    static content = {
        email { $("input", name: "Email") }
		next { $("input#next", name: "signIn") }
        password(wait: true) { $("input", name: "Passwd") }
		signIn { $("#signIn") }
		submitApproveAccess(wait: true) { $("#submit_approve_access") }
/*        loginButton(to: AdminPage) { loginForm.login() }*/
    }
	
	def submitApproveAccess() {
		waitFor { 
			try {
				submitApproveAccess.click()
			} catch (RuntimeException e) {
				println e
			}
		}
	}
	
	def setPasswordField(newValue) {
		waitFor { password.isEnabled() }
		password = newValue
	}
}