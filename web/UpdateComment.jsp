<%-- 
    Document   : UpdateComment
    Created on : Mar 21, 2023, 4:34:37 PM
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
        <form action="comment?&cmid=${cm.getCmid()}" method="post">
            Account: <input type="text"  name="account" value="${cm.getAccount()}" readonly>
            <br>Content: <input type="text" name="content" value="${cm.getContent()}">
            <br>Comment-Date: <input type="text" value="${cm.getCm_date()}" readonly>
            <br><input type="submit" value="Update Comment" name="updatecomment">
        </form>

    </body>
</html>
