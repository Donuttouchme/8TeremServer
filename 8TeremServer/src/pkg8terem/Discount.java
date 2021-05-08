/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.Serializable;
import pkg8terem.Main.*;
/**
 *
 * @author polga
 */
public class Discount implements Serializable{
    
    private int discountID;
    private int discountPercentage;
    private int foodID;
    private int restaurantID;
    
    public Discount(){}
    
    public Discount(int _discountID, int _discountpercentage,int _foodID, int _restaurantID)
    {
        this.discountID = _discountID;
        this.discountPercentage = _discountpercentage;
        this.foodID = _foodID;
        this.restaurantID = _restaurantID;
    }
    
    public void changeDiscountPercentage(Discount disc, int perc)
    {
        disc.setDiscountPercentage(perc);
    }
    
    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
    
}
