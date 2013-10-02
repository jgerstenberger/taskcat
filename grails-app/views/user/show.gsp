<html>
	<body>
		<script>
			var userId = ${user.id};
		</script>
	
		<g:render template="/task/create"/>
		<g:render template="/dailyTask/create"/>
		
		<div id="tasks">
			<g:render template="/task/index" model="[tasks: tasks]"/>
		</div>
	<%--	<g:render template="/dailyTask/index" model="[dailyTasks: user.dailyTasks]"/>--%>
	</body>
</html>