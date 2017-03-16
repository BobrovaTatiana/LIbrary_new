<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
    <title>Страница пользователя</title>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Tanya's library</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav" role="search">
                <%--<li><a href="/library/list">Список книг</a></li>--%>
                <%--<li><a href="#about">About</a></li>--%>
                <%--<li><a href="#contact">Contact</a></li>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="navbar-brand" href="/library/profile"><c:out value="${username}"></c:out></a></li>
                <li class="active"><a href="/library/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <div style="top: 10%; width: 100%; height: 80%">
            <br>
        <h4>Профайл пользователя</h4>
        <br><br>
        <%--смена пароля--%>
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                Изменить пароль
            </button><br><br>
            <c:out value="${msg}"></c:out>

            <form action="/library/profile" method="post">
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Изменение пароля</h4>
                            </div>

                            <div class="modal-body">
                                <input required type="password" maxlength="15" size="30" name="psw" id="psw" value="" placeholder="Введите пароль">
                                <br><br>
                                <input required type="password" maxlength="15" size="30" name="psw1" id="psw1" value="" placeholder="Введите новый пароль">
                                <br><br>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                                <input class="btn btn-default" type="submit" value="Сохранить" formmethod="post">
                            </div>
                        </div>
                    </div>
                </div>
            </form><br><br>

        <p><a class="btn btn-primary btn-lg" href="/library/history" role="button">Ваша библиотека</a></p>
        </div>
    </div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>