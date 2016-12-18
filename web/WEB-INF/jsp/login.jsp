<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<c:import url="/navbar"/>
    <div class="row" style="min-height: 400px">
    <form name="LoginForm" method="POST" action="controller" class="col s8">
        <input name="command" type="hidden" value="login">
        <div class="row">
            <div class="input-field col s4">
                <input name="login" type="text">
                <label>Login</label>
            </div>
            <div class="input-field col s4">
                <input name="password" type="password" class="validate" length="15">
                <label>Password</label>
            </div>
            <div class="text-accent-4">${errorLoginOrPassMessage}</div>
        </div>
        ${wrongAction}
        ${nullPage}
        <button class="btn waves-effect waves-light" type="submit">Войти</button>
        <a class="waves-effect waves-light btn blue lighten-2" href="/index.jsp">На главную</a>
    </form>
    </div>
    <jsp:include page="/footer"/>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
</body>
</html>
