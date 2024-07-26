/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Post;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doanq
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != null) {
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            boolean check = true;
            User u = new User(account, password);

            boolean checkLogin = u.checkLogin();
            boolean checkRole = u.checkRole(account);
            boolean checkStatus = u.checkStatus(account);
            String error = "";
            if (account.isEmpty() || password.isEmpty()) {
                check = false;
                error += "Please fill out the information completely";
                req.setAttribute("error", error);
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
            if (check) {
                if (checkLogin) {
                    HttpSession session = req.getSession();
                    session.setAttribute("account", u);
                    session.setAttribute("acc", account);
                    if (checkRole) {
                        req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
//                        resp.sendRedirect("AdminInterface.jsp");
                    } else {
                        if (checkStatus) {
                            Post p = new Post();
                            ArrayList<Post> getListPostPublic = p.getListPostPublic();
                            ArrayList<User> getListUser = u.getListUser();
                            req.setAttribute("mode", "listpostofuser");
                            req.setAttribute("getListPostPublic", getListPostPublic);
                            req.setAttribute("getListUser", getListUser);
                            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
                        } else {
                            req.getRequestDispatcher("DisplayDisable.jsp").forward(req, resp);
//                            resp.sendRedirect("DisplayDisable.jsp");
                        }

                    }
                } else {
                    error += "Enter the wrong account or password";
                    req.setAttribute("error", error);
                    req.getRequestDispatcher("Login.jsp").forward(req, resp);
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("Login.jsp");
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }

}
