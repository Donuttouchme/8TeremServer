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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author krisz
 */
public class Main {

    static Connection con;
    public static void dostuff(Socket socket)throws IOException, ClassNotFoundException{
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object c = objectInputStream.readObject();
        //System.out.println("class:");
       //System.out.println(c.getClass().getSimpleName()); // MILYEN CLASS EZ?
        
        if (c.getClass().getSimpleName().equals("ArrayList")){
        //csinal valamit... FÜGGVÉNY IDE
        //List<Message> listOfMessages = (List<Message>) c;//objectInputStream.readObject();
        
        }
        if (c.getClass().getSimpleName().equals("Vasarlo")){
        //Vasarlo v = (Vasarlo) c;
        //csinal valamit... //FÜGGVÉNY IDE
        
    }
        
        if (c.getClass().getSimpleName().equals("Restaurant")){
        insertRestaurant(c);
    }
        if (c.getClass().getSimpleName().equals("BusinessManager")){
        insertManager((BusinessManager) c);
    }
    }
    //függvények adatbázisozáshoz:
    
    static void insertManager(BusinessManager c){
    try {
            Statement stmt=con.createStatement();
            stmt.executeQuery("INSERT INTO BusinessManager(username,passwd,firstName,lastName,corporateName,email,registrationDate)\n" +
               "VALUES('"+c.getUsername()+"','"+c.getPassword()+"','"+c.getFirstName()+"','"+c.getLastName()+"','"+c.getCorporationName()+
                    "','"+c.getEmail()+"','"+c.getRegistrationDate()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    static void insertRestaurant(Object c){
        try {
            Statement stmt=con.createStatement(); // managerID hogyan?
            stmt.executeQuery("INSERT INTO Restaurant (RestaurantName, address, openHours)VALUES (C.getRestaurantname,C.getRestaurantaddress,C.getOpenHours)");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
        boolean nameExists(){
            try {
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from Guest where username like'" + "nev" + "'" );
                if (!rs.wasNull()){
                    return false;
                }   
            }
            //main:
            catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
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