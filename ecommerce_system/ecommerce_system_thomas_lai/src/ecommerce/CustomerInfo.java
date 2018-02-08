/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PropOfThomas
 */
public class CustomerInfo {

    public static Ecommerce Manager;
    public int id;
    public CustomerInfo() {
        super();
    }
    public int getID(Connection con) throws SQLException{
        Statement stmt = null;
        String query = "select MAX(`idCustomer`)"
                + "from customer";
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return id;
        
    }
    public String getCustomerByID(Connection con, String dbName, int idn) throws SQLException{
       // Statement stmt = null;
        String ans = "DOES NOT EXIST";
         PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("select `idCustomer`, `First Name`, `Last Name`, `Address`, `Phone Number`"
                + "from customer WHERE idCustomer=?;");
            pstmt.setInt(1, idn);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String cFName = rs.getString("First Name");
                String cLName = rs.getString("Last Name");
                int cID = rs.getInt("idCustomer");
                String cAddress = rs.getString("Address");
                String cPNumber = rs.getString("Phone Number");
                ans = (cFName + "\t" + cLName + "\t" + cID + "\t" + cAddress + "\t" + cPNumber);
            }
            con.commit();
            pstmt.close();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
         return ans;
    }
    public void viewTable(Connection con, String dbName) throws SQLException {
        Statement stmt = null;
        String query = "select `idCustomer`, `First Name`, `Last Name`, `Address`, `Phone Number`"
                + "from customer";
                
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String cFName = rs.getString("First Name");
                String cLName = rs.getString("Last Name");
                int cID = rs.getInt("idCustomer");
                String cAddress = rs.getString("Address");
                String cPNumber = rs.getString("Phone Number");
                System.out.println(cID + "\t" + cFName + "\t" + cLName + "\t" + cAddress + "\t" + cPNumber);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public void removeCustomer(Connection con, int itemid) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM customer WHERE idCustomer = ?");
            pstmt.setInt(1, itemid);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addCustomer(Connection con, String dbName, String fn, String ln, String addr, int pn) throws SQLException {
        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into customer "
                    + "values(0,?, ?, ?, ?)");
            //pstmt.setString(1, dbName);
            pstmt.setString(1, fn);
            pstmt.setString(2, ln);
            pstmt.setString(3, addr);
            pstmt.setInt(4, pn);
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    public void updateFirstName(Connection con, String fn, int idCustomer)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE customer " +
                        "SET First Name = ? " +
                        "WHERE idCustomer = ?");
            pstmt.setString(1, fn);
            pstmt.setInt(2,idCustomer);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updateLastName(Connection con, String ln, int idCustomer)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE customer " +
                        "SET Last Name = ? " +
                        "WHERE idCustomer = ?");
            pstmt.setString(1, ln);
            pstmt.setInt(2,idCustomer);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updateAddress(Connection con, String caddr, int idCustomer)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE customer " +
                        "SET Address = ? " +
                        "WHERE idCustomer = ?");
            pstmt.setString(1, caddr);
            pstmt.setInt(2,idCustomer);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    
        public void updatePhoneNumber(Connection con, String pnbr, int idCustomer)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE customer " +
                        "SET Phone Number = ? " +
                        "WHERE idCustomer = ?");
            pstmt.setString(1, pnbr);
            pstmt.setInt(2,idCustomer);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
}
