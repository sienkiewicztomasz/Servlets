<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${ requestScope.posts }" var="post">
    <h3>${ post.text }</h3>
    <h3>${ post.createdDate }</h3>
    <h4>${ post.user.login }</h4>

    <c:url var="deleteUrl" value="/delete-post">
        <c:param name="id" value="${ post.id }" />
    </c:url>
    <c:url var="editUrl" value="/edit-post">
        <c:param name="id" value="${ post.id }" />
    </c:url>

    <c:if test="${ sessionScope.user.equals(post.user) || sessionScope.user.isAdmin() }">
        <a href="${ deleteUrl }">Usuń post</a>
        <a href="${ editUrl }">Edytuj post</a>
    </c:if>

</c:forEach>
