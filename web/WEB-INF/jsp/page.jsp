<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.12.2016
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    Hello ${user}!

    <a href="controller?command=logout">logout</a>

    <jsp:include page="footer.jsp"/>
</body>
</html>
