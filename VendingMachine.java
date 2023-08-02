import java.util.ArrayList;

/**
 *  This class : VendingMachine Represents a REGULAR vending machine
 *
 * @author : Charlize Kirsten M. Brodeth
 * @version 2.0
 */
public class VendingMachine {

    //Variables used in the class//
    private String name;
    private ArrayList<Slot> slots;
    private ArrayList<OrderSlip> orderSlips;
    private OrderSlip curOrder;
    private int slotAvailable;
    private Register cashRegister;

    /**
     * This constructor is used to initialize the name and the number of slots the vending machine has, it also creates
     * an array of slots and creates a cashregister
     * @param name name of vending machine
     * @param cap number of slots in the machine
     */
    public VendingMachine(String name, int cap) {
        this.name = name;
        this.slotAvailable = 8;
        if(cap >= 8)
        {
            this.slotAvailable = cap;
        }
        this.slots = new ArrayList<>();
        this.orderSlips = new ArrayList<>();
        this.curOrder = null;
        this.cashRegister = new Register();
    }

    /**
     * This method is used to display the inventory of the machine of the list of items for sale
     */
    public void displayInventory(){
        int i;
        System.out.println();
        System.out.println("Vending Machine : " + this.name);
        System.out.println("Here or the items in stock...");
        for(i=0; i < slots.size(); i++)
        {
            if(slots.get(i) == null)
            {
                System.out.println("There are no items in slot: " + slots.get(i).getName());
                break;
            }
            System.out.println("|| Slot: " + slots.get(i).getName() + " ||");
            System.out.println("Item: " + slots.get(i).getItem().getName());
            System.out.println("Calories: " + slots.get(i).getItem().getCalories());
            System.out.println("Price: " + slots.get(i).getItem().getPrice());
            System.out.println("Stock: " + slots.get(i).getSlotSize());
            System.out.println(" ");
        }
    }

    /**
     * This method is used to display the transactions made by the buyer
     */
    public void displayTransaction(){
        if(orderSlips.isEmpty()){
            System.out.print("There are no transactions yet, make a purchase to view them");
            return;
        }
        int i;
        double totalMoneyCost = 0;
        for(i = 0; i < orderSlips.size(); i++){
            System.out.println("Transaction # " + (i+1));
            System.out.println("Product: " + orderSlips.get(i).getProduct());
            System.out.println("Quantity: " + orderSlips.get(i).getQuantity());
            System.out.println("Price: " + orderSlips.get(i).getPrice());
            totalMoneyCost = totalMoneyCost + orderSlips.get(i).getPrice();
            System.out.println(" ");
        }
        System.out.println("Total Price of all items bought: " + totalMoneyCost);
    }

    //Maintenance methods
    // restock, replenish, add cash

    /**
     * This method is used to check if there is money in the cashRegister
     * @return true if the register empty
     */
    public boolean checkRegisterEmpty(){
        if (cashRegister.isEmpty()){
            System.out.println("Warning!! cash register is empty, might want to add some cash to it");
            return true;
        }
        else
            return false;
    }

    /**
     * This method is used to check if the cash input is valid;
     * @param cash Money object inputted
     * @return true if money is valid false otherwise
     */
    public boolean checkValidCash(Money cash){
        if(cash.getValue() == 1000 || cash.getValue() == 100 || cash.getValue() == 10 || cash.getValue() == 1){
            return true;
        }
        else
            return false;
    }


    /**
     * This method is used to restock a slot given a quantity
     * @param slot slot to be restocked
     * @param quantity how many items are to be restocked
     */
    public void restockSlot(Slot slot, int quantity){
        int i;
        for(i = 0 ; i < quantity ; i++){
            if(slot.getSlotSize() == slot.getSlotCapacity()){
                break;
            }
            slot.addItem(slot.getItem());
        }
    }


    /**
     * This method is used to check which slots are needed to be restocked (if items are < 3  then it prints out a warning that slot needs to be restocked)
     */
    public void checkForRestock(){
        int i;
        for(i = 0 ; i < slots.size(); i++){

            if(slots.get(i).getSlotSize() < 3){
                System.out.println("ATTENTION!! SLot: " + slots.get(i).getName() + " is running out of stock, please use restock function to replenish the items");
            }
            else{
                System.out.println("Slot: "+ slots.get(i).getName() + " does not need any restocking");
            }
        }

    }

    //buyer methods

    /**
     * This method is used to compute for the total price of an order
     * @param slot slot to be bought from
     * @param quantity how many items are to be bought
     * @return
     */
    public double computeTotalPrice(Slot slot, int quantity){
        double total;
        total = slot.getItem().getPrice() * quantity;

        return total;
    }

    /**
     * This method is used to create an order for record purposes
     * @param slot slot to be bought from
     * @param quantity how many items are to be bought
     * @param totalPrice total price of the order
     * @return
     */
    public OrderSlip createOrder(Slot slot, int quantity, double totalPrice){
        OrderSlip newOrder = new OrderSlip(slot.getItem().getName(), quantity, totalPrice);
        this.curOrder = newOrder;
        this.orderSlips.add(newOrder);

        return curOrder;
    }

    /**
     * This method is used to accept money into the cash register
     * @param cash Money object to be added to the cash register
     * @return returns true if money was put into the cash register
     */
    public boolean acceptMoney(Money cash){
        if(checkValidCash(cash)){
            this.cashRegister.addCash(cash);
            return true;
        }
        else{
            System.out.println("Invalid cash pls try again");
            return false;
        }

    }

    /**
     * This method is used to compute for the total payment from the user
     * @param cash Money object as payment from user
     * @return the amount of payment the  user paid
     */
    public double getTotalPayment(Money cash){
        double amountPaid = 0;
        if(acceptMoney(cash)){
            amountPaid = amountPaid + cash.getValue();
        }
        return amountPaid;
    }

    /**
     * This method is used to compute for the change to be given
     * @param price price of te order
     * @param payment payment of the buyer
     * @return the amount of change needed to be given to the buyer
     */
    public double computeChange(double price , double payment){
        double change;
        change = payment - price;
        return change;
    }

    /**
     * This method is used to dispense an item or order
     * @param curOrder the order record of the current transaction
     */
    public void dispenseItem(OrderSlip curOrder){
        System.out.println("Here is your Order: ");
        System.out.println("Product: " + curOrder.getProduct());
        System.out.println("Quantity: " + curOrder.getQuantity());
        System.out.println("Thank you for buying at " + this.name + " Vending Machine!");
        int i;
        Slot curSlot = searchSlot(curOrder.getProduct()); //inde nababasa ung slot kasi nippass ung order item name inde slot name

        if (curSlot != null) {
            for (i = 0; i < curOrder.getQuantity(); i++) {
                curSlot.removeItem();
            }
        } else {
            System.out.println("Error: Slot for the product not found.");
        }

    }

    /**
     * This method is used to check if there is enough change in the cash register
     * @param cash change due to the buyer
     * @return true if there is enough change false otherwise
     */
    public boolean checkEnoughChange(double cash){
        if(cashRegister.checkEnoughChange(cash)){
            return true;
        }
        else
            return false;
    }

    /**
     * This method is used to dispense the change into its respective denominations
     * @param change amount of change due to the user
     */
    public void dispenseChange(double change){
        System.out.println("Your Change: ");
        ArrayList<Money> changeSet = cashRegister.getChange((double)change);
        int i;
        for(i=0; i < changeSet.size(); i++){
            System.out.println(changeSet.get(i).getValue());
        }
        System.out.println("in total: " + change);
    }

    /**
     * This method is used to return a slot given by its product name
     * @param name name of product in the slot
     * @return slot of the respective item
     */
    public Slot searchSlot(String name){ //by product name
        int i;
        Slot foundSlot = null;
        for(i = 0 ; i < slots.size(); i++) {
            if (slots.get(i).getItem().getName().equalsIgnoreCase(name)) {
                foundSlot = slots.get(i);
            }
        }

        return foundSlot;
    }

    /**
     * This method is used to return the slot given its slot name
     * @param name name of slot to be searched for
     * @return slot with the respective name
     */
    public Slot searchSlotBySN(String name){ //by slot name
        int i;
        Slot foundSlot = null;
        for(i = 0 ; i < slots.size(); i++) {
            if (slots.get(i).getName().equalsIgnoreCase(name)) {
                foundSlot = slots.get(i);
            }
        }

        return foundSlot;

    }

    /**
     * This method is used to return an Item object given its name
     * @param name name of the item
     * @return Item of the same name of its parameter
     */
    public Item searchItem(String name){
        int i;
        Item foundItem = null;
        for(i=0; i < slots.size() ;i++ ){
            if(slots.get(i).getItem().getName().equalsIgnoreCase(name)){
                foundItem = slots.get(i).getItem();
            }
        }
        return foundItem;
    }

    /**
     * This method is used to check if the quantity of the items in the slot exceeds the order quantity of the buyer
     * @param slot slot to be bought from
     * @param quantity number of items to be bought
     * @return true if slot size exceed order quantity
     */
    public boolean checkforQuantityItem(Slot slot, int quantity){

        if (slot.getSlotSize() > quantity){
            return true;
        }
        else
            return false;
    }

    //getters

    /**
     * This method is used to return the array list of slots of the machine
     * @return array list of slots
     */
    public ArrayList<Slot> getSlots(){
        return this.slots;
    }

    /**
     * This method is used to return the number of slots the machine has
     * @return
     */
    public int getSlotNumber(){
        return this.slots.size();
    }

    /**
     * This method is used to return the register object used in the class
     * @return register object
     */
    public Register getRegister(){
        return this.cashRegister;
    }

    /**
     * This method is used to return the name of the vending machine
     * @return name
     */
    public String getName(){
        return this.name;
    }
}
