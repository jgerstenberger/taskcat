<ul>
	<g:each var="task" in="${tasks}">
		<li>${task.description}</li>
	</g:each>
</ul>

<table>
	<tr>
		<th>Task</th>
		<th>Due</th>
	</tr>
	<g:each var="task" in="${tasks}">
		<tr>
			<td>
				<button type="button" class="updateTaskButton" 
					data-task-id="${task.id ?: ''}" 
					data-daily-task-id="${task.dailyTask?.id}"
					data-due-date="${task.dueDate ?: ''}">
				</button>
			</td>
			<td>${task.description}</td>
			<td>${task.dueDate}</td>
		</tr>
	</g:each>
</table>