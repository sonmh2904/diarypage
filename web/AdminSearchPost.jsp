<%-- 
    Document   : AdminSearchPost
    Created on : Mar 17, 2023, 5:49:55 PM
    Author     : doanq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="filter?&mode=adminsearchpost" method="get">
            <h1>Select</h1>
            <input type="text" name="key" placeholder="Title,Describe">
            <br>Từ ngày: <input type="date" name="fromdate">
            <br>Đến ngày: <input type="date" name="todate">
            <br><input type="submit" value="Find">
        </form>
    </body>
</html>
