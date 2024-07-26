<%-- 
    Document   : AddPost
    Created on : Mar 9, 2023, 9:00:46 AM
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
                margin: 20px auto;
                max-width: 600px;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            input[type="text"], textarea {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            select {
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #3e8e41;
            }

            p {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>ADD POST</h1>
        <form action="post" method="post">
            Title: 
            <select name="title">
                <c:forEach items="${listTitle}" var="i">
                    <option>${i.getName()}</option>
                </c:forEach>
            </select>
            Status:
            <select name="status">
                <option value="0">Public</option>
                <option value="1">Private</option>
            </select>
            <br>Describe: <input type="text" name="describe">
            <br>Content: 
            <textarea name="content" rows="4" cols="50"></textarea>
            <br> <input type="submit" name="addpost" value="Add New Post">
            <p>${error}</p>
        </form>

    </body>
</html>
