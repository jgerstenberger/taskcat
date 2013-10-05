$(function() {
	bindUpdateTaskButtons();
});

function bindUpdateTaskButtons() {
	$(".updateTaskButton").click(function() {
		var b = $(this);
		
		$.post($('head base').attr('href') + '/task/updateStatus', 
				{id: b.data('task-id'), dailyTaskId: b.data('daily-task-id'),
					dueDate: b.data('due-date'), status: b.data('status'), userId: userId}, function() {
			$.get($('head base').attr('href') + '/task?status=NOT_DONE&userId=' + userId, function(data){
				$("#tasks").html(data);				
				bindUpdateTaskButtons();
			});
		});
	});	
}