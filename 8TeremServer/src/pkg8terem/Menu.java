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
public class Menu {
    private List<Meal> meals;
    Scanner input = new Scanner(System.in);
    
    public Menu(List<Meal> _meals)
    {
        this.meals=_meals;
    }
    List<Meal> getMenu()
    {
        return meals;
    }
     public void addMealToMenu() {
        System.out.println("How many meals do you want to add: ");
        int numOfDishes = input.nextInt();
        for(int i=0;i<numOfDishes;i++)
        {
            System.out.println("Plese give the " +i+". meal's name you want to add: ");
                String dishName =input.nextLine();
                int mealCost =input.nextInt();
            System.out.println("How many allergens do you want to add?");
                int numOfAllergens = input.nextInt();
            List<String> mealAllergens=null;
            for(int j=0;j<numOfAllergens;j++)
            {
                System.out.println("Enter the "+(i+1)+". allergene: ");
                    mealAllergens.add(input.nextLine());
                    
            }
            meals.add(new Meal(meals.size()+1,dishName,mealCost,mealAllergens));
        }
    }
     public void listMenu()
    {
        for(int i=0;i<meals.size();i++)
        {
            System.out.println(meals.get(i));
        }
    }
}
