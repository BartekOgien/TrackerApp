<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-18
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Log into account</title>
</head>
<body>
    <h3>Log to your account</h3>
    <form method="post" action="validation">
        <p style="color:#FF0000";>${sessionScope.loginError}</p>
        Login: <br />
        <input type="text" name="username"/><br />
        Password: <br />
        <input type="password" name="userPassword" /><br />
        <input type="submit" value="Log in">
    </form>
    <br>
    <form method="post" action="registerUser">
        <input type="submit" value="register new user">
    </form>
</body>
</html>
