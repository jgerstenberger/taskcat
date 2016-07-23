<g:if test="${task.isNotDone()}">
	<button type="button" class="btn btn-success updateTaskButton" 
		data-task-id="${task.id ?: ''}" 
		data-daily-task-id="${task.dailyTask?.id}"
		data-due-date="${task.dueDate ?: ''}"
		data-status="${task.requiresConfirmation ? 'NOT_CONFIRMED' : 'DONE'}">Done
	</button>
	<g:if test="${task.isPastDailyTask()}">
		<button type="button" class="btn btn-danger updateTaskButton" 
			data-task-id="${task.id ?: ''}" 
			data-daily-task-id="${task.dailyTask?.id}"
			data-due-date="${task.dueDate ?: ''}"
			data-status="MISSED">Missed
		</button>
	</g:if>
</g:if>
<sec:ifAnyGranted roles="ROLE_ADMIN">
	<g:if test="${task.isNotConfirmed()}">
		<button type="button" class="btn btn-success updateTaskButton" 
			data-task-id="${task.id ?: ''}" 
			data-daily-task-id="${task.dailyTask?.id}"
			data-due-date="${task.dueDate ?: ''}"
			data-status="DONE">Confirm
		</button>
	</g:if>
	<g:if test="${task.id}">
		<g:link class="btn btn-default editTaskButton" 
			controller="task" action="edit" id="${task.id}">Edit</g:link>
	</g:if>
	<g:elseif test="${task.dailyTask}">
		<g:link class="btn btn-default editTaskButton" 
			controller="dailyTask" action="edit" id="${task.dailyTask.id}">Edit</g:link>
	</g:elseif>
</sec:ifAnyGranted>