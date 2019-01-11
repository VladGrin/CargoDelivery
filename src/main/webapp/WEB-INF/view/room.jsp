<%--
  Created by IntelliJ IDEA.
  User: ЭЛЬДОРАДО
  Date: 30.12.2018
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room</title>
    <link rel="stylesheet" type="text/css" href="/styles/room.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            border-spacing: 5px;
        }

        th, td {
            padding: 3px;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">
        <a href="../../WEB-INF/view/index.jsp">
            <img class="graficlogo" src="../../images/logo.png" alt="Logo">
            <p style="color:white;font-size: 35px;font-family: 'Arial';margin-top: 20px;">CargoDelivery</p>
        </a>
        <a class="button" href=<c:url value="/logout"/>>Выход</a>
    </div>
    <nav>
        <div class="topnav" id="myTopnav">
            <a href="#">КАБИНЕТ</a>
            <a href="">О КОМПАНИИ</a>
            <a href="">ТАРИФЫ</a>
            <a href="/direction">НАПРАВЛЕНИЕ</a>
            <a href="/calculator">КАЛЬКУЛЯТОР</a>
            <a href="">КОНТАКТЫ</a>
            <a href="#" id="menu" class="icon">&#9776;</a>
        </div>
    </nav>
</header>

<main>
    <h1>MY ROOM!!!</h1>
    <a class="button" href=<c:url value="/room/order"/>>Сделать заказ</a>
    <div>
        <table>
            <tr>
                <th>Номер</th>
                <th>Дата создания</th>
                <th>Город отправления</th>
                <th>Город доставки</th>
                <th>Вес, кг</th>
                <th>Дата отправления</th>
                <th>Дата доставки</th>
                <th>Получатель</th>
                <th>Контактный телефон</th>
                <th>Адресс доставки</th>
                <th>Цена, грн</th>
                <th>Счёт</th>
                <th>Удалить</th>
            </tr>
            <c:forEach var="order" items="${requestScope.orders}">
                <tr>
                    <td><b><c:out value="${order.id}"/></b></td>
                    <td><b><c:out value="${order.createDate}"/></b></td>
                    <td><span><c:out value="${order.cityFrom}"/></span></td>
                    <td><span><c:out value="${order.cityTo}"/></span></td>
                    <td><span><c:out value="${order.weight}"/></span></td>
                    <td><span><c:out value="${order.startDate}"/></span></td>
                    <td><span><c:out value="${order.endDate}"/></span></td>
                    <td><span><c:out value="${order.recipient}"/></span></td>
                    <td><span><c:out value="${order.recipientPhone}"/></span></td>
                    <td><span><c:out value="${order.deliveryAddress}"/></span></td>
                    <td><span><c:out value="${order.price}"/></span></td>
                    <td>
                        <c:if test="${order.payment == false}">
                            <form action="/room/bill" method="post">
                                <input type="hidden" name="orderId" c:out value="${order.id}">
                                <input class="button" type="submit" value="счёт">
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${order.payment == false}">
                            <form action="/room" method="post">
                                <input type="hidden" name="orderId" c:out value="${order.id}">
                                <input class="button" type="submit" value="удалить">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</main>

<footer>
    <nav>
        <a href="#">КАБИНЕТ</a>
        <a href="">О КОМПАНИИ</a>
        <a href="">ТАРИФЫ</a>
        <a href="/direction">НАПРАВЛЕНИЕ</a>
        <a href="/calculator">КАЛЬКУЛЯТОР</a>
        <a href="">КОНТАКТЫ</a>
    </nav>
    <div class="social">
        <a href="https://instagram.com"><img src="../../images/instagram.png"></a>
        <a href="https://whatsapp.com"><img src="../../images/whatsapp.png"></a>
        <a href="https://youtube.com"><img src="../../images/youtube.png"></a>
        <a href="https://facebook.com"><img src="../../images/facebook.png"></a>
        <a href="https://telegram.com"><img src="../../images/telegram.png"></a>
        <a href="https://viber.com"><img src="../../images/viber.png"></a>
    </div>
    <p>Java Developer.2018-2019</p>
</footer>

<script src="../../js/script.js"></script>
</body>
</html>