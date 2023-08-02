import java.util.ArrayList;

public class Testing {
    //private Register cashReg;

    public static void main(String[] args) {
       Register cashReg = new Register();
       Money Money1 = new Money(1);
       Money Money10 = new Money(10);
       Money Money100 = new Money(100);
       Money Money1000 = new Money(1000);

        int i;
        for(i = 0; i < 10; i++){
            cashReg.addCash(Money1);
            cashReg.addCash(Money10);
            cashReg.addCash(Money100);
            cashReg.addCash(Money1000);
        }

        cashReg.displayRegister();

        ArrayList<Money> changeSet = cashReg.getChange((double) 20);

        System.out.println("Here is change for 20: ");
        for(i = 0 ; i < changeSet.size(); i++){
            System.out.println(changeSet.get(i).getValue());
        }

        cashReg.displayRegister();
    }
}
