
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
            <%--<ul class="nav navbar-nav navbar-right">--%>
                <%--<li><a class="navbar-brand" href="/library/profile"><c:out value="${username}"></c:out></a></li>--%>
                <%--<li class="active"><a href="/library/logout">Logout</a></li>--%>
            <%--</ul>--%>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
    <form action="/library/registration" method="post">

            <div class="input-group">
                <span for="firstname" class="input-group-addon" >Firstname:</span>
                <input required type="text" class="form-control" placeholder="Input" aria-describedby="basic-addon1" id="firstname" name="firstname" >
            </div>

            <div class="input-group">
                <span for="lastname" class="input-group-addon" >Lastname:</span>
                <input required type="text" class="form-control" placeholder="Input" aria-describedby="basic-addon1" id="lastname" name="lastname" >
            </div>
            <div class="input-group">
                <span for="login" class="input-group-addon" >Login:</span>
                <input required type="text" class="form-control" placeholder="Input" aria-describedby="basic-addon1" id="login" name="login" >
            </div>
            <div class="input-group">
                <span for="password" class="input-group-addon" >Password:</span>
                <input required type="text" class="form-control" placeholder="Input" aria-describedby="basic-addon1" id="password" name="password" >
            </div>
            <div class="input-group">
                <span for="email" class="input-group-addon" >E-MAIL:</span>
                <input required type="text" class="form-control" placeholder="Input" aria-describedby="basic-addon1" id="email" name="email" >
            </div>





    <%--<label for="firstname">Firstname:</label>--%>
        <%--<input type="text" name="firstname" id="firstname" value="" placeholder="Input">--%>

        <%--<label for="lastname">Lastname:</label>--%>
        <%--<input type="text" name="lastname" id="lastname" value="" placeholder="Input">--%>

        <%--<label for="login">Login:</label>--%>
        <%--<input type="text" name="login" id="login" value="" placeholder="Input">--%>

        <%--<label for="password">Password:</label>--%>
        <%--<input type="password" name="password" id="password" value="" placeholder="Input">--%>

        <%--<label for="email">E-MAIL:</label>--%>
        <%--<input type="text" name="email" id="email" value="" placeholder="Input">--%>

        <input class="btn btn-primary btn-lg" type="submit" value="Submit" formmethod="post">
    </form>
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
