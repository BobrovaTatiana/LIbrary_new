<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Tanya's library</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav" role="search">
                <li><a href="/library/list">Список книг</a></li>
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

<div class="container">
    <div class="jumbotron">
        <div>
                <br><h2><c:out value="${book.name}"></c:out></h2>

                <output name="result"><c:out value="${text}"></c:out></output></p>
                <br>
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>

        <!-- Комментарий к книге -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            Добавить комментарий и оценку
        </button>

        <form action="/library/text" method="post">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Комментарий и оценка</h4>
                    </div>

                    <div class="modal-body">
                        <select size="6" name="rating">
                            <option disabled>Выберите оценку</option>
                            <option value="1">1</option>
                            <option selected value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>

                        <%--<input type="text" name="rating" id="rating" value="" placeholder="Оценка">--%>
                        <br><br>
                        <textarea rows="3" cols="50" type="text" name="comment" id="comment" value="" placeholder="Комментарий"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <%--<button type="button" class="btn btn-primary" type="submit" formmethod="post">Сохранить</button>--%>
                        <input class="btn btn-default" type="submit" value="Сохранить" formmethod="post">
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        </form>

    </div>
</div>

</body>
</html>
