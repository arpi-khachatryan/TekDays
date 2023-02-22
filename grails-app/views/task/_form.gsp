<%@ page import="com.tekdays.Task" %>

%{--<g:javascript library="calendar" />--}%
<%
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
%>

<script>
    $(function () {
        $("#intakeDate").datepicker();
        $("#screenoutDate").datepicker();
        $("#investigatorAssignedDate").datepicker();
        $("#intakeTime").timepicker({});
    });
</script>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="task.title.label" default="Title"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" required="" value="${taskInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="task.notes.label" default="Notes"/>
    </label>
    <g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${taskInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'assignedTo', 'error')} ">
    <label for="assignedTo">
        <g:message code="task.assignedTo.label" default="Assigned To"/>

    </label>
    <g:select id="assignedTo" name="assignedTo.id" from="${com.tekdays.TekUser.list()}" optionKey="id"
              value="${taskInstance?.assignedTo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'dueDate', 'error')} ">
    <label for="dueDate">
        <g:message code="task.dueDate.label" default="Due Date"/>

    </label>

    %{--<calendar:datePicker dateFormat="%Y-%m-%d"--}%
    %{--                     name="dueDate"--}%
    %{--                     singleClick="true"--}%
    %{--                     value="${taskInstance?.dueDate}"--}%
    %{--                     />--}%

    %{--    <g:textField name="intakeDate" style="width: 7em" value="${formatDate(format:'MM/dd/yyyy',date:intakeCommand?.intakeDate)}" class="form-control"--}%
    %{--                 title="mm/dd/yyyy" placeholder="mm/dd/yyyy" pattern="(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)[0-9][0-9]" />--}%


    <g:datePicker name="dueDate" precision="day" value="${taskInstance?.dueDate}" years="${currentYear}" default="none"
                  noSelection="['': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'event', 'error')} required">
    <label for="event">
        <g:message code="task.event.label" default="Event"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="event" name="event.id" from="${com.tekdays.TekEvent.list()}" optionKey="id" required=""
              value="${taskInstance?.event?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'completed',
        'error')} ">
    <label for="completed">
        <g:message code="task.completed.label" default="Completed"/>
    </label>
    <g:checkBox name="completed" value="${taskInstance?.completed}"/>
</div>

