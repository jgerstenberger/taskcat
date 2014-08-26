<table class="table hidden-xs" id="${tasksType}">
	<tr>
		<th/>
		<th>Category</th>
		<th>Task</th>
		<th>Due</th>
	</tr>
	<g:each var="task" in="${tasks}">
		<g:set var="colorClass">
			<g:if test="${task.isNotConfirmed()}">warning</g:if>
			<g:elseif test="${task.isPastDue()}">danger</g:elseif>
			<g:elseif test="${showCompletedGreen && task.isCompleted()}">success</g:elseif>
			<g:elseif test="${task.isDueTodayAndNotDone()}">info</g:elseif>
		</g:set>
		<tr class="${colorClass}">
			<td>
				<g:render template="taskButtons" model="['task':task]"/>
			</td>
			<td>
				<g:if test="${task.category}">
					<g:link controller="category" id="${task.category.id}" params="[userId: user.id]">${task.category.name}</g:link>
				</g:if>
			</td>
			<td>
				<g:if test="${task.isDailyTaskType()}"><span class="glyphicon glyphicon-repeat"></span> </g:if>${task.description}
				<g:if test="${task.isDailyTaskType()}">
					<span class="sparklines-tristate id="dstl-${task.id}" values="${dtTrend[task.dailyTask.id]?.join(',')}"></span>
				</g:if>
			</td>
			<td>
				<div>${humanDate(date: task.dueDate)}</div>
				<div class="date-fine-print"><g:formatDate date="${task.dueDate.toDate()}" format="EEE (yyyy-MM-dd)"/></div>
			</td>
		</tr>
	</g:each>
</table>

<div class="visible-xs" id="${tasksType}-xs">
	<g:each var="task" in="${tasks}">
		<g:set var="colorClass">
			<g:if test="${task.isPastDue()}">label-danger</g:if>
			<g:elseif test="${task.isNotConfirmed()}">label-warning</g:elseif>
			<g:elseif test="${showCompletedGreen && task.isCompleted()}">label-success</g:elseif>
			<g:elseif test="${task.isDueTodayAndNotDone()}">label-info</g:elseif>
			<g:else>label-default</g:else>
		</g:set>
		<div class="xs-task">
			<div>
				<span class="label ${colorClass}">
					<g:if test="${task.category}">
						${task.category.name}:
					</g:if>
					${task.description}
				</span>
			</div>
			<div>Due: ${humanDate(date: task.dueDate)}, <g:formatDate date="${task.dueDate.toDate()}" format="EEE (yyyy-MM-dd)"/></div>
			<div><g:render template="taskButtons" model="['task':task]"/></div>

		</div>
		<hr>
<%--		<div class="panel ${colorClass}">--%>
<%--			<div class="panel-heading">--%>
<%--				<h3 class="panel-title">--%>
<%--					<g:if test="${task.category}">--%>
<%--						${task.category.name}:--%>
<%--					</g:if>--%>
<%--					${task.description}--%>
<%--				</h3>--%>
<%--			</div>--%>
<%--			<div class="panel-body">--%>
<%--				Due Date: ${task.dueDate}--%>
<%--			</div>--%>
<%--		</div>--%>
	</g:each>
</div>