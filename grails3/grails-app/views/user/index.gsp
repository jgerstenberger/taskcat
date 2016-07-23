<g:each var="user" in="${users}">
	<div>
		<g:link controller="user" action="show" id="$user.id">${user.firstName}</g:link>
	</div>
</g:each>