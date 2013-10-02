$(function() {
	$("#dueDate").datepicker({dateFormat: 'm/d/y'});
	$(".updateTaskButton").click(function() {
		var b = $(this);
		$.post($('head base').attr('href') + '/task/updateStatus', 
				{id: b.data('task-id'), dailyTaskId: b.data('daily-task-id'),
					dueDate: b.data('due-date'), status: 'DONE'}, function() {
			$.get($('head base').attr('href') + '/task?status=NOT_DONE&userId=' + userId, function(data){
				$("#tasks").html(data);				
			})
		});
	})
});