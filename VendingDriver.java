import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class : VendingDriver serves as a driver class for vending machine
 */
public class VendingDriver {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //choose regular or special vending machine//
        int choice;
        System.out.println("What do you want to create:");
        System.out.println("[1] Regular Vending Machine");
        System.out.println("[2] Special Vending Machine");
        do{
            System.out.print("Choice: ");
            choice = sc.nextInt();
        }
        while(choice > 2 || choice < 1);

        //Switch choice//
        switch(choice){
            case 1:{ //Regular Vending machine//
                sc.nextLine();
                System.out.println("LET'S CREATE A REGULAR VENDING MACHINE");
                System.out.print("Enter The name of the Vending machine: ");
                String machineName = sc.nextLine();
                System.out.println();
                System.out.print("Enter number of slots: ");
                int machineCapacity = sc.nextInt();
                System.out.print("Enter number of items per slot: ");
                int slotCapacity = sc.nextInt();
                sc.nextLine();
                System.out.println();

                //creates regular vending machine//
                VendingMachine machineReg = new VendingMachine(machineName, machineCapacity);


                //initializing all elements in the slot//
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
                    System.out.print("Quantity to stock in Slot (must be greater than 0): ");
                    int initialQuan;
                    do{
                        initialQuan = sc.nextInt();
                        sc.nextLine();
                    }
                    while(initialQuan <= 0);

                    Item newItem = new Item(prodName, calCount, prodPrice);

                    for(j = 0; j < initialQuan; j++){
                        newSlot.addItem(newItem);
                    }

                }

                //initial display of individual items//
                machineReg.displayInventory();

                //Choose action (maintenance, buyer, see transaction, exit program)//
                //Variables//
                int choice1, choice2, choice3, choice4, choice6, orderQuantity, restockQuantity;
                String choice5, orderChoice;

                do{
                    //check if there is money in the vending machine//
                    if(machineReg.checkRegisterEmpty())
                    {
                        System.out.println("WARNING THE REGISTER IS EMPTY, CANNOT PERFORM BUYER ACTION");
                    }

                    //regular vending machine menu//
                    System.out.println("Choose Role: ");
                    System.out.println("[1] Maintenance");
                    System.out.println("[2] Buyer");
                    System.out.println("[3] See Transaction");
                    System.out.println("[4] Exit Program");
                    do{
                        System.out.print("Choice: ");
                        choice1 = sc.nextInt();
                    }
                    while(choice1 > 4 || choice1 < 1);
                    switch(choice1){ //maintenance//
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
                                        //displaying the stock of the slots//
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

                                        //restock action//
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
                                    case 2:{//replenish funds//
                                        System.out.println("[Replenish Funds]");

                                        Register cashReg = machineReg.getRegister();
                                        cashReg.displayRegister();
                                        double cash, totalamount= 0;
                                        System.out.println("Insert bill, accepted denominations (1000, 100, 10, 1)");
                                        do{
                                            cash = sc.nextDouble();
                                            Money cashDen = new Money(cash);
                                            machineReg.acceptMoney(cashDen);
                                            totalamount = totalamount + cash;

                                            System.out.println("Total Amount added so far: " + totalamount);
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
                                        System.out.println("Here are the amounts in the register: ");
                                        cashReg.displayRegister();
                                    }
                                }
                            }
                            while(choice4 != 3);
                            break;
                        }

                        case 2:{ //buyer//
                            //if cash register is empty it will not perform this action//
                            if(machineReg.checkRegisterEmpty()){
                                break;
                            }
                            System.out.println("[Buyer]");
                            do{
                                //display individual items inventory//
                                machineReg.displayInventory();

                                //getting the order//
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

                                Slot orderSlot = machineReg.searchSlotBySN(orderChoice); //searching for slot via Slot name

                                //check if item quantity in slot is greater than order quantity//
                                if(machineReg.checkforQuantityItem(orderSlot, orderQuantity) == false){
                                    System.out.println("Sorry, buying these items will make the inventory to 0");
                                    System.out.println("Please enter another quantity or restock this slot");
                                    break;
                                }

                                //calculate total cost of order//
                                double totalCost = machineReg.computeTotalPrice(orderSlot, orderQuantity);

                                //creating orderslip//
                                OrderSlip curOrder = machineReg.createOrder(orderSlot, orderQuantity, totalCost);

                                //payment transaction//
                                System.out.println();
                                System.out.println("[Transaction]");
                                System.out.println("Payment Due: " + totalCost);
                                System.out.println();
                                System.out.println("Start Inserting bills, accepted bills are (1, 10, 100, 1000)");
                                double totalPay = 0;
                                do{
                                    double pay = sc.nextDouble();
                                    Money payment = new Money(pay);
                                    totalPay =  totalPay + machineReg.getTotalPayment(payment);

                                    System.out.println("Current money inputted: " + totalPay);
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

                                if(totalPay < totalCost){
                                    System.out.println("The amount you inputted is not enough");
                                    break;
                                }

                                //payment process//
                                System.out.println();
                                double change;
                                change = machineReg.computeChange(totalCost, totalPay);

                                if(machineReg.checkEnoughChange(change)){
                                    machineReg.dispenseChange(change);
                                    machineReg.dispenseItem(curOrder);
                                }
                                else{
                                    System.out.println("Sorry the machine does not have enough change, here is your money back");
                                    machineReg.dispenseChange(totalPay);
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
                            machineReg.checkForRestock();
                            break;
                        }
                        case 3:{ //displaying transaction//
                            System.out.println("[Displaying past transactions so far: ]");
                            machineReg.displayTransaction();
                            break;
                        }
                    }
                }
                while(choice1 != 4);
                System.out.println("Now exiting transaction with the Vending Machine...");
                System.out.println("Here is a summary of your Transaction");
                machineReg.displayTransaction();
                //end//
                break;
            }
            case 2:{ // Special Vending Machine //
                int choice1;

                System.out.println("LETS MAKE A SPECIAL VENDING MACHINE");
                sc.nextLine();
                System.out.print("Enter The name of the Vending machine: ");
                String machineName = sc.nextLine();
                System.out.println();
                System.out.print("Enter number of slots: ");
                int machineCapacity = sc.nextInt();
                System.out.print("Enter number of items per slot: ");
                int slotCapacity = sc.nextInt();
                System.out.print("Enter number of preset meals: ");
                int numPreset = sc.nextInt();
                sc.nextLine();
                System.out.println();

                //creates special vending machine//
                SpecialVending machineSpec = new SpecialVending(machineName, machineCapacity, numPreset);

                //initializing all individual items in the slot//
                ArrayList<Slot> machineSlots = machineSpec.getSlots();
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
                    System.out.print("Quantity to stock in Slot (must be greater than 0): ");
                    int initialQuan;
                    do{
                        initialQuan = sc.nextInt();
                        sc.nextLine();
                    }
                    while(initialQuan <= 0);
                    sc.nextLine();

                    Item newItem = new Item(prodName, calCount, prodPrice);

                    for(j = 0; j < initialQuan; j++){
                        newSlot.addItem(newItem);
                    }

                }
                //add preset products//
                ArrayList<Product>  presetMeals = machineSpec.getPresetMeals();
                for(i=0; i < numPreset; i++){
                    sc.nextLine();
                    System.out.print("Meal# " + (i+1));
                    System.out.print("Meal name: ");
                    String mealName = sc.nextLine();
                    System.out.print("Calories: ");
                    double mealcal= sc.nextDouble();
                    System.out.print("Price: ");
                    double mealprice = sc.nextDouble();

                    Product newMeal = new Product(mealName, mealprice, mealcal);


                    System.out.println("Enter ingredients: ");
                    do{
                        sc.nextLine();
                        String ingredient = sc.nextLine();
                        Item foundingredient = machineSpec.searchItem(ingredient);
                        if(foundingredient != null){
                            newMeal.addIngredient(foundingredient);
                        }

                        System.out.println("Do you want to add more ingredients:");
                        System.out.println("[1] YES");
                        System.out.println("[2] NO");
                        do{
                            System.out.print("Choice: ");
                            choice1 = sc.nextInt();
                        }
                        while(choice1 > 2 || choice1 < 1 );
                    }
                    while(choice1 != 2);
                    machineSpec.addProduct(newMeal);

                }

                //initial display of individual items//
                machineSpec.displayInventory();

                //initial display of preset items//
                machineSpec.displayPresetOptions();

                //VARIABLES FOR CHOOSING//
                int choice4, choice6, choice3, choice2, restockQuantity, orderQuantity, mealOrder;
                String choice5, orderChoice;
                //Choose Role
                do{
                    //check if there is money in the vending machine//
                    if(machineSpec.checkRegisterEmpty())
                    {
                        System.out.println("WARNING THE REGISTER IS EMPTY, CANNOT PERFORM BUYER ACTION");
                    }

                    //special vending machine view//
                    System.out.println("Choose Role: ");
                    System.out.println("[1] Maintenance");
                    System.out.println("[2] Buyer");
                    System.out.println("[3] See Transaction");
                    System.out.println("[4] Exit Program");
                    do{
                        System.out.print("Choice: ");
                        choice1 = sc.nextInt();
                    }
                    while(choice1 > 4 || choice1 < 1);

                    switch(choice1){
                        case 1:{ //maintenance//
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
                                    case 1:{//restock//
                                        System.out.println("[Restock]");

                                        System.out.println("Choose which slot to restock");
                                        //displaying the stock of the slots//
                                        for(i = 0; i < machineSpec.getSlotNumber(); i++){
                                            System.out.println("Slot: " + machineSlots.get(i).getName() + " Item: " + machineSlots.get(i).getItem().getName());
                                            System.out.println("Number of items in stock: " + machineSlots.get(i).getSlotSize());
                                            System.out.println();
                                        }

                                        System.out.print("Choice: ");
                                        sc.nextLine();
                                        choice5 = sc.nextLine();

                                        Slot restockSlot = machineSpec.searchSlotBySN(choice5);

                                        System.out.print("How many items would you like to restock: ");
                                        restockQuantity = sc.nextInt();

                                        System.out.println("Now restocking slot...");
                                        machineSpec.restockSlot(restockSlot, restockQuantity);
                                        for(i = 0; i < machineSpec.getSlotNumber(); i++){
                                            System.out.println("Slot: " + machineSlots.get(i).getName() + " Item: " + machineSlots.get(i).getItem().getName());
                                            System.out.println("Number of items in stock: " + machineSlots.get(i).getSlotSize());
                                            System.out.println();
                                        }
                                        System.out.println("Restocking Done");
                                        break;
                                    }
                                    case 2:{//replenish funds//
                                        System.out.println("[Replenish Funds]");

                                        Register cashReg = machineSpec.getRegister();
                                        cashReg.displayRegister();
                                        double cash, totalamount= 0;
                                        System.out.println("Insert bill, accepted denominations (1000, 100, 10, 1)");
                                        do{
                                            cash = sc.nextDouble();
                                            Money cashDen = new Money(cash);
                                            machineSpec.acceptMoney(cashDen);
                                            totalamount = totalamount + cash;

                                            System.out.println("Total Amount added so far: " + totalamount);
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
                                        System.out.println("Here are the amounts in the register: ");
                                        cashReg.displayRegister();
                                    }
                                }
                            }
                            while(choice4 != 3);
                            break;
                        }

                        case 2:{ //buyer//
                            //if register is empty action will not be done//
                            if(machineSpec.checkRegisterEmpty()){
                                break;
                            }
                            System.out.println("[Buyer]");
                            do{
                                System.out.println("Choose what to buy: ");
                                System.out.println("[1] Individual Items");
                                System.out.println("[2] Preset Meals");
                                System.out.println("[3] Custom Order");
                                do{
                                    System.out.print("Choice: ");
                                    choice2 = sc.nextInt();
                                }
                                while(choice2 > 3 || choice2 < 1);

                                switch(choice2){
                                    case 1: { //individual items//

                                        //display inventory//
                                        machineSpec.displayInventory();

                                        //getting the order//
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

                                        Slot orderSlot = machineSpec.searchSlotBySN(orderChoice); //searching for slot name

                                        //check if the item quantity in the stock is greater than the order quantity
                                        if(machineSpec.checkforQuantityItem(orderSlot, orderQuantity) == false){
                                            System.out.println("Sorry, buying these items will make the inventory to 0");
                                            System.out.println("Please enter another quantity or restock this slot");
                                            break;
                                        }

                                        double totalCost = machineSpec.computeTotalPrice(orderSlot, orderQuantity);

                                        OrderSlip curOrder = machineSpec.createOrder(orderSlot, orderQuantity, totalCost);

                                        //Payment transaction//
                                        System.out.println();
                                        System.out.println("[Transaction]");
                                        System.out.println("Payment Due: " + totalCost);
                                        System.out.println();
                                        System.out.println("Start Inserting bills, accepted bills are (1, 10, 100, 1000)");
                                        double totalPay = 0;

                                        //getting money//
                                        do{
                                            double pay = sc.nextDouble();
                                            Money payment = new Money(pay);
                                            totalPay =  totalPay + machineSpec.getTotalPayment(payment);

                                            System.out.println("Current money inputted: " + totalPay);
                                            System.out.println("Do you wan to add more");
                                            System.out.println("[1] YES");
                                            System.out.println("[2] NO, payment enough");
                                            do{
                                                System.out.print("Choice: ");
                                                choice4 = sc.nextInt();
                                            }
                                            while(choice4 > 2 || choice4 < 1);
                                        }
                                        while(choice4 != 2);

                                        if(totalPay < totalCost){
                                            System.out.println("The amount you inputted is not enough");
                                            break;
                                        }

                                        //payment process//
                                        System.out.println();
                                        double change;
                                        change = machineSpec.computeChange(totalCost, totalPay);

                                        if(machineSpec.checkEnoughChange(change)){
                                            machineSpec.dispenseChange(change);
                                            machineSpec.dispenseItem(curOrder);
                                        }
                                        else{
                                            System.out.println("Sorry the machine does not have enough change, here is your money back");
                                            machineSpec.dispenseChange(totalPay);
                                        }
                                        break;
                                    }
                                    case 2:{ //preset meals//

                                        //display preset meals//
                                        machineSpec.displayPresetOptions();

                                        //oder choice (index of preset meal)//
                                        System.out.println("Enter [0] if you want to cancel your order");
                                        System.out.println("Choose the number of your order: " );
                                        mealOrder = sc.nextInt();

                                        if(mealOrder == 0){
                                            break;
                                        }
                                        System.out.println("Quantity: " );
                                        orderQuantity = sc.nextInt();


                                        ArrayList<Product> availMeals = machineSpec.getPresetMeals();
                                        Product meal = availMeals.get((mealOrder-1));
                                        double totalcost = meal.getPrice();

                                        //gather payment//
                                        System.out.println();
                                        System.out.println("[Transaction]");
                                        System.out.println("Payment Due: " + totalcost);
                                        System.out.println();
                                        System.out.println("Start Inserting bills, accepted bills are (1, 10, 100, 1000)");
                                        double totalPay = 0;

                                        do{
                                            double pay = sc.nextDouble();
                                            Money payment = new Money(pay);
                                            totalPay = totalPay + machineSpec.getTotalPayment(payment);

                                            System.out.println("Current money inputted: " + totalPay);
                                            System.out.println("Do you wan to add more");
                                            System.out.println("[1] YES");
                                            System.out.println("[2] NO, payment enough");
                                            do{
                                                System.out.print("Choice: ");
                                                choice4 = sc.nextInt();
                                            }
                                            while(choice4 > 2 || choice4 < 1);
                                        }
                                        while(choice4 != 2);

                                        if(totalPay < totalcost){
                                            System.out.println("The amount you inputted is not enough");
                                            break;
                                        }

                                        //payment//
                                        System.out.println();
                                        double change;
                                        change = machineSpec.computeChange(totalcost, totalPay);

                                        if(machineSpec.checkEnoughChange(change)){
                                            machineSpec.dispenseChange(change);
                                            machineSpec.dispenseProduct(meal, orderQuantity);
                                        }
                                        else{
                                            System.out.println("Sorry the machine does not have enough change, here is your money back");
                                            machineSpec.dispenseChange(totalPay);
                                        }
                                        break;
                                    }
                                    case 3:{ //custom order//

                                        //display inventory//
                                        machineSpec.displayInventory();

                                        System.out.println("Build your cart...");
                                        do{
                                            //getting the order//
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

                                            Slot orderSlot = machineSpec.searchSlotBySN(orderChoice);

                                            if(machineSpec.checkforQuantityItem(orderSlot, orderQuantity) == false){
                                                System.out.println("Sorry, buying these items will make the inventory to 0");
                                                System.out.println("Please enter another quantity or restock this slot");
                                                break;
                                            }

                                            double totalCost = machineSpec.computeTotalPrice(orderSlot, orderQuantity);

                                            OrderSlip curOrder = machineSpec.createOrder(orderSlot, orderQuantity, totalCost);

                                            //adding orders to the cart//
                                            machineSpec.addCusOrder(curOrder);

                                            System.out.println("Is that all you want to add in your cart");
                                            System.out.println("[1] YES");
                                            System.out.println("[2] NO");
                                            do{
                                                System.out.print("Choice: ");
                                                choice6 = sc.nextInt();
                                            }
                                            while(choice6 > 2 || choice6 < 1);
                                        }
                                        while(choice6 != 1);

                                        ArrayList<OrderSlip> cart = machineSpec.getCustomOrders();
                                        //getting total cost
                                        double customOrderCost =0;
                                        for(i =0 ; i < cart.size(); i++){
                                            customOrderCost = customOrderCost + cart.get(i).getPrice();
                                        }

                                        //getting payment//
                                        System.out.println();
                                        System.out.println("[Transaction]");
                                        System.out.println("Payment Due: " + customOrderCost);
                                        System.out.println();
                                        System.out.println("Start Inserting bills, accepted bills are (1, 10, 100, 1000)");
                                        double totalPay = 0;
                                        do{
                                            double pay = sc.nextDouble();
                                            Money payment = new Money(pay);
                                            totalPay = totalPay + machineSpec.getTotalPayment(payment);

                                            System.out.println("Current money inputted: " + totalPay);
                                            System.out.println("Do you wan to add more");
                                            System.out.println("[1] YES");
                                            System.out.println("[2] NO, payment enough");
                                            do{
                                                System.out.print("Choice: ");
                                                choice4 = sc.nextInt();
                                            }
                                            while(choice4 > 2 || choice4 < 1);
                                        }
                                        while(choice4 != 2);

                                        //payment process//

                                        if(totalPay < customOrderCost){
                                            System.out.println("The amount you inputted is not enough");
                                            break;
                                        }

                                        System.out.println();
                                        double change;
                                        change = machineSpec.computeChange(customOrderCost, totalPay);

                                        if(machineSpec.checkEnoughChange(change)){
                                            machineSpec.dispenseChange(change);
                                            machineSpec.dispenseCusOrder(cart);

                                        }
                                        else{
                                            System.out.println("Sorry the machine does not have enough change, here is your money back");
                                            machineSpec.dispenseChange(totalPay);
                                        }
                                        break;
                                    }
                                }
                                System.out.println("Do you want to buy again: ");
                                System.out.println("[1] YES");
                                System.out.println("[2] NO");
                                do{
                                    System.out.print("Choice: ");
                                    choice3 = sc.nextInt();
                                }
                                while(choice3 > 2 || choice < 1);
                            }
                            while(choice3 != 2); //exit buy transaction//

                           break;
                        }
                        case 3:{ //transaction//
                            System.out.println("[Displaying past transactions so far: ]");
                            machineSpec.displayTransaction();

                            break;
                        }
                    }
                }
                while(choice1 !=4); //Exit the program//
            }
        }
    }
}
