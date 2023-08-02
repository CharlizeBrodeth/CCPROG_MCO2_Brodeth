import java.util.ArrayList;

/**
 * This class Product represents a preset meal for recording purposes
 * @author : Charlize Kirsten M. Brodeth
 * @version 1.0
 */
public class Product {
    //Variables used//
     private String name;
     private double price;
     private double calories;
     private ArrayList<Item> ingredients;

     //

    /**
     * This constructor is used to initialize the product
     * @param name name of preset meal
     * @param price price of the meal
     * @param cal number of calories the meal has
     */
    public Product(String name, double price, double cal){
        this.name = name;
        this.price = price;
        this.calories = cal;
        this.ingredients = new ArrayList<>();
    }

    /**
     * This method is used to add an ingredient (item) to the ingredient list for the meal
     * @param ingredient item object that is used to make the meal
     */
    public void addIngredient(Item ingredient){
        ingredients.add(ingredient);
    }

    /**
     * This method is used to return the name of the meal
     * @return name of preset meal
     */
    //getters
    public String getName(){
        return this.name;
    }

    /**
     * This method is used to return the price of the meal
     * @return meal price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * This method is used to return the calories of the meal
     * @return meal calories
     */
    public double getCalories(){
        return this.calories;
    }

    /**
     * This method is used to return the list of items used as ingredients to make to meal
     * @return array list of items
     */
    public ArrayList<Item> getIngredients(){
        return this.ingredients;
    }
}
