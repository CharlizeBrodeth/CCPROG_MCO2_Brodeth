/**
 * This class represents an orderslip for recording purposes
 * @author : Charlize Kirsten M. Brodeth
 * @version 2.0
 */
public class OrderSlip {
    //Variables used//
    private String product;

    private int quantity;

    private double totalPrice;


    /**
     * This constructor is used to initialize the orderlip
     * @param product name of item bought
     * @param quantity number of items bought
     * @param totalPrice total price of the order
     */
    public OrderSlip(String product, int quantity, double totalPrice){
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    //method getters

    /**
     * This method is used to return the name of the item  in the orderslip
     * @return item name
     */
    public String getProduct(){
        return this.product;
    }

    /**
     * This method is used to return the quantity of the items bought
     * @return the number of items bought
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * This method is used to return the price of the order
     * @return price
     */
    public double getPrice(){
        return this.totalPrice;
    }


}
