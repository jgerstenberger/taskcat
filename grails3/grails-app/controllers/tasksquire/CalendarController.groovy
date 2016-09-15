package tasksquire

import grails.converters.JSON;
import grails.plugin.springsecurity.annotation.Secured;

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Instant

@Secured(['ROLE_USER'])
class CalendarController {

	def index(int userId) {
		def user = User.get(userId)
		[user: user]
	}

	def data(int userId, long start, long end) {
		def theUser = User.get(userId)		
		def tasks = Task.findByUser(theUser).findByDueDateBetween(
			LocalDateTime.ofInstant(Instant.ofEpochSecond(start), user.timeZone()),
			LocalDateTime.ofInstant(Instant.ofEpochSecond(end), user.timeZone())
		)
				
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
						
			[title:description, start:it.dueDate.toString(), color:color]
		}
		
		render events as JSON
	}
}
