package pkg8terem;

import java.io.DataInputStream;
import java.io.EOFException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

//0 ID-t kéri le
//1 insertáció/hozzáadás adatbázishoz
//2 Business (login ellenőrzés)
//3 Guest
//4 Courier
//5 létezik-e a név manager
//6 guest
//7 courier
//8 lekérdezés: 
public class Main implements Serializable{
    static Connection con;
    static ObjectOutputStream objectOutputStream = null;
    static ObjectInputStream objectInputStream = null;
    static InputStream inputStream = null;
    static OutputStream outputStream;
    private static final long serialVersionUID = 6529685098267757691L;
    
    
    public static void dostuff(ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream)throws IOException, ClassNotFoundException{
        
        Object c = objectInputStream.readObject();
        System.out.println("objektum megkapva");
        System.out.println(c.getClass().getSimpleName());
        
        if (c.getClass().getSimpleName().equals("Pair")){
            Pair pairObj = (Pair) c;
            int keyvalue = (int)pairObj.getValue();
        // -------------- létezik-e a név: ha létezik true
            if (pairObj.getKey().getClass().getSimpleName().equals("String")){
                if (keyvalue==5){
                    boolean exists = nameExists((String) pairObj.getKey(),"BusinessManager");
                    System.out.println("kuldes elott: ");
                    objectOutputStream.writeBoolean(exists);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if (keyvalue==6){
                    boolean exists=nameExists((String) pairObj.getKey(),"Guest"); 
                    objectOutputStream.writeBoolean(exists);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if (keyvalue==7){
                    boolean exists =nameExists((String) pairObj.getKey(),"Courier");
                    objectOutputStream.writeBoolean(exists);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
            }
        //---------------------CHECKLOGIN IFEK
            if (pairObj.getKey().getClass().getSimpleName().equals("Pair")){
                if(keyvalue==2){
                    
                    BusinessManager bs = CheckLoginManager((Pair)pairObj.getKey());
                    System.out.println("kuldes elotttttt");
                    System.out.println(bs.getUsername()+bs.getPassword());
                    objectOutputStream.writeObject(bs);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                    System.out.println("kuldes utan");
                }
                if(keyvalue==3){
                    boolean match = CheckLoginGuest((Pair)pairObj.getKey());
                    objectOutputStream.writeBoolean(match);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if(keyvalue==4){
                    /*boolean match = CheckLoginCourier((Pair)pairObj.getKey());
                    objectOutputStream.writeBoolean(match);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                    */
                }
            }
        // -----------------INSERTÁLÁS IFEK ÉS GET-IDK!
            if(pairObj.getKey().getClass().getSimpleName().equals("BusinessManager")){
                if(keyvalue==1){
                    insertManager((BusinessManager)pairObj.getKey());
                }
                if (keyvalue==0){
                    int id = getManagerID((BusinessManager) pairObj.getKey());
                    objectOutputStream.writeObject(id);
                }
            }
            if(pairObj.getKey().getClass().getSimpleName().equals("Guest")){
                if(keyvalue==1){
                    insertGuest((Guest)pairObj.getKey());
                }
                if(keyvalue==0){
                    int id = getGuestID((Guest) pairObj.getKey());
                    objectOutputStream.writeObject(id);
                }
            }
                if(pairObj.getKey().getClass().getSimpleName().equals("Restaurant")){
                if(keyvalue==1){
                    insertRestaurant((Restaurant)pairObj.getKey());
                }
                if(keyvalue==0){
                    int id = getRestaurantID((Restaurant) pairObj.getKey());
                    objectOutputStream.writeObject(id);
                }
                }
                /*if(pairObj.getKey().getClass().getSimpleName().equals("Courier")){
                if(keyvalue==1){
                    insert((Courier)pairObj.getKey());
                if(keyvalue==0){
                    int id = getRestaurantID((pairObj.getKey()) c);
                    objectOutputStream.writeObject(id);
                }
        }
                        */
        }
    }
    
    //----------------------CHECK LOGINOK!!!!!!!!-------
    static BusinessManager CheckLoginManager(Pair p){ 
        ResultSet rs=null;
        ResultSet rs2=null;
        BusinessManager businessManager=null;
    try {
        Statement stmt=con.createStatement();
        Statement stmt2=con.createStatement();
        
        rs=stmt.executeQuery("select * from BusinessManager left join Restaurant on Restaurant.managerID=BusinessManager.managerID where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
        if(rs.next())
                    { 
                        System.out.println(rs.getString(2));
                        businessManager = new BusinessManager(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                        if (rs.getInt(9)==0){
                            rs2=stmt2.executeQuery("select max(restaurantID) from Restaurant");
                            rs2.next();
                        businessManager.setManagedRestaurant(new Restaurant(rs2.getInt(1)+1," "," "," ",rs.getInt(1)));
                        }
                        else{
                        businessManager.setManagedRestaurant(new Restaurant(rs.getInt(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13)));
                        }
                        businessManager.setManagerID(rs.getInt(1));  
                    }
        }
    catch (SQLException ex) {   
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return businessManager;
    }
    
    static boolean CheckLoginGuest(Pair p){
    try {
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select username from Guest where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
        if (rs.next()){
            return true;
            }   
        }
    catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    /*static boolean CheckLoginCourier(Pair p){
       try {
                Statement stmt=con.createStatement();
                
                ResultSet rs=stmt.executeQuery("select username from Courier where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
                if (rs.next()){
                    return true;
                }   
            }
            catch (SQLException ex) {
                //return false;
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }
    */
    //------------------------INSERTÁLÁSOK!!
    static void insertManager(BusinessManager c){
    try {
        System.out.println("insertálás:");
        PreparedStatement stmt = con.prepareStatement("INSERT INTO BusinessManager(username,passwd,firstName,lastName,corporateName,email,registrationDate)VALUES(?,?,?,?,?,?,?)");

        stmt.setString(1, c.getUsername());
        stmt.setString(2, c.getPassword());
        stmt.setString(3, c.getFirstName());
        stmt.setString(4, c.getLastName());
        stmt.setString(5, c.getCorporationName());
        stmt.setString(6, c.getEmail());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        stmt.setDate(7,date);
        
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("insert kész");
    }
    
    static void insertGuest(Guest c){
    try {
        System.out.println("insertálás:");
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Guest(username,passwd,firstName,lastName,guestAddress,phoneNumber,registrationDate)VALUES(?,?,?,?,?,?,?)");
        stmt.setString(1, c.getUsername());
        stmt.setString(2, c.getPassword());
        stmt.setString(3, c.getFirstName());
        stmt.setString(4, c.getLastName());
        stmt.setString(5, c.getGuestAddress());
        stmt.setString(6, c.getPhoneNumber());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        stmt.setDate(7,date);
        
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("insert kész");
    }
    
    
    static void insertRestaurant(Restaurant c){
    try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Restaurant(RestaurantName,address,openHours,managerID)VALUES(?,?,?,?)");
        stmt.setString(1, c.getRestaurantName());
        stmt.setString(2, c.getRestaurantAddress());
        stmt.setString(3, c.getOpenHours());
        stmt.setInt(4,c.getManagerID());
        
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    /*
    static void insertMenu(Menu c){
    try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Menu(RestaurantID)VALUES(?)");
        stmt.setInt(1, c.getRestaurantID());
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    static void insertMeal(Meal c){
    try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Food(name,ingredients,allergenes,price,menuID)VALUES(?,?,?,?,?)");
        stmt.setString(1, c.getMealName());
        stmt.setString(2, c.getIngredients());
        stmt.setString(3, c.getAllergenes());
        stmt.setInt(4,c.getprice());
        stmt.setInt(5,c.getMenuID());
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    */
    //-----------------------------Queries
    static ResultSet RestaurantQuery(){
        ResultSet rs=null;
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Restaurant");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    static ResultSet MenuQuery(Restaurant r){
        ResultSet rs=null;
        int id = r.getRestaurantId();
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Menu where restaurantID = "+id);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    /*
    static ResultSet FoodQuery(Menu r){
        ResultSet rs=null;
        int id = r.getMenuId();
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Food where menuID = "+id);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    static ResultSet DiscountQuery(Food r){
        ResultSet rs=null;
        int id = r.getFoodId();
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Discounts where foodID = "+id);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    */
    
    //-----------------------------NÉV LÉTEZIK-E ha létezik = true
    static boolean nameExists(String nev,String tipus){
    try {
        Statement stmt=con.createStatement();
        //System.out.println("select username from "+ tipus +" where username like '" + nev + "'");
        ResultSet rs=stmt.executeQuery("select username from " + tipus +" where username like '" + nev + "'" );
        if (rs.next()){
            return true;
        }   
        }
    catch (SQLException ex) {
       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
        //--------GET ID'S
    static int getManagerID(BusinessManager b){
    String nev = b.getUsername();
    ResultSet rs;
    int retval =0;
    try {
        Statement stmt=con.createStatement();
        rs=stmt.executeQuery("select managerID from BusinessManager where username like'" + nev + "'" );
        retval = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    return retval;
        }
        
    static int getGuestID(Guest b){
    String nev = b.getUsername();
    ResultSet rs;
    int retval =0;
    try {
        Statement stmt=con.createStatement();
        rs=stmt.executeQuery("select guestID from Guest where username like'" + nev + "'" );
        retval = rs.getInt(1);  
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    return retval;
        }
         
    static int getRestaurantID(Restaurant b){
    String nev = b.getRestaurantName();
    ResultSet rs;
    int retval =0;
    try {
        Statement stmt=con.createStatement();
        rs=stmt.executeQuery("select restaurantID from Restaurant where username like'" + nev + "'" );
        retval = rs.getInt(1);  
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    return retval;
        }
        
    
    public static void main(String[] args)throws IOException, ClassNotFoundException  {
        boolean closeServer = false ;
        // SOCKET CSATLAKOZAS
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = new Socket();
                //ADATBÁZIS OLVASÁS:
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/8teremdb","root","root");
        //-----------------------------------------------------------------------------------

            if (closeServer==true){
                con.close();
            }
        }catch(Exception e){ System.out.println(e);}
    //SOCKET OLVASÁS:
        socket=ss.accept();
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        
        outputStream = socket.getOutputStream();
        objectOutputStream= new ObjectOutputStream(outputStream);
        
        do{
            try{
                dostuff(objectInputStream,objectOutputStream);
                }catch(EOFException e){
            System.out.println("No new message just yet");
            try {
                sleep(2000);
            }catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }while(closeServer!=true);
        //----------------------------------------------------------------------
        //HA VÉGE:
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();    
    }   
}