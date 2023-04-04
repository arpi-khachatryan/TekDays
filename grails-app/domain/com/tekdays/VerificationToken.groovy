package com.tekdays

import org.hibernate.envers.Audited
import java.time.LocalDateTime

@Audited
class VerificationToken {

    TekUser tekUser
    String plainToken
    LocalDateTime expiresAt

    static constraints = {
    }
}
