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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doanq
 */
public class Comment {

    String cmid, pid, account, content, cm_date;

    public Comment() {
        connect();
    }
 
    
    
    public Comment(String cmid, String pid, String account, String content, String cm_date) {
        this.cmid = cmid;
        this.pid = pid;
        this.account = account;
        this.content = content;
        this.cm_date = cm_date;
        connect();
    }

    public String getCmid() {
        return cmid;
    }

    public void setCmid(String cmid) {
        this.cmid = cmid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCm_date() {
        return cm_date;
    }

    public void setCm_date(String cm_date) {
        this.cm_date = cm_date;
    }

    @Override
    public String toString() {
        return "Comments{" + "cmid=" + cmid + ", pid=" + pid + ", account=" + account + ", content=" + content + ", cm_date=" + cm_date + '}';
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

    public List<Comment> getListCommentByPid(String pid) {
        List<Comment> data = new ArrayList<>();
        try {
            String strSelect = "select * from Comments where pid =? order by cmid desc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(pid));
            rs = pstm.executeQuery();
            while (rs.next()) {
                String cmid = rs.getString(1);
                String account = rs.getString(3);
                String content = rs.getString(4);
                String cm_date = rs.getString(5);
                data.add(new Comment(cmid, pid, account, content, cm_date));
            }
        } catch (Exception e) {
            System.out.println("getListCommentByPid" + e.getMessage());
        }
        return data;
    }

    public void addComment(String pid, String account, String content, String cm_date) {
        try {
            String strSelect = "insert into Comments(pid, account, content, cm_date) values (?,?,?,?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(pid));
            pstm.setString(2, account);
            pstm.setString(3, content);
            pstm.setString(4, cm_date);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addComment" + e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        Comment cm = new Comment();
//    }
    public void deleteCommentByCmid(String cmid) {
        try {
            String strSelect = "DELETE FROM Comments WHERE cmid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(cmid));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteCommentByCmid" + e.getMessage());
        }
    }


    public String getAccountByCmid(String cmid) {
        try {
            String strSelect = "select account from Comments where cmid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(cmid));
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
            System.out.println("getListCommentByPid" + e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) {
        Comment cm = new Comment();
        System.out.println(cm.getAccountByCmid("18"));
    }

    public void deleteCommentByPid(String pid) {
        try {
            String strSelect = "DELETE FROM Comments WHERE pid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("deleteCommentByPid" + e.getMessage());
        }
    }


    

    public Comment getCommentByCmid(String cmid) {
        Comment cm = new Comment();
        try {
            String strSelect = "select * from Comments where cmid =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(cmid));
            rs = pstm.executeQuery();
            while (rs.next()) {
                cmid = rs.getString(1);
                pid = rs.getString(2);
                account = rs.getString(3);
                content = rs.getString(4);
                cm_date = rs.getString(5);
                cm = new Comment(cmid, pid, account, content, cm_date);
            }
        } catch (Exception e) {
            System.out.println("getCommentByCmid" + e.getMessage());
        }
        return cm;
    }
    
    public void updateCommentByCmid(String cmid, String content, String cm_date) {
        try {
            String strSelect = "UPDATE Comments SET content = ?, cm_date = ? WHERE cmid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, content);
            pstm.setString(2, cm_date);
            pstm.setInt(3, Integer.parseInt(cmid));
            pstm.execute();

        } catch (Exception e) {
            System.out.println("updateCommentByCmid" + e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        Comment cm = new Comment();
//        cm.updateCommentByCmid("24", "vua sua");
//    }
    
}
