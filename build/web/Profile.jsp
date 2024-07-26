<%-- 
    Document   : Profile
    Created on : Mar 17, 2023, 10:16:22 PM
    Author     : doanq
--%>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-6">
                <table>
                    <tr>
                        <td>Account:</td>
                        <td><input type="text" name="acc" value="${sessionScope.acc}" readonly></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="password" value="${profile.getPassword()}" readonly></td>
                    </tr> 
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="${profile.getName()}" readonly></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td><input type="text" name="name" value="${profile.getGender()}" readonly></td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td><input type="text" name="name" value="${profile.getAddress()}" readonly></td>
                    </tr>
                    <tr>
                        <td>DOB:</td>
                        <td><input type="text" name="name" value="${profile.getDob()}" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="col-md-6">
                <form action="profile" method="post">
                    <table>
                        <tr>
                            <td>Password Old:</td>
                            <td><input type="text" name="password_old"></td>
                        </tr>
                        <tr>
                            <td>New Password:</td>
                            <td><input type="text" name="password_new"></td>
                        </tr>
                        <tr>
                            <td>Re-Password:</td>
                            <td><input type="text" name="re_password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Change Password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>${error}</td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>
    </body>
</html>
