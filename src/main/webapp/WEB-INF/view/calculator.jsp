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
    <link rel="stylesheet" type="text/css" href="../../styles/calculator.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>
<body>


<header>
    <div class="logo">
        <a href="/">
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
            <a href="#">КАЛЬКУЛЯТОР</a>
            <a href="">КОНТАКТЫ</a>
            <a href="#" id="menu" class="icon">&#9776;
        </div>
    </nav>
</header>

<main>
    <div>
        <form method="post" action="/calculator" class="forma">
            <div class="one">
                <lable>Город отправления</lable>
                <select name="cityFrom">
                    <option disabled selected>Выберите город</option>
                    <c:forEach var="cityFrom" items="${requestScope.cities}">
                        <option c:out value="${cityFrom.id}">${cityFrom.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="one">
                <lable>Город доставки</lable>
                <select name="cityTo">
                    <option disabled selected>Выберите город</option>
                    <c:forEach var="cityTo" items="${requestScope.cities}">
                        <option c:out value="${cityTo.id}">${cityTo.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="one">
                <lable>Вид доставки</lable>
                <select name="cargo">
                    <option disabled selected>Вид отправления</option>
                    <option c:out value="DOCUMENT">документ</option>
                    <option c:out value="PARSEL">посылка</option>
                    <option c:out value="FREIGHT">груз</option>
                </select>
            </div>
            <div class="one">
                <%--<input type="text" required plFREIGHTaceholder="Имя" name="name" >--%>
                <lable>Вес груза</lable>
                <input type="text" required placeholder="вес" name="weight">
            </div>
            <input class="button" type="submit" value="Посчитать стоимость">
        </form>
    </div>
</main>

<footer>
    <nav>
        <a href="/">ГЛАВНАЯ</a>
        <a href="">О КОМПАНИИ</a>
        <a href="">ТАРИФЫ</a>
        <a href="/direction">НАПРАВЛЕНИЕ</a>
        <a href="#">КАЛЬКУЛЯТОР</a>
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
