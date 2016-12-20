import geb.Page
     
class LoginPage extends Page {
    static url = "/login/auth"
    static at = { title == "Login" }
    static content = {
        username { $("input", name: "username") }
        password { $("input", name: "password") }
		loginButton { $("input", type: "submit") }
	}
}