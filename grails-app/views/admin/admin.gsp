<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>TekDays - Admin page!</title>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="page-body" role="main">
    <g:message code="my.message" args="[session?.user?.fullName]" default="admin"/>
    <g:if test="${user?.email == "mrbill@email.com"}">
        <div id="controller-list" role="navigation">
            <br>

            <h2>Available Controllers</h2>
            <br>
            <ul>
                <span class="buttons" style="position:relative; top:2px;">
                    <g:link controller="tekUser" action="index">Users</g:link>
                    <g:link controller="tekEvent" action="index">Events</g:link>
                    <g:link controller="tekMessage" action="index">Messages</g:link>
                    <g:link controller="task" action="index">Tasks</g:link>
                    <g:link controller="sponsor" action="index">Sponsors</g:link>
                    <g:link controller="sponsorship" action="index">Sponsorships</g:link>
                </span>
            </ul>
        </div>
    </g:if>
</div>
</body>
</html>