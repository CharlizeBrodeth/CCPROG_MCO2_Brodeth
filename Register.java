import java.util.ArrayList;

/**
 * This class: Register is used to represents a cash register
 *
 * @author : Charlize Kirsten M. Brodeth
 * @version 1.0
 */
public class Register {
    //Variables used//
    private ArrayList<Money> moneyStock1000;

    private ArrayList<Money> moneyStock100;

    private ArrayList<Money> moneyStock10;

    private ArrayList<Money> moneyStock1;


    /**
     * This constructor is used to initialize an array for the chosen money denominations
     */
    public Register(){
        this.moneyStock1000 = new ArrayList<>();
        this.moneyStock100 = new ArrayList<>();
        this.moneyStock10 = new ArrayList<>();
        this.moneyStock1 = new ArrayList<>();
    }

    /**
     * This method is used to add money object to its respective array based on its value
     * @param cash money object to be added
     */
    public void addCash(Money cash){
        if(cash.getValue() == 1000){
            moneyStock1000.add(cash);
        }
        else if(cash.getValue() == 100){
            moneyStock100.add(cash);
        }
        else if(cash.getValue() == 10){
            moneyStock10.add(cash);
        }
        else if(cash.getValue() == 1){
            moneyStock1.add(cash);
        }
    }


    /**
     * This method is used to check if there enough stock of money in each array list to dispense as change
     * @param cash due change amount
     * @return true if there is enough of the money stock for all denominations false otherwise
     */
    public boolean checkEnoughChange(double cash){
        int ones, tenths, hundreds, thousands;
        ones = (int)cash % 10;
        tenths = ((int)cash / 10 ) %10;
        hundreds = ((int)cash / 100) % 10;
        thousands = (int)cash / 1000;
        //check if mas mataas ung mga nasa array ng money kaysa sa kailangan na cash
        if(moneyStock1.size() > ones && moneyStock10.size() > tenths && moneyStock100.size() > hundreds && moneyStock1000.size() > thousands ){
            return true;
        }
        else
            return false;
    }


    /**
     * This method is used to break down the money into its ones, tenths, hundreds, and thousands units and get the amount of those units from the respective money slot and transfer it to a change array
     * @param cash due change
     * @return arraylist of money as the break down of the change
     */
    public ArrayList<Money> getChange(double cash){
        int ones, tenths, hundreds, thousands, i;
        ArrayList<Money> changeSet = new ArrayList<>();
         ones = (int)cash % 10;
         tenths = ((int)cash / 10 ) %10;
         hundreds = ((int)cash / 100) % 10;
         thousands = (int)cash / 1000;


         if(ones > 0){
             for(i = ones; i > 0; i--) {
                 int indexToTransfer = (moneyStock1.size() - 1);
                 if (indexToTransfer >= 0 && indexToTransfer < moneyStock1.size()){
                     Money moneyToTransfer = moneyStock1.remove(indexToTransfer);
                     changeSet.add(moneyToTransfer);
                 }
             }
         }

         if(tenths > 0){
             for(i = tenths; i > 0; i--) {
                 int indexToTransfer = (moneyStock10.size() - 1);
                 if (indexToTransfer >= 0 && indexToTransfer < moneyStock10.size()){
                     Money moneyToTransfer = moneyStock10.remove(indexToTransfer);
                     changeSet.add(moneyToTransfer);
                 }
             }

         }

         if(hundreds > 0){
             for(i = hundreds; i > 0; i--) {
                 int indexToTransfer = (moneyStock100.size() - 1);
                 if (indexToTransfer >= 0 && indexToTransfer < moneyStock100.size()){
                     Money moneyToTransfer = moneyStock100.remove(indexToTransfer);
                     changeSet.add(moneyToTransfer);
                 }
             }

         }

         if(thousands > 0){
             for(i = thousands; i > 0; i--) {
                 int indexToTransfer = (moneyStock1000.size() - 1);
                 if (indexToTransfer >= 0 && indexToTransfer < moneyStock1000.size()){
                     Money moneyToTransfer = moneyStock1000.remove(indexToTransfer);
                     changeSet.add(moneyToTransfer);
                 }
             }

         }

         return changeSet;
    }

    /**
     * This method is used to display the contents of the register
     */
    public void displayRegister(){
        System.out.println("Money stock [1]");
        System.out.println("Stock: " + moneyStock1.size());
        System.out.println("Total Amount: " + moneyStock1.size());
        System.out.println();
        System.out.println("Money stock [10]");
        System.out.println("Stock: " + moneyStock10.size());
        System.out.println("Total Amount: " + moneyStock10.size() * 10);
        System.out.println();
        System.out.println("Money stock [100]");
        System.out.println("Stock: " + moneyStock100.size());
        System.out.println("Total Amount: " + moneyStock100.size() * 100);
        System.out.println();
        System.out.println("Money stock [1000]");
        System.out.println("Stock: " + moneyStock1000.size());
        System.out.println("Total Amount: " + moneyStock1000.size() * 1000);
        System.out.println();
    }

    /**
     * This method is used to check if the money stocks are empty
     * @return true if ALL are empty false otherwise
     */
    public boolean isEmpty(){
        if(moneyStock1.isEmpty() && moneyStock10.isEmpty() && moneyStock100.isEmpty() && moneyStock1000.isEmpty()){
            return true;
        }
        else
            return false;
    }
}
