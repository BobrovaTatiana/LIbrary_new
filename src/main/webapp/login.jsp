<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div>--%>
<%--<a href="/library/registration">Регистрация</a>--%>
<%--<form action="/library/login" method="post">--%>
<%--<label for="login">Login:</label>--%>
<%--<input type="text" name="login" id="login" value="" placeholder="логин">--%>
<%--<label for="password">Password:</label>--%>
<%--<input type="password" name="password" id="password" value="" placeholder="пароль">--%>

<%--<input type="submit" value="Submit" formmethod="post">--%>
<%--</form>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>


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
    <title>Страница авторизации</title>
</head>
<body>
<div class="jumbotron">
    <div class="container">

        <h2>Страница авторизации</h2>
        <form action="/library/login" method="post">
            <p><b>Логин:</b><br>
                <input type="text" name="login" id="login" size="30"></p>

            <p><b>Пароль:</b><br>
                <input type="password" name="password" id="password" size="30"></p>
            <p><input class="btn btn-primary btn-lg" type="submit" value="ОK" formmethod="post"></p>
            <%--<p><a class="btn btn-primary btn-lg" role="button" formmethod="post">OK</a></p>--%>
            </p>
        </form>
        <p><a class="btn btn-primary btn-lg" href="registration.jsp" role="button">Регистрация</a></p>

        <%--<p><input type="submit" value="Регистрация"--%>
                  <%--onclick="window.location='registration.jsp';"/>--%>
        <%--</p>--%>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>
