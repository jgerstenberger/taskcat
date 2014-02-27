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
	
	$("#createTaskPanel #category").change(function() {
		var select = $(this);
		$.get($('head base').attr('href') + '/task/recentForCategory?categoryId=' 
				+ select.val() + '&userId=' + userId, function(data) {
			$('#descriptionHelperSection').html(data);
			bindDescriptionHelperLinks();
		});
	});
});

function bindDescriptionHelperLinks() {
	$(".descriptionHelperBtn").click(function() {
		var btn = $(this);
		$('#description').val(btn.html());
	});
}