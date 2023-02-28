package com.tekdays


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekEventController {

    TaskService taskService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params),
                model: [tekEventInstanceCount: TekEvent.count()]
    }

    def show(TekEvent tekEventInstance) {
        respond tekEventInstance
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

        taskService.addDefaultTasks(tekEventInstance)

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
//                redirect tekEventInstance
//            }
//            '*' { respond tekEventInstance, [status: CREATED] }
//        }

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', //message (optional) - The object to resolve the message for. Objects must implement MessageSourceResolvable.
                        // event.created.message="Event {0} created."
                        [message(code: 'tekEventInstance.label',         //args (optional) - A list of argument values to apply to the message when code is used.
                                default: 'TekEvent'), tekEventInstance.id])
                //default (optional) - The default message to output if the error or code cannot be found in messages.properties.
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: CREATED] }
        }
    }

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
}
