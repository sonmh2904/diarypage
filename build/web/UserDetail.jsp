<%-- 
    Document   : UserDetail
    Created on : Mar 17, 2023, 3:53:29 PM
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
        <form action="usermanagement?&account=${profile.getAccount()}&mode=updateuser" method="post">
            
        
        <table>
            <tr>
                <td>Account:</td>
                <td><input type="text" name="acc" value="${profile.getAccount()}" readonly></td>
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
            <tr>
                <td>Role:</td>
                <td>
                    <input type="radio" name="role" value="0" ${profile.getRole() == 0 ? 'checked' : ''}>
                    <label>User:</label>

                    <input type="radio" name="role" value="1" ${profile.getRole() == 1 ? 'checked' : ''}>
                    <label>Admin:</label>
                </td>
            </tr>
            <tr>
                <td>Status</td>
                <td>
                    <input type="radio" name="status" value="0" ${profile.getStatus() == 0 ? 'checked' : ''}>
                    <label>Normal</label>

                    <input type="radio" name="status" value="1" ${profile.getStatus() == 1 ? 'checked' : ''}>
                    <label>Disable</label>
                </td>
            </tr>
        </table>
                    <input type="submit" value="Apply">
        </form>
    </body>
</html>
