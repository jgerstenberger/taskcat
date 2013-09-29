<r:require module="taskCreate"/>
<g:form controller="dailyTask" action="save">
	<g:textField name="description" value="$dailyTask.description"/>
	<g:hiddenField name="userId" value="$dailyTask.userId"/>
	<g:submitButton name="Add Task"/>
</g:form>