<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: thongnguyen
  Date: 8/10/18
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CATEGORY</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/static/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/static/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/static/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
    <script>
        function confirmComplete() {
            var answer=confirm("Are you sure you want to continue");
            if (answer==true)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    </script>
</head>

<body>

<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/admin/beer">CRAFT BEER</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/beer">BEER</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/category">CATEGORY</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/administrator">ADMINISTRATOR</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/logout">LOGOUT</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="pt-5 pt-sm-5 pt-md-5 pt-lg-5 pt-xl-5 pb-5 pb-sm-5 pb-md-5 pb-lg-5 pb-xl-5">
        <h4 class="pt-5 pt-sm-5 pt-md-5 pt-lg-5 pt-xl-5">NEW CATEGORY</h4>
        <c:choose>
            <c:when test="${msg == 'Successfully'}">
                <div class="alert alert-success">Successfully</div>
            </c:when>
        </c:choose>
        <form action="/admin/newCategory" method="post">
            <div class="text-center">
                <div class="pt-2 pt-sm-2 pt-md-2 pt-lg-2 pt-xl-2">
                    <input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="name"
                           id="name" placeholder="Enter category name" re=""quired>
                </div>
                <div class="pt-2 pt-sm-2 pt-md-2 pt-lg-2 pt-xl-2">
                    <button type="submit" class="btn btn-primary col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6">Add</button>
                </div>
            </div>
        </form>
        <h4 class="pt-5 pt-sm-5 pt-md-5 pt-lg-5 pt-xl-5">CATEGORY LIST</h4>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>Index</th>
                    <th>Name</th>
                    <th>Amount beer</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="category" varStatus="status">
                    <form action="/admin/updateCategory" method="post">
                        <input hidden name="id" value=${category.id}>
                        <tr>
                            <td>${status.index + 1}</td>
                            <td><input type="text" name="name" value="${category.name}"></td>
                            <td>${category.amountBeer}</td>
                            <td>
                                <button class="btn btn-sm btn-success" type="submit"
                                        onclick="{return confirmComplete();}">Update</button>
                            </td>
                    </form>
                            <td>
                            <c:if test="${category.amountBeer > 0}">
                                <button type="button" class="btn btn-danger btn-sm" disabled>Delete</button>
                            </c:if>
                            <c:if test="${category.amountBeer == 0}">
                                <form action="/admin/deleteCategory/${category.id}" method="get">
                                    <button class="btn-sm btn-danger" type="submit"
                                    onclick="{return confirmComplete();}">Delete</button>
                                </form>
                            </c:if>
                            </td>
                        </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- /.container -->

<!-- Footer -->
<footer class="py-2 bg-dark fixed-bottom">
    <div class="container" style="padding: 10px">

    </div>
    <!-- /.container -->
</footer>

</body>

</html>
