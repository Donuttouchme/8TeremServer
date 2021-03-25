/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8teremserver;
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

    public static void dostuff(Socket socket)throws IOException, ClassNotFoundException{
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object c = objectInputStream.readObject();
        System.out.println("class:");
       System.out.println(c.getClass().getSimpleName()); // MILYEN CLASS EZ?
        
        if (c.getClass().getSimpleName().equals("ArrayList")){
            //csinal valamit...
        //List<Message> listOfMessages = (List<Message>) c;//objectInputStream.readObject();
        
        }
        if (c.getClass().getSimpleName().equals("Vasarlo")){
        //Vasarlo v = (Vasarlo) c;
        //csinal valamit...
        
    }
    }
    public static void main(String[] args)throws IOException, ClassNotFoundException  {
        int sajt =0;
        // SOCKET CSATLAKOZAS
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = new Socket();
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
        }while(sajt!=1);
        //----------------------------------------------------------------------
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
        
        
        //ADATBÁZIS OLVASÁS
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/8teremdb","root","root");
        //-----------------------------------------------------------------------------------
        //SQL PARANCSOKHOZ ITTEN MAJD:
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from test");

        while(rs.next())
        System.out.println(rs.getInt(1));

        con.close();

    }catch(Exception e){ System.out.println(e);}
    }
    
}