<%-- 
    Document   : ListUser
    Created on : Mar 6, 2023, 10:31:16 PM
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

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <table border="1">
            <tr>
                <td>Account</td>
                <td>Password</td>
                
                <td>Status</td>
            </tr>
            <c:forEach items="${listuser}" var="item">
                <tr>
                    <td><a href="usermanagement?&account=${item.getAccount()}&mode=userdetail">${item.getAccount()}</a></td>
                    <td>${item.getPassword()}</td>
                    
                    <td>${item.getStatus()}</td>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

