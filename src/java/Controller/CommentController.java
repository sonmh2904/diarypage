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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author doanq
 */
public class CommentController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
            Comment cm = new Comment();
            HttpSession session = req.getSession();
        if (req.getParameter("addcomment") != null) {
            
            //
            
            String pid = (String) session.getAttribute("pidsession");
            String account = (String) session.getAttribute("acc");
            p = p.getPostDetailByPid(pid);

            //
            LocalDateTime cm_date_row = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String cm_date = cm_date_row.format(dateTimeFormatter);
            //
            String content = "";
            String error = "";
            boolean check =true;
            if (req.getParameter("comment").length() == 0) {
                error = "Please enter a comment";
                check = false;
            } else {
                content = req.getParameter("comment");
            }

            String postOwner = p.getPostOwner(pid);
            String accnow = (String)session.getAttribute("acc");
            //
            if(check){
            cm.addComment(pid, account, content, cm_date);
            }else{
                req.setAttribute("error", error);
            }
           
            req.setAttribute("postOwner", postOwner.trim());
            req.setAttribute("accnow", accnow.trim());
            //
            List<Comment> listCommentByPid = cm.getListCommentByPid(pid);
            req.setAttribute("listCommentByPid", listCommentByPid);
            req.setAttribute("postdetail", p);
            req.getRequestDispatcher("PostDetail.jsp").forward(req, resp);
        }if(req.getParameter("updatecomment") != null){
            String cmid = req.getParameter("cmid");
            String content = req.getParameter("content");
            
            LocalDateTime cm_date_row = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String cm_date = cm_date_row.format(dateTimeFormatter);
            
            cm.updateCommentByCmid(cmid,content,cm_date);
            
            
            
            
            String pid = (String) session.getAttribute("pidsession");
            String account = (String) session.getAttribute("acc");
            p = p.getPostDetailByPid(pid);
            String postOwner = p.getPostOwner(pid);
            String accComment = cm.getAccountByCmid(cmid);
            List<Comment> listCommentByPid = cm.getListCommentByPid(pid);
            req.setAttribute("postOwner", postOwner);
            req.setAttribute("accComment", accComment.trim());
            req.setAttribute("listCommentByPid", listCommentByPid);
            req.setAttribute("postdetail", p);
            req.setAttribute("accnow", (String)session.getAttribute("acc"));
            req.getRequestDispatcher("PostDetail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        Comment cm = new Comment();
        HttpSession session = req.getSession();
        if (req.getParameter("mode").equals("deletecomment")) {
            String cmid = req.getParameter("cmid");
            String pid = (String)session.getAttribute("pidsession");
            String accComment = cm.getAccountByCmid(cmid);
            String postOwner = p.getPostOwner(pid);
            cm.deleteCommentByCmid(cmid);
            p = p.getPostDetailByPid(pid);
            List<Comment> listCommentByPid = cm.getListCommentByPid(pid);
            req.setAttribute("listCommentByPid", listCommentByPid);
            req.setAttribute("postOwner", postOwner);
            req.setAttribute("accComment", accComment.trim());
            req.setAttribute("postdetail", p);
            req.setAttribute("accnow", (String)session.getAttribute("acc"));
            req.getRequestDispatcher("PostDetail.jsp").forward(req, resp);
        }
        if(req.getParameter("mode").equals("updatecm")){
            String cmid = req.getParameter("cmid");
            cm = cm.getCommentByCmid(cmid);
            req.setAttribute("cm", cm);
            req.getRequestDispatcher("UpdateComment.jsp").forward(req, resp);
        }
    }
    
}
