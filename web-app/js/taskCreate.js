$(function() {
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
	
	$("#editTaskCancel").click(function() {
		history.go(-1);
	});
	
	$("#editTaskDelete").click(function() {
		$('form#deleteTaskForm').submit();
		return false;
	});
	
	var categorySelect = $("#createTaskPanel #category");
	categorySelect.change(function() {
		updateDescriptionHelperLinks($(this));
	});
	
	if (categorySelect.val() != '')
		updateDescriptionHelperLinks(categorySelect)
});

function updateDescriptionHelperLinks(select) {
	$.get($('head base').attr('href') + '/task/recentForCategory?categoryId=' 
			+ select.val() + '&userId=' + userId, function(data) {
		$('#descriptionHelperSection').html(data);
		bindDescriptionHelperLinks();
		if ($('#dueDate').val() == '') {
			$('#dueDate').val($('#nextDueDate').val());
			$('#dueDate').datepicker('update');
		}
	});
}

function bindDescriptionHelperLinks() {
	$(".descriptionHelperBtn").click(function() {
		var btn = $(this);
		$('#description').val(btn.text());
	});
}