<html>
	<body>
		<script>
			var userId = ${user.id};
		</script>

		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<g:render template="/task/create"/>
			<g:render template="/dailyTask/create"/>
		</sec:ifAnyGranted>
		
		<div id="tasks">
			<g:render template="/task/index" model="[tasks: tasks]"/>
		</div>
	<%--	<g:render template="/dailyTask/index" model="[dailyTasks: user.dailyTasks]"/>--%>
	</body>
</html>