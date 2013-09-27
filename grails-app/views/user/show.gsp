<html>
	<g:render template="/task/create"/>
	
	<g:render template="/task/index" model="[tasks: user.tasks]"/>
</html>