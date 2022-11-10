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
    private double total = 0; //Euan: changed from int to double to resemble currency better
    private Menu m; // todo: needs to get menu method in restaurant chain.//Ronan: fixed?
    private foodStatus status;
    private Restaurant r;

    /**
     * Constructor that takes a Customer and Restaurant
     * @param c Customer
     * @param r Restaurant
     * @author Thomas
     */
    public Order(Customer c, Restaurant r){
        m = r.getMenu();
        this.c = c; 
        status = foodStatus.ORDERING ;
        takeOrder(c);
        Bill bill = new Bill(this); //todo: WHY DOES THIS TAKE NULL AND NOT AN ORDER AGHHH maybe i can give it a customer ?
    }

    /**
     * Enumerated values to represent the status of an order
     * @author Thomas
     */
    public enum foodStatus{
        WAITING,
        ORDERING,
        ORDERED,
        READY,
        DELIVERED;
    }

    /**
     * Manages the ordering process
     * @param c Customer making the order
     * @author Thomas, Euan
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
                System.out.printf("This is your current order\n %s\n", ordered.toString()); //printing what the customer has currently ordered so the can see what their order is currently
                total += m.getFoodItemAtIndex(item-1).getPrice();
                System.out.printf("""
                                Your current total is €%.2f
                                To order an item or another item enter the number of the item on the menu
                                To confirm your order enter the number 0
                                To cancel an item enter the number 14
                                To cancel your order enter the number 15
                                """, total); //Euan: cleaned up this chunk of print statements. Havent tested this with printf but i assume it works

                
            }else if (item == 0){
                status = foodStatus.ORDERED;
            }
            else if (item == 14){
                System.out.println("Enter the number on your order that you would like to remove");
                int removeItem = order.nextInt();
                total -= ordered.get(removeItem - 1).getPrice();
                ordered.remove(removeItem - 1);
                System.out.println(m.getFoodItemAtIndex(removeItem-1 )+" was removed from the order");
                
            }
            else if (item == 15){
                ordered.clear();
                status = foodStatus.WAITING ;
            }
        }
    }

    /**
     * Gets foodStatus
     * @return status as foodStatus
     * @author Thomas
     */
    public foodStatus getStatus() {
        return status;
    }

    /**
     * Gets ordered FoodItems
     * @return ordered as ArrayList of FoodItem
     * @author Thomas
     */
    public ArrayList<FoodItem> getOrdered() {
        return ordered;
    }

    /**
     * Sets foodStatus of the order
     * @param status foodStatus
     * @return boolean
     * @author Thomas
     */
    public boolean setStatus(String status) {
        return(status.equals("WAITING"));
    }

    /**
     * Calculates if a discount is applied to the order
     * @author Thomas
     */
    public void giveLoyaltyDiscount(){
        if (c.getLoyalty()%10 == 0){ //t when they get to 10 orders/visits the get a ten percent discount
            total -= total / 10;
        }
    }

    

    /**
     * Checks if the foodStatus is ready
     * @param status foodStatus as String
     * @return boolean
     */
    public boolean checkStatus(String status){ //this is used in is deliverable for the chef. 
        return ( status.equals("READY")); 
    }

    /**
     * Gets the Customer
     * @return c as Customer
     * @author Thomas
     */
    public Customer getCustomer() {
        return c;
    }

    /**
     * Returns order total
     * @return total as double
     * @author Thomas, Euan
     */
    public double getTotal() {
        return total;
    }

    /**
     * Returns Order as a String
     * @return String representation of the order
     * @author Bayan
     */
    @Override
    //this definitely wont work but going to leave it here for now 
    //also dont think i need to have a to string here maybe
    public String toString() {
        String output = "";
        for(FoodItem f : ordered) {
            output = output + f.getName() + " ";
        }
        return output;
    }
}