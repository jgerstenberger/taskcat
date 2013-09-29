<html>
	<g:render template="/task/create"/>
	<g:render template="/dailyTask/create"/>
	
	<g:render template="/task/index" model="[tasks: user.tasks]"/>
	<g:render template="/dailyTask/index" model="[dailyTasks: user.dailyTasks]"/>
</html>