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
public class PaymentInfo {
    public int id;
    String cardNum;
    String payType;
    String expiration;
    String billAddress;
    String name;
    int security;
    double cost;
    public PaymentInfo(){
        
    }
    public static Ecommerce manager;
public int getID(Connection con) throws SQLException{
        Statement stmt = null;
        String query = "select MAX(`idPayment`)"
                + "from payment";
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
    public void viewTable(Connection con, String dbName) throws SQLException {
        Statement stmt = null;
        String query = "select idPayment, Payment Type, Card Number, Expiring Date, Billing Address, Name on Card, Security number, Cost"
                + "from " + dbName + ".payment";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int payid = rs.getInt("idPayment");
                String ptype = rs.getString("Payment Type");
                String cardnum = rs.getString("Card Number");
                String expdate = rs.getString("Expiring Date");
                String billaddr = rs.getString("Billing Address");
                String cardname = rs.getString("Name on Card");
                int sn = rs.getInt("Security number");
                double pcost = rs.getDouble("Cost");
                System.out.println(payid + "\t" + ptype + "\t" + cardnum + "\t" + expdate + "\t" + billaddr + "\t" + cardname + "\t"+ sn + "\t" + pcost);
            }
        } catch (SQLException e) {

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public String viewByID(Connection con, int id)throws SQLException{
        String ham = "\nPaymentID\tPayment Type\tCardNumber\t Expiration Date\t Billing Address \tName On Card\t\t Security Number\t Cost";
        Statement stmt = null;
         String query = "select `idPayment`, `Payment Type`, `Card Number`, `Expiring Date`, `Billing Address`, `Name on Card`, `Security number`, `Cost`"
                + "from payment WHERE idPayment="+id;
         try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int payid = rs.getInt("idPayment");
                String ptype = rs.getString("Payment Type");
                String cardnum = rs.getString("Card Number");
                String expdate = rs.getString("Expiring Date");
                String billaddr = rs.getString("Billing Address");
                String cardname = rs.getString("Name on Card");
                int sn = rs.getInt("Security number");
                double pcost = rs.getDouble("Cost");
                ham += "\n"+(payid + "\t\t" + ptype + "\t\t" + cardnum + "\t\t" + expdate + "\t\t" + billaddr + "\t\t" + cardname + "\t\t"+ sn + "\t\t" + pcost);
            }
        } catch (SQLException e) {

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            return ham;
        }
    }
    public void removePayment(Connection con, int payid) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM payment WHERE idPayment = ?");
            pstmt.setInt(1, payid);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPayment(Connection con, String ptype, String cardnum, String expDate, String billaddr, String noc, int secure, double cost) throws SQLException {
        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into payment "
                    + "values(0,?, ?, ?, ?, ?, ?, ?)");
            
            pstmt.setString(1, ptype);
            pstmt.setString(2, cardnum);
            pstmt.setString(3, expDate);
            pstmt.setString(4, billaddr);
            pstmt.setString(5, noc);
            pstmt.setInt(6, secure);
            pstmt.setDouble(7, cost);
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
    public void updatePaymentType(Connection con, String type, int idPayment)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE payment " +
                        "SET Payment Type = ? " +
                        "WHERE idPayment = ?");
            pstmt.setString(1, type);
            pstmt.setInt(2,idPayment);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updateCardNumber(Connection con, String card, int idPayment)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE payment " +
                        "SET Card Number = ? " +
                        "WHERE idPayment = ?");
            pstmt.setString(1, card);
            pstmt.setInt(2,idPayment);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }

}
