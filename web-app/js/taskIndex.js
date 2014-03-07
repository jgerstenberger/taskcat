$(function() {
	bindCogs();
});

function bindCogs() {
	$(".task-cog").click(function(){
		var span = $(this);
		$("#task-btns-" + span.data("task-id")).toggle();
	});
}