package com.tekdays


import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class TekEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TekEventService.class)

    def addRespondents(TekEvent tekEvent) {
        LOGGER.info("We only want to add respondents to a new event {}", tekEvent.name)
        if (!tekEvent.respondents) {
            return
        }
        //def resp = (tekEvent.respondents as String).split(",")
        //tekEvent.respondents = resp
        tekEvent.respondents = null
        def respArray = tekEvent.respondents.toArray().toString().replace("[", '').replace("]", '').split(",")
        for (int i = 0; i < respArray.length; i++) {
            tekEvent.addToRespondents(respArray[i])
        }
    }
}
