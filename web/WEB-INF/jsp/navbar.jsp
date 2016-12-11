<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<nav>
  <div class="nav-wrapper">
    <ul>
        <li>My accounts</li>
        <c:if test="${isAdmin}">
            <li>Show blocked accounts</li>
        </c:if>
        <c:if test="${isSignedIn == true}">
            <li><a href="controller?command=logout">logout</a></li>
        </c:if>
    </ul>
  </div>
</nav>
</body>
</html>
