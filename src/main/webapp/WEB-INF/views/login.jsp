<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thongnguyen
  Date: 1/24/19
  Time: 04:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/static/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/static/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/static/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
</head>
<body>
<div class="container">
    <form action="/loginAction" method="post" id="loginAction">

        <div class="text-center">
            <div class="pt-5 pt-sm-5 pt-md-5 pt-lg-5 pt-xl-5">
                <input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="username"
                       id="username" placeholder="Enter username" required>
            </div>
            <div class="pt-2 pt-sm-2 pt-md-2 pt-lg-2 pt-xl-2">
                <input type="password" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="password"
                       id="password" placeholder="Enter password" required>
            </div>
            <div class="pt-2 pt-sm-2 pt-md-2 pt-lg-2 pt-xl-2">
                <button type="submit" class="btn btn-primary col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6">Login</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
