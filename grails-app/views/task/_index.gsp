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
				<g:render template="taskButtons" model="['task':task]"></g:render>
			</td>
			<td>${task.category?.name}</td>
			<td><g:if test="${task.isDailyTaskType()}"><span class="glyphicon glyphicon-repeat"></span> </g:if>${task.description}</td>
			<td>${humanDate(date: task.dueDate)}</td>
		</tr>
	</g:each>
</table>
</g:else>
