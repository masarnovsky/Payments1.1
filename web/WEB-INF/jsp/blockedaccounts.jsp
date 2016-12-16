<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blocking accounts</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
<c:import url="/navbar"/>

<div class="container">
    <div class="row">
        <h5 class="center-align">Заблокированные счета:</h5>
        <h5 class="center-align">${unblockingMessage}</h5>
        <c:if test="${zeroBlockedAccounts eq true}">
            <h5 class="center-align">Заблокированных счетов пока нет</h5>
        </c:if>
    </div>
    <div class="row">
        <c:if test="${zeroBlockedAccounts ne true}">
            <table>
                <thead>
                    <tr>
                        <th data-field="id">Номер счета</th>
                        <th data-field="cash">Баланс</th>
                        <th data-field="owner">Владелец</th>
                        <th data-field="login">Логин</th>
                        <th data-field="unblock"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="account" items="${blockedAccounts}">
                        <c:set var="idAcc" value="${account.getId()}"/>
                        <c:set var="cl" value="${clientsOfBlockingAccounts[idAcc]}"/>
                        <tr>
                            <td>${account.getId()}</td>
                            <td>${account.getCash()}</td>
                            <td>${cl.getFio()}</td>
                            <td>${cl.getLogin()}</td>
                            <td><a class="waves-effect waves-light btn indigo darken-4" href="controller?command=unblockAccount${account.getId()}">Разблокировать счет</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>

<c:import url="/footer"/>
</body>
</html>
