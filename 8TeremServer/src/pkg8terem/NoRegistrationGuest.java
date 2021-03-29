/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NoRegistrationGuest implements Serializable{
    private String firstName = null;
    private String lastName = null;
    private String guestAddress = null;
    private String phoneNumber = null;
    private String registrationDate = null;
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    static Scanner input = new Scanner(System.in);
    
    public NoRegistrationGuest()
    {
        
    }
    
    public NoRegistrationGuest(String _firstName,String _lastName,String _guestAddress, String _phoneNumber, String _registrationDate)
    {
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.guestAddress = _guestAddress;
        this.guestAddress = _phoneNumber;
        this.registrationDate = _registrationDate;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        this.firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        this.lastName = _lastName;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String _guestAddress) {
        this.guestAddress = _guestAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String _phoneNumber) {
        this.phoneNumber = _phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String _registrationDate) {
        this.registrationDate = _registrationDate;
    }
     

    public NoRegistrationGuest Registration() {
    System.out.println("Enter your first name: ");                      //NAME
        firstName = input.nextLine();
    System.out.println("Enter your last name: ");
        lastName = input.nextLine();
    System.out.println("Enter your address: ");                         //ADDRESS
        guestAddress = input.nextLine();
    System.out.println("Enter your phone number: ");                    //PHONE NUMBER
        phoneNumber = input.nextLine();
                            
    registrationDate = formatter.format(new Date(System.currentTimeMillis())); //REGISTRATION DATE   
    return new NoRegistrationGuest(firstName,lastName,guestAddress,phoneNumber,registrationDate);
    }
    
    
    public void makeOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
      public void checkOrderStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
      
    public void listIngredients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    public void listAllergenes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    public void searchRestaurant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    public void getMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    public void addMealToOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    
}


