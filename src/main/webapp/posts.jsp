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
    <title>Posts</title>
</head>
<body>

<c:if test="${not empty requestScope.posts}">
    <c:forEach items="${requestScope.posts}" var="post">
        <p>${ post.getText() }</p>
        <p>${ post.getDate() }</p>
        <p>${ post.getUser().getLogin() }</p>

        <c:url var="deleteUrl" value="/delete-post">
            <c:param name="id" value="${ post.getId() }"/>
        </c:url>

        <c:if test="${ post.getUser().equals(sessionScope.user) or sessionScope.user.isAdmin() }">
            <p>
                <a href="${ deleteUrl }">Usuń post</a>
            </p>
        </c:if>
    </c:forEach>
</c:if>

</body>
</html>