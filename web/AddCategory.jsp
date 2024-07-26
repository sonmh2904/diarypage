<%-- 
    Document   : AddCategory
    Created on : Mar 17, 2023, 5:03:18 PM
    Author     : doanq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1 {
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 10px;
            }

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
        <h1>Add Category</h1>
        <form action="category?&mode=addcategory" method="post">
            <input type="text" name="addcategory">
            <br><input type="submit" value="Add New">
        </form>

    </body>
</html>
