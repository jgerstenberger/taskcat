<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${request.contextPath}"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<newrelic:browserTimingHeader/>
		<link rel="apple-touch-icon-precomposed" href="${resource(file: 'apple-touch-icon.png')}" />
		<title><g:layoutTitle default="TaskSquire"/></title>

		<!-- <r:require modules="bootstrap"/>
		<r:require module="taskCreate"/>
		<r:layoutResources/> -->

	    <asset:stylesheet src="application.css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.9.0/fullcalendar.min.css"/>

		<script>
			var userId = ${user.id};
		</script>
		<g:layoutHead/>
	</head>
	<body>
		<g:include controller="main" action="taskbar"/>
		<div class="container">
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<g:include controller="task" action="create" />
				<g:include controller="dailyTask" action="create" />
			</sec:ifAnyGranted>
			<g:layoutBody/>
		</div>
		<!-- <r:layoutResources/> -->
	    <asset:javascript src="application.js"/>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-sparklines/2.1.2/jquery.sparkline.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.14.1/moment.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.9.0/fullcalendar.min.js"></script>
		<newrelic:browserTimingFooter/>
	</body>
</html>