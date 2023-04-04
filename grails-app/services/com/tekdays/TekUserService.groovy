package com.tekdays

import grails.transaction.Transactional

import java.time.LocalDateTime

@Transactional
class TekUserService {

    LocalDateTime localDateTime

    def findByPlainToken(String plainToken) {
        def token = VerificationToken.findByPlainToken(plainToken)
        if (!token) {
            log.info("Token not found")
            return
        } else if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            token.delete(flush: true)
            log.info("Token expired")
            return "expired"
        } else {
            log.info("Token successfully found")
            return token
        }
    }
}
