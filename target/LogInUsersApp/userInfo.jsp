<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<html>
<head>
    <title>Информация</title>
</head>
<body>
<form name="userInfo" method="post" action="login">
    <h1 align="center">Информация о пользователе</h1>
    <br>
    <p align="center">
        Логин: <jsp:getProperty name="user" property="name"/><br>
        Имя: <jsp:getProperty name="user" property="firstName"/><br>
        Фамилия: <jsp:getProperty name="user" property="lastName"/><br>
    <c:if test="${user.sex != 'нет'}">
        Пол: <jsp:getProperty name="user" property="sex"/><br>
    </c:if>
    Место работы: <jsp:getProperty name="user" property="address"/>
    <br>
    <br>
    <c:if test="${user.isAdmin}">
        <h4 align="center">Пользователи:</h4>
        <table align="center"border="1" width="50%">
            <tr>
                <td>№</td>
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Пол</td>
                <td>Место работы</td>
            </tr>
            <c:forEach var="tempUser" items="${userList}" varStatus="сounter">
                <tr>
                    <td>${сounter.count}</td>
                    <td><c:out value="${tempUser.firstName}"/></td>
                    <td><c:out value="${tempUser.lastName}"/></td>
                    <td><c:out value="${tempUser.sex}"/></td>
                    <td><c:out value="${tempUser.address}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <h4 align="center">
    <input type="submit" name="logOut" value="Выход">
        <input type="submit" name="edit" value="Редактировать профиль">
    </h4>
    </p>
</form>
</body>
</html>
