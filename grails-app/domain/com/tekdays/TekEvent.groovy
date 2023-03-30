package com.tekdays

import org.hibernate.envers.Audited

@Audited
class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    String nickname


    def beforeInsert() {
    }

    def beforeUpdate() {
    }

    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: Sponsorship,
                      tasks       : Task,
                      messages    : TekMessage
    ]

    static mapping = {//sorting
        //organizer sort: "fullName"
        // organizer cascade: 'save-update'       //then GORM uses a cascading policy of "save-update" by default.
        //volunteers cascade: 'all-delete-orphan' // user will also be deleted if it is removed (orphaned) from an Event’s `users association.
        // tasks fetch:'join'
        //batchSize: 10

        //The Second-Level Cache
        //cache true
        //tasks cache: 'read-only'


        //Not to use optimistic locking,
        //version false
    }

    //event.save(insert:true)

    static constraints = {
        name()
        city()
        description maxSize: 5000
        organizer()
        venue()
        startDate()
        endDate(validator: { val, obj ->
            val?.after(obj.startDate)
        })
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true

        nickname nullable: true
    }

    String toString() {
        "$name, $city"
    }
}
