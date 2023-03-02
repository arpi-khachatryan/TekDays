<span style="text-align:center">
    <h1>${event}</h1>
</span>
<table>
    <thead>
    <tr>
        <th>Start Date</th>
        <g:if test="${event.endDate}">
            <th>End Date</th>
        </g:if>
        <th>Venue</th>
        <th>Number of potential attendees</th>
    </tr>
    </thead>
    <tr>
        <td>
            <g:formatDate format="MMM/dd/yyyy" date="${event.startDate}"/>
        </td>

        <g:if test="${event.endDate}">
            <td>
                <g:formatDate format="MMM/dd/yyyy" date="${event.endDate}"/>
            </td>
        </g:if>

        <td>
            ${event.venue}
        </td>

        <td>
            ${event.respondents.size()}
        </td>
    </tr>
</table>









%{--<span style="text-align:center">--}%
%{--    <h1>${event}</h1>--}%
%{--</span>--}%
%{--<table>--}%
%{--    <tr>--}%
%{--        <td>--}%
%{--            Start Date: <g:formatDate format="MMM/dd/yyyy" date="${event.startDate}"/>--}%
%{--        </td>--}%
%{--        <td>--}%
%{--            <g:if test="${event.endDate}">--}%
%{--                End Date: <g:formatDate format="MMM/dd/yyyy" date="${event.endDate}"/>--}%
%{--            </g:if>--}%
%{--        </td>--}%
%{--    </tr>--}%
%{--    <tr>--}%
%{--        <td>--}%
%{--            Venue: ${event.venue}--}%
%{--        </td>--}%
%{--        <td>--}%
%{--            Number of potential attendees: ${event.respondents.size()}--}%
%{--        </td>--}%
%{--    </tr>--}%
%{--</table>--}%