<%--
  Created by IntelliJ IDEA.
  User: ЭЛЬДОРАДО
  Date: 06.01.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bill</title>
    <link rel="stylesheet" type="text/css" href="/styles/bill.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
        <a href="../../WEB-INF/view/index.jsp">
            <img class="graficlogo" src="../../images/logo.png" alt="Logo">
            <p style="color:white;font-size: 35px;font-family: 'Arial';margin-top: 20px;">CargoDelivery</p>
        </a>
    </div>
    <nav>
        <div class="topnav" id="myTopnav">
            <a href="/room">КАБИНЕТ</a>
            <a href="">О КОМПАНИИ</a>
            <a href="">ТАРИФЫ</a>
            <a href="/direction">НАПРАВЛЕНИЕ</a>
            <a href="/calculator">КАЛЬКУЛЯТОР</a>
            <a href="">КОНТАКТЫ</a>
            <a href="#" id="menu" class="icon">&#9776;</a>
        </div>
    </nav>
</header>

<main style="margin-left: 10%">
    <h3>Счёт на оплату от <c:out value="${requestScope.order.createDate}"/></h3>
    <br>
    <div>ПОСТАВЩИК:
        <span><c:out value="${requestScope.company.name}"/>.  </span>
        <span><c:out value="${requestScope.company.address}"/></span>
    </div>
    <div>
        <lable>р/с:</lable>
        <span><c:out value="${requestScope.company.account}"/>.   </span>

        <lable>Банк:</lable>
        <span><c:out value="${requestScope.company.bank}"/>.  </span>

        <lable>МФО:</lable>
        <span><c:out value="${requestScope.company.mfo}"/></span>
    </div>
    <div>
        <lable>Код ЕДРПОУ:</lable>
        <span><c:out value="${requestScope.company.codEDRPOU}"/>.   </span>

        <lable>ИНН:</lable>
        <span><c:out value="${requestScope.company.codINN}"/></span>
    </div>
    <br>
    <div>ПОКУПАТЕЛЬ:
        <lable>Ф.И.О.</lable>
        <span><c:out value="${requestScope.user.surname}"/>  <c:out value="${requestScope.user.name}"/></span>
    </div>
    <div>
        <lable>Город, телефон:</lable>
        <span><c:out value="${requestScope.user.city}"/>, <c:out value="${requestScope.user.phone}, "/></span>
    </div>
    <br>
    <div>
        <lable>Наименование услуг:</lable>
        <span>Доставка груза.</span>
    </div>
    <br>
    <div>Маршрут</div>
    <div>
        <lable>Город отправления:</lable>
        <span><c:out value="${requestScope.order.cityFrom}"/></span>
    </div>
    <div>
        <lable>Город назначения:</lable>
        <span><c:out value="${requestScope.order.cityTo}"/></span>
    </div>
    <br>
    <div>
        <lable>Стоимость:</lable>
        <span><c:out value="${requestScope.order.price}"/> грн.</span>
    </div>
    <form action="/room/bill/payment" method="post">
        <input type="hidden" name="orderId" c:out value="${requestScope.order.id}">
        <input class="button" type="submit" value="Оплатить">
    </form>
</main>

<footer>
    <nav>
        <a href="/room">КАБИНЕТ</a>
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
