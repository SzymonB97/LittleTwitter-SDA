<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Post</title>
</head>
<body>

<section>
    <form action="${ pageContext.request.contextPath }/add-post" method="post">
        <label>
            <textarea name="text" placeholder="Enter your text"></textarea>
        </label>
        <button type="submit">Dodaj</button>
    </form>
</section>

</body>
</html>