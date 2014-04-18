<r:require module="taskUpdate"/>
<r:require module="taskIndex"/>
<html>
	<head>
		<meta http-equiv="refresh" content="3600">
		<script>
			var categoryId = ${category.id};
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">${category.name}</h3>
			</div>
			<g:include controller="task" action="indexCategory" params="[categoryId:category.id]"/>
		</div>
	</body>
</html>