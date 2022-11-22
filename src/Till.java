import java.text.BreakIterator;
import java.util.Scanner;
//what else does a till do 
//prints bill
//builds bill and a receipt 
//
/**
* this is the till class
* @author Thomas
*/
public class Till implements ReadWrite {
    
    private Restaurant rest;
    private double change;
    private double amountGiven;
    
    public Till(Restaurant rest) {
        this.rest = rest;
    }
    /**
    * processes a payment from the customer for the order.
    * @return returns the change due to the customer if any
    * @author Thomas
    */
    
   
    public void processPayment(Order order){
        Scanner in = new Scanner(System.in);
        change = 0;
        amountGiven = 0;
        
        Bill bill = new Bill(order);//this creates a bill for said order
        System.out.println( order.getTotal() + " is the total");
        double amountDue = order.getTotal();
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
            total = order.getTotal() + tip ;
            System.out.println( total + ": is the total");
            System.out.println("You tipped :" + tip);
            
            
        }else{ 
            tip = 0;
            System.out.println( total + ": is the total");
        }
        
        System.out.println("1) Cash or 2) Card");//is the customer paying in cash or card
        int choice = in.nextInt(); //they decide what they pay with
        
        double amountGiven = 0;
        
        while(amountGiven < amountDue){
            System.out.println(amountDue +" left to pay");
            System.out.println("Enter amount given "); //this is the amount the customer gives
            amountGiven += in.nextInt();
        }
        
        if(amountGiven> amountDue){
            System.out.println(change + " is the change");
        }
        printReceipt(order);
        updateFile(rest.getMoney(), toCsv(order));
        rest.removeOrder(order);
        
    }
    /**
    * prints the receipt
    * @return returns the receipt as a formatted String
    */
    private String printReceipt(Order order) { 
        return  
        String.format(order.toString()+ "\n"
        + "Amount given = " + amountGiven + "\n"
        + "Change = " + change + "\n"
        + "Total  €%.2f. Thank you for visiting Yum", order.getTotal());
    }
    
    private String toCsv(Order order){
        return order.getDate().toString()+","+order.getTotal();
    }
    
}
