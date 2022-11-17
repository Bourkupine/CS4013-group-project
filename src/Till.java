import java.util.Scanner;
//what else does a till do 
//prints bill
//builds bill and a receipt 
//
/**
* this is the till class
* @author Thomas
*/
public class Till {
    Scanner in = new Scanner(System.in);
    private double change = 0;
    private Restaurant rest;
    private boolean cash;
    
    private Order order;
    private double creditCardT;
    private double cashInDrawer;
    private double amountDue;//TODO: this is in constructor but never used???
    /**
    * 
    * @param rest this is the restaurant the till is in
    * @author Thomas
    */
    public Till(Restaurant rest, Order order){
        this.rest = rest;//this is the restaurant
        this.order = order;//this is the order
        this.amountDue = order.getTotal();//this is the amount the customer owes
    }
    /**
    * processes a payment from the customer for the order.
    * @return returns the change due to the customer if any
    * @author Thomas
    */

    //TODO is there really a point of returning change if we are going to tell them how much to give,
    //also method is called but returned type is never used
    public double processPayment(){
        
        Bill bill = new Bill(order);//this creates a bill for said order
        System.out.println( order.getTotal() + " is the total");
        double amountDue = order.getTotal();
        System.out.println("1) Cash or 2) Card");//is the customer paying in cash or card
        int choice = in.nextInt(); //they decide what they pay with
        System.out.println("Enter amount given "); //this is the amount the customer gives
        int amountGiven = in.nextInt();
        System.out.println(bill.toString());
        
        
        if(choice == 1 && amountGiven > amountDue){
            cash = true;
            cashInDrawer = cashInDrawer+amountDue;
        }else if(amountGiven > amountDue){
            creditCardT = creditCardT+amountDue;
            change = 0;
        }
        
        if(cash && amountGiven>amountDue){
            change = amountGiven - amountDue;
        }
        rest.removeOrder(order);
        System.out.println(bill.toString());
        System.out.printf("Give %f", change);
        return change;
        
    }
    
}
