<g:if test="${tasksType == 'currentTasks' && tasks.isEmpty()}">
<g:img file="grumpygoaway.jpg" width="500"/>
</g:if>
<g:else>
<table class="table" id="${tasksType}">
	<tr>
		<th/>
		<th>Category</th>
		<th>Task</th>
		<th>Due</th>
	</tr>
	<g:each var="task" in="${tasks}">
		<tr class="${task.isPastDue() ? 'danger' : ''}">
			<td>
				<g:if test="${task.isNotDone()}">
					<button type="button" class="btn btn-success updateTaskButton" 
						data-task-id="${task.id ?: ''}" 
						data-daily-task-id="${task.dailyTask?.id}"
						data-due-date="${task.dueDate ?: ''}"
						data-status="DONE">Done
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
					<g:if test="${task.id}">
						<g:link class="btn btn-default editTaskButton" 
							controller="task" action="edit" id="${task.id}">Edit</g:link>
					</g:if>
					<g:elseif test="${task.dailyTask}">
						<g:link class="btn btn-default editTaskButton" 
							controller="dailyTask" action="edit" id="${task.dailyTask.id}">Edit</g:link>
					</g:elseif>
				</sec:ifAnyGranted>
			</td>
			<td>${task.category.name}</td>
			<td>${task.description}</td>
			<td>${humanDate(date: task.dueDate)}</td>
		</tr>
	</g:each>
</table>
</g:else>
