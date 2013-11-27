<r:require module="taskCreate"/>

<div class="container">
	<div class="panel panel-primary" id="editDailyTaskPanel">
		<div class="panel-heading">
			<h3 class="panel-title">Edit Task</h3>
		</div>
		<g:form class="panel-body" role="form" action="update">
			<div class="form-group">
				<label for="description">Description</label>
				<g:textField class="form-control" name="description" value="${task.description}"/>
			</div>
			<div class="form-group">
				<label for="status">Status</label>
				<g:select from="${taskcat.TaskStatus.values()}" optionKey="name" optionValue="label" class="form-control" name="status" value="${task.status.getName()}"/>
			</div>
			<div class="form-group">
				<label for="category">Category</label>
				<g:select from="${categories}" optionKey="id" optionValue="name" class="form-control" name="category" value="1"/>
			</div>
			<div class="form-group">
				<label for="dueDate">Due Date</label>
				<g:textField class="form-control" name="dueDate" id="dueDate" value="${task.dueDate ?: ''}"/>
			</div>
			<g:hiddenField name="id" value="${task.id}"/>
			<g:submitButton name="Save" class="btn btn-primary"/>
			<button id="editTaskCancel" class="btn btn-default">Cancel</button>
			<button id="editTaskDelete" class="btn btn-danger">Delete</button>
		</g:form>
	</div>
</div>