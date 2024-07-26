<%-- 
    Document   : SearchUser
    Created on : Mar 17, 2023, 4:35:01 PM
    Author     : doanq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            form {
                display: inline-block;
                margin: 10px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            input[type="text"] {
                padding: 5px;
                margin: 5px;
                border-radius: 5px;
                border: 1px solid #ccc;
                width: 200px;
            }

            input[type="submit"] {
                padding: 5px 10px;
                border-radius: 5px;
                border: none;
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <form action="usermanagement?&mode=search" method="post">
            Search: <input type="text" name="key" placeholder="Account,Status">
            <br><input type="submit" value="Search">
        </form>
    </body>
</html>
