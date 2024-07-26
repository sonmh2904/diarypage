/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author doanq
 */
public class PostManagement extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        if(req.getParameter("mode").equals("adminupdatepost")){
            String pid = req.getParameter("pid");
            String status = req.getParameter(pid);
            p.updateStatusPostByPid(pid,status);
            
            List<Post> listPostAll = p.getListPostAll();
            req.setAttribute("mode", "listpost");
            req.setAttribute("listPostAll", listPostAll);
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        
        if(req.getParameter("mode").equals("listpost")){
            List<Post> listPostAll = p.getListPostAll();
            req.setAttribute("mode", "listpost");
            req.setAttribute("listPostAll", listPostAll);
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
    }
    
}
