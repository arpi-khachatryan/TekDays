package com.tekdays

import org.hibernate.envers.Audited

@Audited
class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio
    Boolean enable = false

//    static mapping = {
//        sort: fullName
//    }

    static constraints = {
        fullName size: 2..10
        userName nullable: true, blank: true
        password blank: false
        email email: true
        website()
        bio maxSize: 5000
    }

    String toString() { fullName }
}

//album.save(insert:true)
//Album.listOrderByDateCreated()