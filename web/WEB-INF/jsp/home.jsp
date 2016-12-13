<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    ${login}
    <br>
    acc: ${errorAccounts}

    <jsp:include page="footer.jsp"/>
</body>
</html>
