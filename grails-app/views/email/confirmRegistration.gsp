<%--
  Created by IntelliJ IDEA.
  User: sofs5
  Date: 29.03.23
  Time: 11:31
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<%@ page contentType="text/plain"%>
Dear ${user.fullName}
Congratulations! You have registered with TekDays, giving you access to be part of great event.
Your login id is: ${user.userName}
You can use the following link to login:
<g:createLink base="http://localhost:8080/TekDays/tekUser/login"/>
%{--<g:createLink controller="login" absolute="true" />--}%
Kind Regards,
The TekDays Team

</body>
</html>