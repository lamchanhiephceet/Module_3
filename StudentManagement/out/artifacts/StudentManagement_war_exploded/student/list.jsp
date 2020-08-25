
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
          integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
    <title>Student manager</title>
    <style>
        th{
            color:darkcyan;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row"><p></p></div>
    <div class="row">
        <div class="container-fluid">
            <a href="students?action=create" class="btn btn-info">
                <i class="fas fa-plus"></i> Add new student
            </a>
<%--            <form class="form-inline ml-auto float-right" method="post" action="/products?action=search">--%>
<%--                <input name="search" type="text" class="form-control mr-sm-2" placeholder="Search">--%>
<%--                <button type="submit" class="btn btn-primary">Search</button>--%>
<%--            </form>--%>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr class="bg-secondary">
                <th>Student Code</th>
                <th>Student Name</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items='${requestScope["listStudent"]}' var="students"><%--@elvariable id="student" type=""--%>
                <tr>
                    <td>${students.getCode()}</td>
                    <td>${students.getName()}</td>
                    <td>${students.getAddress()}</td>
                    <td>
                        <a href="/students?action=edit&id=${students.id}" type="button" class="btn btn-primary"><i class="fas fa-pencil-alt"></i></a>
                        |
                        <a href="/students?action=delete&id=${students.id}" type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>