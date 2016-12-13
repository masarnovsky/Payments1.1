<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${login} home</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <table>
        <thead>
        <tr>
            <th data-field="id">id</th>
            <th data-field="cash">Баланс</th>
            <th data-field="isBlocked">Заблокирована</th>
            <th data-field="number">Номер КК</th>
            <th data-field="valid">Валидность</th>
            <th data-field="cvv">CVV</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="account" items="${accountsAttribute}">
                <c:set var="idAcc" value="${account.getId()}"></c:set>
                <tr>
                    <td>${account.getId()}</td>
                    <td>${account.getCash()}</td>
                    <td>${account.isBlocked()}</td>
                    <td>${creditCardAttribute[idAcc].getNumber()}</td>
                    <td>${creditCardAttribute[idAcc].getValid()}</td>
                    <td>${creditCardAttribute[idAcc].getCvv()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



    <br>
    acc: ${errorAccounts}



    <jsp:include page="footer.jsp"/>
</body>
</html>
