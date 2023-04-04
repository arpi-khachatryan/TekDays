package com.tekdays

import grails.plugin.mail.MailService
import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekUserController {

    ChronoUnit chronoUnit
    MailService mailService
    LocalDateTime localDateTime
    def datatablesSourceService
    TekUserService tekUserService
    ThreadLocalRandom threadLocalRandom
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    private static final Logger LOGGER = LoggerFactory.getLogger(TekUserController.class)

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserInstanceCount: TekUser.count()]
    }

    def dataTablesRenderer() {
        def propertiesToRender = ["fullName", "email", "website", "bio", "id"]
        def entityName = 'TekUser'
        render datatablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
    }

    def show(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    def create() {
        respond new TekUser(params)
    }

    @Transactional
    def save(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }
        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'create'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: CREATED] }
        }
    }

    def edit(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    @Transactional
    def update(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'edit'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekUser tekUserInstance) {
        def user = session.user
        if (tekUserInstance.email == user.email) {
            flash.message = "You can not delete yourself."
            redirect(action: "show", id: user.id)
            return
        }
        if (tekUserInstance == null) {
            notFound()
            return
        }

        tekUserInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def login() {
        if (params.cName)
            return [cName: params.cName, aName: params.aName, id: params.id]
    }

    def validate() {
        def user = TekUser.findByUserName(params.username)
        if (user && user.password == params.password) {
            session.user = user
            if (params.cName) {
                redirect(controller: params.cName, action: params.aName, id: params.id)
            } else {
                redirect(uri: '/')
            }
        } else {
            flash.message = "Invalid username and password."
            render view: 'login'
        }
    }

    def logout = {
        session.user = null
        redirect(uri: '/')
    }

    def register() {
        respond new TekUser()
    }

//    @Transactional
//    def registration() {
//        TekUser user = new TekUser(params)
//        user.save flush: true
//        try {
//            mailService.sendMail {
//                to user.email
//                subject "Registration Confirmation"
//                html view: "/email/confirmRegistration", model: [user: user]
//            }
//        } catch (Exception e) {
//            log.info("**** Error : Sending Job Fail Email:\n******* Cause : " + e.getMessage())
//        }
//        redirect(action: 'login')
//    }

    @Transactional
    def registration() {
        TekUser user = new TekUser(params)
        LOGGER.info("User {} has successfully registered", user.fullName)
        LOGGER.info("New request to get registered. Email {}", user.email)
        def email = TekUser.findByEmail(user.email)?.email
        if (user?.email == email) {
            LOGGER.info("There is already a user with email {}", user.email)
            redirect(action: "login")
            return
        }
        user.save(flush: true)
        def token = createToken()
        token.tekUser = user
        token.save(flush: true)
//        try {
        mailService.sendMail {
            to user.email
            subject "Registration Confirmation"
            html view: "/email/confirmRegistration", model: [user: user, token: token]
        }
//        } catch (Exception e) {
//            log.info("**** Error : Sending Job Fail Email:\n******* Cause : " + e.getMessage())
//        }
        redirect(action: 'login')
    }

    def createToken() {
        log.info("Token successfully created")
        def verificationToken = new VerificationToken(expiresAt: LocalDateTime.now().plus(12, ChronoUnit.HOURS), plainToken: UUID.randomUUID().toString())
//        def verificationToken = new VerificationToken(expiresAt: LocalDateTime.now(), plainToken: UUID.randomUUID().toString())
        return verificationToken
    }

    @Transactional
    def verifyUser(String plainToken) {
        def token = tekUserService.findByPlainToken(plainToken)
        if (token == null) {
            flash.message = "Token not found."
            redirect(view: 'login')
            return
        } else if (token == "expired") {
            flash.message = "Token expired."
            redirect(view: 'index')
            return
        } else {
            def user = token?.tekUser
            token.delete(flush: true)
            if (user == null) {
                flash.message = "User does not exists with email and token"
                redirect(view: 'index')
                return
            }
            if (user.enable == true) {
                flash.message = "Something went wrong, try again!"
                redirect(view: 'index')
                return
            }
            user.enable = true
            user.save(flush: true)
            redirect(action: 'login')
        }
    }
}
