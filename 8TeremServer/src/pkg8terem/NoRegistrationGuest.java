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
    private String firstName = null, lastName = null, guestAddress = null, phoneNumber = null;
    
    String registrationDate;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Scanner input = new Scanner(System.in);

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
    
    public NoRegistrationGuest(String _firstName,String _lastName,String _guestAddress, String _phoneNumber, String _registrationDate)
    {
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.guestAddress = _phoneNumber;
        this.registrationDate = _registrationDate;
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


