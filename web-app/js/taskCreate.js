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
	
	$("#editTaskCancel").click(function() {
		history.go(-1);
	});
	
	$("#editTaskDelete").click(function() {
		$('form#deleteTaskForm').submit();
		return false;
	});
	
	$("#createTaskPanel #category").change(function() {
		var select = $(this);
//		$.ajax({url: $('head base').attr('href') + '/task/recentForCategory?categoryId=' 
//			+ select.val() + '&userId=' + userId, cache:false}).done(function(data) {
//			$('#descriptionHelperSection').html(data);
//			bindDescriptionHelperLinks();
//		});
		$.get($('head base').attr('href') + '/task/recentForCategory?categoryId=' 
				+ select.val() + '&userId=' + userId, function(data) {
			$('#descriptionHelperSection').html(data);
			bindDescriptionHelperLinks();
			if ($('#dueDate').val() == '') {
				$('#dueDate').val($('#nextDueDate').val());
			}
		});
	});
});

function bindDescriptionHelperLinks() {
	$(".descriptionHelperBtn").click(function() {
		var btn = $(this);
		$('#description').val(btn.html());
	});
}