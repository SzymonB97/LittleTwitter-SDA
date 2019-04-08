<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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

<section>
    <h1>Zarejestruj się</h1>
    <form action="${ pageContext.request.contextPath }/registration" method="post">
        <input type="text" name="login" placeholder="Login">
        <input type="password" name="password" placeholder="Hasło">
        <input type="password" name="repeatPassword" placeholder="Powtórz hasło">
        <input type="submit" value="Zarejestruj się">
    </form>
</section>

<jsp:include page="message.jsp">
    <jsp:param name="message" value="${ requestScope.message.value }"/>
    <jsp:param name="type" value="${ requestScope.message.type }"/>
</jsp:include>

</body>
</html>