<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
          integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
</head>
<body>
<div style="text-align: center;">
    <h1>Edit Product</h1>
    <h2>
        <a href="products?action=products">List All Product</a>
    </h2>
</div>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">

            <c:if test="${product != null}">
                <input type="hidden" name="id" value="${product.getId()}"/>
            </c:if>
            <tr>
                <th>Product Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="${product.getName()}"/>
                </td>
            </tr>
            <tr>
                <th>Product price:</th>
                <td>
                    <input type="text" name="price" size="45"
                           value="${product.getPrice()}"/>
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td>
                    <input type="text" name="quantity" size="15"
                           value="${product.getQuantity()}"/>
                </td>
            </tr>
            <tr>
                <th>Color:</th>
                <td>
                    <input type="text" name="color" size="15"
                           value="${product.getColor()}"/>
                </td>
            </tr>
            <tr>
                <th>description:</th>
                <td>
                    <input type="text" name="description" size="15"
                           value="${product.getDescription()}"/>
                </td>
            </tr>
            <tr>
                <th>Category:</th>
                <td>
                    <div class="form-group">
                        <select name="category" id="category">
                            <c:forEach items='${requestScope["categories"]}' var="category">
                                <option value="${category.getName()}" <c:if test="${category.getName() == product.getCategory()}"> selected </c:if> > ${category.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>