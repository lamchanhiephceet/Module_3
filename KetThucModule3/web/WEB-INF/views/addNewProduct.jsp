<%--
  Created by IntelliJ IDEA.
  User: HN
  Date: 9/4/2020
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Product</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Product Management</h1>
    <h2>
        <a href="/demo?action=listProduct">List All Products</a>
    </h2>
</div>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Product</h2>
            </caption>
            <tr>
                <th>Product Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Product Price:</th>
                <td>
                    <input type="text" name="price" id="price" size="45"/>
                </td>
            </tr>
            <tr>
                <th>description:</th>
                <td>
                    <input type="text" name="description" id="description" size="15"/>
                </td>
            </tr>
            <tr>
                <th> Category: </th>
                <td>
                    <div class="form-group">
                        <select name="category" id="category">
                            <c:forEach items='${requestScope["categories"]}' var="category">
                                <option value="${category.getId()}" <c:if test="${category.getName() == product.getCategory()}"> selected </c:if> > ${category.getName()}</option>
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

