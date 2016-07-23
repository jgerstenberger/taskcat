<r:require module="taskCreate"/>

<div class="panel panel-primary" id="createDailyTaskPanel">
	<div class="panel-heading">
		<h3 class="panel-title">Create Daily Task</h3>
	</div>

	<g:form controller="dailyTask" action="save" class="create-task panel-body">
		<g:render template="dailyTaskFormFields"/>
		<g:hiddenField name="userId" value="$dailyTask.userId"/>
		<g:submitButton name="Add Task" class="btn btn-primary"/>
		<button id="createDailyTaskCancel" class="btn btn-default">Cancel</button>
	</g:form>
</div>