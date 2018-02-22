
<%@ page import="com.tracker.repository.VariableRepository" %><%--
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
<form method="GET" action="ticketListAddTicket">
    Status <br />
    <select name="status">
        <option value="new" ${param.getStatus == '1' ? 'new' : ''}>new</option>
        <option value="in progress" ${param.getStatus == '2' ? 'in progress' : ''}>in progress</option>
        <option value="reject" ${param.getStatus == '3' ? 'reject' : ''}>reject</option>
        <option value="done" ${param.getStatus == '4' ? 'done' : ''}>done</option>
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
<form method="post" action="/ticketList">
    <input type="submit" value="back to list">
</form>
</body>
</html>
