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
			<td>
				<g:if test="${task.category}">
					<g:link controller="category" id="${task.category.id}" params="[userId: user.id]">${task.category.name}</g:link>
				</g:if>
			</td>
			<td><g:if test="${task.isDailyTaskType()}"><span class="glyphicon glyphicon-repeat"></span> </g:if>${task.description}</td>
			<td>
				<div>${humanDate(date: task.dueDate)}</div>
				<div class="date-fine-print"><g:formatDate date="${task.dueDate.toDate()}" format="EEE (yyyy-MM-dd)"/></div>
			</td>
		</tr>
	</g:each>
</table>
<%--			<td class="visible-xs">--%>
<%--				<div class="xs-task-btn-holder">--%>
<%--					<div id="task-btns-${task.id}" class="xs-task-btns panel panel-default">--%>
<%--						<div class="panel-body">--%>
<%--							<g:render template="taskButtons" model="['task':task]"></g:render>			--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<span data-task-id="${task.id}" class="glyphicon glyphicon-cog task-cog"></span>--%>
<%--			</td>--%>
