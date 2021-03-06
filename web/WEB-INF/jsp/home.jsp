<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${login} home</title>
    <link rel="stylesheet" type="text/css" href="../../css/materialize.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
    <c:set var="hasNoAccount" value="${errorAccounts}"/>
        <c:if test="${hasNoAccount ne true}">
            <div class="row">
                <h5 class="center-align">Ваши счета:</h5>
                <div class="center-align">
                    <a class="waves-effect waves-light btn indigo darken-4" href="controller?command=createAccount">Создать счет</a>
                </div>
                <h5 class="center-align">${accountMessage}</h5>
            </div>
            <c:forEach var="account" items="${accountsAttribute}">
                <c:set var="idAcc" value="${account.getId()}"/>
                <c:set var="isBlckd" value="${account.isBlocked()}"/>
                <div class="row">
                    <div class="col s8 offset-l2">
                        <div class="card indigo lighten-4">
                            <div class="card-content">
                                <span class="card-title">Сведения о счете</span>
                                <div class="row">
                                    <div class="col s5">
                                        <span style="font-weight: 600">Номер: </span>${account.getId()} <br>
                                        <span style="font-weight: 600">Баланс: </span>${account.getCash()} BYR <br>
                                        <span style="font-weight: 600">Заблокирован: </span>${isBlckd}
                                    </div>
                                    <div class="col s7">
                                        <span style="font-weight: 600">Номер КК: </span>${creditCardAttribute[idAcc].getNumber()}<br>
                                        <span style="font-weight: 600">Валидность: </span>${creditCardAttribute[idAcc].getValid()}<br>
                                        <span style="font-weight: 600">CVV: </span>${creditCardAttribute[idAcc].getCvv()}
                                    </div>
                                </div>
                            </div>
                            <c:if test="${isBlckd ne true}">
                                <div class="card-action">
                                    <a href="#modal${account.getId()}" class="grey-text text-darken-4">Пополнить счет</a>
                                    <a href="#modalPayments${account.getId()}" class="grey-text text-darken-4">Оплатить</a>
                                    <a href="controller?command=updateCash${account.getId()}" class="grey-text text-darken-4">История оплат</a>
                                    <a href="controller?command=blockAccount${account.getId()}" class="grey-text text-darken-4">Заблокировать</a>
                                </div>

                                <!-- Modal Structure -->
                                <div id="modal${account.getId()}" class="modal">
                                    <div class="modal-content">
                                        <h4>Пополнение счета</h4>
                                        <form id="formUpdateCash" name="UpdateCash" action="controller" method="POST">
                                            <input name="command" type="hidden" value="updatecash"/>
                                            <input id="idAccount" name="idAccount" type="hidden" value="${account.getId()}">
                                            <input id="accountCash" name="accountCash" type="hidden" value="${account.getCash()}">

                                            <div class="row">
                                                <span style="font-weight: 600; padding-left: 11px;">Номер счета: </span> ${account.getId()} <br>
                                                <span style="font-weight: 600; padding-left: 11px;">Текущий баланс: </span> ${account.getCash()} BYR <br>
                                                <span style="font-weight: 600; padding-left: 11px;">Введите сумму для пополнения: </span> <br>
                                                <div class="input-field col s5">
                                                    <input name="cashForUpdate" type="text">
                                                </div>
                                            </div>
                                            <div class="row right-align">
                                                <button type="submit" class=" modal-action modal-close waves-effect waves-green btn-flat">Оплатить</button>
                                                <a class="modal-action modal-close waves-effect waves-red btn-flat">Закрыть</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- modal -->

                                <!-- Modal for Payments -->
                                <div id="modalPayments${account.getId()}" class="modal">
                                    <div class="modal-content">
                                        <h4>Сделать платёж</h4>
                                        <form id="formCreatePayment" name="formCreatePayment" action="controller" method="POST">
                                            <input name="command" type="hidden" value="createPayment"/>
                                            <input id="idAccountForPayment" name="idAccountForPayment" type="hidden" value="${account.getId()}">
                                            <input id="accountCashForPayment" name="accountCashForPayment" type="hidden" value="${account.getCash()}">

                                            <div class="row">
                                                <span style="font-weight: 600; padding-left: 11px;">Номер счета: </span> ${account.getId()} <br>
                                                <span style="font-weight: 600; padding-left: 11px;">Текущий баланс: </span> ${account.getCash()} BYR <br>
                                                <span style="font-weight: 600; padding-left: 11px;">Введите сумму для оплаты: </span> <br>
                                                <div class="input-field col s5">
                                                    <input name="cashForPayment" type="text">
                                                </div>
                                            </div>
                                            <div class="row right-align">
                                                <button type="submit" class=" modal-action modal-close waves-effect waves-green btn-flat">Оплатить</button>
                                                <a class="modal-action modal-close waves-effect waves-red btn-flat">Закрыть</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- end modal -->
                            </c:if>
                            <c:if test="${isBlckd}">
                                <div class="card-action center-align">
                                    <a class="red-text text-accent-4">Заблокировано</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${hasNoAccount eq true}">
            <h5 class="center-align">У вас пока нет ни одного аккаунта</h5>
            <a class="waves-effect waves-light btn indigo darken-4" href="controller?command=createAccount">Создать счет</a>
        </c:if>
    </div>
    <jsp:include page="footer.jsp"/>

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
    <script type="text/javascript" src="js/script.js">
    </script>
</body>
</html>



<%--
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

--%>
