<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
    <jsp:include page="/navbar"/>
    <div id="fullWindow">
    <div class="row">
        <form name="SigninForm" action="controller" method="POST" class="col s8">
            <input name="command" type="hidden" value="signin">
            <div class="row">
                <div class="input-field col s4">
                    <input name="fio" type="text">
                    <label>FIO</label>
                </div>
                <div class="input-field col s4">
                    <input name="login" type="text" class="validate" length="15">
                    <label>Login</label>
                </div>
                <div class="col s4"><p>${errorInLogin}</p></div>
            </div>
            <div class="row">
                <div class="input-field col s4">
                    <input id="password" type="password" name="password" class="validate" length="15">
                    <label for="password">Password</label>
                </div>
                <div class="input-field col s4">
                    <input name="passwordRepeat" id="passwordRepeat" type="password" class="validate" length="15">
                    <label for="passwordRepeat">Repeat password</label>
                </div>
                <div class="col s4"><p>${errorInPass}</p></div>
                <div class="col s4"><p>${errorSIGNIN}</p></div>
            </div>
            <button class="btn waves-effect waves-light" type="submit">Зарегистрироваться</button>
            <a class="waves-effect waves-light btn blue lighten-2" href="/index.jsp">На главную</a>

        </form>
    </div>
    </div>
    <jsp:include page="/footer"/>

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
</body>
</html>
