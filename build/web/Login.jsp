<%-- 
    Document   : Login
    Created on : Mar 6, 2023, 5:55:17 PM
    Author     : doanq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Poppins');

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Poppins', sans-serif;
                height: 100vh;
            }

            .login {
                background-color: #ffffff;
                max-width: 400px;
                margin: 0 auto;
                margin-top: 50px;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
            }

            .login h1 {
                text-align: center;
                margin-bottom: 30px;
            }

            .login label {
                display: block;
                font-size: 14px;
                margin-bottom: 5px;
                color: #666666;
            }

            .login input[type="text"], .login input[type="password"] {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: none;
                margin-bottom: 20px;
                box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
            }

            .login input[type="submit"]{
                width: 100%;
                background-color: #55acee;
                color: #ffffff;
                padding: 10px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }

            .login input[type="submit"]:hover {
                background-color: #0077b5;
            }

            /*            .create{
                            background-color: #55acee;
                            color: #ffffff;
                            padding: 10px;
                            border-radius: 5px;
                            border: none;
                            cursor: pointer;
                        }*/
            p {
                font-size: 15px;
            }
            p.error{
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="login">
            <h1>Login</h1>
            <form  action="login" method="post">
                <input type="text" name="account" placeholder="Username">
                <input type="password" name="password" placeholder="Password" >
                <input type="submit" name="login" value="Login">
                <br>
                <br>
            </form>
            <p>Do you have an account? <a href="Register.jsp" >Register?</a></p>
            <p class="error">${error}</p>
        </div>
    </body>
</html>
