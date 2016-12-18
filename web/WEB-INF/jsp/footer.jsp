<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<footer class="page-footer indigo">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Курсовой проект</h5>
                <p class="grey-text text-lighten-4">Тема: Платежи</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <c:if test="${isSignedIn == true}">
                <h5 class="white-text">Ссылки</h5>
                <ul>
                        <li><a class="grey-text text-lighten-3" href="controller?command=updateHome">Мои счета</a></li>
                        <c:if test="${isAdmin}">
                            <li><a class="grey-text text-lighten-3" href="controller?command=getAllBlockedAccounts">Заблокированные аккаунты</a></li>
                        </c:if>
                        <li><a class="grey-text text-lighten-3" href="controller?command=logout">Выйти</a></li>
                </ul>
                </c:if>
            </div>
        </div>
    </div>
    <div class="footer-copyright indigo darken-2">
        <div class="container center-align">
            © 2016 Max Masarnovsky | m.masarnovsky@gmail.com
        </div>
    </div>
</footer>
</body>
</html>
