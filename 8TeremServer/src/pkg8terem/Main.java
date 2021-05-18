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
//9...
//ORDER és 1-es update order

//DISCOUNT 1 insert, 2 query, 3 update

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
        
        if (c.getClass().getSimpleName().equals("Order")){
            insertOrder((Order) c);
        }
        
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
                    Pair<BusinessManager,List<Order>> bs = CheckLoginManager((Pair)pairObj.getKey());
                    objectOutputStream.writeObject(bs);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if(keyvalue==3){
                    Guest g = CheckLoginGuest((Pair)pairObj.getKey());
                    List<Restaurant> l = RestaurantQuery();
                    List<Menu> temp;
                    for (int i=0; i<l.size();i++){        
                        temp=MenuQuery(l.get(i));
                        l.get(i).setMenu(temp);
                    }
                    Pair<Guest,List<Restaurant>> p = new Pair<>(g,l);
                    System.out.println("guest objekt kuldes elott: nev: " + p.getKey().getFirstName() + " restaurant id: "+p.getValue().get(0).getRestaurantID());
                    //System.out.println("elso kaja nev: "+p.getValue().get(0).getMenu().get(0).getMeals().get(0).getName());
                    objectOutputStream.writeObject(p);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if(keyvalue==4){
                    int [] sajt = new int[1];
                    Pair<List<Order>,Courier> match = CheckLoginCourier((Pair)pairObj.getKey());
                    if (match.getValue().getFirstName()==null){
                        System.out.println(sajt[-1]);
                    }
                    
                    objectOutputStream.writeObject(match);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                }
                if(keyvalue==5){
                List<Order> o = updateOrderCourier((Pair)pairObj.getKey());
                    objectOutputStream.writeObject(o);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                
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
                      if(keyvalue==2){
                        System.out.println("discount 2");
                        Restaurant tempres = (Restaurant)pairObj.getKey();
                        List<Discount> list = DiscountQuery(tempres.getRestaurantID());
                        objectOutputStream.writeObject(list);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                      if (keyvalue==3){
                        Restaurant r = (Restaurant) pairObj.getKey();
                        List <Menu> m = MenuQuery(r);
                        objectOutputStream.writeObject(m);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                      }
                }
                if(pairObj.getKey().getClass().getSimpleName().equals("Courier")){
                    if(keyvalue==1){
                        insertCourier((Courier)pairObj.getKey());
                    }
                    if(keyvalue==2){
                        System.out.println("courier update");
                        updateCourierAv((Courier)pairObj.getKey());
                    }
                }
                //ORDEREK
                if(pairObj.getKey().getClass().getSimpleName().equals("Order")){
                    System.out.println("Ordert kap");
                    if(keyvalue==1){
                        System.out.println("1-es");
                        List <Order> l = updateOrderStatus((Order)pairObj.getKey());
                        objectOutputStream.writeObject(l);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                    if(keyvalue==2){
                    updateOrderEstimatedTime((Order) pairObj.getKey());
                    }
                }
                //DISCOUNT
                if(pairObj.getKey().getClass().getSimpleName().equals("Discount")){
                    if(keyvalue==1){
                        System.out.println("discount 1");
                        List<Discount> list = insertDiscount((Discount)pairObj.getKey());
                        objectOutputStream.writeObject(list);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                    if(keyvalue==3){
                        List<Discount> list=deleteDiscount((Discount)pairObj.getKey());
                        System.out.println("discount delete kuldese elott: +");
                        objectOutputStream.writeObject(list);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                }
        }
    }
    
    //----------------------CHECK LOGINOK!!!!!!!!-------
    static Pair<BusinessManager,List<Order>> CheckLoginManager(Pair p){ 
        ResultSet rs=null;
        ResultSet rs2=null;
        ResultSet rs3=null;
        BusinessManager businessManager=null;
        List<Order> orders= new ArrayList<>();
    try {
        Statement stmt=con.createStatement();
        Statement stmt2=con.createStatement();
        Statement stmt3=con.createStatement();
        rs=stmt.executeQuery("select * from BusinessManager left join Restaurant on Restaurant.managerID=BusinessManager.managerID where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
        if(rs.next()){ 
            businessManager = new BusinessManager(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            if (rs.getInt(9)==0){
                rs2=stmt2.executeQuery("select max(restaurantID) from Restaurant");
                rs2.next();
                businessManager.setManagedRestaurant(new Restaurant(rs2.getInt(1)+1," "," "," ",rs.getInt(1)));
            }
            else{
                businessManager.setManagedRestaurant(new Restaurant(rs.getInt(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13)));
                //System.out.println("Étterme lekérdezve:");
                //System.out.println(businessManager.getManagedRestaurant().getRestaurantID()+""+businessManager.getManagedRestaurant().getRestaurantName()+" "+businessManager.getManagedRestaurant().getRestaurantAddress()+" "+businessManager.getManagedRestaurant().getOpenHours()+" "+businessManager.getManagedRestaurant().getManagerID());
                businessManager.getManagedRestaurant().setMenu(MenuQuery(businessManager.getManagedRestaurant()));
            }
            businessManager.setManagerID(rs.getInt(1));  
        }

        rs3=stmt3.executeQuery("select o.*,f.name from Orders as o join Restaurant as r on r.restaurantID=o.restaurantID join Food as f on f.foodID=o.foodID join BusinessManager as b on b.managerID=r.managerID where b.managerID="+businessManager.getManagerID());
        while (rs3.next()){
            //System.out.println(rs3.getInt(1)+rs3.getInt(2)+rs3.getInt(3)+rs3.getInt(4)+rs3.getInt(6)+rs3.getInt(7)+rs3.getString(15)+rs3.getInt(8)+rs3.getInt(9)+rs3.getTimestamp(10)+rs3.getTimestamp(11)+rs3.getInt(12)+rs3.getInt(13)+rs3.getTimestamp(14));
            //System.out.println(rs3.getInt(1)+" | "+rs3.getInt(2)+" | "+rs3.getInt(3)+" | "+rs3.getInt(4)+" | "+rs3.getInt(6)+" | "+rs3.getInt(7)+" | "+rs3.getString(15)+" | "+rs3.getInt(8)+" | "+rs3.getInt(9)+" | "+rs3.getTimestamp(10)+" | "+rs3.getTimestamp(11)+" | "+rs3.getInt(12)+" | "+rs3.getInt(13)+" | "+rs3.getTimestamp(14));
            orders.add(new Order(rs3.getInt(1),rs3.getInt(2),rs3.getInt(3),rs3.getInt(4),rs3.getInt(6),rs3.getInt(7),rs3.getString(15),rs3.getInt(8),rs3.getInt(9),rs3.getTimestamp(10),rs3.getTimestamp(11),rs3.getInt(12),rs3.getInt(13),rs3.getTimestamp(14)));   
        }
    }
    catch (SQLException ex) {   
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return new Pair<BusinessManager,List<Order>>(businessManager,orders);
    }
    
    static Guest CheckLoginGuest(Pair p){
        Guest g=null;
        System.out.println("checkloginguest teszt: "+p.getKey() +" "+ p.getValue());
    try {
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from Guest where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
        if (rs.next()){
            System.out.println("checkloginguest infók: " + rs.getString(2) + rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));
            g = new Guest(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            g.setGuestID(rs.getInt(1));
        }   
        }
    catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            return g;
    }
    
    static Pair<List<Order>,Courier> CheckLoginCourier(Pair p){
        Courier c = new Courier();
        List<Order> orders= new ArrayList<>();
        Pair<ArrayList<Order>,Courier> pair;
        
       try {
                Statement stmt=con.createStatement();
                
                ResultSet rs=stmt.executeQuery("select * from Courier where username like '" + p.getKey() + "' AND passwd like '"+p.getValue() + "'" );
                if (rs.next()){
                    c = new Courier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
                }   
                
                rs = stmt.executeQuery("select o.batchID, r.restaurantName, g.guestAddress, concat(g.firstName,\" \",g.lastName) as Name, g.phoneNumber,o.courierID,o.paymentmethod,o.orderstatus,sum(o.subsum),o.restaurantID from orders as o\n" +
"join Restaurant as r on r.restaurantID=o.restaurantID\n" +
"join Guest as g on g.guestID=o.guestID group by o.batchID");
                while (rs.next()){
                    orders.add(new Order(rs.getInt(1),rs.getInt(6),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(9),rs.getInt(7),rs.getInt(8),rs.getInt(10)));
                }   //_batchID,int _courierID,String _restaurantName, String _guestAddress,String _guestName, String _guestPNumber,int _sum, int _paymentMethod, int _orderStatus
            }
            catch (SQLException ex) {
                //return false;
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
       return new Pair<List<Order>,Courier>(orders,c);
    }
    
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
    static void insertCourier(Courier c){
    try {
        System.out.println("insertálás:");
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Courier(username,passwd,firstName,lastName,phoneNumber,workingHours,salary)VALUES(?,?,?,?,?,?,?)");
        stmt.setString(1, c.getUsername());
        stmt.setString(2, c.getPassword());
        stmt.setString(3, c.getFirstName());
        stmt.setString(4, c.getLastName());
        stmt.setString(5, c.getPhoneNumber());
        stmt.setString(6, c.getWorkingHours());
        stmt.setInt(7, c.getSalary());        
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("insert kész");
    }
    
    static List<Order> updateOrderStatus(Order orders){
        System.out.println("eljut ide");
        List<Order> lista = new ArrayList<Order>();
    try {
        ResultSet rs3=null;
        Statement stmt3=con.createStatement();
        System.out.println("eljut ide is");
        System.out.println("order status: "+orders.getOrderStatus());
        if (orders.getOrderStatus()==3){
            System.out.println("updateli 3-asraaa");
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());
            PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET orderstatus = "+orders.getOrderStatus()+", orderdonetime='"+time+"' WHERE batchID="+orders.getBatchID());
            stmt.executeUpdate();
        }
        else{
            PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET orderstatus = "+orders.getOrderStatus()+" WHERE batchID="+orders.getBatchID());
            stmt.executeUpdate();
            System.out.println("eljut ide issss");
        }
        
        System.out.println("restaurant id:"+orders.getRestaurantID());
                rs3 = stmt3.executeQuery("select o.batchID, r.restaurantName, g.guestAddress, concat(g.firstName,\" \",g.lastName) as Name, g.phoneNumber,o.courierID,o.paymentmethod,o.orderstatus,sum(o.subsum),o.restaurantID from orders as o\n" +
"join Restaurant as r on r.restaurantID=o.restaurantID\n" +
"join Guest as g on g.guestID=o.guestID group by o.batchID");
             
        while (rs3.next()){
            //System.out.println(rs3.getInt(1)+rs3.getInt(2)+rs3.getInt(3)+rs3.getInt(4)+rs3.getInt(6)+rs3.getInt(7)+rs3.getString(15)+rs3.getInt(8)+rs3.getInt(9)+rs3.getTimestamp(10)+rs3.getTimestamp(11)+rs3.getInt(12)+rs3.getInt(13)+rs3.getTimestamp(14));
            //System.out.println(rs3.getInt(1)+" | "+rs3.getInt(2)+" | "+rs3.getInt(3)+" | "+rs3.getInt(4)+" | "+rs3.getInt(6)+" | "+rs3.getInt(7)+" | "+rs3.getString(15)+" | "+rs3.getInt(8)+" | "+rs3.getInt(9)+" | "+rs3.getTimestamp(10)+" | "+rs3.getTimestamp(11)+" | "+rs3.getInt(12)+" | "+rs3.getInt(13)+" | "+rs3.getTimestamp(14));
           lista.add(new Order(rs3.getInt(1),rs3.getInt(6),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),rs3.getInt(9),rs3.getInt(7),rs3.getInt(8),rs3.getInt(10)));
            System.out.println("eljut ide is de nagyon");
            System.out.println("rs.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("returnolás előtt");
    return lista;
    }
    
    static List<Order> updateOrderCourier(Pair p){
            ResultSet rs=null;
            ResultSet rs2=null;  
            int futarid = (int) p.getKey();
            int batchid= (int) p.getValue();
            List<Order> orders = new ArrayList<>();
        try {
            
            PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET courierID="+futarid+" WHERE batchID="+batchid);
            stmt.executeUpdate();
            
            Statement stmt2 = con.createStatement();
             rs2 = stmt2.executeQuery("select o.batchID, r.restaurantName, g.guestAddress, concat(g.firstName,\" \",g.lastName) as Name, g.phoneNumber,o.courierID,o.paymentmethod,o.orderstatus,sum(o.subsum),o.restaurantID from orders as o\n" +
"join Restaurant as r on r.restaurantID=o.restaurantID\n" +
"join Guest as g on g.guestID=o.guestID group by o.batchID");
             
                while (rs2.next()){
                    orders.add(new Order(rs2.getInt(1),rs2.getInt(6),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),rs2.getInt(9),rs2.getInt(7),rs2.getInt(8),rs2.getInt(10)));
        }} catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    
    static void updateOrderEstimatedTime(Order o){
        ResultSet rs = null;
        System.out.println("eljut ideeeeeeeeee?????");
        try {
            Statement stmt = con.createStatement();
            /*
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new java.util.Date());
            calendar.add(Calendar.MINUTE, rand_int1);
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            */
            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp =null;
            
            rs = stmt.executeQuery("select o.ordertime from orders as o where batchID="+o.getBatchID());
            
            if(rs.next()){
            calendar.setTime(rs.getTime(1));
            calendar.add(Calendar.MINUTE,o.getEstimated_time());
            timestamp = new Timestamp(calendar.getTimeInMillis());
            }
            PreparedStatement stmt2 = con.prepareStatement("UPDATE Orders set estdeliverytime = '"+timestamp+"' WHERE batchID = "+o.getBatchID());
            stmt2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static void updateCourierAv(Courier c){
        try {
            System.out.println(c.getCourierID()+" | "+c.getWorkingHours());
            PreparedStatement stmt = con.prepareStatement("UPDATE Courier SET workingHours = '"+c.getWorkingHours()+"', phoneNumber='"+c.getPhoneNumber()+"' WHERE courierID="+c.getCourierID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static List<Discount> deleteDiscount(Discount d){
        try {
            System.out.println("delete discount :" + d.getDiscountID());
            
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Discounts WHERE discountID = "+ d.getDiscountID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DiscountQuery(d.getRestaurantID());
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
        insertMenu(c.getRestaurantID());
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    static void insertMenu(int RestaurantID){
    try {
        System.out.println("restaurantID: " + RestaurantID);
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
        //System.out.println("Menü kategóriák beinzertálása megtörtént");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    static void insertMeal(Pair p){
    try {
        Meal c = (Meal)p.getKey();
        int id = (int) p.getValue();
        System.out.println("Kaja beinzertálás előtt");
        System.out.println("menuid: " + c.getMenuID());
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Food(name,ingredients,allergenes,price,menuID)VALUES(?,?,?,?,?)");
        stmt.setString(1, c.getName());
        stmt.setString(2, c.getIngredients());
        stmt.setString(3, c.getAllergens());
        stmt.setInt(4,c.getCost());
        stmt.setInt(5,c.getMenuID());
        stmt.executeUpdate();
        //System.out.println("Kaja beinzertálása megtörtént");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    
    static void insertOrder(Order orders){
        ResultSet rs=null;
        int nextbatchID = 0;
        try {
            
            Statement stmt2=con.createStatement();
            rs=stmt2.executeQuery("select batchID from Orders\n" +
                "order by batchID desc\n" +
                "LIMIT 1;");
            
            if (rs.next()){
            nextbatchID = rs.getInt(1)+1;
            System.out.println("nextbatch if ág");
             } else{
               
            nextbatchID=1;
            }
            
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Orders(batchID,courierID,guestID,subsum,foodID,amount,restaurantID,ordertime,estdeliverytime,paymentmethod,orderstatus,orderdonetime)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            //jelenlegi dátum
            
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());
            //véletlenszerű idő generálása +15-30 perccel ezután.
            Random rand = new Random();
            int rand_int1 = rand.nextInt(15);
            rand_int1=rand_int1+15;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new java.util.Date());
            calendar.add(Calendar.MINUTE, rand_int1);
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            //order ID+amount mapenként bepakolás
            for(Map.Entry<Meal,Integer> entry : orders.getMealsOrdered().entrySet()){
                
            
                
            stmt.setInt(1,nextbatchID);
            stmt.setInt(2,orders.getCourierID());
            stmt.setInt(3,orders.getGuestID());
            
            stmt.setInt(4, entry.getKey().getCost()*entry.getValue());
            stmt.setInt(5, entry.getKey().getId());
            stmt.setInt(6, entry.getValue());
            stmt.setInt(7, orders.getRestaurantID());
            stmt.setTimestamp(8, time);
            stmt.setTimestamp(9, timestamp);
            stmt.setInt(10, orders.getPaymentMethod());
            stmt.setInt(11,0);
            stmt.setTimestamp(12,timestamp);
            stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
        static List<Discount> insertDiscount(Discount d){
        try {
            System.out.println("insert discount");
            System.out.println("kaja id:"+d.getFoodID());
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Discounts(discount_percentage,foodID,restaurantID)VALUES(?,?,?)");
        stmt.setInt(1,d.getDiscountPercentage());
        stmt.setInt(2,d.getFoodID());
        stmt.setInt(3,d.getRestaurantID());
        stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DiscountQuery(d.getRestaurantID());
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
                //System.out.println("menük kiiratása: ");
                //System.out.println(rs.getInt(3)-1+" "+rs.getInt(2)+" "+rs.getInt(1)+" "+r.getRestaurantID());
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
            //System.out.println("kaják kiiratása: ");
            //System.out.println(rs.getString(2)+" "+rs.getInt(5)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(6));
            list.add(new Meal(rs.getString(2),rs.getInt(5),rs.getString(3),rs.getString(4),rs.getInt(9),rs.getInt(6),rs.getInt(1)));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    static List<Discount> DiscountQuery(int restaurantID){
        ResultSet rs=null;
        List<Discount> list = new ArrayList<Discount>();
        System.out.println("discount query ");
        try {
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Discounts where restaurantID = "+restaurantID);
            while(rs.next()){
            list.add(new Discount(rs.getInt(1),rs.getInt(2),rs.getInt(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    //-----------------------------NÉV LÉTEZIK-E ha létezik = true
    static boolean nameExists(String nev,String tipus){
    try {
        Statement stmt=con.createStatement();
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