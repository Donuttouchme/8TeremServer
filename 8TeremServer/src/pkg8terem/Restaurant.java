/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author polga
 */
public class Restaurant implements Serializable{
    private int restaurantID = 0;
    private int managerID = 0;
    private String restaurantName = null;
    private String restaurantAddress = null;
    private String openHours = null;
    private List<Menu> menus=new ArrayList<Menu>();

    
    static Scanner input = new Scanner(System.in);

    public Restaurant()
    {
        
    }
    
    public Restaurant(int _restaurantID, String _restaurantName, String _restaurantAddress, String _openHours, int _managerID, List<Menu> _menus)
    {
        this.restaurantID = _restaurantID;
        this.restaurantName = _restaurantName;
        this.restaurantAddress = _restaurantAddress;
        this.openHours = _openHours;
        this.managerID = _managerID;
        this.menus=_menus;
    }
    public Restaurant(int _restaurantID, String _restaurantName, String _restaurantAddress, String _openHours, int _managerID)
    {
        this.restaurantID = _restaurantID;
        this.restaurantName = _restaurantName;
        this.restaurantAddress = _restaurantAddress;
        this.openHours = _openHours;
        this.managerID = _managerID;
    }
    
    public void addMealToSpecifiedMenu(int category, Meal meal) throws IOException
    {
        menus.get(category).addMealToMenu(meal);
       
    }
    
    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }
    
    public int getRestaurantId() {
        return restaurantID;
    }

    public void setRestaurantId(int id) {
        this.restaurantID = restaurantID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }
    
    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public List<Menu> getMenu() {
        return menus;
    }

    public void setMenu(List<Menu> menus) {
        this.menus = menus;
    }
}