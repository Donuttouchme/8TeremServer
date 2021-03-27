/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.util.List;

/**
 *
 * @author polga
 */
public class Meal {
    private int id, cost;
    private String name;
    private List<String> allergens;
    
    public Meal(int _id, String _name, int _cost, List<String> _allergens)
    {
        this.id = _id;
        this.name = _name;
        this.cost = _cost;
        this.allergens = _allergens;
    }
}
