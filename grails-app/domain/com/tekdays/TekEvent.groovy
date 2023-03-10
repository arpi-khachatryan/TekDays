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

    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: Sponsorship,
                      tasks       : Task,
                      messages    : TekMessage
    ]

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
