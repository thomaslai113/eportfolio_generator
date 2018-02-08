/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author PropOfThomas
 */
public class Ecommerce {
    public GridPane gPane;
    public CustomerInfo customer = new CustomerInfo();
    public PaymentInfo payment = new PaymentInfo();
    public ShipmentInfo shipping = new ShipmentInfo();
    public ShoppingCartInfo shopping = new ShoppingCartInfo();
    public Has shoppingcart = new Has();
    public OrderInfo order = new OrderInfo();
    //public ShoppingCartInfo shopper;
    public InventoryItem invent = new InventoryItem();
    public String dbms = "mysql";
    public String jarFile;
    public String dbName = "ecommercesystem";
    public String userName = "root";
    public String password = "thomaslai";
    public String urlString;
    private String driver = "com.mysql.jdbc.Driver";
    private String serverName = "localhost";
    private int portNumber = 3306;
    private Properties prop;
    
    public Connection conn = null;
    /**
     * @param args the command line arguments
     */
    
    public Connection getConnection() throws SQLException {
        try{
    Class.forName("com.mysql.jdbc.Driver");  
        }
        catch(Exception e)
        {
            System.out.println("whatttt");
        }
    conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", this.userName);
    connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercesystem","root","thomaslai");
    System.out.println("Connected to database");
    return conn;
}
    /*public static void main(String[] args) {
        // TODO code application logic here
        //Ecommerce j = new Ecommerce();
        EcommerceUI kale = new EcommerceUI();
        kale.initUI();
        /*try{
            j.conn = j.getConnection();
        if(j.conn==null){
            System.out.println("No Database");
        }
        else
            System.out.println("PERFECT");
            InventoryItem xm = new InventoryItem();
            xm.addItem(j.conn, j.dbName, "Wither", "Book", "Amazon", 1, -9.5);
            xm.viewTable(j.getConnection(), j.dbName);
            xm.updateName(j.conn, "Sp", 2);
            xm.viewTable(j.getConnection(), j.dbName);
        }
        catch(SQLException e){
            System.out.println(e);
        }*/
    //}
    
}
