<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
</head>
<body>
<div>
<nav>
  <div class="nav-wrapper indigo darken-2">
      <a class="brand-logo white-text" style="padding-left: 15px;">BANK</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${isSignedIn == true}">
                <li class="white-text" style="padding: 0px 15px 0px 15px">Пользователь: ${login}</li>
                <li><a href="controller?command=updateHome">Мои счета</a></li>
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
