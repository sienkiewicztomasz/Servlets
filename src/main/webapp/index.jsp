<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/style.css">
    <title>Hello JSP</title>
</head>
<body>

<h1>Strona główna</h1>

<jsp:include page="message.jsp">
    <jsp:param name="message" value="${ requestScope.message.value }"/>
    <jsp:param name="type" value="${ requestScope.message.type }"/>
</jsp:include>

<c:choose>
    <c:when test="${ empty sessionScope.user }">
        <section>
            <form action="${ pageContext.request.contextPath }/login" method="post">
                <input type="text" name="login" placeholder="Login">
                <input type="password" name="password" placeholder="Hasło">
                <input type="submit" value="Loguj">
            </form>
        </section>
        <a href="${ pageContext.request.contextPath }/registration">Zarejestruj się</a>
    </c:when>
    <c:otherwise>
        <a href="${ pageContext.request.contextPath }/logout">Wyloguj</a>
    </c:otherwise>
</c:choose>

<c:if test="${ sessionScope.user.isAdmin() }">
    <a href="${pageContext.request.contextPath}/users">Wyświetl użytkowników</a>
</c:if>

<c:if test="${ not empty sessionScope.user }">
    <form action="${pageContext.request.contextPath}/add-post" method="post">
        <textarea name="text" placeholder="Napisz coś"></textarea>
        <input type="submit" value="Wyślij">
    </form>
</c:if>

<jsp:include page="/posts"/>


</body>
</html>