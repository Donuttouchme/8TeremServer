/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import pkg8terem.Restaurant;
import pkg8terem.Users;
/**
 *
 * @author polgar
 */


public class BusinessManager implements Users, Serializable{

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
    public BusinessManager()
    {
        
    }
    //variables
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
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
    
    //functions
    //
    
        public BusinessManager(String _username,String _password,String _firstName,String _lastName,String _corporationName,String _email,String _registrationDate)
    {
        this.username = _username;
        this.password = _password;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.corporationName = _corporationName;
        this.email = _email;
        this.registrationDate = _registrationDate;
    }
    
    @Override
    public void Registration() {
    System.out.println("Enter email: ");                        //EMAIL
        email=input.nextLine();
    System.out.println("Enter username: ");                     //USERNAME
        username = input.nextLine();
    //TO-DO check with the server
    //
    //
    
    System.out.println("Enter password: ");                      //PASSWORD
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
         new BusinessManager(username,password,firstName,lastName,corporationName,email,registrationDate);
    }

    @Override
    public BusinessManager Login() {
        System.out.println("Enter username: ");
            username = input.nextLine();
            //TO-DO CHECK WITH THE SERVER
            //
            //
        System.out.println("Enter password: ");
            password = input.nextLine();
            //TO-DO CHECK WITH THE SERVER
            //
            //
            return new BusinessManager("user","password","firstname","lastname", "corporation", "emal@email.email","2021-03-20 'at' 18:00:00 z");
    }
    
    Restaurant RestaurantRegistration()
    {
        int id,managerID;
        String restaurantName, address, openHours, authorisationNumber;
        System.out.println("");
        id=0;
        managerID=0;
        System.out.println("Enter your restaurant's name: ");
            restaurantName = input.nextLine();
        System.out.println("Enter your restaurant's address: ");
            address = input.nextLine();
        System.out.println("Enter your restaurant's hopen hours: ");
            openHours = input.nextLine();
        System.out.println("Enter your restaurant's authorisation number: ");
            authorisationNumber = input.nextLine();
            return new Restaurant(id,restaurantName, address, openHours, authorisationNumber, managerID);
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
    void addMealToMenu()
    {
        managedRestaurant.menu.addMealToMenu();
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
}
