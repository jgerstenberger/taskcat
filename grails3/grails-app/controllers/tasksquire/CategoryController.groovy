package tasksquire

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_USER'])
class CategoryController {

	@Secured(['ROLE_ADMIN'])
	def create() {
		respond new Category(params)
	}
	
	def save(Category category) {
		if (category.hasErrors()) {
			respond category.errors, view:'create'
			return
		}
		
		category.save flush:true
		
		redirect category
	}

    def show(int userId, int id) {
		def category = Category.get(id)
		[category: category, user: User.get(userId)]
	}
}
