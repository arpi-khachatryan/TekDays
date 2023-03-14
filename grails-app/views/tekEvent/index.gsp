<%@ page import="com.tekdays.TekEvent" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>

    <script>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "/TekDays/tekEvent/dataTablesRenderer",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [5, 10, 25, 50, 100, 200],
                iDisplayLength: 10,
                aoColumnDefs: [
                    {
                        aTargets: [6],
                        visible: false,
                        bSearchable: false
                    },
                    {
                        // bSearchable: false,
                        render: function (data, type, full, meta) {
                            if (full[6]) {
                                return '<a href="${createLink(controller: 'TekEvent', action: 'show')}/' + full[6] + '"class="btn">' + data + '</a>';
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
                    {
                        createdCell: function (td, cellData, rowData, row, col) {
                            $(td).attr('style', 'color: #885959FF');
                        },
                        aTargets: [4, 5],
                        bSearchable: false,
                        bSortable: false,
                        visible: true
                    },
                    // {
                    //     aTargets: [4, 5],
                    //     visible: true
                    // },
                ]
            });
        });
    </script>
</head>

<body>
<a href="#list-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-tekEvent" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table-bordered" id="dt">
        <thead>
        <tr>
            <th>Name</th>
            <th>City</th>
            <th>Organizer</th>
            <th>Venue</th>
            <th>Start Date</th>
            <th>End Date</th>
        </tr>
        </thead>
    </table>

    %{--			<table>--}%
    %{--			<thead>--}%
    %{--					<tr>--}%

    %{--						<g:sortableColumn property="name" title="${message(code: 'tekEvent.name.label', default: 'Name')}" />--}%

    %{--						<g:sortableColumn property="city" title="${message(code: 'tekEvent.city.label', default: 'City')}" />--}%

    %{--						<g:sortableColumn property="description" title="${message(code: 'tekEvent.description.label', default: 'Description')}" />--}%

    %{--						<th><g:message code="tekEvent.organizer.label" default="Organizer" /></th>--}%

    %{--						<g:sortableColumn property="venue" title="${message(code: 'tekEvent.venue.label', default: 'Venue')}" />--}%

    %{--						<g:sortableColumn property="startDate" title="${message(code: 'tekEvent.startDate.label', default: 'Start Date')}" />--}%

    %{--					</tr>--}%
    %{--				</thead>--}%
    %{--				<tbody>--}%
    %{--				<g:each in="${tekEventInstanceList}" status="i" var="tekEventInstance">--}%
    %{--					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">--}%

    %{--						<td><g:link action="show" id="${tekEventInstance.id}">${fieldValue(bean: tekEventInstance, field: "name")}</g:link></td>--}%

    %{--						<td>${fieldValue(bean: tekEventInstance, field: "city")}</td>--}%

    %{--						<td>${fieldValue(bean: tekEventInstance, field: "description")}</td>--}%

    %{--						<td>${fieldValue(bean: tekEventInstance, field: "organizer")}</td>--}%

    %{--						<td>${fieldValue(bean: tekEventInstance, field: "venue")}</td>--}%

    %{--						<td><g:formatDate date="${tekEventInstance.startDate}" /></td>--}%

    %{--					</tr>--}%
    %{--				</g:each>--}%
    %{--				</tbody>--}%
    %{--			</table>--}%
    %{--			<div class="pagination">--}%
    %{--				<g:paginate total="${tekEventInstanceCount ?: 0}" />--}%
    %{--			</div>--}%
</div>
</body>
</html>
