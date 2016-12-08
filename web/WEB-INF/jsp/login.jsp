<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.12.2016
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <form name="LoginForm" method="POST" action="controller">
        <input name="command" type="hidden" value="Login">
        Login:
        <input type="text" name="login" value="">
        Password:
        <input type="password" name="password" value="">
        ${errorLoginOrPassMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Log in"/>
    </form>

    <jsp:include page="footer.jsp"/>
</body>
</html>
