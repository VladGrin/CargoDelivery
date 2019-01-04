<%--
  Created by IntelliJ IDEA.
  User: ЭЛЬДОРАДО
  Date: 30.12.2018
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/styles/registration.css">
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
            <a href="/">ГЛАВНАЯ</a>
            <a href="">О КОМПАНИИ</a>
            <a href="">ТАРИФЫ</a>
            <a href="/direction">НАПРАВЛЕНИЕ</a>
            <a href="/calculator">КАЛЬКУЛЯТОР</a>
            <a href="">КОНТАКТЫ</a>
            <a href="#" id="menu" class="icon">&#9776;
        </div>
    </nav>
</header>
<main>
    <form method="post" action="/registration" class="forma">
        <div class="one">
            <img src="../../images/name.png">
            <input type="text" required placeholder="Имя" name="name">
        </div>
        <div class="one">
            <img src="../../images/surname.png">
            <input type="text" required placeholder="Фамилия" name="surname">
        </div>
        <div class="one">
            <img src="../../images/city.png">
            <input type="text" required placeholder="Город" name="city">
        </div>
        <div class="one">
            <img src="../../images/number.png">
            <input type="text" required placeholder="Телефон" name="phone">
        </div>
        <div class="one">
            <img src="../../images/e-mail.png">
            <input type="text" required placeholder="E-mail" name="mail">
        </div>
        <div class="one">
            <img src="../../images/lock.png">
            <input type="password" required placeholder="Пароль" name="password"><br><br>
        </div>
        <div>
            <c:set var="registrationError" value="${requestScope.registrationError}"/>
            <div>${registrationError}</div>
        </div>
        <input class="button" type="submit" value="Регистрация">
    </form>

</main>

<footer>
    <nav>
        <a href="/">ГЛАВНАЯ</a>
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
