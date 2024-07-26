/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Comment;
import Model.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author doanq
 */
public class PostDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        Comment cm = new Comment();
        //
        HttpSession session = req.getSession();
            
            String pid = req.getParameter("pid");
            session.setAttribute("pidsession", pid);
            p = p.getPostDetailByPid(pid);
            //
            String postOwner = p.getPostOwner(pid);
            String accnow = (String)session.getAttribute("acc");
            //
            List<Comment> listCommentByPid = cm.getListCommentByPid(pid);
            //
            req.setAttribute("postOwner", postOwner.trim());
            req.setAttribute("accnow", accnow.trim());
            req.setAttribute("listCommentByPid", listCommentByPid);
            req.setAttribute("pid", pid);
            req.setAttribute("postdetail", p);
            req.getRequestDispatcher("PostDetail.jsp").forward(req, resp);
        
        
        
    }

}
