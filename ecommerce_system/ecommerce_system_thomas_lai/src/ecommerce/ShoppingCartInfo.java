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
public class ShoppingCartInfo {
    public int id = -1;
    public double prices = 0;
    public int getID(Connection con) throws SQLException{
        Statement stmt = null;
        String query = "select MAX(`idCart`)"
                + "from `shopping cart`";
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
    String query = "select `idCart`,  `Total Price`, `Contains_idContains`, `Customer_idCustomer`" +
                   "from `shopping cart`";
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int scID = rs.getInt("idCart");
            String customer = rs.getString("Customer_idCustomer");
            //int hasID = rs.getInt("Shopping Cart_has_Inventory_Shopping Cart_Contains_idContains"); 
            int iQuantity = rs.getInt("Contains_idContains");
            double iprice = rs.getDouble("Total Price");
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            System.out.println(scID + "\t" + customer + "\t" + iQuantity +
                               "\t" + iprice);
        }
    } catch (SQLException e ) {
        System.out.println(e);
    } finally {
        if (stmt != null) { stmt.close(); }
    }
}
    public void removeCart(Connection con, int itemid) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM `shopping cart` WHERE idCart = ?");
            pstmt.setInt(1, itemid);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addCart(Connection con, String dbName,int incr, int customernum) throws SQLException{
            PreparedStatement pstmt;
    try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into `shopping cart` " +
            "values(0,0,?,?)");
            //pstmt.setDouble(1,Math.abs(pr));
            pstmt.setInt(1,incr);
            pstmt.setInt(2, customernum);
            
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
    public void updateTotalPrice(Connection con, String price, int idCustomer)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE `shopping cart` " +
                        "SET `Total Price` = ? " +
                        "WHERE idCart = ?");
            pstmt.setString(1, price);
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
