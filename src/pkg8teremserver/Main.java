/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8teremserver;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author krisz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        ServerSocket ss=new ServerSocket(6666);
        Socket s=ss.accept();//establishes connection 

        DataInputStream dis=new DataInputStream(s.getInputStream());

        String	str=(String)dis.readUTF();
        System.out.println("message= "+str);
        ss.close();

        }catch(Exception e){System.out.println(e);}
        
        
        //ADATBÁZIS OLVASÁS KRISZTIÁN
        //ASD
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/8teremdb","root","root");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from test");

        while(rs.next())
        System.out.println(rs.getInt(1));

        con.close();

    }catch(Exception e){ System.out.println(e);}
    }
    
}
