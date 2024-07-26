/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
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
public class CategoryManagementController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("mode").equals("addcategory")){
            String error ="";
            String addcategory = "";
            if(req.getParameter("addcategory").isEmpty()||req.getParameter("addcategory").length()==0){
                error+= "Please fill in the information";
                req.setAttribute("error", error);
                req.getRequestDispatcher("AddCategory.jsp").forward(req, resp);
            }else{
                addcategory = req.getParameter("addcategory");
            }
            Category c = new Category();
            c.addCategory(addcategory);
            
            List<Category> listCategory = c.getListTitle();
            req.setAttribute("listCategory", listCategory);
            req.setAttribute("mode", "listcategory");
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category c = new Category();
        if(req.getParameter("mode").equals("listcategory")){
            
            List<Category> listCategory = c.getListTitle();
            req.setAttribute("listCategory", listCategory);
            req.setAttribute("mode", "listcategory");
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        
        }if(req.getParameter("mode").equals("deletecategory")){
            String cid = req.getParameter("cid");
            c.deleteCategoryByCid(cid);
            List<Category> listCategory = c.getListTitle();
            req.setAttribute("listCategory", listCategory);
            req.setAttribute("mode", "listcategory");
            req.getRequestDispatcher("AdminInterface.jsp").forward(req, resp);
        
        }
            
    }
    
}
