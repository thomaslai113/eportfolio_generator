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
public class ShipmentInfo {
    public int id;
    public double price;
    public int getID(Connection con) throws SQLException{
        Statement stmt = null;
        String query = "select MAX(`idShipping`)"
                + "from shipping";
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
    public String viewByID(Connection con, int id)throws SQLException{
        String ham = "\nShipping Id\tDelivery Company\tDelivery Type\tExpenses\tAddress";
       Statement stmt = null;
    String query = "select `idShipping`, `Delivery Company`, `Delivery Type`, `Expenses`, `Tracking Number`" +
                   "from `shipping` WHERE idShipping="+id;
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int scID = rs.getInt("idShipping");
            String customer = rs.getString("Delivery Company");
            String hasID = rs.getString("Delivery Type"); 
            double iQuantity = rs.getInt("Expenses");
            String iprice = rs.getString("Tracking Number");
            
            ham += "\n"+scID + "\t\t" + customer + "\t\t" + hasID + "\t\t" + iQuantity +
                               "\t\t" + iprice;
        }
    } catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (stmt != null) { stmt.close(); }
        return ham;
    }
    }
    
    public void addShipping(Connection con,String dcom, String dtype, double expense, String trackingnum)throws SQLException{
        PreparedStatement pstmt;
    try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into shipping " +
            "values(0, ?, ?, ?, ?)");
            
            pstmt.setString(1, dcom);
            pstmt.setString(2,dtype);
            pstmt.setDouble(3,expense);
            pstmt.setString(4,trackingnum);
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
            con.setAutoCommit(true);
    } catch (SQLException e) {
        System.out.println(e);
    } finally {
        if (con != null) con.close();
    }
    }
}
