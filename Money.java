/**
 * This class :Money represents a money object
 *
 * @author : Charlize Kirsten M. Brodeth
 * @version 1.0
 */
public class Money {

    private double value;

    /**
     * This constructor is used to initialize the value of the money object
     * @param val value of the money
     */
    public Money(double val){
        if (val == 1000 || val == 100 || val == 10 || val == 1){
            this.value = val;
        }
    }

    // method getter

    /**
     * This method is used to return the value of the money object
     * @return value
     */
    public double getValue(){
        return this.value;
    }
}
