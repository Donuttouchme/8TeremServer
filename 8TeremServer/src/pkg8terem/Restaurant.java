/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author polga
 */
public class Restaurant {
    private int id, managerID;
    private String restaurantName, address, openHours, authorisationNumber;
    Menu menu;
    Scanner input = new Scanner(System.in);

        public Restaurant(int id, String restaurantName, String address, String openHours, String authorisationNumber, int managerID)
    {
        this.id=id;
        this.restaurantName=restaurantName;
        this.address=address;
        this.openHours=openHours;
        this.authorisationNumber=authorisationNumber;
        this.managerID=managerID;
    }
    
    public Menu getMenu() {
        return menu;
    }        
}
