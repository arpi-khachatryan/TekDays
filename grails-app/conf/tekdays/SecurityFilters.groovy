package tekdays

class SecurityFilters {

    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                if (!controllerName) {
                    return true
                }
                def allowedActions = ['show', 'index', 'login', 'validate', 'showDetail','dataTablesRenderer']
                if (!session.user && !allowedActions.contains(actionName)) {
                    if (actionName.equals('edit')) {
                        redirect(controller: 'tekUser', action: 'login',
                                params: ['cName': controllerName, 'aName': actionName, 'id': params.id])
                        return false
                    }
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName, 'aName': actionName])
                    return false
                }
            }
        }
    }
}
