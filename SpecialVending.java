import java.util.ArrayList;

/**
 * This class : Special Vending machine represents a SPECIAL vending machine and serves as an extension for the vending machine class
 * @author : Charlize Kirsten M. Brodeth
 * @version 1.0
 */
public class SpecialVending extends VendingMachine{
    //Additional Variables used//
    private ArrayList<Product> presetMeal;

    private ArrayList<OrderSlip> customOrders;

    private int numRecipe;

    /**
     * This constructor is used to initilize the special vending machine
     * @param name name of vending machine
     * @param cap number of slots
     * @param numPre number of preset meals to be sold
     */
    public SpecialVending(String name, int cap, int numPre){
        super(name, cap);
        this.presetMeal = new ArrayList<>();
        this.customOrders = new ArrayList<>();
        this.numRecipe = numPre;
    }

    // preset meals

    /**
     * This method is used to add a preset meal to the array list of preset meals
     * @param meal Product object representing a meal
     */
    public void addProduct(Product meal){
        presetMeal.add(meal);
    }

    /**
     * This method is used to display the preset meals to the buyer
     */
    public void displayPresetOptions(){
        System.out.println("Here are the preset meals you can choose from: ");
        int i, j;
        for(i=0; i < presetMeal.size(); i++)
        {
            System.out.println("||Meal: " + (i+1) + " ||");
            System.out.println("Name: " + presetMeal.get(i).getName());
            System.out.println("Calories: " + presetMeal.get(i).getCalories());
            System.out.println("Price: " + presetMeal.get(i).getPrice());

            System.out.println("Ingredients: " );

            //Get the ingredients list
            ArrayList<Item> ingredients = presetMeal.get(i).getIngredients();
            for(j = 0; j < ingredients.size(); j++){
                if(ingredients.get(j) != null){
                    System.out.println(ingredients.get(j).getName());
                }
            }
        }
    }

    /**
     * This method is used to dispense the meal ordered
     * @param meal Product object meal bought by the buyer
     * @param quantity number of meals bought
     */
    public void dispenseProduct(Product meal, int quantity){
        System.out.println("Preparing your order...");
        System.out.println("Gathering ingredients...");
        System.out.println("Mixing ingredients...");
        System.out.println("Putting ingredients in a bowl...");
        System.out.println("ALL SET!!");

        System.out.println("Here is your Order: ");
        System.out.println("Product: " + meal.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Thank you for buying at " + getName() + " Vending Machine!");

        //removing item from slot
        int i, j;
        for(j=0 ; j < quantity; j++){
            for(i=0 ; i< meal.getIngredients().size(); i++){
                Slot curSlot = searchSlot(meal.getIngredients().get(i).getName());
                curSlot.removeItem();
            }
        }

    }

    //customize

    /**
     * This method is used to add an order to the custom order list for record purposes
     * @param order orderslip object of the buyer
     */
    public void addCusOrder(OrderSlip order){
        this.customOrders.add(order);

    }

    /**
     * This method is used to dispense the custom order of the buyer
     * @param cusOrders the arraylist of orders the buyer chose
     */
    public void dispenseCusOrder(ArrayList<OrderSlip> cusOrders){
        System.out.println("Preparing your order...");
        System.out.println("Gathering ingredients...");
        System.out.println("Mixing ingredients...");
        System.out.println("Putting ingredients in a bowl...");
        System.out.println("ALL SET!!");

        System.out.println("Here is your Order: ");
        System.out.println("Products: " );

        ArrayList<OrderSlip> orders = cusOrders;
        int i;
        for(i = 0 ; i < orders.size() ; i++){
            System.out.println(orders.get(i).getProduct());
            Slot curSlot = searchSlot(orders.get(i).getProduct());
            if (curSlot != null) {
                curSlot.removeItem();
            } else {
                System.out.println("Error: Slot for the product not found.");
            }
        }
        System.out.println("Thank you for buying at " + getName() + " Vending Machine!");
    }

    /**
     * This method is used to return the array list of preset meals
     * @return preset meals
     */
    public ArrayList<Product> getPresetMeals(){
        return this.presetMeal;
    }

    /**
     * This method is used to return the array list of orders for the custom order
     * @return orders for the custom order
     */
    public ArrayList<OrderSlip> getCustomOrders(){
        return this.customOrders;
    }


}
