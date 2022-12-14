import java.util.Scanner;
//what else does a till do 
//prints bill
//builds bill and a receipt 
//
/**
* A class to represent a till at a restaurant
*/
public class Till implements ReadWrite {
    
    private Restaurant rest;
    private double change;
    private double amountGiven;

    /**
     * Constructor for a Till object
     * @param rest the restaurant the till is for
     * @author Thomas
     */
    public Till(Restaurant rest) {
        this.rest = rest;
    }

    /**
     * processes a payment from the customer for the order.
     * @param order the order that is being processed
     * @author Thomas
     */
    public void processPayment(Order order){
        Scanner in = new Scanner(System.in);
        change = 0;
        amountGiven = 0;
        System.out.println( order.getTotal() + " is the total");
        order.getCustomer().incrementLoyalty();
        double amountDue = order.getTotal();
        if (order.isDiscounted()) {
            amountDue = amountDue / 10;
        }
        double tip;
        double totalTips = 0;
        double total = order.getTotal();
        System.out.println("Is the customer tipping ");
        System.out.println("1)Yes  2)No");
        int YN = in.nextInt();
        if(YN == 1  ){
            System.out.println("Enter tip amount");
            tip = in.nextInt();
            totalTips = totalTips + tip; 
            amountDue= order.getTotal() + tip ;
            System.out.println( total + ": is the total");
            System.out.println("You tipped :" + tip);
            
            
        }else{ 
            tip = 0;
            System.out.println( total + ": is the total");
        }
        
        System.out.println("1) Cash or 2) Card");//is the customer paying in cash or card
        int choice = in.nextInt(); //they decide what they pay with
        
       
        
        while(amountGiven < amountDue){
            System.out.println((amountDue - amountGiven) +" left to pay");
            System.out.println("Enter amount given "); //this is the amount the customer gives
            amountGiven += in.nextInt();
        }
        
        if(amountGiven> amountDue){
            change = amountGiven - amountDue ;
            System.out.println(change + " is the change");
        }
        order.getR().getRestaurantChain().updateCustomerCsv();
        printReceipt(order);
        updateFile(rest.getMoney(), toCsv(order));
        rest.removeOrder(order);
        
    }

    /**
     * Prints the receipt to standard output
     * @param order the order that the receipt is for
     * @author Bayan
     */
    private void printReceipt(Order order) {
        order.printOrder();
        System.out.printf("""
                Amount given: %.2f
                Change: %.2f
                Thank you for visiting Yum
                """, amountGiven, change);
    }

    /**
     * Returns an order's data in csv format
     * @param order Order being processed
     * @return order as csv formatted String
     * @author Ronan, Thomas
     */
    private String toCsv(Order order){
        return rest.getIdNum()+","+order.getDate().toString()+","+order.getTotal();
    }
    
}
