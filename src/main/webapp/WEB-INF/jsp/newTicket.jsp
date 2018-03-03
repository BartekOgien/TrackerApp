<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-19
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Ticket</title>
</head>
<body>
<p>You are logged as: ${sessionScope.user.userName}</p>
<form method="post" action="/">
    <input type="submit" value="Log out">
</form>
<br>
<h3>Add new ticket</h3>
<form method="post" action="ticketListAddTicket">
    Status <br />
    <select name="status">
        <option value="new">new</option>
        <option value="in progress">in progress</option>
        <option value="reject">reject</option>
        <option value="done">done</option>
    </select>
    <br>
    Title: <br />
    <input type="text" name="title" /><br />
    Description <br />
    <input type="text" name="description" /><br />
    <input type="submit" value="create ticket">
</form>
<br>
<br>
<form method="get" action="/ticketList">
    <input type="submit" value="back to list">
</form>
</body>
</html>
