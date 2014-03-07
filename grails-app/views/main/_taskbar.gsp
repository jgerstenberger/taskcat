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
	</g:if>
</nav>
