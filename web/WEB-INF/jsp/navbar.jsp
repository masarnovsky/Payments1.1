<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
</head>
<body>
<nav>
  <div class="nav-wrapper">
    <ul>
        <c:if test="${isSignedIn == true}">
            <li>Мои счета</li>
            <c:if test="${isAdmin}">
                <li>Показать все заблокированные аккаунты</li>
            </c:if>

            <li><a href="controller?command=logout">Выйти</a></li>
        </c:if>
    </ul>
  </div>
</nav>
</body>
</html>
