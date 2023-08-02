/**
 * This class : Item represents an item that is being sold in a vending machine
 *
 * @author Charlize Kirsten M. Brodeth
 * @version 2.0
 */
public class Item {
    //Variables used//
    private String name;

    private double calories;

    private double price;

    /**
     * This constructor initializes the name and the amount of calories a food item has
     * It also initializes the price of an item to 0 (to be set later)
     * @param name name of the item
     * @param cal amount of calories it has
     */
    public Item(String name, double cal, double price)
    {
        this.name = name;
        this.calories = cal;
        this.price = 0;
        if(price > 0){
            this.price = price;
        }
    }

    //getters

    /**
     * This method is used to return the name of the item
     *
     * @return name name of the food item
     */
    public String getName(){
        return this.name;
    }

    /**
     * This method is used to return the amount of calories
     *
     * @return calories the amount of calories the item has
     */
    public double getCalories(){
        return this.calories;
    }

    /**
     * This method is used to return the price of the item
     *
     * @return price the price set to the item
     */
    public double getPrice(){
        return this.price;
    }

}
