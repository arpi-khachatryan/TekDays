package com.tekdays

class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    static hasMany = [volunteers     : TekUser,
                      respondents    : String,
                      sponsorships   : Sponsorship,
                      tasks          : Task,
                      messages       : TekMessage,
//                      recurDaysOfWeek: Integer
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
    }

    String toString() {
        "$name, $city"
    }
//    // Recurring Options
//    boolean isRecurring = false
//    EventRecurType recurType
//    Integer recurInterval = 1
//    Date recurUntil
//    Integer recurCount
//
//}
//
//public enum EventRecurType {
//    DAILY('Daily'),
//    WEEKLY('Weekly'),
//    MONTHLY('Monthly'),
//    YEARLY('Yearly')
//
//    String name
//
//    EventRecurType(String name) {
//        this.name = name
//    }
}
