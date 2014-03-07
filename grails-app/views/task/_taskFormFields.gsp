<div class="form-group">
	<label for="category">Category</label>
	<g:select from="${categories}" optionKey="id" optionValue="name" class="form-control" name="category" value="${task.category?.id}" noSelection="['':'']"/>
</div>
<div class="form-group">
	<label for="description">Description</label>
	<g:textField class="form-control" name="description" value="${task.description}"/>
	<div id="descriptionHelperSection"></div>
</div>
<div class="form-group">
	<label for="dueDate">Due Date</label>
	<g:textField data-provide="datepicker" data-date-format="yyyy-mm-dd" class="form-control" name="dueDate" id="dueDate" value="${task.dueDate ?: ''}"/>
</div>