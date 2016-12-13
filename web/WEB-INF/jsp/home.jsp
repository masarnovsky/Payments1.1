<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${login} home</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
    <table>
        <thead>
        <tr>
            <th data-field="id">Номер счета</th>
            <th data-field="cash">Баланс</th>
            <th data-field="isBlocked">Заблокирована</th>
            <th data-field="number">Номер КК</th>
            <th data-field="valid">Валидность</th>
            <th data-field="cvv">CVV</th>
            <th data-field="#"></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="account" items="${accountsAttribute}">
                <c:set var="idAcc" value="${account.getId()}"/>
                <c:set var="isBlckd" value="${account.isBlocked()}"/>
                <tr>
                    <td>${account.getId()}</td>
                    <td>${account.getCash()}</td>
                    <c:choose>
                        <c:when test="${not isBlckd}">
                            <td>${account.isBlocked()}</td>
                        </c:when>
                        <c:otherwise>
                            <td class="red-text accent-4">${account.isBlocked()}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${creditCardAttribute[idAcc].getNumber()}</td>
                    <td>${creditCardAttribute[idAcc].getValid()}</td>
                    <td>${creditCardAttribute[idAcc].getCvv()}</td>
                    <c:choose>
                        <c:when test="${not isBlckd}">
                            <td><button class="btn waves-effect waves-light" type="submit">butt</button></td>
                        </c:when>
                        <c:otherwise>
                            <td><button class="btn waves-effect waves-light grey">butt</button></td>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>
        </tbody>
    </table>



    <br>
    ${errorAccounts}


    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
