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
import java.sql.Types;

/**
 *
 * @author PropOfThomas
 */
public class OrderInfo {
    public int id;
    public int getID(Connection con) throws SQLException{
        Statement stmt = null;
        String query = "select MAX(`idOrder`)"
                + "from order";
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
    public void addOrder(Connection con, boolean completed, int custid, int payid, int cartid, int shipid, String date) throws SQLException {
        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("insert into order "
                    + "values(0, ?, ?, ?, ?, ?, ?)");
            //pstmt.setString(1, dbName);
            pstmt.setBoolean(1, completed);
            pstmt.setInt(2, custid);
            pstmt.setInt(3, payid);
            pstmt.setInt(4, cartid);
            pstmt.setInt(5, shipid);
            pstmt.setNull(6, Types.DATE);
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
}
