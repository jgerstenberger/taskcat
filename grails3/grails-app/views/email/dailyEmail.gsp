<html>
<head>
<r:require modules="bootstrap"/>
<r:layoutResources/>
</head>
<body>
<g:if test="${!current.isEmpty()}">
<p>You've got work to do!</p>
<ul>
<g:each var="task" in="${current}">
<li>${task.description}</li>
</g:each>
</ul>
</g:if>
<%--<g:layoutBody/>--%>
<r:layoutResources/>
</body>
</html>