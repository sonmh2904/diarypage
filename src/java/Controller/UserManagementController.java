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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doanq
 */
public class UserManagementController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        if (req.getParameter("mode").equals("updateuser")) {
            String account = req.getParameter("account");
            String role = req.getParameter("role");
            String status = req.getParameter("status");
            u.updateUserByAdmin(account,role,status);
            
            
            ArrayList<User> listuser = u.getListUser();
            req.setAttribute("listuser", listuser);
            req.setAttribute("mode", "listuser");
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
        if(req.getParameter("mode").equals("search")){
            String key = req.getParameter("key");
            List<User> listSearch = u.listSearchByAdmin(key);
            
            req.setAttribute("listuser", listSearch);
            req.setAttribute("mode", "listuser");
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        if (req.getParameter("mode").equals("listuser")) {
            
            ArrayList<User> listuser = u.getListUser();
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.setAttribute("listuser", listuser);
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }if(req.getParameter("mode").equals("userdetail")){
            String account = req.getParameter("account");
            u = u.getProfileByAccount(account);
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.setAttribute("profile", u);
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
        
    }
    
}
