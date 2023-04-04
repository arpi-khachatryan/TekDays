<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Email For Volunteering</title>
</head>

<body>
<%@ page contentType="text/plain" %>
Good day ${user.fullName}.
You have received this email because we want to tell you that our volunteering projects have been planned.
You can use the following link to see our events:
%{--<g:createLink  base="http://localhost:8080/TekDays/tekEvent"/>--}%
<a href="http://localhost:8080/TekDays/tekEvent">LINK</a>
Kind Regards,
The TekDays Team
</body>
</html>