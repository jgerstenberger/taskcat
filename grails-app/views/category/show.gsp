<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">${category.name}</h3>
	</div>
	<g:include controller="task" action="indexCategory" params="[categoryId:category.id]"/>
</div>