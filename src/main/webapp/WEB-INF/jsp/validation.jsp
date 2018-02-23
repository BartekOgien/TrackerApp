<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-19
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Validation</title>
</head>
<body>
    <%
        if ((Boolean)request.getAttribute("validation")) {
        String redirectURL = "ticketList";
        response.sendRedirect(redirectURL);
        }
        else {
        String redirectURL = "/";
        response.sendRedirect(redirectURL);
        }

    %>
</body>
</html>
