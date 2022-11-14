// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 
// scanner we need to take an order 
// need to make a menu to
import java.util.ArrayList;
import java.util.Scanner;


public class Order{//Ronan: do we need to write to csv here?
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
        m = r.getMenu(); //getting the menu for the order
        this.c = c; //initialising the customer to identify who is ordering
        status = foodStatus.ORDERING ; // setting the enum status to ordering as the constructor for order has been invoked hence crwating an order
        takeOrder(c); //commencing the take order method where a waiter or manager will take a customers order
    }
    
    /**
    * Enumerated values to represent the status of an order
    * @author Thomas
    */
    public enum foodStatus{
        WAITING,//set to this by the waiter when an order has been dropped
        ORDERING,// when the order is being taken
        ORDERED,//to let the chef know there is an order done
        READY,//this is so the chef can alert the waiter that an order is ready to be dropped 
        DELIVERED;// the waiter lets the chef know it is delivered with no problems 
    }

    /**
     * adds the order total to Daily Restaurant Earnings
     * @param total order total as a double
     * @author Euan
     */
    public void updateRestaurantTotal(double total) {
        r.addToDailyEarnings(total);
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
        
        while(status == foodStatus.ORDERING ){ // when the constructor of order is invoked takeOrder is invoked. take order runs when foodstatus is ordering
            // Menu item input
            int item = order.nextInt();//taking the no. of the item they want
            if( item > 0 & item <= m.getSize() ){ // //m.getsize allows it to be dynamic menu instead of static
                
                ordered.add(m.getFoodItemAtIndex(item-1)); //t adding item to the string for the bill
                System.out.printf("This is your current order\n %s\n", ordered.toString()); //printing what the customer has currently ordered so the can see what their order is currently
                total += m.getFoodItemAtIndex(item-1).getPrice(); // adding up the total for the customer to see what their total is at
                System.out.printf("""
                Your current total is €%.2f
                To order an item or another item enter the number of the item on the menu
                To confirm your order enter the number 0
                To cancel an item enter the number\s"""+(m.getSize() + 1)+
                "\nTo cancel your order enter the number " + (m.getSize() + 2)+"\n"
                , total); //Euan: cleaned up this chunk of print statements. Havent tested this with printf but i assume it works
                
                
            }else if (item == 0){ // this is exiting the system and commiting their order, finalising their order
                status = foodStatus.ORDERED; // lets the chef know the order is ready to be cooked
            }
            else if (item == m.getSize()+1){ //remove order function
                System.out.println(ordered.toString());
                System.out.println("Enter the number on your order that you would like to remove");
                int removeItem = order.nextInt(); // the index no. that they want removed
                System.out.println(ordered.get(removeItem )+" was removed from the order");//letting the customer know what was removed
                total -= ordered.get(removeItem - 1).getPrice(); //minusing the price of the item from the total
                ordered.remove(removeItem - 1); //removing the item from the list of ordered stuff
                
            }
            else if (item == m.getSize()+2){
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
    * get restaurant
    * @return restaurant
    * @author Thomas
    */
    public Restaurant getR() {
        return r;
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
    public String toString() {
        StringBuilder output = new StringBuilder(c.getName());
        output.append("{");
        for(int i = 0; i < ordered.size(); i++) {
            output.append(ordered.get(i).getName());
            if (i != ordered.size() - 1) {
                output.append(", ");
            }
        }
        output.append("}");
        return output.toString();
    }
}