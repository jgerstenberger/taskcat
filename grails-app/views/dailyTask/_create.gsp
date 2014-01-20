<r:require module="taskCreate"/>

<div class="panel panel-primary" id="createDailyTaskPanel">
	<div class="panel-heading">
		<h3 class="panel-title">Create Daily Task</h3>
	</div>

	<g:form controller="dailyTask" action="save" class="create-task panel-body">
		<div class="form-group">
			<label for="dtDescription">Description</label>
			<g:textField class="form-control" name="description" id="dtDescription" value="$dailyTask.description"/>
		</div>
		<div class="form-group">
			<label for="dtCategory">Category</label>
			<g:select from="${categories}" optionKey="id" optionValue="name" class="form-control" name="category" id="dtCategory" noSelection="['':'']"/>	
		</div>
		<div class="form-group">
			<div class="checkbox-title">
				<label>Excluded Days</label>
			</div>
			<g:each var="dayOfWeek" in="${1..7}">
				<div class="checkbox">
					<label>
						<g:checkBox name="excludedDays" value="$dayOfWeek" checked="false"/> <g:humanDayOfWeek dayOfWeek="$dayOfWeek"/>
					</label>
				</div>
			</g:each>
		</div>
		<g:hiddenField name="userId" value="$dailyTask.userId"/>
		<g:submitButton name="Add Task" class="btn btn-primary"/>
		<button id="createDailyTaskCancel" class="btn btn-default">Cancel</button>
	</g:form>
</div>