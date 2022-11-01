// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 
// scanner we need to take an order 
// need to make a menu to
import java.util.Formatter;
import java.util.Scanner;

public class Order{
    
    private Customer c; // bayan:
    private int total = 0; //thomas adding up the total amount for the bill

    private StringBuilder sb = new StringBuilder();

    public Order(Customer c){
        this.c = c; 
    }

    public StringBuilder getSb() {
        return sb;
    }
    /**
     *
     * @author thomas
     * @return order for the bill
     */
    public void takeOrder(){
        //t: creating the scanner
        Scanner order = new Scanner(System.in);
        
        System.out.println(Restaurant.printMenu()); //t:this is printing the menu so the customer can choose their items
        boolean ordering = true ; //t: this is for the customers they will input 0 when they want to stop ordering 
        while(ordering ){
            // Menu item input
            int item = order.nextInt();
            addItemToBill(item); //t adding item to the string for the bill

            if (item == 0){ //t this is breaking out of the ordering when the customer decides
                break;
            }
        }
        
    }
    
    
    
    /**
    * will update the Customer c's loyalty
    @author thomas
    */
    public void giveLoyaltyDiscount(){
        if (c.getLoyalty()%10 == 0){ //t when they get to 10 orders/visits the get a ten percent discount
            total -= total / 10;
        }
    }
   
 
    public void addItemToBill(int item){ //t adding item to the string bill
        sb.append(item).append("\n");//todo: .append price after item!!
    }

    public Customer getC() {
        return c;
    }
    
    @Override
    public String toString() {

        return sb.toString();
    }
    
    
    
}