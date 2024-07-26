<%-- 
    Document   : ListPost
    Created on : Mar 9, 2023, 9:56:13 AM
    Author     : doanq
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LIST POST ALL!</h1>
        
            <table border="1">
                <tr>
                    <td>Id Post</td>
                    <td>Account</td>
                    <td>Title</td>
                    <td>Post Date</td>
                    <td>Status</td>
                </tr>

                <c:forEach  items="${listPostAll}" var="item" >
                    <form action="postmanagement?&pid=${item.getPid()}&mode=adminupdatepost" method="post">
                    <tr>
                        <td>${item.getPid()}</td>
                        <td>${item.getAccount()}</td>
                        <td>${item.getCname()}</td>
                        <td>${item.getPost_date()}</td>
                        <td>
                            <input type="radio" name="${item.getPid()}" value="0" ${item.getStatus() == 'Public' ? 'checked' : ''}>
                            <label>Public</label>
                            <input type="radio" name="${item.getPid()}" value="1" ${item.getStatus() == 'Private' ? 'checked' : ''}>
                            <label>Private</label>
                            <input type="radio" name="${item.getPid()}" value="2" ${item.getStatus() == 'Disable' ? 'checked' : ''}>
                            <label>Disable</label>
                            <input type="submit" value="UPDATE">
                        </td>
                        
                    </tr>
                    </form>
                </c:forEach>
            </table>
        

    </body>
</html>
