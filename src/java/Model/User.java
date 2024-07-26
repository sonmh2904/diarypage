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
public class User {

    String account, password, name, gender, address, dob, role, status;

    public User() {
        connect();
    }

    public User(String account, String password, String name, String gender, String address, String dob) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        connect();
    }

    public User(String account, String password, String name, String gender, String address, String dob, String role, String status) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.role = role;
        this.status = status;
        connect();
    }

    

    
    public User(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "account=" + account + ", password=" + password + ", name=" + name + ", gender=" + gender + ", address=" + address + ", dob=" + dob + ", role=" + role + ", status=" + status + '}';
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

    public ArrayList<User> getListUser() {
        ArrayList<User> data = new ArrayList<>();
        try {
            String strSelect = "select * from Users where role != 1";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String account = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                String gender = "Male";
                if (rs.getBoolean(4)) {
                    gender = "Female";
                }
                String address = "";
                if(rs.getString(5)!=null){
                    address = rs.getString(5);
                }
                        
                String dob = "";
                if(rs.getString(6)!=null){
                    dob = rs.getString(6);
                }
                String role = rs.getString(7);
                String status = "Normal";
                if(rs.getBoolean(8)){
                    status = "Disable";
                }
                data.add(new User(account, password, name, gender, address, dob, role, status));
            }
        } catch (Exception e) {
            System.out.println("getListUser" + e.getMessage());
        }
        return data;
    }

    public boolean checkLogin() {
        try {
            String strSelect = "select * from Users where account=? and Password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkLogin" + e.getMessage());
        }
        return false;
    }

    public boolean checkRole(String account) {
        try {
            String strSelect = "select role from Users where account = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if(rs.getString(1).equals("1")){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("checkRole" + e.getMessage());
        }
        return false;
    }

    public void addUser(String account, String password, String name, String gender, String address, String dob) {
        try {
            String strSelect = "INSERT INTO Users (account, password, name, gender, address, dateofbirth) VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1,account);
            pstm.setString(2, password);
            pstm.setString(3, name);
            pstm.setString(4, gender);
            pstm.setString(5, address);
            pstm.setDate(6, Date.valueOf(dob));
            pstm.execute();
            
        } catch (Exception e) {
            System.out.println("addUser" + e.getMessage());
        }
    }

    public boolean checkUserExist(String account) {
        try {
            String strSelect = "select * from Users where account = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserExist" + e.getMessage());
        }
        return false;
    }

//    public static void main(String[] args) {
//        User u = new  User();
//        u.addUser("quan6", "123", "qwer", "1", "77 nguyen du", "1626-03-15");
//        List<User> list = u.getListUser();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).account);
//        }
//    }

    public User getProfileByAccount(String acc) {
        User data = new User();
        try {
            String strSelect = "select * from Users where account= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, acc);
            rs = pstm.executeQuery();
            while (rs.next()) {
                account = rs.getString(1);
                password = rs.getString(2);
                name = rs.getString(3);
                gender = "Male";
                if (rs.getBoolean(4)) {
                    gender = "Female";
                }
                address = "";
                if(rs.getString(5)!=null){
                    address = rs.getString(5);
                }
                        
                dob = "";
                if(rs.getString(6)!=null){
                    dob = rs.getString(6);
                }
                
                role = rs.getString(7);
                status = rs.getString(8);;
                data=new User(account, password, name, gender, address, dob, role, status);
            }
        } catch (Exception e) {
            System.out.println("getProfileByAccount" + e.getMessage());
        }
        return data;
    }

    

    public String getPasswordByAccount(String account) {
        try {
            String strSelect = "select password from Users where account=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
                
            }
                
            
        } catch (Exception e) {
            System.out.println("getPasswordByAccount" + e.getMessage());
        }
        return "";
    }

    public void changePasswordByAccount(String account, String password_new) {
        try {
            String strSelect = "update Users set password = ? where account=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, password_new);
            pstm.setString(2, account);
            pstm.execute();
                
            
        } catch (Exception e) {
            System.out.println("changePasswordByAccount" + e.getMessage());
        }
    }
    


    public void updateUserByAdmin(String account, String role, String status) {
        try {
            String strSelect = "UPDATE users SET role = ?, status = ? where account = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(role));
            pstm.setInt(2, Integer.parseInt(status));
            pstm.setString(3, account);
            pstm.execute();
                
            
        } catch (Exception e) {
            System.out.println("updateUserByAdmin" + e.getMessage());
        }
    }

    

    public List<User> listSearchByAdmin(String key) {
        List<User> data = new ArrayList<>();
        try {
            String strSelect = "select * from Users where 1=1 ";
            if (key != null && !key.equals("")) {
                strSelect += " and account like '%" + key + "%' or status like '%" + key + "%'";
            }
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String account = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                String gender = "Male";
                if (rs.getBoolean(4)) {
                    gender = "Female";
                }
                String address = "";
                if(rs.getString(5)!=null){
                    address = rs.getString(5);
                }
                        
                String dob = "";
                if(rs.getString(6)!=null){
                    dob = rs.getString(6);
                }
                String role = rs.getString(7);
                String status = "Normal";
                if(rs.getBoolean(8)){
                    status = "Disable";
                }
                data.add(new User(account, password, name, gender, address, dob, role, status));
            }
        } catch (Exception e) {
            System.out.println("listSearchByAdmin" + e.getMessage());
        }
        return data;
    }
    public static void main(String[] args) {
        User u  = new User();
        
        System.out.println(u.listSearchByAdmin("di"));
    }

    public boolean checkStatus(String account) {
        try {
            String strSelect = "select * from Users where status = 0 and account = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                    return true;
                
            }
        } catch (Exception e) {
            System.out.println("checkStatus" + e.getMessage());
        }
        return false;
    }
}
