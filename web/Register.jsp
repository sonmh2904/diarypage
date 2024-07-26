<%-- 
    Document   : CreateUser
    Created on : Mar 6, 2023, 9:44:31 PM
    Author     : doanq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            input[type="text"],
            input[type="date"],
            input[type="password"] {
                border: 1px solid #ccc;
                border-radius: 3px;
                padding: 5px;
                margin-bottom: 10px;
                font-size: 16px;
                width: 100%;
            }

            /* Style the form labels */
            label {
                /*display: block;*/
                margin-bottom: 5px;
                font-weight: bold;
                font-size: 16px;
            }

            /* Style the form submit button */
            input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
            }

            /* Style the error message */
            p {
                color: red;
                font-weight: bold;
                margin-top: 10px;
            }
            
        </style>
    </head>
    <body>
        <h1>Create User</h1>
        <form action="register" method="post">
            <table>
                <tr>
                    <td>Account:</td>
                    <td><input type="text" name="account" value="${accountnew}"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password" value="${password}"></td>
                </tr>
                <tr>
                    <td>Re-Password:</td>
                    <td><input type="text" name="repassword" value="${repassword}"></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" value="${name}"></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <input type="radio" id="male" name="gender" value="0" ${gender == "0" ? "checked" : ""}>
                        <label for="male">Male</label>
                        <input type="radio" id="female" name="gender" value="1" ${gender == "1" ? "checked" : ""}>
                        <label for="female" >Female</label>
                    </td>
                </tr>
                <tr>
                    <td>Birthdate:</td>
                    <td><input type="date" name="dob" value="${dob}"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" value="${address}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="createuser" value="Create"></td>
                </tr>

            </table>
            <p>${error}</p>
        </form>
    </body>
</html>
