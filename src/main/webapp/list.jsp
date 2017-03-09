<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="jumbotron">
    <div class="container">
<table width="100%">
    <tr>
        <div style="position: fixed; top: 0%; right: 2%; color: royalblue; width: 50%; height: 10%">
            <p align="right"><c:out value="${username}"></c:out></p>
            <p align="right"><input type="submit" value="Logout" onclick="window.location='/library/logout';"> </p>
        </div>
    </tr>
</table>

<div style="top: 10%; width: 100%; height: 80%">
    <br><h2>Список книг</h2>
    <table class="table" border="1" width="100%" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Type publication</th>
            <th>Name</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Actions</th>
        </tr>

        <c:forEach items="${listBook}" var="book">

            <tr>
                <td><c:out value="${book.id_book}"></c:out></td>
                <td><c:out value="${book.type_publication}"></c:out></td>
                <td><c:out value="${book.name}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.genre}"></c:out></td>

                <td><a href="/library/edit?id=${book.id_book}">edit</a>
                    <a href="/library/delete?id=${book.id_book}">del</a></td>
            </tr>

        </c:forEach>
    </table>
    <br>
    <a href="/library/insert">Добавить книгу</a>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    </div>
</div>
</body>
</html>
