$(function() {
	initSparklines();
});

function initSparklines() {
	$(".sparklines-tristate").sparkline('html', {type: 'tristate', disableInteraction: 'true'});	
}
