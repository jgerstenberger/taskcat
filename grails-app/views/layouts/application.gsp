<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${request.contextPath}"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="apple-touch-icon-precomposed" href="${resource(file: 'apple-touch-icon.png')}" />
		<title><g:layoutTitle default="TaskCat"/></title>
		<r:require modules="bootstrap"/>
		<r:layoutResources/>
	</head>
	<body>
		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<nav class="navbar navbar-default" role="navigation">
				<g:if test="${user}">
					<ul class="nav navbar-nav">
						<g:if test='${users}'>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.firstName} <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<g:each var="otherUser" in="${users}">
			          					<li><g:link controller="user" action="show" id="${otherUser.id}">${otherUser.firstName}</g:link></li>
									</g:each>
		          				</ul>
							</li>
						</g:if>
						<li><a href="#" id="createDailyTask">Create Daily Task</a></li>
						<li><a href="#" id="createTask">Create Task</a></li>
					</ul>
<%--					<p class="navbar-text">${user.firstName}</p>--%>
				</g:if>
			</nav>
		</sec:ifAnyGranted>
		<g:layoutBody/>
		<r:layoutResources/>
	</body>
</html>