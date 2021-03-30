/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author polga
 */
public class Menu implements Serializable{
    private List<Meal> meals;
    static Scanner input = new Scanner(System.in);
    
    public Menu(List<Meal> _meals)
    {
        this.meals=_meals;
    }


    List<Meal> getMenu()
    {
        return meals;
    }
     public void addMealToMenu() {
        System.out.println("Please enter the meals, when you are done, enter 'end' ");
         System.out.println("Enter the first meal's name: ");
        String dishName = input.nextLine();
        while (!dishName.equals("end")) 
        {
            System.out.println("Please enter the price of the meal: ");
                int mealCost =input.nextInt();
            System.out.println("Please enter the ingredients, if you are done enter 'end'");
                String inIngredients = input.nextLine();
                List<String>mealIngredients=null;
            while(!inIngredients.equals("end"))
            {
                mealIngredients.add(inIngredients);
                inIngredients=input.nextLine();
                
            }
            System.out.println("Please enter the allergens, if you are done enter 'end'");
                String inAllergens = input.nextLine();
            List<String> mealAllergens=null;
             while(!inAllergens.equals("end"))
            {
                mealAllergens.add(inIngredients);
                inIngredients=input.nextLine();
                
            }
            meals.add(new Meal(meals.size()+1,dishName,mealCost,mealIngredients,mealAllergens));
            System.out.println("Please enter the next meal's name: ");
            dishName = input.nextLine();
        }
         System.out.println("You've successfully added meals to your menu! ");
    }
     public void listMenu()
    {
        for(int i=0;i<meals.size();i++){
        System.out.println("The cost of "+meals.get(i).getName()+" is: "+meals.get(i).getCost()+ "$ and it contains the following allergens: ");
        for(int j=0;j<meals.get(i).getAllergens().size();j++)
        {
            System.out.println(meals.get(i).getAllergens().get(j));
        }
    }
    }
} 