import taskcat.Role
import taskcat.User
import taskcat.UserRole

class BootStrap {

    def init = { servletContext ->
      String password = 'password'

      def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
      def roleUser = new Role(authority: 'ROLE_USER').save()

//      def user = new User(username: 'foo@bar.com', password: password, enabled: true).save()
//      def admin = new User(username: 'admin', password: password, enabled: true).save()

//      UserRole.create user, roleUser
//      UserRole.create admin, roleUser
//      UserRole.create admin, roleAdmin, true
    }
    def destroy = {
    }
}
