<%-- 
    Document   : SearchOfUser
    Created on : Mar 17, 2023, 11:01:51 PM
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
            form {
                max-width: 500px;
                margin: 20px auto;
                padding: 20px;
                background-color: #f2f2f2;
                border-radius: 5px;
            }

            h1 {
                margin-top: 0;
            }

            label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
            }

            input[type="text"], input[type="date"], select {
                display: block;
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                margin-top: 5px;
            }

            input[type="submit"] {
                display: inline-block;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 10px;
                margin-right: 10px;
                font-size: 16px;
            }

            input[type="submit"]:hover {
                background-color: #3e8e41;
            }

            .error {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <c:set value="${getListUser}" var="getListUser" />
        <form action="filter" method="post">
            <h1>Search</h1>
            <input type="text" name="key" placeholder="Title,Describe">


            <br>Từ ngày: <input type="date" name="fromdate">
            <br>Đến ngày: <input type="date" name="todate">
            <br>See Other Users' Posts:
            <select name="op">
                <option value="">Select</option>
                <c:forEach items="${getListUser}" var="getListUser">
                    <option>${getListUser.getAccount()}</option>
                </c:forEach>
            </select>
            <br><input type="submit" value="Find">
        </form>
    </body>
</html>
