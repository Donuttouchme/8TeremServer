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
    private String name;
    private List<String> ingredients;
    private List<String> allergens;
    
    public Meal()
        {             
        }

    public Meal(int _id, String _name, int _cost,List<String> _ingredients, List<String> _allergens)
    {
        this.id = _id;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }
}
    