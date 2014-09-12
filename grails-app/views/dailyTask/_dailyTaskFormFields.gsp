<div class="form-group">
	<label for="dtDescription">Description</label>
	<g:textField class="form-control" name="description" id="dtDescription" value="$dailyTask.description"/>
</div>
<div class="form-group">
	<label for="dtCategory">Category</label>
	<g:select from="${categories}" optionKey="id" optionValue="name" class="form-control" name="category" value="${dailyTask.category?.id}" id="dtCategory" noSelection="['':'']"/>	
</div>
<div class="form-group">
	<div class="checkbox-title">
		<label>Excluded Days</label>
	</div>
	<g:each var="dayOfWeek" in="${1..7}">
		<div class="checkbox">
			<label>
				<g:checkBox name="excludedDays" value="$dayOfWeek" checked="${dailyTask.excludedDays?.contains(dayOfWeek)}"/> <g:humanDayOfWeek dayOfWeek="$dayOfWeek"/>
			</label>
		</div>
	</g:each>
</div>
<div class="form-group">
	<label for="active">Active</label>
	<g:checkBox name="active" value="${dailyTask.active}"/>
</div>