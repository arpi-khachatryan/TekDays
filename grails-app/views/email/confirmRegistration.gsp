<%--
  Created by IntelliJ IDEA.
  User: sofs5
  Date: 29.03.23
  Time: 11:31
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Email For Registration</title>
</head>

<body>
<%@ page contentType="text/plain" %>
Dear ${user.fullName}
Congratulations! You have registered with TekDays, giving you access to be part of great event.
Your login id is: ${user.userName}
Please confirm your registration
%{--<g:createLink base="http://localhost:8080/TekDays/tekUser/verifyUser?plainToken=${token.plainToken}"/>--}%
<a href="http://localhost:8080/TekDays/tekUser/verifyUser?plainToken=${token.plainToken}">LINK</a>
Kind Regards,
The TekDays Team
</body>
</html>