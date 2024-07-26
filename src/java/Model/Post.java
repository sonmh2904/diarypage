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
public class Post {

    String pid, account, describe, content, post_date, status, cname;

    public Post() {
        connect();
    }

    public Post(String pid, String account, String describe, String content, String post_date, String status) {
        this.pid = pid;
        this.account = account;
        this.describe = describe;
        this.content = content;
        this.post_date = post_date;
        this.status = status;
        connect();
    }

    public Post(String pid, String account, String describe, String content, String post_date, String status, String cname) {
        this.pid = pid;
        this.account = account;
        this.describe = describe;
        this.content = content;
        this.post_date = post_date;
        this.status = status;
        this.cname = cname;
        connect();
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Post{" + "pid=" + pid + ", account=" + account + ", describe=" + describe + ", content=" + content + ", post_date=" + post_date + ", status=" + status + ", cname=" + cname + '}';
    }

    // khai báo các thành phần xử lý database
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

    public ArrayList<Post> getListPostPublic() {
        ArrayList<Post> data = new ArrayList<>();
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid where Posts.status = 0 order by Posts.pid desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String pid = rs.getString(1);
                String account = rs.getString(2);
                String describe = rs.getString(3);
                String content = rs.getString(4);
                String post_date = rs.getString(5);
                String status = rs.getString(6);
                String cname = rs.getString(10);
                data.add(new Post(pid, account, describe, content, post_date, status, cname));
            }
        } catch (Exception e) {
            System.out.println("getListPost" + e.getMessage());
        }
        return data;
    }

//    
//    
//    public void addPost(String title, String content, String post_date, String account, String status) {
//        try {
//            String strSelect = "insert into Posts (title,content,post_date,account,status) values (?,?,?,?,?)";
//            pstm = cnn.prepareStatement(strSelect);
//            pstm.setString(1, title);
//            pstm.setString(2, content);
//            pstm.setDate(3, Date.valueOf(post_date));
//            pstm.setString(4, account);
//            pstm.setInt(5, Integer.parseInt(status));
//            pstm.execute();
//            
//        } catch (Exception e) {
//            System.out.println("addPost" + e.getMessage());
//        }
//    }
    public void addPost(String account, String describe, String content, String post_date, String status) {
        try {
            String strSelect = "insert into Posts (account,  describe,  content,  post_date,  status) VALUES (?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, describe);
            pstm.setString(3, content);
            pstm.setDate(4, Date.valueOf(post_date));
            pstm.setInt(5, Integer.parseInt(status));
            pstm.execute();

        } catch (Exception e) {
            System.out.println("addPost" + e.getMessage());
        }
    }

    public int getPidNew() {
        try {
            String strSelect = "select top 1 pid from Posts order by pid desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getPidNew" + e.getMessage());
        }
        return 0;
    }

    public Post getPostDetailByPid(String pid) {
        Post p = new Post();
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid where Posts.pid =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(pid));
            rs = pstm.executeQuery();
            while (rs.next()) {
                account = rs.getString(2);
                describe = rs.getString(3);
                content = rs.getString(4);
                post_date = rs.getString(5);
                status = rs.getString(6);
                cname = rs.getString(10);
                p = new Post(pid, account, describe, content, post_date, status, cname);
            }
        } catch (Exception e) {
            System.out.println("getPostDetailByPid" + e.getMessage());
        }
        return p;
    }

    public List<Post> listSearch(String key, String fromdate, String todate, String account) {
        List<Post> data = new ArrayList<>();
        int i = 0, j = 0;
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid where Posts.status = 0";
            if (key != null && !key.equals("")) {
                strSelect += " and Posts.describe like '%" + key + "%' or Category.name like '%" + key + "%'";
            }
            if (fromdate != null && !fromdate.equals("")) {
                strSelect += " and Posts.post_date >= ?";
                i++;
            }

            if (todate != null && !todate.equals("")) {
                strSelect += " and Posts.post_date <= ?";
                j++;
            }
            if (account != null && !account.equals("")) {
                strSelect += " and Posts.account = '" + account + "'";
            }

            pstm = cnn.prepareStatement(strSelect);
            if (fromdate != null && !fromdate.equals("")) {
                pstm.setDate(i, Date.valueOf(fromdate));
            }
            if (todate != null && !todate.equals("")) {
                pstm.setDate(i + j, Date.valueOf(todate));
            }

            rs = pstm.executeQuery();
            System.out.println("123");
            while (rs.next()) {
                String pid = rs.getString(1);
                account = rs.getString(2);
                String describe = rs.getString(3);
                String content = rs.getString(4);
                String post_date = rs.getString(5);
                String status = rs.getString(6);
                String cname = rs.getString(10);
                data.add(new Post(pid, account, describe, content, post_date, status, cname));
            }
        } catch (Exception e) {
            System.out.println("listSearch" + e.getMessage());
        }
        return data;
    }

    public List<Post> listPostPersonal(String account) {
        ArrayList<Post> data = new ArrayList<>();
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid where account=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String pid = rs.getString(1);
                account = rs.getString(2);
                String describe = rs.getString(3);
                String content = rs.getString(4);
                String post_date = rs.getString(5);
                String status = "";
                if (rs.getString(6).equals("1")) {
                    status = "Private";
                } else if (rs.getString(6).equals("0")) {
                    status = "Public";
                } else {
                    status = "Disable";
                }
                String cname = rs.getString(10);
                data.add(new Post(pid, account, describe, content, post_date, status, cname));
            }
        } catch (Exception e) {
            System.out.println("listPostPersonal" + e.getMessage());
        }
        return data;
    }

    public String getPostOwner(String pid) {
        try {
            String strSelect = "select account from Posts where pid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("listPostPersonal" + e.getMessage());
        }
        return "";
    }

    public void updatePost(String pid, String describe, String content, String status) {
        try {
            String strSelect = "UPDATE Posts SET content = ?, describe=?, status=? WHERE pid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, content);
            pstm.setString(2, describe);
            pstm.setInt(3, Integer.parseInt(status));
            pstm.setString(4, pid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("updatePost" + e.getMessage());
        }
    }

    public void detelePost(String pid) {
        try {
            String strSelect = "DELETE FROM Posts WHERE pid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("updatePost" + e.getMessage());
        }

    }

    public List<Post> getListPostAll() {
        List<Post> data = new ArrayList<>();
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid  order by Posts.pid desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String pid = rs.getString(1);
                String account = rs.getString(2);
                String describe = rs.getString(3);
                String content = rs.getString(4);
                String post_date = rs.getString(5);
                String status = "";
                if (rs.getString(6).equals("1")) {
                    status = "Private";
                } else if (rs.getString(6).equals("0")) {
                    status = "Public";
                } else {
                    status = "Disable";
                }
                String cname = rs.getString(10);
                data.add(new Post(pid, account, describe, content, post_date, status, cname));
            }
        } catch (Exception e) {
            System.out.println("getListPostAll" + e.getMessage());
        }
        return data;
    }

    public List<Post> listAdminSearchPost(String key, String fromdate, String todate) {
        List<Post> data = new ArrayList<>();
        int i = 0, j = 0;
        try {
            String strSelect = "select * from Posts join postCategory on Posts.pid = postCategory.pid join Category on postCategory.cid = Category.cid where 1 = 1 ";
            if (key != null && !key.equals("")) {
                strSelect += " and Posts.describe like '%" + key + "%' or Category.name like '%" + key + "%'";
            }
            if (fromdate != null && !fromdate.equals("")) {
                strSelect += " and Posts.post_date >= ?";
                i++;
            }

            if (todate != null && !todate.equals("")) {
                strSelect += " and Posts.post_date <= ?";
                j++;
            }

            pstm = cnn.prepareStatement(strSelect);
            if (fromdate != null && !fromdate.equals("")) {
                pstm.setDate(i, Date.valueOf(fromdate));
            }
            if (todate != null && !todate.equals("")) {
                pstm.setDate(i + j, Date.valueOf(todate));
            }

            rs = pstm.executeQuery();
            System.out.println("123");
            while (rs.next()) {
                String pid = rs.getString(1);
                String account = rs.getString(2);
                String describe = rs.getString(3);
                String content = rs.getString(4);
                String post_date = rs.getString(5);
                String status = "";
                if (rs.getString(6).equals("1")) {
                    status = "Private";
                } else if (rs.getString(6).equals("0")) {
                    status = "Public";
                } else {
                    status = "Disable";
                }
                String cname = rs.getString(10);
                data.add(new Post(pid, account, describe, content, post_date, status, cname));
            }
        } catch (Exception e) {
            System.out.println("listAdminSearchPost" + e.getMessage());
        }
        return data;
    }

    public void updateStatusPostByPid(String pid, String status) {
        try {
            String strSelect = "UPDATE Posts SET status = ? WHERE pid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, status);
            pstm.setString(2, pid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("updateStatusPostByPid" + e.getMessage());
        }
    }

}
