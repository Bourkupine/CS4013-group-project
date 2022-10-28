// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 
// scanner we need to take an order 
// need to make a menu to
import java.util.Scanner;

public class Order{
    
    private Customer c;
    private int total = 0;
    
    public void takeOrder(){
        // creating the scanner
        Scanner order = new Scanner(System.in);
        
        System.out.println(Restaurant.printMenu());
        boolean ordering = true ;
        while(ordering ){
            // Menu item input
            int item = order.nextInt();
            if (item == 0){
                break;
            }
        }
        
    }
    
    
    /**
    * @param 
    */
    public void giveLoyaltyDiscount(){
        if (c.getLoyalty()%10 == 0){
            total -= total / 10;
        }
    }
    /**
    * @param adds up the total amount 
    * @author thomas
    * @return the bill
    */
    public double getBill(){
        
    }
    public  String Bill(){
        
    }
    
    // make new receipt
    
    @Override
    public String toString() {
        
        return Bill() + 
    }
    
    
    
}