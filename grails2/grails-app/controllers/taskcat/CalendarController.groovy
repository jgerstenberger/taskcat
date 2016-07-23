package taskcat

import grails.converters.JSON;
import grails.plugin.springsecurity.annotation.Secured;

import org.joda.time.LocalDate

@Secured(['ROLE_USER'])
class CalendarController {

	def index(int userId) {
		def user = User.get(userId)
		[user: user]
	}

	def data(int userId, long start, long end) {
		def tasks = Task.forUser(User.get(userId)).findAllByDueDateBetween(
			new LocalDate(start * 1000), new LocalDate(end * 1000))
		
		def events = tasks.collect {
			def color = '#888'
			if (it.isPastDue()) {
				color = '#f00'
			} else if (it.isDueTodayAndNotDone()) {
				color = '#00f'
			} else if (it.isCompleted()) {
				color = '#0f0'
			} else if (it.isNotConfirmed()) {
				color = '#ff0'
			} else if (it.isDailyTaskType() && it.status == TaskStatus.MISSED) {
				color = '#f00'
			}
			
			def description = it.description
			
			if (it.category) {
				def catName = it.category.name
				if (catName.length() > 4) {
					catName = catName.substring(0, 4)
				}
				description = catName + ':' + description
			}
			
			[title:description, start:it.dueDate, color:color]
		}
		
		render events as JSON
	}
}
