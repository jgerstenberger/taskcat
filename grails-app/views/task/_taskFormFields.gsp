<div class="form-group">
	<label for="description">Description</label>
	<g:textField class="form-control" name="description" value="${task.description}"/>
</div>
<div class="form-group">
	<label for="category">Category</label>
	<g:select from="${categories}" optionKey="id" optionValue="name" class="form-control" name="category" value="${task.category?.id}" noSelection="['':'']"/>
</div>
<div class="form-group">
	<label for="dueDate">Due Date</label>
	<g:textField class="form-control" name="dueDate" id="dueDate" value="${task.dueDate ?: ''}"/>
</div>