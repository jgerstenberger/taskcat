$(function() {
	$('#calendar').fullCalendar({
        events: {
        	url: $('head base').attr('href') + '/calendar/data',
        	data: {
        		userId: userId
        	}
        }
    })
});