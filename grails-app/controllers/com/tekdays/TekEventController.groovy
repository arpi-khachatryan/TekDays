package com.tekdays

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekEventController {

    TaskService taskService
    EnversService enversService
    MessageSource messageSource
    TekEventService tekEventService
    DatatablesSourceService datatablesSourceService
    private static final Logger LOGGER = LoggerFactory.getLogger(TekEventController.class)

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params), model: [tekEventInstanceCount: TekEvent.count()]
        //respond TekEvent.list(params, [fetch: [tasks: 'join']]), model: [tekEventInstanceCount: TekEvent.count()]
        //Query Caching respond TekEvent.list(params, cache: true), model: [tekEventInstanceCount: TekEvent.count()]
        //println messageSource.getMessage('abc.a.a', args1 as Object[], new Locale('es'))
    }

    def dataTablesRenderer() {
        def propertiesToRender = ["name", "city", "organizer", "venue", "startDate", "endDate", "id"]
        def entityName = 'TekEvent'
        render datatablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
    }

//    def show(TekEvent tekEventInstance) {
//        respond tekEventInstance
//    }

    def show(Long id) {
        def tekEventInstance
        if (params.nickname) {
            tekEventInstance = TekEvent.findByNickname(params.nickname)
        } else {
            tekEventInstance = TekEvent.get(id)
        }
        if (!tekEventInstance) {
            if (params.nickname) {
                flash.message = "TekEvent not found with nickname ${params.nickname}"
            } else {
                flash.message = "TekEvent not found with id $id"
            }
            redirect(action: "list")
            return
        }
        [tekEventInstance: tekEventInstance]
    }

    def create() {
        respond new TekEvent(params)
    }

    @Transactional
    def save(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'create'
            return
        }

        tekEventInstance.save flush: true
        //When set to true flushes the persistence context, persisting the object immediately and updating the version column for optimistic locking

        tekEventService.addRespondents(tekEventInstance)
        taskService.addDefaultTasks(tekEventInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: CREATED] }
        }
    }
    //message (optional) - The object to resolve the message for. Objects must implement MessageSourceResolvable.
    //event.created.message="Event {0} created."
    //args (optional) - A list of argument values to apply to the message when code is used.
    //default (optional) - The default message to output if the error or code cannot be found in messages.properties.

    def edit(TekEvent tekEventInstance) {
        respond tekEventInstance
    }

    @Transactional
    def update(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'edit'
            return
        }

        tekEventInstance.save flush: true
        tekEventService.addRespondents(tekEventInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        tekEventInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    @Transactional
    def volunteer() {
        def event = TekEvent.get(params.id)
        event.addToVolunteers(session.user)
        if (!event.save(flush: true)) {
            render "Something went wrong!"
        }
        render "Welcome to the team! Your help will make a huge difference"
    }

    def getAudits() {
        def res = enversService.getAllAudited(TekEvent.class)
        render(res)
    }

    def json = {
        def c = TekEvent.createCriteria()
        def event = c.list {}
        if (event) {
            def jsonify = event as JSON
            jsonify.prettyPrint = true
            render jsonify
        } else {
            response.sendError 404
        }
    }

    def xml = {
        def c = TekEvent.createCriteria()
        def event = c.list {}
        if (event) {
            withFormat {
                xml { render event as XML }
            }
        } else {
            response.sendError 404
        }
    }

    def dynamicFindersInAction() {
        //retrieve an event where the city contains 'MO'
        TekEvent.findByCityIlike('%MO%')
        // get a event created in last 10 days
        def today = new Date()
        def last10Days = TekEvent.findByStartDateBetween(today - 10, today)
        // first event that is not 'LA'
        def somethingElse = TekEvent.findByCityNotEqual('LA')
    }

    def dynamicFindersInAction2() {
        def queryMap = [name: 'Gateway Code Camp', respondents: ['ben@grailsmail.com', 'zachary@linuxgurus.org']]
        def query = {
            // go through the query map
            queryMap.each { key, value ->
                // if we have a list assume a between query
                if (value instanceof List) {
                    // use the spread operator to invoke
                    between(key, *value)
                } else {
                    like(key, value)
                }
            }
        }
        // create a criteria instance
        def criteria = TekEvent.createCriteria()

        // count the results
        println(criteria.count(query))

        // reuse again to get a unique result
        println(criteria.get(query))

        // reuse again to list all
        criteria.list(query).each { println it }

        // use scrollable results
        def scrollable = criteria.scroll(query)
        def next = scrollable.next()
//        while (next) {
//            println(scrollable.getString('city'))
//            next = scrollable.next()
//        }
        TekEvent.findAllByName("sds", [max: 10, offset: 20, sort: 'aaa', order: 'desc'])
    }
}

//    def g3Event=TekEvent.createCriteria().list {
//        and{  //payman
//            gt('startdate',new Date())
//            or{
//                ilike('description','%grovy%')
//                ilike('description','%grails%')
//                ilike('description','%griffon%')
//            }
//        }
//            def contegixEvents = TekEvent.createCriteria().list{
//                sponsorships{
//                    sponsor{
//                        eq('name', 'Contegix')
//                    }
//                }
//            }
//        }




