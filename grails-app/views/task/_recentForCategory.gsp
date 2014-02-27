<g:each var="task" in="${recentlyCompleted}">
	<button type="button" class="btn btn-link descriptionHelperBtn">${task.description}</button>
</g:each>