$(function() {
	$("#dueDate").datepicker({format: 'yyyy-mm-dd'});
	
	$("#createTask").click(function() {
		$("#createTaskPanel").show();
		return false;
	});

	$("#createDailyTask").click(function() {
		$("#createDailyTaskPanel").show();
		return false;
	});

	$("#createTaskCancel").click(function() {
		$("#createTaskPanel").hide();
		return false;
	});

	$("#createDailyTaskCancel").click(function() {
		$("#createDailyTaskPanel").hide();
		return false;
	});
});