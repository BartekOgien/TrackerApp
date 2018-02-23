
<%--  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register new user</title>
</head>
<body>
<h3>Register new user</h3>
<form method="GET" action="addUser">
    <p style="color:#FF0000";>${registerError}</p>
    Your login <br />
    <input type="text" name="newUsername" /><br />
    Your password: <br />
    <input type="password" name="newPassword" /><br />
    <input type="submit" value="Register">
</form>
</body>
</html>
