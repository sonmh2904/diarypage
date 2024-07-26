<%-- 
    Document   : ListCategory
    Created on : Mar 17, 2023, 2:56:15 PM
    Author     : doanq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            table td {
                padding: 10px;
                border: 1px solid #ccc;
            }

            table tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            table tr:hover {
                background-color: #e2e2e2;
            }
        </style>
    </head>
    <body>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>NAME</td>
            </tr>
            <c:forEach items="${listCategory}" var="i">
                <tr>
                    <td>${i.getCid()}</td>
                    <td>${i.getName()}</td>
                    <td><a href="category?&cid=${i.getCid()}&mode=deletecategory">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
