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
<form method="get" action="/">
    <input type="submit" value="Log out">
</form>
<br>
    <form method="post" action="comment">
        Comment: <br />
        <input type="text" name="comment" size="30"/><br />
        <input type="submit" value="add comment">
    </form>
    <br>
    <form method="get" action="ticketList">
        <input type="submit" value="back to list">
    </form>
</body>
</html>
