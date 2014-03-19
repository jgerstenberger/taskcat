modules = {
    taskCreate {
		dependsOn 'bootstrap'
		dependsOn 'bootstrap-datepicker'
        resource url:'js/taskCreate.js'
		resource url:'css/styles.css'
    }
    taskUpdate {
		dependsOn 'bootstrap-datepicker'
        resource url:'js/taskUpdate.js'
    }
    taskIndex {
		dependsOn 'sparklines'
        resource url:'js/taskIndex.js'
    }
	sparklines {
		resource url:'js/jquery.sparkline.min.js'
	}
}