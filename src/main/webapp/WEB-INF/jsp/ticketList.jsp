<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-18
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of tickets</title>
</head>
<body>
    <h3>Tabela</h3>
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
                <th>Edit Ticket</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="ticketList" items="${listOfTickets}">
                <tr>
                    <td>${ticketList.idNumber}</td>
                    <td>${ticketList.reportedUser}</td>
                    <td>${ticketList.assignUser}</td>
                    <td>${ticketList.status}</td>
                    <td>${ticketList.title}</td>
                    <td>${ticketList.description}</td>
                    <td>
                        <ol>
                            <c:forEach var="comments" items="${ticketList.commentaryList}">
                                <li>${comments.comment}</li>
                            </c:forEach>
                        </ol>
                    </td>
                    <td>Edit</td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
