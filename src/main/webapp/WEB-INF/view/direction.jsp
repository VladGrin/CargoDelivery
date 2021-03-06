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
    <title>Direction</title>
    <link rel="stylesheet" type="text/css" href="/styles/direction.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>
<body>

<header>
    <div class="logo">
        <a href="/">
            <img class="graficlogo" src="../../images/logo.png" alt="Logo">
            <p style="color:white;font-size: 35px;font-family: 'Arial';margin-top: 20px;">CargoDelivery</p>
        </a>
        <c:if test="${requestScope.role == 0}">
            <form method="post" action="/" class="form">
                <input type="email" required placeholder="Email" name="login" class="first"><!-- <br> -->
                <input type="password" required placeholder="Password" name="password" class="second">
                <input class="button" type="submit" value="Вход">
                <div class="text">
                    <a href="/registration" style="color:white;">регистрация</a>
                </div>
            </form>
        </c:if>
        <c:if test="${requestScope.role == 1}">
            <div class="exit">
                <a class="button" href=<c:url value="/logout"/>>Выход</a>
            </div>
        </c:if>
    </div>
    <nav>
        <div class="topnav" id="myTopnav">
            <c:if test="${requestScope.role == 0}">
                <a href="/">ГЛАВНАЯ</a>
            </c:if>
            <c:if test="${requestScope.role == 1}">
                <a href="/">КАБИНЕТ</a>
            </c:if>
            <a href="">О КОМПАНИИ</a>
            <a href="">ТАРИФЫ</a>
            <a href="#">НАПРАВЛЕНИЕ</a>
            <a href="/calculator">КАЛЬКУЛЯТОР</a>
            <a href="">КОНТАКТЫ</a>
            <a href="#" id="menu" class="icon">&#9776;</a>
        </div>
    </nav>
</header>

<main>
    <h2>Все города</h2><br/>
    <c:forEach var="city" items="${requestScope.cities}">
        <ul>
            <li style="text-align: center"><c:out value="${city.name }"/></li>
            <br>
        </ul>
    </c:forEach>

</main>

<footer>
    <nav>
        <c:if test="${requestScope.role == 0}">
            <a href="/">ГЛАВНАЯ</a>
        </c:if>
        <c:if test="${requestScope.role == 1}">
            <a href="/">КАБИНЕТ</a>
        </c:if>
        <a href="">О КОМПАНИИ</a>
        <a href="">ТАРИФЫ</a>
        <a href="#">НАПРАВЛЕНИЕ</a>
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
