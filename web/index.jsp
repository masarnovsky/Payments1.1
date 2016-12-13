<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
        <title>Hello page</title>
        <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
  </head>
  <body>
    <c:import url="/navbar"/>
  <div class="row">
      <div class="col s6 offset-l3">
          <div class="row">
              <a class="waves-effect waves-light btn blue lighten-2" href="/login">Войти</a>
              <a class="waves-effect waves-light btn indigo darken-4" href="/signin">Регистрация</a>
          </div>
      </div>
  </div>





<c:import url="/footer"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
