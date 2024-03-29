package com.tekdays

import org.hibernate.envers.Audited


@Audited
class Task {

    String title
    String notes
    TekUser assignedTo
    Date dueDate
    TekEvent event
    Boolean completed
    static belongsTo = TekEvent

//    static mapping = {
//        batchSize 10
//    }

    static constraints = {
        title blank: false
        notes blank: true, nullable: true, maxSize: 5000
        assignedTo nullable: true
        dueDate nullable: true
        completed nullable: true
    }

    String toString() {
        "$title"
    }
}