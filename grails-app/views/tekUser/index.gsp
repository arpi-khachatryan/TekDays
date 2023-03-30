<%@ page import="com.tekdays.TekUser" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekUser.label', default: 'TekUser')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>

    <script>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "/TekDays/tekUser/dataTablesRenderer",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [5, 10, 25, 50, 100, 200],
                iDisplayLength: 10,
                aoColumnDefs: [
                    {
                        aTargets: [4],
                        visible: false,
                        bSearchable: false
                    },
                    {
                        // bSearchable: false,
                        render: function (data, type, full, meta) {
                            if (full[4]) {
                                return '<a href="${createLink(controller: 'TekUser', action: 'show')}/' + full[4] + '"class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [0],
                        visible: true,
                        bSearchable: true,
                        bSortable: true
                    },
                    {
                        // bSearchable: false,
                        render: function (data, type, full, meta) {
                            if (full[4]) {
                                return '<a href="${createLink(controller: 'TekUser', action: 'show')}/' + full[4] + '"class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [1],
                        visible: true,
                        bSearchable: true,
                        bSortable: true
                    },
                    {
                        createdCell: function (td, cellData, rowData, row, col) {
                            // console.log(td)
                            // console.log(cellData)
                            // console.log(rowData)
                            // console.log(row)
                            //  console.log(col)
                            $(td).attr('style', 'color: #423F3FFF');
                        },
                        aTargets: [1, 2, 3],
                        bSearchable: false,
                        bSortable: false,
                        visible: true
                    },
                ]
            });
        });
    </script>
</head>

<body>
<a href="#list-tekUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-tekUser" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>


    <table class="table-bordered" id="dt">
        <thead>
        <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Website</th>
            <th>Bio</th>
        </tr>
        </thead>
    </table>


    %{--    <table>--}%
    %{--        <thead>--}%
    %{--        <tr>--}%

    %{--            <g:sortableColumn property="fullName"--}%
    %{--                              title="${message(code: 'tekUser.fullName.label', default: 'Full Name')}"/>--}%

    %{--            <g:sortableColumn property="userName"--}%
    %{--                              title="${message(code: 'tekUser.userName.label', default: 'User Name')}"/>--}%

    %{--            <g:sortableColumn property="email" title="${message(code: 'tekUser.email.label', default: 'Email')}"/>--}%

    %{--            <g:sortableColumn property="website" title="${message(code: 'tekUser.website.label', default: 'Website')}"/>--}%

    %{--            <g:sortableColumn property="bio" title="${message(code: 'tekUser.bio.label', default: 'Bio')}"/>--}%

    %{--            <g:sortableColumn property="password"--}%
    %{--                              title="${message(code: 'tekUser.password.label', default: 'Password')}"/>--}%

    %{--        </tr>--}%
    %{--        </thead>--}%
    %{--        <tbody>--}%
    %{--        <g:each in="${tekUserInstanceList}" status="i" var="tekUserInstance">--}%
    %{--            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">--}%

    %{--                <td><g:link action="show"--}%
    %{--                            id="${tekUserInstance.id}">${fieldValue(bean: tekUserInstance, field: "fullName")}</g:link></td>--}%

    %{--                <td>${fieldValue(bean: tekUserInstance, field: "userName")}</td>--}%

    %{--                <td>${fieldValue(bean: tekUserInstance, field: "email")}</td>--}%

    %{--                <td>${fieldValue(bean: tekUserInstance, field: "website")}</td>--}%

    %{--                <td>${fieldValue(bean: tekUserInstance, field: "bio")}</td>--}%

    %{--                <td>${fieldValue(bean: tekUserInstance, field: "password")}</td>--}%

    %{--            </tr>--}%
    %{--        </g:each>--}%
    %{--        </tbody>--}%
    %{--    </table>--}%

    %{--    <div class="pagination">--}%
    %{--        <g:paginate total="${tekUserInstanceCount ?: 0}"/>--}%
    %{--    </div>--}%
</div>
</body>
</html>
