/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author doanq
 */
public class PostCategory {
    String cid,pid;

    public PostCategory() {
        connect();
    }

    public PostCategory(String cid, String pid) {
        this.cid = cid;
        this.pid = pid;
        connect();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "PostCategory{" + "cid=" + cid + ", pid=" + pid + '}';
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
    public void addPostCategory(int cid,int pid) {
        try {
            String strSelect = "insert into postCategory (cid,pid) values (?,?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1,cid);
            pstm.setInt(2, pid);
            pstm.execute();
            
        } catch (Exception e) {
            System.out.println("addPostCategory" + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PostCategory pc = new PostCategory();
        pc.addPostCategory(5, 8);
    }

    public void deletePostCategoryByPid(String pid) {
       try {
            String strSelect = "DELETE FROM postCategory WHERE pid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("deletePostCategoryByPid" + e.getMessage());
        }
    }
}
