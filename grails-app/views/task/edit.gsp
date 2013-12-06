<r:require module="taskCreate"/>

<div class="container">
	<div class="panel panel-primary" id="editDailyTaskPanel">
		<div class="panel-heading">
			<h3 class="panel-title">Edit Task</h3>
		</div>
		<g:form class="panel-body" role="form" action="update">
			<g:render template="taskFormFields"/>
		
			<div class="form-group">
				<label for="status">Status</label>
				<g:select from="${taskcat.TaskStatus.values()}" optionKey="name" optionValue="label" class="form-control" name="status" value="${task.status.getName()}"/>
			</div>

			<g:hiddenField name="id" value="${task.id}"/>
			<g:submitButton name="Save" class="btn btn-primary"/>
			<button id="editTaskCancel" class="btn btn-default">Cancel</button>
			<button id="editTaskDelete" class="btn btn-danger">Delete</button>
		</g:form>
	</div>
</div>