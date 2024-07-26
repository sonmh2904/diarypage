/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author doanq
 */
public class RegisterController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("createuser") != null) {
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String birthdate = req.getParameter("dob");
            String address = req.getParameter("address");
            boolean check = true;
            User u = new User();
            LocalDate now = LocalDate.now();
            LocalDate dob = null;
            if (!birthdate.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dob = LocalDate.parse(birthdate, formatter);
            }
            String error = "";
            if (account.isEmpty() || password.isEmpty() || repassword.isEmpty() || name.isEmpty() || gender.isEmpty() || birthdate.isEmpty() || address.isEmpty()) {
                check = false;
                error = "Please fill out the information completely";
            } else if (!password.equals(repassword)) {
                check = false;
                error += "Password not equal re-password";
            } else if (now.compareTo(dob) < 0) {
                check = false;
                error += "Wrong date";
            } else if (u.checkUserExist(account)) {
                check = false;
                error += "Account exist ";
            }
            if (check) {
                u.addUser(account, password, name, gender, address, birthdate);
//                ArrayList<User> data = u.getListUser();
//                req.setAttribute("data", data);
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
//                resp.sendRedirect("Login.jsp");
            } else {
                req.setAttribute("accountnew", account);
                req.setAttribute("password", password);
                req.setAttribute("repassword", repassword);
                req.setAttribute("name", name);
                req.setAttribute("gender", gender);
                req.setAttribute("dob", birthdate);
                req.setAttribute("address", address);
                req.setAttribute("error", error);
                req.getRequestDispatcher("Register.jsp").forward(req, resp);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
