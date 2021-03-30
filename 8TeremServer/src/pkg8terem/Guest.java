
package pkg8terem;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Guest implements Users, Serializable {
    private String username = null;
    private String password = null;
    private String passwordCheck = null;
    private String firstName = null;
    private String lastName = null;
    private String guestAddress = null;
    private String phoneNumber = null;
    private String registrationDate = null;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    static Scanner input = new Scanner(System.in);

    public Guest()
    {  
    }
    
    public Guest(String _username,String _password, String _firstName,String _lastName,String _guestAddress, String _phoneNumber)
    {
        this.username = _username;
        this.password = _password;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.guestAddress = _guestAddress;
        this.phoneNumber = _phoneNumber;
    }
      
    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
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
    
    
    @Override
    public Guest Registration(String __username) throws IOException {
        System.out.println("Enter username: ");
         username = __username;
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
    
     return new Guest(username,password,firstName,lastName,guestAddress,phoneNumber);
    }
    


    @Override
    public Guest Login() {
//        System.out.println("Enter username: ");
//        username = input.nextLine();
//        System.out.println("Enter password: ");
//        password = input.nextLine();
        return new Guest("user","password","firstname","lastname", "666 Hell shit street 666", "06206666666");
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
