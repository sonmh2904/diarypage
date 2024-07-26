<%-- 
    Document   : ListPostPersonal
    Created on : Mar 17, 2023, 10:23:17 PM
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
                margin-top: 20px;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            tr:hover {
                background-color: #f5f5f5;
            }

            .a1 {
                display: inline-block;
                margin-right: 5px;
                padding: 5px 10px;
                background-color: #4CAF50;
                color: #fff;
                border-radius: 3px;
                text-decoration: none;
            }

            .a1:hover {
                background-color: #3e8e41;
            }
        </style>
    </head>
    <body>
        <h1>LIST POST PERSONAL!</h1>
        <table border="1">
            <tr>

                <td>Account</td>
                <td>Title</td>
                <td>Describe</td>
                <td>Post Date</td>
                <td>Status</td>

            </tr>
            <c:forEach  items="${listPostPersonal}" var="item" >
                <tr>
                    <td>${item.getAccount()}</td>
                    <td>${item.getCname()}</td>
                    <td>${item.getDescribe()}</td>
                    <td>${item.getPost_date()}</td>
                    <td>${item.getStatus()}</td>
                    <td style="padding: none; text-align: none">
                        <c:if test="${item.getStatus()=='Private' or item.getStatus()=='Public'}">
                            <a class="a1" href="postdetail?&pid=${item.getPid()}&mode=postdetail" >Detail</a>
                            
                            
                            <a class="a1" href="post?&pid=${item.getPid()}&mode=updatepost" >Update</a>    
                            <a class="a1" href="post?&pid=${item.getPid()}&mode=deletepost" >Delete</a>
                        </c:if>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
