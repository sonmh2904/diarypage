/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doanq
 */
public class Category {
    String cid,name;

    public Category() {
        connect();
    }

    public Category(String cid, String name) {
        this.cid = cid;
        this.name = name;
        connect();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "cid=" + cid + ", name=" + name + '}';
    }
    
    Connection cnn;//kết nối
    Statement stm;// Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; // lưu trữ và xử lý dữ liệu

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail!");
            }
        } catch (Exception e) {
        }
    }

    public List<Category> getListTitle() {
         List<Category> data = new ArrayList<>();
        try {
            String strSelect = "select * from Category";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String cid = rs.getString(1);
                String name = rs.getString(2);
                data.add(new Category(cid, name));
            }
        } catch (Exception e) {
            System.out.println("getListTitle" + e.getMessage());
        }
        return data;
    }
    
    public int getCidByName(String title) {
        try {
            String strSelect = "select cid from Category where name = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, title);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getCidByName" + e.getMessage());
        }
        return 0;
    }
    public static void main(String[] args) {
        Category c = new Category();
       c.deleteCategoryByCid("9");
    }

    public void addCategory(String addcategory) {
        try {
            String strSelect = "insert into Category (name) values (?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, addcategory);
            pstm.execute();
            
        } catch (Exception e) {
            System.out.println("addCategory" + e.getMessage());
        }
    }

    public void deleteCategoryByCid(String cid) {
        try {
            String strSelect = "DELETE FROM Category WHERE cid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(cid));
            pstm.execute();
            
        } catch (Exception e) {
            System.out.println("deleteCategoryByCid" + e.getMessage());
        }
    }
}
