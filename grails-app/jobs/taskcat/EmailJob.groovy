package taskcat

import org.joda.time.LocalDate;

import taskcat.User;



class EmailJob {
	def TaskService taskService
	
    static triggers = {
//      cron name: 'myTrigger', cronExpression: "00 03 22 * * ?"
		cron name: 'myTrigger', cronExpression: '0 * * * * ?'
    }

    def execute() {
        // execute job
		println('foo')
//		LocalDate yesterday = new LocalDate().minusDays(1)
		User.all.each { user ->
			def completed = taskService.completedTasksForUserInPastDays(user, 1)
			def current = taskService.currentTasksForUser(user)
			
		}
    }
}
