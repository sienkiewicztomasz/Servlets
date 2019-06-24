<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <c:url var="editUrl" value="/edit-post">
        <c:param name="id" value="${ requestScope.post.id }" />
    </c:url>

    <section>
        <h1>Edycja posta</h1>
        <form action="${ editUrl }" method="post">
            <textarea name="text" placeholder="Napisz coś">${ requestScope.post.text }</textarea>
            <input type="submit" value="Wyślij">
        </form>
    </section>

    <jsp:include page="message.jsp">
        <jsp:param name="message" value="${ requestScope.message.value }"/>
        <jsp:param name="type" value="${ requestScope.message.type }"/>
    </jsp:include>

</body>
</html>