
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-18
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>List of tickets</title>
</head>
<body>
    <p>You are logged as: ${sessionScope.user.userName}</p>
    <form method="post" action="/">
        <input type="submit" value="Log out">
    </form>
    <br>
    <h3>Tickets</h3>
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
                <th>Delete Ticket</th>
            </tr>
        </thead>
        <tbody >
            <c:forEach var="ticketList" items="${listOfTickets}">
                <tr>
                    <td>${ticketList.idNumber}</td>
                    <td>${ticketList.reportedUser.userName}</td>
                    <td>${ticketList.assignedUser.userName}</td>
                    <td>${ticketList.status}</td>
                    <td>${ticketList.title}</td>
                    <td>${ticketList.description}</td>
                    <td>
                        <ul>
                            <c:forEach var="comments" items="${ticketList.commentaryList}">
                                <li>${comments.created} | ${comments.user.userName} wrote: | ${comments.comment}</li>
                            </c:forEach>
                                <li>
                                    <form method="post" action="commentTemplate" >
                                        <input type="hidden" name="id" value="${ticketList.idNumber}" />
                                        <input type="submit" value="add comment">
                                     </form>
                                </li>
                        </ul>
                    </td>
                    <td>
                         <form method="post" action="selectTicket">
                             <input type="hidden" name="id" value="${ticketList.idNumber}" />
                             <input  type="submit" value="edit ticket">
                         </form>
                    </td>
                    <td>
                        <form method="post" action="deleteTicket">
                            <input type="hidden" name="id" value="${ticketList.idNumber}" />
                            <input  type="submit" value="delete ticket" style="color:red">
                        </form>
                    </td>
                 </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" action="newTicket">
        <input type="submit" value="add ticket">
    </form>
</body>
</html>
