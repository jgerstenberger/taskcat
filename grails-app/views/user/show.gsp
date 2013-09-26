<html>
	<g:form controller="task" action="save">
		<g:textField name="description"/>
		<g:hiddenField name="user_id" value="$user.id"/>
		<g:submitButton name="Add Task"/>
	</g:form>
	
	<ul>
		<g:each var="task" in="${user.tasks}">
			<li>${task.description}</li>
		</g:each>
	</ul>
</html>