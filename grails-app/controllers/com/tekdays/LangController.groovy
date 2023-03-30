package com.tekdays


class LangController {

    def changeLang() {
        def lang = params.lang
        session.setAttribute('lang', lang)
        def urlList = request?.getHeader("referer")?.split("\\?")[0]
        def url = ""
        if (urlList) {
            url = urlList[0]
            println 'fff'
        }
        switch (lang) {
            case "en":
                url = url + "?lang=${lang}"
                break
            case "ru":
                url = url + "?lang=${lang}"
                break
            case "hy":
                url = url + "?lang=${lang}"
                break
            default:
                url = url + "?lang=en"
                break
        }
        redirect(uri: url)
    }

    def checkLang() {
        def lang = session?.lang
        if (lang != null) {

            render(lang)
        } else render('en')
    }

    def getLang() {
        def lang = params?.lang
        def url = request.getHeader("referer").split("\\?")[0]
        render("${url}?lang=${lang}")
    }
}
