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
    private double amountDue;
    private Order order;
    private double creditCardT;
    private double cashInDrawer;
    private int amountGiven;
    /**
    * 
    * @param rest this is the restaurant the till is in
    * @param amountDue this is how much the order came to
    * @param c this is the customer that owes us money grrr
    * @author Thomas
    */
    public Till(Restaurant rest, Order order){
        this.rest = rest;
        this.order = order;
        this.amountDue = order.getTotal();
    }
    /**
    * processes a payment from the customer for the order.
    * @return returns the change due to the customer if any
    * @author Thomas
    */
    public double processPayment(){
        System.out.println("1) Cash or 2) Card");
        int choice = in.nextInt();
        System.out.println("Enter amount given ");
        amountGiven = in.nextInt();
        
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
        Bill bill = new Bill(order);
        System.out.println(bill.toString());
        System.out.println("Give"+ getChange() );
        return change;
        
    }
    /**
    * 
    * @return gets Amount due which is how much the customer owes us
    * @author Thomas
    */
    public double getAmountDue() {
        return amountDue;
    }
    /**
    * getting the change 
    * @return change
    * @author Thomas
    */
    public double getChange() {
        return change;
    }
    /**
    * 
    * @return this is how much the customer has given us so far
    * @author Thomas
    */
    public int getAmountGiven() {
        return amountGiven;
    }
    /**
    * 
    * @return amount in the drawer currently
    * @author Thomas
    */
    public double getCashInDrawer() {
        return cashInDrawer;
    }
    /**
    * 
    * @return How much we have been payed with credit cards
    * @author Thomas 
    */
    public double getCreditCardT() {
        return creditCardT;
    }
    /**
    * 
    * @return gets the customers order
    * @author Thomas
    */
    public Order getOrder() {
        return order;
    }
    /**
    * 
    * @return gets the Restaurant
    * @author Thomas
    */
    public Restaurant getRest() {
        return rest;
    }
    
    
}
