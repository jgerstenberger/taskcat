<r:require module="taskUpdate"/>
<html>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Tasks to Do</h3>
			</div>
			<g:include controller="task" action="indexNotDone" params="[userId:user.id]"/>
		</div>		
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">Recently Completed Tasks</h3>
			</div>
			<g:include controller="task" action="index" params="[status:'DONE', userId:user.id]"/>
		</div>
	</body>
</html>