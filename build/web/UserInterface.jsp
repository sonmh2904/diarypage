<%-- 
    Document   : UserInterface
    Created on : Mar 6, 2023, 8:08:29 PM
    Author     : doanq
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .sidebar {
                background-color: #f1f1f1;
                height: 100%;
                padding: 15px;
            }

            /* Thiết lập các thuộc tính cho menu trong sidebar */
            .sidebar h3 {
                margin-top: 0px;
                text-align: center;
            }

            .sidebar ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
            }

            .sidebar li {
                margin: 10px 0;
                font-size: 18px;
            }

            .sidebar li a {
                text-decoration: none;
                color: black;
            }

            /* Thiết lập các thuộc tính cho header */
            .header {
                background-color: #3498db;
                color: white;
                text-align: center;
                padding: 15px;
            }

            .header h3 {
                margin-top: 0px;
            }

            /* Thiết lập các thuộc tính cho content */
            .content {
                padding: 15px;
            }

            /* Thiết lập các thuộc tính cho các form input */
            

            /* Thiết lập các thuộc tính cho button */
            form input[type="submit"] {
                background-color: #3498db;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            /* Thiết lập các thuộc tính cho table */
            table {
                border-collapse: collapse;
                width: 100%;
            }

            table th,
            table td {
                text-align: left;
                padding: 8px;
            }

            table th {
                background-color: #3498db;
                color: white;
            }

            table tr:nth-child(even) {
                background-color: #f2f2f2;
            }


        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 sidebar">
                    <h3>Menu</h3>
                    <div class="row">
                        <div col-md-6>
                            <ul>
                                <li style="margin-left: 20px"><a href="post?&mode=listpostofuser">Danh sách bài viết</a></li>
                                <li style="margin-left: 20px"><a href="post?&mode=addpost">Thêm bài viết</a></li>
                                <li style="margin-left: 20px"><a href="profile?&mode=profile">Profile</a></li>
                            </ul>
                        </div>
                        <div col-md-6>
                            <ul>
                                <li style="margin-left: 20px"><a href="post?&mode=listpostpersonal">Bài viết cá nhân</a></li>
                                <li style="margin-left: 20px"><a href="post?&mode=logout">LOG OUT</a></li>
                            </ul>
                        </div>
                    </div>

                </div>
                <div class="col-md-9 header">
                    <h3>Trang Nhật Ký Online</h3>

                </div>
            </div>
            <div class="row">
                <div class="col-md-3 test">
                    
                    <%@include file="SearchOfUser.jsp" %>
                </div>
                <div class="col-md-9 content">
                    <c:if test="${mode=='listpostofuser'}" >
                        <%@include file="ListPostPublicOfUser.jsp" %>
                    </c:if>
                    <c:if test="${mode=='addpost'}" >
                        <%@include file="AddPost.jsp" %>
                    </c:if>

                    <c:if test="${mode=='profile'}">
                        <%@include file="Profile.jsp" %>
                    </c:if>

                    <c:if test="${mode=='listpostpersonal'}" >
                        <%@include file="ListPostPersonal.jsp" %>

                    </c:if>


                    <c:if test="${mode=='updatepost'}" >
                        <%@include file="UpdatePostByUser.jsp" %>
                    </c:if> 

                </div>
            </div>
        </div>


    </body>
</html>