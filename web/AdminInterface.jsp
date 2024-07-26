<%-- 
    Document   : AdminInterface
    Created on : Mar 6, 2023, 8:07:27 PM
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
            .header {
                background-color: #333;
                color: #fff;
                text-align: center;
                padding: 10px;
            }

            /* Định dạng cho phần menu */
            .menu {
                background-color: #f2f2f2;
                border-bottom: 1px solid #ccc;
                padding: 10px;
            }

            .menu ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
            }

            .menu li {
                float: left;
            }

            .menu li a {
                display: block;
                color: #333;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            .menu li a:hover {
                background-color: #ddd;
            }

            /* Định dạng cho phần content */
            .content {
                padding: 20px;
            }

            /* Định dạng cho phần footer */
            .footer {
                background-color: #333;
                color: #fff;
                text-align: center;
                padding: 10px;
            }
            .col-md-3{
                background-color: grey;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Quản lý người dùng</h1>
        </div>
        <div class="menu">
            <ul>
                <li><a href="usermanagement?&mode=listuser">Danh sách người dùng</a></li>
                <li><a href="category?&mode=listcategory">Danh sách Category</a></li>
                <li><a href="Login.jsp">LOG OUT</a></li>
            </ul>
        </div>
        <div class="content">
            <!-- Nội dung của trang -->
            <div class="row">
                <div class="col-md-3">
                    <c:if test="${mode == 'listuser'}">
                        <%@include file="SearchUser.jsp" %>
                    </c:if>
                    <c:if test="${mode == 'listcategory'}">
                        <%@include file="AddCategory.jsp" %>
                    </c:if> 
                </div>
                <div class="col-md-9">
                    <c:if test="${mode == 'listuser'}">
                        <%@include file="ListUser.jsp" %>
                    </c:if>
                    <c:if test="${mode == 'listcategory'}">
                        <%@include file="ListCategory.jsp" %>
                    </c:if>    
                    <c:if test="${mode == 'userdetail'}">
                        <%@include file="UserDetail.jsp" %>
                    </c:if>
                     

                </div>
            </div>
        </div>
        <div class="footer">
            <p>Bản quyền &copy; 2023. Tất cả các quyền được bảo lưu.</p>
        </div>
    </body>
</body>
</html>
