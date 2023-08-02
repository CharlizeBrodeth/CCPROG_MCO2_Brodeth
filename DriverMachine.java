import java.util.ArrayList;
import java.util.Scanner;

public class DriverMachine {
    public static void main(String[] args){

        //Creating a Vending Machine
        Scanner sc = new Scanner(System.in);
        System.out.println("Lets Create The Vending Machine...");
        System.out.print("Enter The name of the Vending machine: ");
        String machineName = sc.nextLine();
        System.out.println();
        System.out.print("Enter number of slots: ");
        int machineCapacity = sc.nextInt();
        System.out.print("Enter number of items pr slot: ");
        int slotCapacity = sc.nextInt();
        sc.nextLine();
        System.out.println();

        VendingMachine machineReg = new VendingMachine(machineName, machineCapacity);

        ArrayList<Slot> machineSlots = machineReg.getSlots();
        int i, j;
        for(i=0; i < machineCapacity ; i++) {

            System.out.println("Slot #" + (i+1));
            System.out.print("Name of slot: ");
            String slotName = sc.nextLine();
            Slot newSlot = new Slot(slotName, slotCapacity);
            machineSlots.add(newSlot);

            System.out.print("Name of product: ");
            String prodName = sc.nextLine();
            System.out.print("Number of Calories: ");
            double calCount = sc.nextDouble();
            System.out.print("Price of product: ");
            double prodPrice = sc.nextDouble();
            sc.nextLine();

            Item newItem = new Item(prodName, calCount, prodPrice);

            for(j = 0; j < slotCapacity; j++){
                newSlot.addItem(newItem);
            }

        }

        //Displaying items
        machineReg.displayInventory();


        //Choose Role (Maintenance or Buyer)
        int choice1, choice2, choice3, choice4, choice6, orderQuantity, restockQuantity;
        String choice5, orderChoice;

        do{
            //check if there is money in the vending machine
            if(machineReg.checkRegisterEmpty())
            {
                System.out.println("WARNING THE REGISTER IS EMPTY, CANNOT PERFORM BUYER ACTION");
            }

            //menu
            System.out.println("Choose Role: ");
            System.out.println("[1] Maintenance");
            System.out.println("[2] Buyer");
            System.out.println("[3] Exit Program");
            do{
                System.out.print("Choice: ");
                choice1 = sc.nextInt();
            }
            while(choice1 > 3 || choice1 < 1);
            switch(choice1){
                case  1:{
                    System.out.println("[Maintenance]");
                    do{
                        System.out.println("Choose Action: ");
                        System.out.println("[1] Restock a slot");
                        System.out.println("[2] Replenish Funds");
                        System.out.println("[3] Back to Menu");
                        do{
                            System.out.print("Choice: ");
                            choice4 =sc.nextInt();
                        }
                        while(choice4 > 3 || choice4 < 1);

                        switch(choice4){
                            case 1:{
                                System.out.println("[Restock]");
                                System.out.println("Choose which slot to restock");
                                //displaying the stock of the slots
                                for(i = 0; i < machineReg.getSlotNumber(); i++){
                                    System.out.println("Slot: " + machineSlots.get(i).getName() + " Item: " + machineSlots.get(i).getItem().getName());
                                    System.out.println("Number of items in stock: " + machineSlots.get(i).getSlotSize());
                                    System.out.println();
                                }

                                System.out.print("Choice: ");
                                sc.nextLine();
                                choice5 = sc.nextLine();

                                Slot restockSlot = machineReg.searchSlotBySN(choice5);

                                System.out.print("How many items would you like to restock: ");
                                restockQuantity = sc.nextInt();

                                System.out.println("Now restocking slot...");
                                machineReg.restockSlot(restockSlot, restockQuantity);
                                for(i = 0; i < machineReg.getSlotNumber(); i++){
                                    System.out.println("Slot: " + machineSlots.get(i).getName() + " Item: " + machineSlots.get(i).getItem().getName());
                                    System.out.println("Number of items in stock: " + machineSlots.get(i).getSlotSize());
                                    System.out.println();
                                }
                                System.out.println("Restocking Done");
                                break;
                            }
                            case 2:{
                                Register cashReg = machineReg.getRegister();
                                cashReg.displayRegister();
                                double cash;
                                System.out.println("Insert bill, accepted denominations (1000, 100, 10, 1");
                                do{
                                    cash = sc.nextDouble();
                                    Money cashDen = new Money(cash);
                                    machineReg.acceptMoney(cashDen);

                                    System.out.println("Do you want to add more cash");
                                    System.out.println("[1] YES");
                                    System.out.println("[2] NO");
                                    do{
                                        System.out.print("Choice: ");
                                        choice6 = sc.nextInt();
                                    }
                                    while(choice6 > 2 || choice6 < 1);
                                }
                                while(choice6 != 2);
                            }
                        }
                    }
                    while(choice4 != 3);
                    break;
                }

                case 2:{
                    if(machineReg.checkRegisterEmpty()){
                        break;
                    }
                    System.out.println("[Buyer]");
                    do{
                        //display inventory
                        machineReg.displayInventory();

                        //getting the order
                        System.out.println("If you want to cancel your order type 'cancel' ");
                        System.out.print("Order (Pick Slot): ");
                        sc.nextLine();
                        orderChoice = sc.nextLine();

                        if(orderChoice.equalsIgnoreCase("cancel"))
                        {
                            break;
                        }
                        System.out.print("Order Quantity: ");
                        orderQuantity = sc.nextInt();

                        Slot orderSlot = machineReg.searchSlotBySN(orderChoice); //searching for slot name inde item name

                        double totalCost = machineReg.computeTotalPrice(orderSlot, orderQuantity);

                        OrderSlip curOrder = machineReg.createOrder(orderSlot, orderQuantity, totalCost);


                        System.out.println();
                        System.out.println("[Transaction]");
                        System.out.println("Payment Due: " + totalCost);
                        System.out.println();
                        System.out.println("Start Inserting bills, accepted bills are (1, 10, 100, 1000)");
                        double totalPay;
                        do{
                           double pay = sc.nextDouble();
                           Money payment = new Money(pay);
                           totalPay = machineReg.getTotalPayment(payment);

                            System.out.println("Do you wan to add more");
                            System.out.println("[1] YES");
                            System.out.println("[2] NO, payment enough");
                            do{
                                System.out.print("Choice: ");
                                choice3 = sc.nextInt();
                            }
                            while(choice3 > 2 || choice3 < 1);
                        }
                        while(choice3 != 2);

                        //payment process
                        System.out.println();
                        double change;
                        change = machineReg.computeChange(totalCost, totalPay);

                        if(machineReg.checkEnoughChange(change)){
                            machineReg.dispenseChange(change);
                            machineReg.dispenseItem(curOrder);
                        }
                        else{
                            System.out.println("Sorry the machine does not have enough change, here is your money back");
                        }

                        System.out.println("Would you like to buy an Item again?");
                        System.out.println("[1] YES");
                        System.out.println("[2] NO, back to menu");
                        do{
                            System.out.print("Choice: ");
                            choice2 = sc.nextInt();
                        }
                        while(choice2 > 2 || choice2 < 1);
                    }
                    while(choice2 !=2);
                    break;
                }
            }
        }
        while(choice1 != 3);
        System.out.println("Now exiting transaction with the Vending Machine...");
        System.out.println("Here is a summary of your Transaction");
        machineReg.displayTransaction();
    }
}
