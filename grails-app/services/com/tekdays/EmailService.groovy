package com.tekdays

import grails.plugin.mail.MailService
import grails.transaction.Transactional

@Transactional
class EmailService {

    MailService mailService

    def sendEmail() {
        def list = TekUser.list()
        for (user in list) {
            if (user.email) {
                try {
                    mailService.sendMail {
                        to user.email
                        subject "Join our team"
                        html view: "/email/emailForVolunteering", model: [user: user]
                    }
                }
                catch (Exception e) {
                    log.info("**** Error : Sending Job Fail Email:\n******* Cause : " + e.getMessage())
                }
            }
        }
    }
}
