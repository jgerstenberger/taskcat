<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${request.contextPath}"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="apple-touch-icon-precomposed" href="${resource(file: 'apple-touch-icon.png')}" />
		<title><g:layoutTitle default="TaskCat"/></title>
		<r:require modules="bootstrap"/>
		<r:require module="taskCreate"/>
		<r:layoutResources/>
		<script>
			var userId = ${user.id};
		</script>
	</head>
	<body>
		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<g:include controller="main" action="taskbar"/>
		</sec:ifAnyGranted>
		<div class="container">
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<g:include controller="task" action="create" />
				<g:include controller="dailyTask" action="create" />
			</sec:ifAnyGranted>
			<g:layoutBody/>
		</div>
		<r:layoutResources/>
	</body>
</html>