/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author polga
 */
public class Meal implements Serializable{
    private int id, cost;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    private int categoryID;
    private int menuID;
    private String name;
    private String ingredients;
    private String allergens;
    
    public Meal()
        {             
        }

    public Meal(String elso,int masodik, String harmadik, String negyedik,int id2, int _menuID)
    {
        this.name=elso;
        this.cost=masodik;
        this.ingredients=harmadik;
        this.allergens=negyedik;
        this.categoryID=id2;
        this.menuID=_menuID;
    }
    
    public Meal( String _name, int _cost,String _ingredients, String _allergens)
    {
        this.name = _name;
        this.cost = _cost;
        this.ingredients=_ingredients;
        this.allergens = _allergens;
    }
    
    
        public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }
     public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
    