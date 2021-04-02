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
//9. 
public class Main implements Serializable{
    static Connection con;
    static ObjectOutputStream objectOutputStream = null;
    static ObjectInputStream objectInputStream = null;
    static InputStream inputStream = null;
    static OutputStream outputStream;
    private static final long serialVersionUID = 6529685098267757691L;
    static Pair<Object,Integer>datas;
    
    
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
                
                if(keyvalue==1){
                insertMeal((Pair)pairObj.getKey());
                }
                
                if(keyvalue==2){
                    
                    BusinessManager bs = CheckLoginManager((Pair)pairObj.getKey());
                    System.out.println("kuldes elotttttt");
                    
//                    for (int i=0; i<4;i++){
//                        
//                        for (int j=0; j<bs.getManagedRestaurant().getMenu().get(i).getMeals().size();j++){
//                            if (bs.getManagedRestaurant().getMenu().get(i).getMeals().get(j)!=null){
//                            System.out.println(bs.getManagedRestaurant().getMenu().get(i).getMeals().get(j).getName());
//                            System.out.println(bs.getManagedRestaurant().getMenu().get(i).getMeals().get(j).getMenuID());
//                        }
//                    }
//                    }
                    
                    
                    
                    System.out.println(bs.getUsername()+bs.getPassword());
                    objectOutputStream.writeObject(bs);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                    System.out.println("kuldes utan");
                }
                if(keyvalue==3){
                    Guest g = CheckLoginGuest((Pair)pairObj.getKey());
                    //objectOutputStream.writeObject(g);
                    //objectOutputStream.flush();
                    //objectOutputStream.reset();
//                    try {
//                        sleep(600);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    List<Restaurant> l = RestaurantQuery();
                    List<Menu> temp;
                    for (int i=0; i<l.size();i++){    
                    temp=MenuQuery(l.get(i));
                    l.get(i).setMenu(temp);
                    }
                    
                    System.out.println(l.get(0).getMenu().size());
                    Pair<Guest,List<Restaurant>> p = new Pair<>(g,l);
                    objectOutputStream.writeObject(p);
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
                //if(pairObj.getKey().getClass().getSimpleName().equals("Menu")){
                //if(keyvalue==1){
                //    insertMenu((Menu)pairObj.getKey());
                //}
                //if(keyvalue==0){
                  //  int id = getRestaurantID((Restaurant) pairObj.getKey());
                   // objectOutputStream.writeObject(id);
                //}
                //}
                if(pairObj.getKey().getClass().getSimpleName().equals("Meal")){
                if(keyvalue==1){
                    //insertMeal((Meal)pairObj.getKey());
                }
                //if(keyvalue==0){
                  //  int id = getRestaurantID((Restaurant) pairObj.getKey());
                   // objectOutputStream.writeObject(id);
                //}
                }
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
                            System.out.println("Étterme lekérdezve:");
                        System.out.println(businessManager.getManagedRestaurant().getRestaurantID()+""+businessManager.getManagedRestaurant().getRestaurantName()+" "+businessManager.getManagedRestaurant().getRestaurantAddress()+" "+businessManager.getManagedRestaurant().getOpenHours()+" "+businessManager.getManagedRestaurant().getManagerID());
                        businessManager.getManagedRestaurant().setMenu(MenuQuery(businessManager.getManagedRestaurant()));
                        }
                        businessManager.setManagerID(rs.getInt(1));  
                    }
        }
    catch (SQLException ex) {   
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return businessManager;
    }
    
    static Guest CheckLoginGuest(Pair p){
        Guest g=null;
    try {
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from Guest where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
        if (rs.next()){
            g = new Guest(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            g.setGuestID(rs.getInt(1));
            }   
        }
    catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return g;
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
    System.out.println("manager insert kész");
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
            Statement stmt=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Restaurant(RestaurantName,address,openHours,managerID)VALUES(?,?,?,?)");
        stmt.setString(1, c.getRestaurantName());
        stmt.setString(2, c.getRestaurantAddress());
        stmt.setString(3, c.getOpenHours());
        stmt.setInt(4,c.getManagerID());
        
        stmt.executeUpdate();
        
            //ResultSet rs2=stmt.executeQuery("select count(restaurantID) from Restaurant");
            //rs2.next();
            //System.out.println(rs2.getInt(1));
            System.out.println("be lett insertálva az étterem: "+c.getManagerID()+" "+c.getRestaurantName()+" "+ c.getRestaurantAddress()+" "+ c.getOpenHours()+" "+ c.getManagerID());
        insertMenu(c.getRestaurantID());
        System.out.println("Menü beinzertálása megtörtént");
        //insertMenu(rs2.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    static void insertMenu(int RestaurantID){
    try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Menu(restaurantID,categoryID)VALUES(?,?),(?,?),(?,?),(?,?)");
        stmt.setInt(1,RestaurantID);
        stmt.setInt(3,RestaurantID);
        stmt.setInt(5,RestaurantID);
        stmt.setInt(7,RestaurantID);
        stmt.setInt(2,1); //előétel
        stmt.setInt(4,2); //főétel
        stmt.setInt(6,3); //desszert
        stmt.setInt(8,4); //italok
        stmt.executeUpdate();
        System.out.println("Menü kategóriák beinzertálása megtörtént");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    static void insertMeal(Pair p){
    try {
        Meal c = (Meal)p.getKey();
        int id = (int) p.getValue();
        System.out.println("Kaja beinzertálás előtt");
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Food(name,ingredients,allergenes,price,menuID)VALUES(?,?,?,?,?)");
        stmt.setString(1, c.getName());
        stmt.setString(2, c.getIngredients());
        stmt.setString(3, c.getAllergens());
        stmt.setInt(4,c.getCost());
        stmt.setInt(5,c.getMenuID());
        stmt.executeUpdate();
        System.out.println("Kaja beinzertálása megtörtént");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    //-----------------------------Queries
    static List<Restaurant> RestaurantQuery(){
        ResultSet rs=null;
        List<Restaurant> list = new ArrayList<Restaurant>();
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Restaurant");
            while(rs.next()){
                list.add(new Restaurant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
                
}
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    static List<Menu> MenuQuery(Restaurant r){
        ResultSet rs=null;
        List<Menu> menulista=new ArrayList<Menu>();
        int id = r.getRestaurantId();
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Menu where restaurantID = "+id);
            while (rs.next()){
                System.out.println("menük kiiratása: ");
                System.out.println(rs.getInt(3)-1+" "+rs.getInt(2)+" "+rs.getInt(1)+" "+r.getRestaurantID());
                menulista.add(new Menu(rs.getInt(1),rs.getInt(3)-1,rs.getInt(2),FoodQuery(rs.getInt(3),r.getRestaurantID())));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menulista;
    }
    
    static List<Meal> FoodQuery(int id,int resid){
        ResultSet rs=null;
        List<Meal> list = new ArrayList<Meal>();
        
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Food join Menu on Menu.menuID=Food.menuID where Menu.categoryID = "+id+" AND Menu.restaurantID="+resid);
            
            while(rs.next()){
            System.out.println("kaják kiiratása: ");
            System.out.println(rs.getString(2)+" "+rs.getInt(5)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(6));
            list.add(new Meal(rs.getString(2),rs.getInt(5),rs.getString(3),rs.getString(4),rs.getInt(9),rs.getInt(6)));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /*
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