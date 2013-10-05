modules = {
    taskCreate {
		dependsOn 'jquery-ui'
        resource url:'js/taskCreate.js'
    }
    taskUpdate {
		dependsOn 'jquery-ui'
        resource url:'js/taskUpdate.js'
    }
}