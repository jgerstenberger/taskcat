<r:require module="taskCreate"/>

<div class="panel panel-primary" id="createTaskPanel">
	<div class="panel-heading">
		<h3 class="panel-title">Create Task</h3>
	</div>
	<g:form controller="task" action="save" class="create-task panel-body">
		<div class="form-group">
			<label for="tDescription">Description</label>
			<g:textField name="description" id="tDescription" value="$task.description"/>
		</div>
		<div class="form-group">
			<label for="dueDate">Due Date</label>
			<g:textField name="dueDate" id="dueDate" value="${task.dueDate ?: ''}"/>
		</div>
		<g:hiddenField name="userId" value="$task.userId"/>
		<g:submitButton name="Add Task" class="btn btn-primary"/>
		<button id="createTaskCancel" class="btn btn-default">Cancel</button>
	</g:form>
</div>