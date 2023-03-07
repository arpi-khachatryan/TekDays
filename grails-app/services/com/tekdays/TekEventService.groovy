package com.tekdays


import grails.transaction.Transactional
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.security.auth.login.Configuration

@Transactional
class TekEventService {

    SessionFactory sessionFactory

    private static final Logger LOGGER = LoggerFactory.getLogger(TekEventService.class)

    def addRespondents(TekEvent tekEvent) {
        LOGGER.info("We only want to add respondents to a new event {}", tekEvent.name)
        if (!tekEvent.respondents) {
            return
        }
        //def resp = (tekEvent.respondents as String).split(",")
        //tekEvent.respondents = resp
        def respArray = tekEvent.respondents.toArray().toString().replace("[", '').replace("]", '').split(",")
        tekEvent.respondents = null
        for (int i = 0; i < respArray.length; i++) {
            tekEvent.addToRespondents(respArray[i])
        }
    }


//    def revisions() {
//        def auditQueryCreator = AuditReaderFactory.get(essionFactory.currentSession).createQuery()
//        def revisionList = []
//        AuditQuery query = auditQueryCreator.forRevisionsOfEntity(TekEvent.class, false, true)
//        query.resultList.each {
//            if(it[0].id==params.getLong('id')) {
//                revisionList.add(it)
//            }
//        }
//        [revisionList: revisionList]
//        println revisionList
//    }
//
//
//    List getAllAudited(Class clazz) {
//        List listOfAudited = AuditReaderFactory
//                .get(sessionFactory.currentSession)
//                .createQuery()
//                .forRevisionsOfEntity(clazz, true, true).getResultList()
//
//        List result = listOfAudited
//        return result
//    }
}
