package pkg8terem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

public class Main {

    static Connection con;
    public static void dostuff(Socket socket)throws IOException, ClassNotFoundException{
        InputStream inputStream = socket.getInputStream();
        System.out.println("socket megkapta az inputot");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
 
        Object c = objectInputStream.readObject();
        System.out.println("objektum megkapva");
        
        
        if (c.getClass().getSimpleName().equals("Pair")){
        Pair pairObj = (Pair) c;
        
        // -------------- létezik-e a név: ha létezik true
        int keyvalue = (int)pairObj.getKey();
            if (pairObj.getKey().getClass().getSimpleName().equals("String")){
                if (keyvalue==5){
                boolean exists = nameExists((String) pairObj.getKey(),"BusinessManager");
                 OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(exists);
                }
                if (keyvalue==6){
                boolean exists=nameExists((String) pairObj.getKey(),"Guest");
                 OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(exists);
                }
                if (keyvalue==7){
                boolean exists =nameExists((String) pairObj.getKey(),"Courier");
                 OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(exists);
                }
            }
        //---------------------CHECKLOGIN IFEK
            if (pairObj.getKey().getClass().getSimpleName().equals("Pair")){
                if(keyvalue==2){
                CheckLoginManager((Pair)pairObj.getKey());
                }
                if(keyvalue==3){
                CheckLoginGuest((Pair)pairObj.getKey());
                }
                if(keyvalue==4){
                //CheckLoginCourier((Pair)pairObj.getKey());
                }
            }
        // -----------------INSERTÁLÁS IFEK ÉS GET-IDK!
            if(pairObj.getKey().getClass().getSimpleName().equals("BusinessManager")){
                if(keyvalue==1){
                insertManager((BusinessManager)pairObj.getKey());
                }
                if (keyvalue==0){
         int id = getManagerID((BusinessManager) pairObj.getKey());
         OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(id);
                }
            }
            if(pairObj.getKey().getClass().getSimpleName().equals("Guest")){
                if(keyvalue==1){
                insertGuest((Guest)pairObj.getKey());
                }
                if(keyvalue==0){
         int id = getGuestID((Guest) pairObj.getKey());
         OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(id);
                }
            }
                if(pairObj.getKey().getClass().getSimpleName().equals("Restaurant")){
                if(keyvalue==1){
                insertRestaurant((Restaurant)pairObj.getKey());
                }
                if(keyvalue==0){
         int id = getRestaurantID((Restaurant) pairObj.getKey());
         OutputStream outputStream = socket.getOutputStream();
         ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(id);
                }
                }
                //if(pairObj.getKey().getClass().getSimpleName().equals("Courier")){
                //if(keyvalue==1){
                //insert((Courier)pairObj.getKey());
                 //if(keyvalue==0){
         //int id = getRestaurantID((pairObj.getKey()) c);
         //OutputStream outputStream = socket.getOutputStream();
         //ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
         //objectOutputStream.writeObject(id);
                //}
        //}
        
                
        }
    }
    
    //----------------------CHECK LOGINOK!!!!!!!!-------
    static boolean CheckLoginManager(Pair p){
    return true;
    }
    
    static boolean CheckLoginGuest(Pair p){
    return true;
    }
    
    //static boolean CheckLoginCourier(Pair p){
    //return true;
    //}
    
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
    //-----------------------------NÉV LÉTEZIK-E ha létezik = true
        static boolean nameExists(String nev,String tipus){
            try {
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select username from"+ tipus +"where username like'" + nev + "'" );
                if (!rs.wasNull()){
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
        
        
        //Statement stmt=con.createStatement();
        //ResultSet rs=stmt.executeQuery("select * from test");

        //while(rs.next())
        //System.out.println(rs.getInt(1));

        if (closeServer==true){
        con.close();
        }
    }catch(Exception e){ System.out.println(e);}
    //SOCKET OLVASÁS:
        socket=ss.accept();
        do{
        try{
        
        dostuff(socket);
        }catch(EOFException e){
        System.out.println("No new message just yet");
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
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