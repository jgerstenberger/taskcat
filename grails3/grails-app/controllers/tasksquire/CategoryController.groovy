package taskcat

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_USER'])
class CategoryController {

    def show(int userId, int id) {
		def category = Category.get(id)
		[category: category, user: User.get(userId)]
	}
}
