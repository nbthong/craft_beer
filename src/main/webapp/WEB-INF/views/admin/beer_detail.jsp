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

    <title>BEER</title>
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
        <h4 class="pt-5 pt-sm-5 pt-md-5 pt-lg-5 pt-xl-5">BEER DETAIL</h4>

        <c:choose>
            <c:when test="${msg == 'Successfully'}">
                <div class="alert alert-success">Successfully</div>
            </c:when>
            <c:when test="${msg == 'Invalid'}">
                <div class="alert alert-danger">Invalid</div>
            </c:when>
        </c:choose>

        <div class="text-center">
            <form action="/admin/updateBeer" method="post">
                <input hidden name="id" value=${beer.id}>
                <table style="border: none; margin: 0 auto" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6">
                    <tr>
                        <td>Category</td>
                        <td><select class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="category">
                            <c:forEach items="${categoryList}" var="category">
                                <c:if test="${category.id == beer.categoryId}">
                                    <option value="${category.id}" selected>${category.name}</option>
                                </c:if>
                                <c:if test="${category.id != beer.categoryId}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="name"
                                   id="name" placeholder="Enter the name" value="${beer.name}" required></td>
                    </tr>
                    <tr>
                        <td>Manufacturer</td>
                        <td><input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="manufacturer"
                                   id="manufacturer" placeholder="Enter the manufacturer" value="${beer.manufacturer}" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="country"
                                   id="country" placeholder="Enter the country" value="${beer.country}" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="number" step="any" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="price"
                                   id="price" placeholder="Enter the price" value="${beer.price}" required></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" name="description"
                                   id="description" placeholder="Enter the description" value="${beer.description}" required></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>${beer.status}</td>
                    </tr>
                </table>
                <div class="pt-4 pt-sm-4 pt-md-4 pt-lg-4 pt-xl-4">
                    <button type="submit" class="btn btn-primary col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6">Update</button>
                </div>
            </form>
            <c:if test="${beer.status == 'available'}">
                <form action="/admin/archiveBeer/${beer.id}" method="get">
                    <button class="btn col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6 btn-danger" type="submit"
                            onclick="{return confirmComplete();}">Archive</button>
                </form>
            </c:if>
            <c:if test="${beer.status == 'archive'}">
                <form action="/admin/activeBeer/${beer.id}" method="get">
                    <button class="btn btn-success col-12 col-sm-8 col-md-8 col-lg-6 col-xl-6" type="submit"
                            onclick="{return confirmComplete();}">Active</button>
                </form>
            </c:if>
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
