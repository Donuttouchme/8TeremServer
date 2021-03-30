/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;
import static pkg8terem.Main.objectOutputStream;
import static pkg8terem.Main.objectInputStream;
import pkg8terem.Restaurant;
import pkg8terem.Users;
/**
 *
 * @author polgar
 */


public class BusinessManager implements Users, Serializable{
    private static final long seralVersionID = 6529685098267757690L;
    Main m= new Main();
    private int managerID;
    private String username = null;
    private String password=null;
    private String passwordCheck=null;
    private String firstName=null;
    private String lastName=null;
    private String corporationName=null;
    private String email;
    private String registrationDate;
    private Restaurant managedRestaurant;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    static Scanner input = new Scanner(System.in);
    Pair<Object, Integer>datas;
    //functions
    //
    public BusinessManager()
    {
        
    }
    
    public BusinessManager(String _username,String _password,String _firstName,String _lastName,String _corporationName,String _email)
    {
        this.username = _username;
        this.password = _password;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.corporationName = _corporationName;
        this.email = _email;
    }
    
    @Override
    public BusinessManager Registration(String __username) throws IOException {
        username=__username;
        System.out.println("Email:");
            email=input.nextLine();
            System.out.println("password");
            password = input.nextLine();
        System.out.println("Enter password again: ");   
            passwordCheck = input.nextLine();
        while(!password.equals(passwordCheck))                      //PASSWORD CHECK
        {
        System.out.println("Passwords are not matching, please enter them again: ");
            password = input.nextLine();
        System.out.println("Enter password again: ");
            passwordCheck = input.nextLine();
        }
        System.out.println("Enter your first name: ");              //NAME
            firstName = input.nextLine();
        System.out.println("Enter your last name: ");
            lastName = input.nextLine();
        System.out.println("Enter your Corporation's Name: ");      //CORPORATION NAME
           corporationName = input.nextLine();
        registrationDate=formatter.format(new Date(System.currentTimeMillis()));    //REGISTRATION DATE 
             return new BusinessManager(username,password,firstName,lastName,corporationName,email);
    }

    @Override
    public BusinessManager Login() {
        boolean correct = false;
        while(!correct){
        System.out.println("Enter username: ");
            username = input.nextLine();
        System.out.println("Enter password: ");
            password = input.nextLine();
            datas = new Pair<>(new Pair<>(username,password), 0);
//            if()
//            {
//            correct = true;
//            }
//            else
//            {
//                System.out.println("Incorrect password or username, please enter them again: ");
//            }
//            
        }
            
            return new BusinessManager();
    }
    
    void RestaurantRegistration(String restaurantName,String address,String openHours) throws IOException
    {
            datas = new Pair<>((new Restaurant(managedRestaurant.getRestaurantId(),restaurantName, address, openHours, managerID)),1);
            m.objectOutputStream.writeObject(datas);
    }
    
        void addMealToMenu() throws IOException
    {
        managedRestaurant.menu.addMealToMenu();
        datas = new Pair<>(managedRestaurant.getMenu(),1);
        try {
            m.objectOutputStream.writeObject(datas); 
        } catch (Exception e) {
            System.out.println("Something happened, so we couldn't add your meals to your menu, please try again later! ");
        }
        System.out.println("You've successfully added meals to your menu! ");

    }
    
    void checkOrders()
    {
        //TO-DO
        //
        //
    }
    
    void getTotalRevenue()
    {
        //TO-DO
        //
        // 
    }
    void deleteOrder()
    {
        //TO-DO
        //
        // 
    }
    void editOrder()
    {
        //TO-DO
        //
        // 
    }
    void editMealProperties()
    {
        //TO-DO
        //
        // 
    }
    void editDiscount()
    {
        //TO-DO
        //
        // 
    }
    void setPaymentType()
    {
       //TO-DO
        //
        //  
    }
    void setOpenHours()
    {
        //TO-DO
        //
        // 
    }
    void setDeliveryTime()
    {
        //TO-DO
        //
        // 
    }
    void setOrderQueue()
    {
        //TO-DO
        //
        // 
    }
    void assignCourierToOrder()
    {
        //TO-DO
        //
        // 
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

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
        public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    
        public Restaurant getManagedRestaurant() {
        return managedRestaurant;
    }

    public void setManagedRestaurant(Restaurant managedRestaurant) {
        this.managedRestaurant = managedRestaurant;
    }
} 