/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
/**
 *
 * @author PropOfThomas
 */
public class InventoryItem {
    public static Ecommerce manager;
    private int id,quantity;
    private String name,type,seller;
    private double price;
    public InventoryItem(){
        super();
    }
    public InventoryItem(int itemId, String itemName, String itemType, String itemSeller, int itemQuantity, double itemPrice){
        id = itemId;
        quantity = itemQuantity;
        name = itemName;
        type = itemType;
        seller = itemSeller;
        price = itemPrice;
    }
    public String viewTable(Connection con, String dbName) throws SQLException {
        String silly = "ID\tName\tType\t\tSeller\t\tQuantity\t\tPrice";
    Statement stmt = null;
    String query = "select `Item Name`, `idInventory`, `Item Type`, `Seller`, `Quantity`, `Price`" +
                   "from inventory";
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String iName = rs.getString("Item Name");
            int iID = rs.getInt("idInventory");
            String iType = rs.getString("Item Type");
            String iSeller = rs.getString("Seller");
            int iQuantity = rs.getInt("Quantity");
            double iprice = rs.getDouble("Price");
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            silly += '\n'+(iID + "\t" + iName + "\t" + iType + "\t" + iSeller + "\t" + iQuantity +
                               "\t" + iprice);
            System.out.println(iID + "\t" + iName + "\t" + iType + "\t" + iSeller + "\t" + iQuantity +
                               "\t" + iprice);
        }
    } catch (SQLException e ) {
        System.out.println(e);
        silly = e.toString();
    } finally {
        if (stmt != null) { stmt.close(); }
    }
    return silly;
}
    public double getPrice(Connection con, int itemid){
        double x = 0;
        Statement stmt = null;
        String query = "select `Price`" +
                   "from inventory WHERE idInventory="+itemid;
        try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            double iprice = rs.getDouble("Price");
            if(iprice < 0){
                iprice = Math.abs(iprice);
            }
            x = iprice;
        }}catch(SQLException e){
                System.out.println(e);
                }
        return x;
    }
    /*=========================================================================DELETE ITEM=======================================================================================*/
    public void removeItem(Connection con, int itemid)throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM inventory WHERE idInventory = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        if (con != null) con.close();
    }
    }
    /*==========================================================================INSERT ITEM==========================================================================================*/
    public void addItem(Connection con, String dbName, String n, String t,String s, int q, double pr) throws SQLException{
            PreparedStatement pstmt;
    try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into inventory " +
            "values(0, ?, ?, ?, ?, ?)");
            
            pstmt.setString(2, n);
            pstmt.setString(3,t);
            pstmt.setString(1,s);
            pstmt.setInt(5,q);
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
    /*==========================================================================UPDATE ATTRIBUTES=================================================================================*/
     public void updateSeller(Connection con, String itemseller, int idItem)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE inventory " +
                        "SET Seller = ? " +
                        "WHERE idInventory = ?");
            pstmt.setString(1, itemseller);
            pstmt.setInt(2,idItem);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updateType(Connection con, String itemtype, int idItem)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE inventory " +
                        "SET Item Type = ? " +
                        "WHERE idInventory = ?");
            pstmt.setString(1, itemtype);
            pstmt.setInt(2,idItem);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updateQuantity(Connection con, int itemquantity, int idItem)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE inventory " +
                        "SET Quantity = ? " +
                        "WHERE idInventory = ?");
            pstmt.setInt(1, itemquantity);
            pstmt.setInt(2,idItem);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    
        public void updateName(Connection con, String itemname, int idItem)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE inventory " +
                        "SET `Item Name` = ? " +
                        "WHERE `idInventory` = ?");
            pstmt.setString(1, itemname);
            pstmt.setInt(2,idItem);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
    public void updatePrice(Connection con, double itemprice, int idItem)
        throws SQLException{

        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE Inventory " +
                        "SET Price = ? " +
                        "WHERE idInventory = ?");
            pstmt.setDouble(1, itemprice);
            pstmt.setInt(2,idItem);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
            con.setAutoCommit(true);

        } finally {
            if (con != null) con.close();
        }
    }
}
