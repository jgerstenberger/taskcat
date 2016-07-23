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
			<g:else>
				<li><g:link controller="user" action="show" id="${user.id}">Home</g:link></li>
			</g:else>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<li><a href="#" id="createDailyTask">Create Daily Task</a></li>
				<li><a href="#" id="createTask">Create Task</a></li>
			</sec:ifAnyGranted>
			<g:if test='${categories}'>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<g:each var="category" in="${categories}">
          					<li><g:link controller="category" action="show" id="${category.id}" params="[userId:user.id]">${category.name}</g:link></li>
						</g:each>
       				</ul>
				</li>
			</g:if>
			<li>
				<g:link controller="calendar" params="[userId:user.id]">Calendar</g:link>
			</li>
		</ul>
	</g:if>
</nav>
