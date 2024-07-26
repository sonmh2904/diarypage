<%-- 
    Document   : PostDetail
    Created on : Mar 15, 2023, 9:16:15 PM
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
            .postdetail {
                background-color: #f9f9f9;
                padding: 20px;
            }

            .post-title {
                font-size: 24px;
                font-weight: bold;
            }

            .post-account, .post-describe, .post-content, .post-date {
                font-size: 16px;
                margin: 10px 0;
            }

            .comments {
                margin-top: 20px;
            }

            .comment {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
            }

            .comment-account, .comment-content, .comment-date {
                font-size: 16px;
                margin: 10px 0;
            }

            .comment-date {
                color: #999;
            }

            .error {
                font-size: 16px;
                margin: 10px 0;
            }

            textarea {
                width: 100%;
                height: 100px;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                margin-bottom: 10px;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            a {
                color: #4CAF50;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <a href="post?&mode=listpostofuser">BACK</a>
        <div class="postdetail">
            <form action="postdetail" method="post">
                <h2 class="post-title">${postdetail.getCname()}</h2>
                <p class="post-account">Account: ${postdetail.getAccount()}</p>
                <p class="post-describe">Describe: ${postdetail.getDescribe()}</p>
                <p class="post-content">Content: ${postdetail.getContent()}</p>
                <p class="post-date">Post-Date: ${postdetail.getPost_date()}</p>

            </form>
        </div>
        <div class="comments">


            <h4>Add a Comment</h4>
            <form action="comment" method="post" >
                <textarea name="comment"></textarea><br><br>
                <input type="submit" value="Add Comment" name="addcomment">
            </form>
            <p style="color: red" class="error">${error}</p>
            <h3>Comments</h3>
            <c:forEach var="i" items="${listCommentByPid}">
                <div class="comment">
                    <p class="comment-account">Account: ${i.getAccount()}</p>
                    <p class="comment-content">${i.getContent()}</p>
                    <p class="comment-date">Comment-Date: ${i.getCm_date()}</p>
                    <c:if test="${postOwner eq accnow or i.getAccount().trim() eq accnow}">
                        <a href="comment?&cmid=${i.getCmid()}&mode=deletecomment">Delete Comment</a>
                    </c:if>
                          
                    <c:if test="${i.getAccount().trim() eq accnow}">
                        <br>  
                        <a href="comment?&cmid=${i.getCmid()}&mode=updatecm">Update Comment</a>
                    </c:if>
                </div>
            </c:forEach>
            </div>
    </body>
</html>
