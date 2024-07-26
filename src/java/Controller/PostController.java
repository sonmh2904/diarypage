/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Comment;
import Model.Post;
import Model.PostCategory;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doanq
 */
public class PostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        Category c = new Category();
        PostCategory pc = new PostCategory();
        if (req.getParameter("addpost") != null) {
            String error = "";
            String title = req.getParameter("title");
            //
            String status = req.getParameter("status");
            //
            String describe = "";
            if (req.getParameter("describe").length() == 0) {
                error += "Describe is empty";
                List<Category> listTitle = c.getListTitle();
                req.setAttribute("error", error);
                req.setAttribute("listTitle", listTitle);
                req.setAttribute("mode", "addpost");
                req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
            } else {
                describe = req.getParameter("describe");
            }
            //
            String content = "";
            if (req.getParameter("content").length() == 0) {
                error += "Content is empty";
                List<Category> listTitle = c.getListTitle();
                req.setAttribute("error", error);
                req.setAttribute("listTitle", listTitle);
                req.setAttribute("mode", "addpost");
                req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
            } else {
                content += req.getParameter("content");
            }
            //
            LocalDate post_date_row = LocalDate.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String post_date = post_date_row.format(dateTimeFormatter);
            //
            HttpSession session = req.getSession();
            //
            p.addPost((String) session.getAttribute("acc"), describe, content, post_date, status);
            int pid = p.getPidNew();
            int cid = c.getCidByName(title);
            pc.addPostCategory(cid, pid);
            //
            ArrayList<Post> getListPostPublic = p.getListPostPublic();
            req.setAttribute("getListPostPublic", getListPostPublic);
            req.setAttribute("mode", "listpostofuser");
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        if (req.getParameter("postupdate") != null) {
            String error = "";
            String describe = "";
            String status = req.getParameter("status");
            if (req.getParameter("describe").length() == 0) {
                error += "Describe is empty";
                List<Category> listTitle = c.getListTitle();
                req.setAttribute("listTitle", listTitle);
                req.setAttribute("mode", "addpost");
                req.setAttribute("error", error);
                req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
            } else {
                describe = req.getParameter("describe");
            }
            //
            String content = "";
            if (req.getParameter("content").length() == 0) {
                error += "Content is empty";
                List<Category> listTitle = c.getListTitle();
                req.setAttribute("listTitle", listTitle);
                req.setAttribute("mode", "addpost");
                req.setAttribute("error", error);
                req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);

            } else {
                content += req.getParameter("content");
            }
            String pid = req.getParameter("pid");
            p.updatePost(pid, describe, content, status);

            HttpSession session = req.getSession();
            String account = (String) session.getAttribute("acc");
            List<Post> listPostPersonal = p.listPostPersonal(account);
            req.setAttribute("listPostPersonal", listPostPersonal);
            req.setAttribute("mode", "listpostpersonal");
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        User u = new User();
        Comment cm = new Comment();
        HttpSession session = req.getSession();
        PostCategory pc = new PostCategory();
        if (req.getParameter("mode").equals("listpostofuser")) {
            ArrayList<Post> getListPostPublic = p.getListPostPublic();
            ArrayList<User> getListUser = u.getListUser();
            
            req.setAttribute("getListUser", getListUser);
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.setAttribute("getListPostPublic", getListPostPublic);
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else if (req.getParameter("mode").equals("addpost")) {
            Category c = new Category();
            List<Category> listTitle = c.getListTitle();
            req.setAttribute("listTitle", listTitle);
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else if (req.getParameter("mode").equals("listpostpersonal")) {
            String account = (String) session.getAttribute("acc");
            List<Post> listPostPersonal = p.listPostPersonal(account);
            req.setAttribute("listPostPersonal", listPostPersonal);
            req.setAttribute("mode", req.getParameter("mode").trim());
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else if (req.getParameter("mode").equals("updatepost")) {
            String pid = req.getParameter("pid");
            p = p.getPostDetailByPid(pid);
            req.setAttribute("postdetail", p);
            req.setAttribute("mode", "updatepost");
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else if (req.getParameter("mode").equals("deletepost")) {
            String pid = req.getParameter("pid");
            pc.deletePostCategoryByPid(pid);
            cm.deleteCommentByPid(pid);
            p.detelePost(pid);
            String account = (String) session.getAttribute("acc");
            List<Post> listPostPersonal = p.listPostPersonal(account);
            req.setAttribute("listPostPersonal", listPostPersonal);
            req.setAttribute("mode", "listpostpersonal");
            req.getRequestDispatcher("UserInterface.jsp").forward(req, resp);
        } else if (req.getParameter("mode").equals("logout")) {
            session.removeAttribute("account");
            session.removeAttribute("acc");
            session.removeAttribute("pidsession");
            resp.sendRedirect("Login.jsp");
        }
    }

}
