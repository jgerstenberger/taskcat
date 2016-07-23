<div class="container">
	<div class="panel panel-primary" id="editDailyTaskPanel">
		<div class="panel-heading">
			<h3 class="panel-title">Edit Daily Task</h3>
		</div>
		<g:form class="panel-body" role="form" action="update">
			<g:render template="dailyTaskFormFields"/>
		
			<g:hiddenField name="id" value="${dailyTask.id}"/>
			<g:submitButton name="Save" class="btn btn-primary"/>
			<button id="editTaskCancel" class="btn btn-default">Cancel</button>
			<button id="editTaskDelete" class="btn btn-danger">Delete</button>
		</g:form>
	</div>
</div>