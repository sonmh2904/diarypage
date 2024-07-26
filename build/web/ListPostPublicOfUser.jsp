<%-- 
    Document   : ListPostPublicOfUser
    Created on : Mar 17, 2023, 9:37:07 PM
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
                text-align: left;
                padding: 8px;
            }

            th {
                background-color: #428bca;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <h1>LIST POST PUBLIC!</h1>
        <table border="1">
            <tr>

                <td>Account</td>
                <td>Title</td>
                <td>Describe</td>
                <td>Post Date</td>

            </tr>
            <c:forEach  items="${getListPostPublic}" var="item" >
                <tr>
                    <td>${item.getAccount()}</td>
                    <td>${item.getCname()}</td>
                    <td>${item.getDescribe()}</td>
                    <td>${item.getPost_date()}</td>
                    <td><a href="postdetail?&pid=${item.getPid()}">Detail</a>
                        <c:if test="${sessionScope.acc eq item.getAccount().trim()}">
                            <a class="a1" href="post?&pid=${item.getPid()}&mode=updatepost" >Update</a>    
                            <a class="a1" href="post?&pid=${item.getPid()}&mode=deletepost" >Delete</a>
                        </c:if>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
