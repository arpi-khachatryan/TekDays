import com.tekdays.*

class BootStrap {

    def init = { servletContext ->
        if (!TekEvent.first()) {
            new TekUser(fullName: 'John Doe',
                    userName: 'jdoe',
                    password: 't0ps3cr3t',
                    email: 'jdoe@johnsgroovyshop.com',
                    website: 'blog.johnsgroovyshop.com',
                    bio: 'John has been programming for over 40 years. ...').save()

            new TekUser(fullName: 'John Deere',
                    userName: 'tractorman',
                    password: 't0ps3cr3t',
                    email: 'john.deere@porkproducers.org',
                    website: 'www.perl.porkproducers.org',
                    bio: 'John is a top notch Perl programmer and a ...').save()


            def event1 = new TekEvent(name: 'Gateway Code Camp',
                    city: 'Saint Louis, MO',
                    organizer: TekUser.findByFullName('John Doe'),
                    venue: 'TBD',
                    startDate: new Date('11/21/2013'),
                    endDate: new Date('11/21/2013'),
                    description: '''This conference will bring coders ...''').save()

            def event2 = new TekEvent(name: 'Perl Before Swine',
                    city: 'Austin, MN',
                    organizer: TekUser.findByFullName('John Deere'),
                    venue: 'SPAM Museum',
                    startDate: new Date('11/2/2013'),
                    endDate: new Date('11/2/2013'),
                    description: 'Join the Perl programmers of the ...').save()

            def g1 = TekEvent.findByName('Gateway Code Camp')
            g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
                    userName: 'sarah',
                    password: '54321',
                    email: 'sarah@martinworld.com',
                    website: 'www.martinworld.com',
                    bio: 'Web designer and Grails afficianado.'))

            g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                    userName: 'Mr_Bill',
                    password: '12345',
                    email: 'mrbill@email.com',
                    website: 'www.mrbillswebsite.com',
                    bio: 'Software developer, claymation artist.'))
            g1.save()


            g1.addToRespondents('ben@grailsmail.com')
            g1.addToRespondents('zachary@linuxgurus.org')
            g1.addToRespondents('solomon@bootstrapwelding.com')
            g1.save()


            def s1 = new Sponsor(name: 'Contegix',
                    website: 'http://www.contegix.com',
                    description: 'Beyond Managed Hosting for your Enterprise').save()

            def s2 = new Sponsor(name: 'Object Computing Incorporated',
                    website: 'http://ociweb.com',
                    description: 'An OO Software Engineering Company').save()

            def sp1 = new Sponsorship(event: g1,
                    sponsor: s1,
                    contributionType: 'Other',
                    description: 'Cool T-Shirts').save()

            def sp2 = new Sponsorship(event: g1,
                    sponsor: s2,
                    contributionType: 'Venue',
                    description: 'Will be paying for the Moscone').save()
        }
    }

    def destroy = {
    }

}
//    if (GrailsUtil.environment == 'development'){
////bootstrap code goes here...
//    }

//findBy, findAllBy, or countBy

//countByCity('New York')
//findAllByStartDateGreaterThan(new Date())
//findByCityAndDescriptionLike("Minneapolis", "%Groovy%")


//    • blank (true/false): Allows an empty string value.
//    • nullable (true/false): Allows null values.
//    • max: Specifies the maximum value.
//    • min: Specifies the minimum value.
//    • vsize: Takes a Groovy range to determine bounds.
//    • maxSize: Specifies the maximum size of a String or Collection.
//    • minSize: Specifies the minimum size of a String or Collection.
//    • inList: Only allows values contained in the supplied list.
//    • matches: Requires value to match a regular expression.
//    • unique (true/false): Enforces uniqueness in the database.
//    • url (true/false): Only accepts a valid URL.
//    • email (true/false): Only accepts a valid email address.
//    • creditCard (true/false): Only accepts a valid credit card number.
//    • validator: Takes a closure for custom validation. The first parameter is the value,
//             and the second (optional) parameter is the instance being validated.

//http://grails.org/doc/2.3.1/ref/Constraints/validator.html.

//http://localhost:8080/TekDays/dbconsole/login.do?jsessionid=32620a43f01206cd537f76b62f0aa3fc


//    class Parent {
//        ...
//        static hasMany = [children : Child]
//    }
//    class Child {
//        ...
//        Parent parent
//        static belongsTo = Parent
//    }

//• LessThan
//• Between
//• IsNotNull
//• Like