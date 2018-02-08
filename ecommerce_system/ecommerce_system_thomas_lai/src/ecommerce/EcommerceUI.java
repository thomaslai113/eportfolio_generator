/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.*;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PropOfThomas
 */
public class EcommerceUI extends Application {
    public static Ecommerce Manager = new Ecommerce();
    public Stage mainStage;
    public Scene mainScene;
    public BorderPane rootPane = new BorderPane();
    public GridPane gridPane;
    public EventHandler EvntHndlr;
    public TextField testField,RegFirName,RegLastName,RegAddress,RegPhoneNum;
    public Label CustomerIDLabel,RegFirLabel,RegLastLabel,RegAddrLabel,RegPhoneLabel,AppTitle;
    public Button CreateAccButton,SubmitButton,RegisterButton,BackToMain;
    public String firstname, lastname, address;
    public int phonenumber;
    public BorderPane holderPane;
    public Label DelCompanyLabel;
    public TextField DelCompany;
    public Label DelTypeLabel;
    public TextField DelType;
    public Label ExpenLabel;
    public TextField Expenses;
    //public Button BackToMain;
    public Button pressy;
    public ComboBox combobox;
    public ObservableList<String> options;
    public TextField ShipAddr;
    public EcommerceUI(){
    }
    public void start(Stage primaryStage){
     mainStage = primaryStage;
     //Manager = new Ecommerce();
     initUI();
    }
    public void initUI(){
        try{
        Manager.conn = Manager.getConnection();}catch(Exception e){System.out.println(e);}
        rootPane = new BorderPane();
        holderPane = new BorderPane();
        mainStage = new Stage();
        mainScene = new Scene(rootPane,1000,700);
        gridPane = new GridPane();
        Manager.gPane = gridPane;
        testField = new TextField();
        AppTitle = new Label("Welcome to CSE 305 Ecommerce Application");
        AppTitle.setFont(new Font("Garamond", 32));
        CustomerIDLabel = new Label();
        CustomerIDLabel.setText("Enter your Customer ID:");
        SubmitButton = new Button();
        SubmitButton.textProperty().set("SUBMIT");
        RegisterButton = new Button();
        RegisterButton.textProperty().set("REGISTER");
        CreateAccButton = new Button("CREATE ACCOUNT");
        BackToMain = new Button("BACK");
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        
        //options = new FXCollections().toString();
                
                /*.observableArrayList(
        "Option 1",
        "Option 2",
        "Option 3"
    );*/
        
        RegisterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent Me)
        {
        RegisterSetup();
        }
        }
        );
        CreateAccButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent Me)
        {
        CreateAccount();
        }
        }
        );
        SubmitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent Me)
        {
        CheckID();
        }
        }
        );
        holderPane.setCenter(AppTitle);
        CustomerIDLabel.setFont(new Font("Garamond", 24));
        SubmitButton.setFont(new Font("Garamond", 18));
        RegisterButton.setFont(new Font("Garamond", 18));
        BackToMain.setFont(new Font("Garamond", 18));
        BackToMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent Me)
        {homeScreenSetup();}});
        
        homeScreenSetup();
        //gridPane.setVgap(20);
       // rootPane.getChildren().add(gridPane);
        RegFirLabel = new Label("First Name:");
        RegFirName = new TextField();
        RegLastLabel = new Label("Last Name:");
        RegLastName = new TextField();
        RegAddrLabel = new Label("Address:");
        RegPhoneLabel = new Label("Phone Number:");
        RegLastName = new TextField();
        RegAddress = new TextField();
        RegPhoneNum = new TextField();
       
        rootPane.topProperty().set(holderPane);
        rootPane.centerProperty().set(gridPane);
        gridPane.alignmentProperty().set(Pos.CENTER);
//        rootPane.leftProperty().set(gridPane);//Silly idea
        
        mainStage.setScene(mainScene);
        mainStage.setTitle("305 Ecommerce application");
        mainStage.show();
    }
        public static void main(String[] args){
            //Ecommerce x = new Ecommerce();
            Application.launch(args);
        }
    public void homeScreenSetup()
        {
            gridPane = Manager.gPane;
        gridPane.getChildren().clear();
        gridPane.add(CustomerIDLabel, 0, 1);
        gridPane.add(testField, 1, 1);
        gridPane.add(SubmitButton,0,3);
        gridPane.add(RegisterButton, 0, 4);
        //gridPane.add(TempShip,0,4);}
        //gridPane.add(combobox,0,5);
    }
    public void generateOrderScene(){
        //Scene orderScene = null;
        
        int custId  = Manager.customer.id;
        int shoppingcartId = Manager.shopping.id;
        BorderPane mainPane = new BorderPane();
        VBox secondary = new VBox();
        LocalDate today = LocalDate.now( ZoneId.of( "-05:00" ) ) ;
        Date y = new Date();
        //String date_s = y.toString(); 
        //SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
        String xp = "";
        try{
            //Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");
        System.out.println(dt1.format(y));
        xp = dt1.format(y);
        }catch(Exception e){System.out.println(e);
        }
        
        
        
        
        Label customerText  = new Label("Customer ID: " + custId);
        Label shoppingText = new Label("Shopping cart ID: " + shoppingcartId);
        try{
        Label cartText = new Label("ITEMS BEING PURCHASED: \n"+ Manager.shoppingcart.viewByID(Manager.getConnection(), Manager.shopping.id));
        Label shipText = new Label("Shipping details:\n" + Manager.shipping.viewByID(Manager.getConnection(), Manager.shipping.id));
        Label payText = new Label("Billing Details: \n" + Manager.payment.viewByID(Manager.getConnection(), Manager.payment.id));
        Manager.order.addOrder(Manager.getConnection(), false,Manager.customer.id,Manager.payment.id,Manager.shopping.id,Manager.shipping.id,"1111-11-11");
        Manager.order.id = Manager.order.getID(Manager.getConnection());
        Label orderText = new Label("Your Order Number is: " + Manager.order.id);
        secondary.getChildren().add(orderText);
        secondary.getChildren().add(customerText);
        secondary.getChildren().add(shoppingText);
        secondary.getChildren().add(cartText);
        secondary.getChildren().add(shipText);
        secondary.getChildren().add(payText);
        mainPane.setCenter(secondary);
        gridPane = Manager.gPane;
        gridPane.getChildren().clear();
        gridPane.add(mainPane,0,0);
        //orderScene = new Scene(mainPane);
        }catch(Exception e){System.out.println(e);}
        //return orderScene;
        
    }
    public void ShippingSetup()
        {
            Manager.shipping.price = 0;
             DelCompanyLabel = new Label("Delivery Company:");
            DelCompany = new TextField();
        DelTypeLabel = new Label("Delivery Type:");
        DelType = new TextField();
        ExpenLabel = new Label("Address:");
        Expenses = new TextField();
        //ShipAddr = new TextField();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        options = observableArrayList;
        options.add("FedEx");
        options.add("USPS");
        options.add("UPS");
        combobox = new ComboBox(options);
        pressy = new Button("Finalize shipping");
        pressy.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent Me)
        {
            String dtype = "";
            if(DelType.getText().equals("Premium")){
                Manager.shipping.price+=15;
                dtype = "Premium";
            }
            if(combobox.getValue() == "FedEx"){
                Manager.shipping.price += 5;
            }
            else if(combobox.getValue() == "USPS"){
                Manager.shipping.price += 3;
            }
            else if(combobox.getValue() == "UPS"){
                Manager.shipping.price += 10;
            }
            try{
                Manager.shipping.addShipping(Manager.getConnection(), combobox.getValue().toString(), dtype, Manager.shipping.price, Expenses.getText());
                Manager.shipping.id = Manager.shipping.getID(Manager.getConnection());
                Manager.shopping.prices = Manager.shoppingcart.getTotalPrice(Manager.getConnection(), Manager.shopping.id);
                if(Manager.shopping.prices>0){
                    GoToPayment();
                }
            }
            catch(Exception e){System.out.println(e);}
            
        }});
            BackToMain = new Button("BACK");
            BackToMain.setFont(new Font("Garamond", 18));
            BackToMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent Me)
            {GoToCart();}});
            gridPane = Manager.gPane;
            gridPane.getChildren().clear();
            gridPane.add(DelCompanyLabel, 0, 1);
            gridPane.add(DelCompany,1,0);
            gridPane.add(DelTypeLabel, 0, 0);
            gridPane.add(combobox,1,1);
            gridPane.add(ExpenLabel, 0, 2);
            gridPane.add(Expenses,1,2);
//            gridPane.add(RegAddrLabel,0,3);
//            gridPane.add(ShipAddr, 1, 3);
            gridPane.add(BackToMain,0,4);
            gridPane.add(pressy,1,4);
            
        }
    public void RegisterSetup()
        {
        gridPane.getChildren().remove(SubmitButton);
        gridPane.getChildren().remove(testField);
        gridPane.getChildren().remove(RegisterButton);
        gridPane.getChildren().remove(CustomerIDLabel);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        RegFirLabel.setFont(new Font("Garamond", 18));
        gridPane.add(RegFirLabel, 0, 0);
        gridPane.add(RegFirName, 0, 1);
        RegLastLabel.setFont(new Font("Garamond", 18));
        gridPane.add(RegLastLabel,0, 2);
        gridPane.add(RegLastName, 0, 3);
        gridPane.add(RegAddress, 0,5);
        RegAddrLabel.setFont(new Font("Garamond", 18));
        gridPane.add(RegAddrLabel, 0, 4);
        RegPhoneLabel.setFont(new Font("Garamond", 18));
        gridPane.add(RegPhoneLabel,0, 6);
        gridPane.add(RegPhoneNum, 0, 7);
        CreateAccButton.setFont(new Font("Garamond", 18));
        gridPane.add(CreateAccButton, 0,9);
        gridPane.add(BackToMain, 0, 10);
        }
        
   /* public static void main(String[] args){
        EcommerceUI h = new EcommerceUI();
        h.initUI();
    }*/
    public void CreateAccount(){
        firstname = RegFirName.getText();
        lastname = RegLastName.getText();
        address = RegAddress.getText();
        phonenumber = Integer.parseInt(RegPhoneNum.getText());
        try{
        Manager.customer.addCustomer(Manager.getConnection(),Manager.dbName,firstname,lastname,address,phonenumber);
        Manager.customer.viewTable(Manager.getConnection(), Manager.dbName);
        Manager.customer.id = Manager.customer.getID(Manager.getConnection());
        Label cusid = new Label("Welcome your customer ID is: "+Manager.customer.id);
        cusid.setFont(new Font("Garamond", 18));
        gridPane.add(cusid,2,4);
        System.out.println(Manager.customer.id);
        Manager.customer.viewTable(Manager.getConnection(), Manager.dbName);
        GoToCart();
        }
        catch(Exception e){System.out.println(e);}
    }
    public void CheckID(){
        int identif = 0;
        String cust = "";
        try{
            identif = Integer.parseInt(testField.getText());
        System.out.println(identif);
    }catch(Exception e){testField.setText("Invalid ID code");}
        try{cust = Manager.customer.getCustomerByID(Manager.getConnection(), Manager.dbName, identif);}
        catch(Exception e){System.out.println("Cannot find Existing Customer ID\n"+e);testField.setText("Invalid ID code");}
        System.out.println(cust);
        if(cust.equals("DOES NOT EXIST")){
            testField.setText("Invalid ID code");
        }else{
        Manager.customer.id = identif;
        GoToCart();
        }
    }
    
   
    
    
    /*==============================================================*/
    @FXML
    private TextArea shopping_cart_text;

    @FXML
    private Button purchasebutton;

    @FXML
    private TextField item_id_textfield;

    @FXML
    private Button addItemButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private TextArea inventoryTextfield;

    @FXML
    public void addItem(MouseEvent event) {
        int itemid = Integer.parseInt(item_id_textfield.getText());
        if(Manager.shopping.id == -1){
            try{Manager.shopping.addCart(Manager.getConnection(),Manager.dbName,Manager.shoppingcart.id,Manager.customer.id);
            Manager.shopping.viewTable(Manager.getConnection(),Manager.dbName);
            Manager.shopping.id = Manager.shopping.getID(Manager.getConnection());
            System.out.println(Manager.shopping.id);
            }catch(Exception e){System.out.println(e);}
        }
        try{
            double pr = Manager.invent.getPrice(Manager.getConnection(),itemid );
            Manager.shoppingcart.addItemToCart(Manager.getConnection(), Manager.shopping.id, itemid, 1, pr);
            shopping_cart_text.setText(Manager.shoppingcart.viewByID(Manager.getConnection(), Manager.shopping.id));
        }
        catch(Exception e){System.out.println(e);}
    }

    @FXML
    public void makePurchase(MouseEvent event) {
            if(Manager.shopping.id  == -1){
                shopping_cart_text.setText("Please add at least 1 item");
            }else{
                /*
            */
             ShippingSetup();   
            }
    }

    @FXML
    public void removeItem(MouseEvent event) {
        int itemid = Integer.parseInt(item_id_textfield.getText());
        if(Manager.shopping.id == -1){
            shopping_cart_text.setText("No Items to remove!");
        }
        try{
            
            Manager.shoppingcart.removeItemFromCart(Manager.getConnection(), Manager.shopping.id, itemid);
            System.out.println("Removing...");
            Manager.shoppingcart.viewTable(Manager.getConnection(), Manager.dbName);
            shopping_cart_text.setText(Manager.shoppingcart.viewByID(Manager.getConnection(), Manager.shopping.id));
            if((Manager.shoppingcart.viewByID(Manager.getConnection(), Manager.shopping.id).equals(new String("Item ID\tQuantity\t\tPrice")))){
                Manager.shopping.removeCart(Manager.getConnection(), Manager.shopping.id);
                Manager.shopping.id = -1;
                shopping_cart_text.setText("Empty Cart");
            }
        }
        catch(Exception e){System.out.println(e);}
    }
public void GoToCart(){
        gridPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventoryUI.fxml"));
            //loader.setRoot(gridPane);
            //loader.setController(this);
            Parent rose= loader.load();
            gridPane.add(rose,0,0);
            //Manager.invent.addItem(Manager.getConnection(), Manager.dbName, "Wild West","Book", "Barnes And Noble", 5, 17.50);
            //inventoryTextfield.setText(Manager.invent.viewTable(Manager.getConnection(), Manager.dbName));
        }
        catch(Exception e){System.out.println(e);}
        
    }
public void displayItems(){
    try{inventoryTextfield.setText(Manager.invent.viewTable(Manager.getConnection(), Manager.dbName));}
    catch(Exception e){System.out.println(e);}
}

 public void GoToPayment(){
     gridPane = Manager.gPane;
        gridPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("paymentUI.fxml"));
            //loader.setRoot(gridPane);
            //loader.setController(this);
            Parent rose= loader.load();
            gridPane.add(rose,0,0);
            
            //cartTotalField.setText("$ "+Manager.shopping.prices);
        }
        catch(Exception e){System.out.println(e);}
    }

    @FXML
    private Label cartTotalField;

    @FXML
    private TextField customerFirstnameField;

    @FXML
    private TextField customerLastnameField;

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField cardTypeField;

    @FXML
    private TextField cartNumberField;

    @FXML
    private TextField cardSecurityField;

    @FXML
    private TextField cardDateField;

    @FXML
    private Button backButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    public void goBackClicked(MouseEvent event) {
        ShippingSetup();
    }

    @FXML
    public void placeOrderClicked(MouseEvent event) {
        String namer = ""+ customerFirstnameField.getText()+" "+customerLastnameField.getText();
        try{
            Manager.payment.addPayment(Manager.getConnection(),cardTypeField.getText(),cartNumberField.getText(),cardDateField.getText(),customerAddressField.getText(),namer,Integer.parseInt(cardSecurityField.getText()),Manager.shopping.prices );
            Manager.payment.id = Manager.payment.getID(Manager.getConnection());
            System.out.println("Payment: "+ Manager.payment.id);
            generateOrderScene();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void updateLabel(){
        cartTotalField.setText("$ "+(Manager.shopping.prices+Manager.shipping.price));
    }
}
