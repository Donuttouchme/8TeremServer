/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.util.Scanner;

/**
 *
 * @author polga
 */
public class Courier implements Users{

    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private String phoneNumber = null;
    private int salary = 0;
    static Scanner input = new Scanner(System.in);
    
    public Courier()
    {
        
    }
    
    public Courier(String _username, String _password, String _firstname, String _lastname, String _phonenumber, int _salary)
    {
        this.username = _username;
        this.password= _password;
        this.firstName = _firstname;
        this.lastName = _lastname;
        this.phoneNumber = _phonenumber;
        this.salary = _salary;
    }
    
    @Override
    public Courier Registration() {
//        System.out.println("Enter email: ");                        //EMAIL
//        email=input.nextLine();
//    System.out.println("Enter username: ");                     //USERNAME
//        username = input.nextLine();
//    //TO-DO check with the server
//    //
//    //
//    
//    System.out.println("Enter password: ");                      //PASSWORD
//        password = input.nextLine();
//    System.out.println("Enter password again: ");   
//        passwordCheck = input.nextLine();
//    while(!password.equals(passwordCheck))                      //PASSWORD CHECK
//    {
//    System.out.println("Passwords are not matching, please enter them again: ");
//        password = input.nextLine();
//    System.out.println("Enter password again: ");
//        passwordCheck = input.nextLine();
//    }
//    System.out.println("Enter your first name: ");              //NAME
//        firstName = input.nextLine();
//    System.out.println("Enter your last name: ");
//        lastName = input.nextLine();
//    System.out.println("Enter your Corporation's Name: ");      //CORPORATION NAME
//       corporationName = input.nextLine();
//    registrationDate=formatter.format(new Date(System.currentTimeMillis()));    //REGISTRATION DATE 
//         return new BusinessManager(username,password,firstName,lastName,corporationName,email,registrationDate);
        
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
}
