/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;
import static pkg8terem.Main.*;

/**
 *
 * @author polga
 */
public class Menu implements Serializable{
    private int id;
    private int restaurantID;
    private int category;
    private List<Meal> meals = new ArrayList<Meal>();
    static Scanner input = new Scanner(System.in);
    
    public Menu(int _id, int _categoryID, int _restaurantID,List<Meal> _meals)
    {
        this.id=_id;
        this.category=_categoryID;
        this.restaurantID = _restaurantID;
        this.meals=_meals;
    }
        public Menu(int _categoryID, int _restaurantID,List<Meal> _meals)
    {

        this.category=_categoryID;
        this.restaurantID = _restaurantID;
        this.meals=_meals;
    }
    
    public Menu()
    {
    }
    
    List<Meal> getMenu()
    {
        return meals;
    }
     public void addMealToMenu(Meal meal) throws IOException {
            datas = new Pair<>(new Pair<>(meal,0),1);
            meals.add(meal);
            objectOutputStream.writeObject(datas);
        }
     
     public void listMenu()
    {
        
    }
     
     
    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
        public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
} 