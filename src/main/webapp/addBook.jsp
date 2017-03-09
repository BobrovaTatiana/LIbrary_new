<%--
  Created by IntelliJ IDEA.
  User: bot
  Date: 23.02.17
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
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
    <br><h2>Добавление книги</h2>
    <form action="/library/insert" method="post">
        <table class="table">
            <tr>
                <td><label for="name">Book:</label></td>
                <td><input type="text" name="name" id="name" value="" placeholder="Input"></td>
            </tr>
            <tr>
                <td><label for="type">Type:</label></td>
                <td><input type="text" name="type" id="type" value="" placeholder="Input"></td>
            </tr>
            <tr>
                <td><label for="date_pub">dd.mm.yyyy:</label></td>
                <td><input type="date" name="date_pub" id="date_pub" value="" placeholder="Input"></td>
            </tr>
            <tr>
                <td><label for="author">Author:</label></td>
                <td><input type="text" name="author" id="author" value="" placeholder="Input"></td>
            </tr>
            <tr>
                <td><label for="genre">Genre:</label></td>
                <td><input type="text" name="genre" id="genre" value="" placeholder="Input"></td>
            </tr>
            <tr>
                <td><label for="year">Year of publication:</label></td>
                <td><input type="text" name="year" id="year" value="" placeholder="Input"></td>
            </tr>
        </table>

        <input class="btn btn-primary btn-lg" type="Submit" value="Ok" formmethod="post">
    </form>
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

