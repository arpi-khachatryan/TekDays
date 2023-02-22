package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class)

    def serviceMethod() {

    }

    def addDefaultTasks(TekEvent tekEvent) {
        LOGGER.info("We only want to add tasks to a new event {}", tekEvent.name)
        if (tekEvent.tasks?.size() > 0)
            return
        tekEvent.addToTasks(new Task(title: 'Identify potential venues'))
        tekEvent.addToTasks new Task(title: 'Get price / availability of venues')
        tekEvent.addToTasks new Task(title: 'Compile potential sponsor list')
        tekEvent.addToTasks new Task(title: 'Design promotional materials')
        tekEvent.addToTasks new Task(title: 'Compile potential advertising avenues')
        tekEvent.addToTasks new Task(title: 'Locate swag provider (preferably local)')
        tekEvent.save()
    }
}
