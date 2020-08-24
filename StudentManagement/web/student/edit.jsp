<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Student Management</h1>
    <h2>
        <a href="students?action=students">List All Student</a>
    </h2>
</div>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Student
                </h2>
            </caption>
            <c:if test="${students != null}">
                <input type="hidden" name="id" value="${students.getId()}"/>
            </c:if>
            <tr>
                <th>Student Code:</th>
                <td>
                    <input type="text" name="code" size="45"
                           value="${students.getCode()}"/>
                </td>
            </tr>
            <tr>
                <th>Student Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="${students.getName()}"/>
                </td>
            </tr>
            <th>Address:</th>
            <td>
                <input type="text" name="address" size="15"
                       value="<c:out value='${students.getAddress}' />"
                />
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