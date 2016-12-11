<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<jsp:include page="/navbar"/>
    <form name="LoginForm" method="POST" action="controller">
        <input name="command" type="hidden" value="login">
        Login:
        <input type="text" name="login" value="">
        Password:
        <input type="password" name="password" value="">
        ${errorLoginOrPassMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Log in"/>
    </form>
    <jsp:include page="/footer"/>
</body>
</html>
