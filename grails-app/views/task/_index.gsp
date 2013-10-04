<table>
	<tr>
		<th/>
		<th>Task</th>
		<th>Due</th>
	</tr>
	<g:each var="task" in="${tasks}">
		<tr>
			<td>
				<button type="button" class="updateTaskButton" 
					data-task-id="${task.id ?: ''}" 
					data-daily-task-id="${task.dailyTask?.id}"
					data-due-date="${task.dueDate ?: ''}"
					data-status="DONE">Y
				</button>
				<g:if test="${task.isPastDailyTask()}">
					<button type="button" class="updateTaskButton" 
						data-task-id="${task.id ?: ''}" 
						data-daily-task-id="${task.dailyTask?.id}"
						data-due-date="${task.dueDate ?: ''}"
						data-status="MISSED">N
					</button>
				</g:if>
			</td>
			<td>${task.description}</td>
			<td>${task.dueDate}</td>
		</tr>
	</g:each>
</table>