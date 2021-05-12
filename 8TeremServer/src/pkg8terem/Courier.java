/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;
import pkg8terem.Main.*;

/**
 *
 * @author polga
 */
public class Courier implements Users, Serializable{

    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private String phoneNumber = null;
    private String workingHours = null;
    private int salary = 0;
    private int courierID;
    
    public Courier()
    {
        
    }
    
    public Courier(int _courierID,String _username,String _password,String _firstname,String _lastname,String _phoneNumber,String _workingHours, int _salary)
    {
        this.courierID=_courierID;
        this.username = _username;
        this.password= _password;
        this.firstName = _firstname;
        this.lastName = _lastname;
        this.phoneNumber = _phoneNumber;
        this.workingHours = _workingHours;
        this.salary=_salary;
    }
    
    public Courier(String _username, String _password, String _firstname, String _lastname, String _phonenumber, String _workingHours)
    {
        this.username = _username;
        this.password= _password;
        this.firstName = _firstname;
        this.lastName = _lastname;
        this.phoneNumber = _phonenumber;
        this.workingHours = _workingHours;
        String[] parts = _workingHours.split("-");
        String part1 = parts[0]; 
        String part2 = parts[1]; 
        this.salary = 1100*(Integer.parseInt(part2)-Integer.parseInt(part1));
    }
    
    public void alterAvailability(String _phoneNumber, String _hours) throws IOException
    {
        this.phoneNumber=_phoneNumber;
        this.workingHours=_hours;
        update(new Courier(courierID,username,password,firstName,lastName,phoneNumber,workingHours,salary));
    }
    
    public void update(Courier c) throws IOException
    {
        Main.datas = new Pair<>(c,2);
        Main.objectOutputStream.writeObject(Main.datas);
        Main.objectOutputStream.flush();
        Main.objectOutputStream.reset();
        
    }
    
    public void orderDelivered(Order order) throws IOException
    {
        order.setOrderStatus(3);
        Main.datas = new Pair<>(order,1);
        Main.objectOutputStream.writeObject(Main.datas);
        Main.objectOutputStream.flush();
        Main.objectOutputStream.reset();
    }
        
    @Override
    public Courier Registration(String __username) {        
       return new Courier(); 
    }

    @Override
    public Courier Login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
        public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }
        public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
