// Thomas : This is for the order
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Order{
    private ArrayList<FoodItem> ordered = new ArrayList<>();
    private Customer c; // bayan:
    private double total = 0; //Euan: changed from int to double to resemble currency better
    private Menu m; // todo: needs to get menu method in restaurant chain.//Ronan: fixed?
    private orderStatus status;
    private Restaurant r;
    private LocalDate date;//Today's date
    
    /**
    * Constructor that takes a Customer and Restaurant
    * @param c Customer
    * @param r Restaurant
    * @author Thomas
    */
    public Order(Customer c, Restaurant r){
        this.r = r; //initialising restaurant
        m = r.getMenu(); //getting the menu for the order
        this.c = c; //initialising the customer to identify who is ordering
        status = orderStatus.ORDERING ; // setting the enum status to ordering as the constructor for order has been invoked hence creating an order
        date=r.getDate();//Today's date
        takeOrder(c); //commencing the take order method where a waiter or manager will take a customers order

    }

    /**
     * Constructor to create an order from a booking
     * @param b Booking
     * @author Euan
     */
    public Order(Booking b) {
        this(b.getCustomer(), b.getRest());

    }

    /**
    * Enumerated values to represent the status of an order
    * @author Thomas
    */
    public enum orderStatus{
        ORDERING,// when the order is being taken
        ORDERED,//to let the chef know there is an order done
        READY,//this is so the chef can alert the waiter that an order is ready to be dropped 
        DELIVERED// the waiter lets the chef know it is delivered with no problems
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
        Scanner order = new Scanner(System.in);

        System.out.println(m.toString()); //t:this is printing the menu so the customer can choose their items
        while(status == orderStatus.ORDERING ){ // when the constructor of order is invoked takeOrder is invoked. take order runs when foodstatus is ordering
            System.out.printf("""
                    Your current total is €%.2f
                        To order an item, enter its number
                        [0] to confirm order
                        [%d] to remove an item
                        [%d] to cancel the order
                    """, total, m.getSize()+1, m.getSize()+2);
            System.out.printf("This is your current order\n %s\n", ordered.toString()); //printing what the customer has currently ordered so the can see what their order is currently
            int item = order.nextInt();//taking the no. of the item they want

            if( item > 0 & item <= m.getSize() ){ // //m.getsize allows it to be dynamic menu instead of static
                ordered.add(m.getFoodItemAtIndex(item-1)); //t adding item to the string for the bill
                total += m.getFoodItemAtIndex(item-1).getPrice(); // adding up the total for the customer to see what their total is at
            } else if (item == 0){ // this is exiting the system and commiting their order, finalising their order
                status = orderStatus.ORDERED; // lets the chef know the order is ready to be cooked
                r.getChef().cooking(this);
                System.out.println("Order confirmed");
            }
            else if (item == m.getSize()+1){ //remove order function
                System.out.println(ordered.toString());
                System.out.println("Enter the number on your order that you would like to remove");
                int removeItem = order.nextInt(); // the index no. that they want removed
                System.out.println(ordered.get(removeItem -1)+" was removed from the order");//letting the customer know what was removed
                total -= ordered.get(removeItem - 1).getPrice(); //minusing the price of the item from the total
                ordered.remove(removeItem - 1); //removing the item from the list of ordered stuff
                
            }
            else if (item == m.getSize()+2){
                ordered.clear();
            }
        }
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
    * @author Thomas
    */
    public void setStatus(String status) {
        for(orderStatus st : orderStatus.values()) {
            if (status.equalsIgnoreCase(st.toString())) {
                this.status = orderStatus.valueOf(status.toUpperCase());
            }
        }
    }

    /**
     * Calculates if a discount is applied to the order
     * @return boolean if loyalty discount is applied as boolean
     * @author Thomas
     */
    public boolean isDiscounted(){
        return c.getLoyalty()%10 == 0; //t when they get to 10 orders/visits the get a ten percent discount
    }



    /**
     * Checks if the foodStatus is ready
     * @param status foodStatus as String
     * @return boolean
     * @author Thomas
     */
    public boolean isReady(String status){ //this is used in is deliverable for the chef.
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
     * Returns date of order
     * @return date of order as LocalDate
     * @author Ronan
     */
    public LocalDate getDate() {
        return date;
    }

    /**
    * Returns Order as a String
    * @return String representation of the order
    * @author Bayan
    */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(c.getName() + ": ");
        for (int i = 0; i < ordered.size(); i++) {
            output.append(ordered.get(i).getName());
            if (i != ordered.size() - 1) {
                output.append(", ");
            }
        }
        return output.toString();
    }

    /**
     * Prints order details to standard output
     * @author Bayan
     */
    public void printOrder() {
        for (FoodItem f : ordered) {
            System.out.printf("%-20s%05.2f\n", f.getName(), f.getPrice());
        }
        System.out.println("Total: " + total);
    }
}