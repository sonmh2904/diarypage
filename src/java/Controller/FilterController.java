/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Post;
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
public class FilterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        String fromdate = req.getParameter("fromdate");
        String todate = req.getParameter("todate");
        String account = req.getParameter("op");
        Post p = new Post();
        User u = new User();
        ArrayList<User> getListUser = u.getListUser();
        List<Post> listSearch = p.listSearch(key, fromdate, todate, account);
        req.setAttribute("getListUser", getListUser);
        req.setAttribute("getListPostPublic", listSearch);
        req.setAttribute("mode", "listpostofuser");
        req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");
        String fromdate = req.getParameter("fromdate");
        String todate = req.getParameter("todate");
        Post p = new Post();
        List<Post> listAdminSearchPost = p.listAdminSearchPost(key, fromdate, todate);
        req.setAttribute("mode", "listpost");
        req.setAttribute("listPostAll", listAdminSearchPost);
        req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);

    }

}
