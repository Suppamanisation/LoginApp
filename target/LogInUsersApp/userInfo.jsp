<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<html>
<head>
    <title>Информация</title>
</head>
<body>
<form name="userInfo" method="post" action="login">
    <h4>Информация о пользователе</h4>
    <br>
    Имя: <jsp:getProperty name="user" property="firstName"/><br>
    Фамилия: <jsp:getProperty name="user" property="lastName"/><br>
    <c:if test="${user.sex != 'нет'}">
        Пол: <jsp:getProperty name="user" property="sex"/><br>
    </c:if>
    Адрес: <jsp:getProperty name="user" property="address"/>
    <br>
    <br>
    <c:if test="${user.isAdmin}">
        <h4>Пользователи:</h4>
        <table>
            <tr>
                <td>№</td>
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Пол</td>
                <td>Адрес</td>
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
    <input type="submit" name="logOut" value="Выход">
</form>
</body>
</html>
