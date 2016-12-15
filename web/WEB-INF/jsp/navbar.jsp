<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
</head>
<body>
<div>
<nav>
  <div class="nav-wrapper">
      <a class="brand-logo white-text">BANK</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${isSignedIn == true}">
                <li><a class="white-text">Пользователь: ${login}</a></li>
                <li><a href="#">Мои счета</a></li>
            <c:if test="${isAdmin}">
                <li><a href="controller?command=getAllBlockedAccounts">Показать все заблокированные аккаунты</a></li>
            </c:if>
            <li><a href="controller?command=logout">Выйти</a></li>
            </c:if>
        </ul>
  </div>
</nav>
</div>
</body>
</html>
