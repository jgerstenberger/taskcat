package taskcat

import grails.gsp.PageRenderer;
import grails.plugin.mail.MailService;

import org.joda.time.LocalDate;

import taskcat.User;

class EmailJob {
	PageRenderer groovyPageRenderer
	TaskService taskService
	MailService mailService
	
    static triggers = {
//      cron name: 'myTrigger', cronExpression: "00 03 22 * * ?"
		cron name: 'myTrigger', cronExpression: '0 * * * * ?'
    }

    def execute() {
        // execute job
//		println('foo')
//		LocalDate yesterday = new LocalDate().minusDays(1)
		User.all.each { user ->
			def completed = taskService.completedTasksForUserInPastDays(user, 1)
			def current = taskService.currentTasksForUser(user)
			
//			println(groovyPageRenderer.render(view: '/email/dailyEmail', 
//				model:[current: current, completed: completed]))

//			mailService.sendMail {
//				to 'jason@gerstenberger.name'
//				subject 'test'
//				html view: '/email/dailyEmail', model: [current: current, completed: completed]
//			}
		}
    }
}
