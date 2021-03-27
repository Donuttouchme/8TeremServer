
package pkg8terem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Guest implements Users, Serializable {
    private String username = null, password = null, passwordCheck = null;
    private String firstName = null, lastName = null, guestAddress = null, phoneNumber = null;

    private String registrationDate;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Scanner input = new Scanner(System.in);

    @Override
    public void Registration() {
        System.out.println("Enter username: ");
         username = input.nextLine();
    //TO-DO check with the server
    //
    //
    System.out.println("Enter password: ");                             //PASSWORD
        password = input.nextLine();
    System.out.println("Enter password again: ");   
        passwordCheck = input.nextLine();
    while(!password.equals(passwordCheck))                              //PASSWORD CHECK
    {
         System.out.println("Passwords are not matching, please enter them again: ");
            password = input.nextLine();
         System.out.println("Enter password again: ");
            passwordCheck = input.nextLine();
    }
    System.out.println("Enter your first name: ");                      //NAME
        firstName = input.nextLine();
    System.out.println("Enter your last name: ");
        lastName = input.nextLine();
    System.out.println("Enter your address: ");                         //ADDRESS
        guestAddress = input.nextLine();
    System.out.println("Enter your phone number: ");                    //PHONE NUMBER
        phoneNumber = input.nextLine();               
    registrationDate = formatter.format(new Date(System.currentTimeMillis()));  //REGISTRATION DATE
    
     new Guest(username,password,firstName,lastName,guestAddress,phoneNumber,registrationDate);
    }
    
    public Guest(String _username,String _password, String _firstName,String _lastName,String _guestAddress, String _phoneNumber, String _registrationDate)
    {
        this.username = _username;
        this.password = _password;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.guestAddress = _phoneNumber;
        this.registrationDate = _registrationDate;
    }

    @Override
    public Guest Login() {
        System.out.println("Enter username: ");
        username = input.nextLine();
        System.out.println("Enter password: ");
        password = input.nextLine();
        return new Guest("user","password","firstname","lastname", "666 Hell shit street 666", "06206666666","2021-03-20 'at' 18:00:00 z");
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
