<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-19
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New comment</title>
</head>
<body>
<p>You are logged as: ${sessionScope.user.userName}</p>
<br>
    <form method="post" action="ticketListAddComment">
        Status <br />
        <input type="text" name="status" /><br />
        Title: <br />
        <input type="text" name="title" /><br />
        Description <br />
        <input type="text" name="description" /><br />
        <input type="submit" value="create ticket">
    </form>
    <br>
    <form method="post" action="/ticketList">
        <input type="submit" value="back to list">
    </form>
    <p>work in progress</p>
</body>
</html>
