<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form method="post" action="editTicket">
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Reported user</th>
                <th>Assigned user</th>
                <th>Status</th>
                <th>Title</th>
                <th>Description</th>
                <th>Comments</th>
            </tr>
        </thead>
        <tbody >
        <tr>
            <td>${ticketDto.idNumber}</td>
            <td>${ticketDto.reportedUser.userName}</td>
            <td>
                <select name="userId">
                    <option value='${ticketDto.assignedUser.id}' selected='selected'>${ticketDto.assignedUser.userName}</option>
                    <c:forEach var="users" items="${userList}">
                        <option value="${users.id}"> ${users.userName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="status">
                    <option value='${ticketDto.status}' selected='selected'>${ticketDto.status}</option>
                    <option value="new">new</option>
                    <option value="in progress">in progress</option>
                    <option value="reject">reject</option>
                    <option value="done">done</option>
                </select>
            </td>
            <td><input type="text"  name="title"
                       value="${ticketDto.title}"></td>
            <td><input type="text"  name="description"
                       value="${ticketDto.description}"></td>
            <td>
                <ul>
                    <c:forEach var="comments" items="${ticketDto.commentaryList}">
                        <li>${comments.created} | ${comments.user.userName} wrote: | ${comments.comment}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </tbody>
</table>
    <br>
    <input type="submit" value="save ticket">
</form>
<br>
    <form method="post" action="ticketList">
        <input type="submit" value="back to list">
    </form>
</body>
</html>
