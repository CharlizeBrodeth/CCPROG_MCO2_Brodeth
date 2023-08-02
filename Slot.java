import java.util.ArrayList;

/**
 * The class : Slot represents a slot in a vending machine
 *
 * @author Charlize Kirsten M. Brodeth
 * @version 2.0
 */public class Slot {
     //Variables used //
    private String name;

    private int slotCapacity;

    private ArrayList<Item> vendingItems;

    /**
     * This constructor initializes the name and the capacity of a slot
     *
     * @param name name of the slot
     * @param cap slot capacity
     */
    public Slot(String name, int cap){
        this.name = name;
        this.slotCapacity = 10;
        if(cap >= 10){
            this.slotCapacity = cap;
        }
        this.vendingItems = new ArrayList<>();
    }


    // main methods
    /**
     * This method is used to add an item object to the list of items in a slot
     *
     * @param item item object representing a product in a vending machine
     */
    public void addItem(Item item){
        vendingItems.add(item);
    }

    /**
     * This method is used to remove an item from the slot array list (specifically the last item in the list)
     */
    public void removeItem(){
        vendingItems.remove(vendingItems.size() - 1);
    }

    //method getters
    /**
     * This method is used to return the name of the slot
     * @return name the name of the slot
     */
    public String getName(){
        return this.name;
    }

    /**
     * This method is used to return the item  object in the first index
     * @return copy of the first item in the vending items list
     */
    public Item getItem(){
        return this.vendingItems.get(0);
    }

    /**
     * This method is used to return the items in a slot
     * @return vendingItems - the list of items in a slot
     */
    public ArrayList<Item> getItems(){
        return this.vendingItems;
    }

    /**
     * This method is used to get the size of the slot currently
     * @return the current size of the vending items array list
     */
    public int getSlotSize(){
        return this.vendingItems.size();
    }

    /**
     * This method returns the capacity of the slot
     * @return slot capacity - the total amount of slot identified
     */
    public int getSlotCapacity(){
        return this.slotCapacity;
    }
}
