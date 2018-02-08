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
public class Has {
    public int id = 0;
    public String viewByID(Connection con, int id)throws SQLException{
        //viewTable(con,"ecommerce");
        String ham ="Item ID\t\tQuantity\t\tPrice";
       Statement stmt = null;
    String query = "select `Shopping Cart_idCart`, `Shopping Cart_Contains_idContains`, `Inventory_idInventory`, `Quantity`, `Price`" +
                   "from `shopping cart_has_inventory` WHERE `Shopping Cart_idCart`="+id;
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int scID = rs.getInt("Shopping Cart_idCart");
            int customer = rs.getInt("Shopping Cart_Contains_idContains");
            int hasID = rs.getInt("Inventory_idInventory"); 
            int iQuantity = rs.getInt("Quantity");
            double iprice = rs.getDouble("Price");
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            ham += "\n\t"+(/*scID + "\t" + customer + "\t" +*/ hasID + "\t\t" + iQuantity +
                               "\t\t" + iprice);
        }
    } catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (stmt != null) { stmt.close(); }
        
    }
    return ham;
    }
     public void viewTable(Connection con, String dbName) throws SQLException {
         
    Statement stmt = null;
    String query = "select `Shopping Cart_idCart`, `Shopping Cart_Contains_idContains`, `Inventory_idInventory`, `Quantity`, `Price`" +
                   "from `shopping cart_has_inventory`";
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int scID = rs.getInt("Shopping Cart_idCart");
            int customer = rs.getInt("Shopping Cart_Contains_idContains");
            int hasID = rs.getInt("Inventory_idInventory"); 
            int iQuantity = rs.getInt("Quantity");
            double iprice = rs.getDouble("Price");
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            
            System.out.println(scID + "\t" + customer + "\t" + hasID + "\t" + iQuantity +
                               "\t" + iprice);
        }
    } catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (stmt != null) { stmt.close(); }
    }
    
}
     public void removeItemFromCart(Connection con,int cartid, int itemid) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM `shopping cart_has_inventory` WHERE `Shopping Cart_idCart`=? AND `Inventory_idInventory`=?");
            pstmt.setInt(1, cartid);
            pstmt.setInt(2, itemid);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
     public void addItemToCart(Connection con, int shop, int item, int quantity, double pr) throws SQLException{
            PreparedStatement pstmt;
    try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into `shopping cart_has_inventory` " +
            "values(?, 0,?, ?, ?)");
            pstmt.setInt(1, shop);
            pstmt.setInt(2, item);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4,Math.abs(pr));
            
            
            
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
     public double getTotalPrice(Connection con, int cartid)throws SQLException{
         double dubs = 0;
         Statement stmt = null;
    String query = "select SUM(Price)" +
                   "from `shopping cart_has_inventory` WHERE `Shopping Cart_idCart`="+cartid;
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            double iprice = rs.getDouble(1);
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            dubs = iprice;
            System.out.println(iprice);
        }
    } catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (stmt != null) { stmt.close(); }
    }
    return dubs;
     }
}
