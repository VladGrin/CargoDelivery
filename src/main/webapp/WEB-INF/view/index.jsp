<%--
  Created by IntelliJ IDEA.
  User: ЭЛЬДОРАДО
  Date: 30.12.2018
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <%--<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">--%>
</head>
<body>

<header>
    <div class="logo">
        <a href="../../WEB-INF/view/index.jsp">
            <img class="graficlogo" src="../../images/logo.png" alt="Logo">
            <p style="color:white;font-size: 35px;font-family: 'Arial';margin-top: 20px;">CargoDelivery</p>
        </a>
        <form method="post" action="" class="form">
            <input type="email" required placeholder="Email" name="login" class="first"><!-- <br> -->
            <input type="password" required placeholder="Password" name="password" class="second">
            <input class="button" type="submit" value="Вход">
            <div class="text">
                <a href="/registration" style="color:white;">регистрация</a>
            </div>
        </form>
    </div>
    <nav>
        <div class="topnav" id="myTopnav">
            <a href="">ГЛАВНАЯ</a>
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
    <img src="../../images/web.jpg" alt="" class="mw-100">
    <div class="advantages__container">
        <div class="advantages">
            <div class="advantages__item">
                <img src="../../images/car1.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>

            <div class="advantages__item">
                <img src="../../images/car3.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>

            <div class="advantages__item">
                <img src="../../images/car6.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>

            <div class="advantages__item">
                <img src="../../images/car4.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>

            <div class="advantages__item">
                <img src="../../images/car5.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>

            <div class="advantages__item">
                <img src="../../images/car2.jpg">
                <h2>Lorem ipsum</h2>
                <p>It looks like nothing was found at this location. Maybe try one of the links below or a search?</p>
            </div>
        </div>
    </div>
</main>

<footer>
    <nav>
        <a href="">ГЛАВНАЯ</a>
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