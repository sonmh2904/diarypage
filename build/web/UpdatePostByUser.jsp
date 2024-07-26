<%-- 
    Document   : UpdatePostByUser
    Created on : Mar 17, 2023, 10:40:49 PM
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
        <h1>Update POST</h1>
        <form action="post?&pid=${postdetail.getPid()}" method="post">
            Title: <input type="text" value="${postdetail.getCname()}" readonly>
            Status:<br>
            Public:
            <input type="radio" name="status" value="0" ${postdetail.getStatus() == 0 ? 'checked' : ''}>
            <br>
            Private:
            <input type="radio" name="status" value="1" ${postdetail.getStatus() == 1 ? 'checked' : ''}>
            
            <br>Describe: <input type="text" value="${postdetail.getDescribe()}" name="describe" >
            <br>Content: 
            <textarea name="content" rows="4" cols="50">${postdetail.getContent()}</textarea>
            <br> <input type="submit" name="postupdate" value="UPDATE POST">
            <p>${error}</p>
        </form>
    </body>
</html>
