import java.util.ArrayList;
public class Receipe {

    private String name;
    private double calories;

    private double price;

    private ArrayList<String> ingredients;

    private ArrayList<Integer> quantityIngredients;

    //getters

    public Receipe(String name, double cal, double price){
        this.name = name;
        this.calories = cal;
        this.price = price;
        this.ingredients = new ArrayList<>();
        this.quantityIngredients = new ArrayList<>();
    }

    public void addIngredient(String ingrid, int quantity){
        ingredients.add(ingrid);
        quantityIngredients.add(quantity);
    }

    //getters

    public String getName(){
        return this.name;
    }

    public double getCalories(){
        return this.calories;
    }

    public double getPrice(){
        return this.price;
    }

    public ArrayList<String> getIngredientsList(){
        return this.ingredients;
    }

    public ArrayList<Integer> getQuantityIngredients(){
        return this.quantityIngredients;
    }
}
