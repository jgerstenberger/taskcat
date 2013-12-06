<r:require module="taskCreate"/>

<div class="panel panel-primary" id="createTaskPanel">
	<div class="panel-heading">
		<h3 class="panel-title">Create Task</h3>
	</div>
	<g:form controller="task" action="save" class="create-task panel-body">
		<g:render template="/task/taskFormFields"/>
	
		<g:hiddenField name="user" value="$task.userId"/>
		<g:submitButton name="Add Task" class="btn btn-primary"/>
		<button id="createTaskCancel" class="btn btn-default">Cancel</button>
	</g:form>
</div>