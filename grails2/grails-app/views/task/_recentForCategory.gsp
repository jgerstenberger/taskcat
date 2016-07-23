<g:each var="task" in="${recent}">
	<button type="button" class="btn btn-link descriptionHelperBtn" data-due-date="${task.dueDate}">${task.description}</button>
</g:each>

<g:if test="${recent}">
	<input id="nextDueDate" type="hidden" value="${recent[0].dueDate.plusDays(1)}"/>
</g:if>