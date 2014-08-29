<r:require module="taskCreate"/>

<div class="container">
	<div class="panel panel-primary" id="editTaskPanel">
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
			<button id="editTaskDelay" class="btn btn-warning">Delay Series</button>

			<g:if test="${task.dailyTask}">
				<g:link controller="dailyTask" action="edit" params="[id:task.dailyTask.id]">Go to Daily Task</g:link>
			</g:if>
		</g:form>
		<g:form name="deleteTaskForm" action="delete">
			<g:hiddenField name="id" value="${task.id}"/>
		</g:form>
		<g:form name="delayTaskForm" action="delay">
			<g:hiddenField name="id" value="${task.id}"/>
		</g:form>
	</div>

	<g:if test="${!otherUsers.empty}">
		<div class="panel panel-primary" id="copyTaskPanel">
			<div class="panel-heading">
				<h3 class="panel-title">Copy Task</h3>
			</div>
			
			<g:form class="panel-body" role="form" action="copy">
				<div class="form-group">
					<label for="destUserId">Copy To:</label>
					<g:select name="destUserId" from="${otherUsers}" optionKey="id" optionValue="firstName" class="form-control"/>
				</div>
				<g:hiddenField name="id" value="${task.id}"/>
				<g:submitButton name="Copy" class="btn btn-primary"/>
			</g:form>
		</div>
	</g:if>
</div>
