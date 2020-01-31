<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
<head>
    <title>Здравствуйте</title>
</head>
<body>
<h1 align="center">Добро пожаловать!</h1>
<h4 align="center">
<form name="login" action="login" method="post">
    Имя пользователя:<br>
    <input type="text" name="userName">
    <br>
    Пароль:<br>
    <input type="password" name="userPassword">
    <br>
    <input type="submit" name="registration" value="Регистрация">
    <input type="submit" name="logIn" value="Вход">
</form>
<c:if test="${error != null}">
    <font color="red">${error}</font><br>
</c:if>
</h4>
</body>
</html>


