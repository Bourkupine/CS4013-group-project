// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 
// scanner we need to take an order 
// need to make a menu to
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class Order{
    private ArrayList<FoodItem> ordered = new ArrayList<>();
    private Customer c; // bayan:
    private int total = 0; //thomas adding up the total amount for the bill
    private Menu m = new Menu(); // todo: needs to get menu method in restaurant chain.
    private Chef chef = new Chef("King");
    private foodStatus status;
    
    public Order(Customer c){
        this.c = c; 
        status = foodStatus.ORDERING ;
        takeOrder(c);
        Bill bill = new Bill(this); //todo: WHY DOES THIS TAKE NULL AND NOT AN ORDER AGHHH maybe i can give it a customer ?
    }
    
    public enum foodStatus{
        WAITING,
        ORDERING,
        ORDERED,
        READY,
        DELIVERED;
    }
    
    /**
    *
    * @author Thomas
    * {@summary  order for the bill} 
    * 
    */
    public void takeOrder(Customer c){
        //t: creating the scanner
        
        Scanner order = new Scanner(System.in);
        
        System.out.println(m.toString()); //t:this is printing the menu so the customer can choose their items
        
        
        
        
        while(status == foodStatus.ORDERING ){
            // Menu item input
            int item = order.nextInt();
            if( item > 0 & item <= 13 ){
                
                //need to relate item number to whatever that no. is on the menu and put that in the array list... not just the no.
                ordered.add(m.getFoodItemAtIndex(item-1)); //t adding item to the string for the bill
                System.out.println("This is your current order " + "\n"); //printing what the customer has currently ordered so the can see what their order is currently
                System.out.println(ordered.toString()); // this is the array list of what they are currently ordering
                System.out.println("To order an item or another item enter the number of the item on the menu");
                System.out.println("To confirm your order enter the number 0");
                System.out.println("To cancel an item enter the number 14 ");
                System.out.println("To cancel your order enter the number 15");
                
            }else if (item == 0){
                status = foodStatus.ORDERED;
            }
            else if (item == 14){
                int removeItem = order.nextInt();
                ordered.remove(removeItem);
                
            }
            else if (item == 15){
                ordered.clear();
                status = foodStatus.WAITING ;
            }
        }
    } 
    
    public foodStatus getStatus() {
        return status;
    }

    public boolean setStatus(String status) {
        return(status.equals("WAITING"));
    }
    
    /**
    * {@summary will give a discount if thw customer has visited 10 or more times }
    @author Thomas
    */
    public void giveLoyaltyDiscount(){
        if (c.getLoyalty()%10 == 0){ //t when they get to 10 orders/visits the get a ten percent discount
            total -= total / 10;
        }
    }
    
    public boolean checkStatus(String status){ //this is used in is deliverable for the chef. 
        return ( status.equals("READY")); 
    }
    
    /**
    * {@summary Gets what customer c is }
    @author Thomas
    */
    public Customer getCustomer() {
        return c;
    }
    
    @Override
    //this definitely wont work but going to leave it here for now 
    //also dont think i need to have a to string here maybe
    public String toString() {
        
        return ordered.toString();
    }
    
    
    
}