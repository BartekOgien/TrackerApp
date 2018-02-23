<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-18
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
<p>You are logged as: ${sessionScope.user.userName}</p>
    <form method="post" action="/">
    <input type="submit" value="Log out">
    </form>
    <br>
    <form method="post" action="/ticketList">
        <input type="submit" value="back to list">
    </form>
    <br>
<br>
    <p>ticket</p>
</body>
</html>
