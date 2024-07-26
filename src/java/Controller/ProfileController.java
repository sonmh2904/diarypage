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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author doanq
 */
public class ProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password_old = req.getParameter("password_old");
        String password_new = req.getParameter("password_new");
        String re_password = req.getParameter("re_password");
        HttpSession session = req.getSession();
        String account = (String) session.getAttribute("acc");
        User u = new User();
        String error = "";
        boolean check = true;
        if (password_old.isEmpty() || password_new.isEmpty() || re_password.isEmpty()) {
            error = "Please fill out the information completely";
            check = false;
        } else {
            if (!password_old.equals(u.getPasswordByAccount(account).trim())) {
                error = "Password Old Incorrect";
                check = false;
            }
            if (password_old.equals(password_new)) {
                error = "Password Old Same New Password";
                check = false;
            }
            if (!password_new.equals(re_password)) {
                error = "New Password Unlike Re-Password";
                check = false;
            }
        }

        if (check) {
            u.changePasswordByAccount(account, password_new);
            u = u.getProfileByAccount(account);
            req.setAttribute("mode", "profile");
            req.setAttribute("profile", u);
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else {
            u = u.getProfileByAccount(account);
            req.setAttribute("profile", u);
            req.setAttribute("mode", "profile");
            req.setAttribute("error", error);
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mode").equals("profile")) {
            HttpSession session = req.getSession();
            String acc = (String) session.getAttribute("acc");
            User u = new User();
            u = u.getProfileByAccount(acc);
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.setAttribute("profile", u);
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        }
    }

}
