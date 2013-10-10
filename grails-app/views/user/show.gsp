<r:require module="taskUpdate"/>
<html>
	<body>
		<script>
			var userId = ${user.id};
		</script>

		<div class="container">
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<g:render template="/task/create"/>
				<g:render template="/dailyTask/create"/>
			</sec:ifAnyGranted>
		
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Tasks to Do</h3>
				</div>
				<g:include controller="task" action="index" params="[status:'NOT_DONE', userId:user.id]"/>
			</div>		
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">Recently Completed Tasks</h3>
				</div>
				<g:include controller="task" action="index" params="[status:'DONE', userId:user.id]"/>
			</div>
		</div>
	</body>
</html>