package com.tekdays

class AdminController {

    def admin() {
        def user = session.user
        if (user && user.email == "mrbill@email.com") {
            render(view: "admin", model: [user: user])
        } else {
            redirect(uri: "/")
        }
    }
}
