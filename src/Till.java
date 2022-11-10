import java.util.Scanner;
//what else does a till do 
//prints bill
//builds bill and a receipt 
//
/**
 * this is the till constructor
 * @author Thomas
 */
public class Till {
    Scanner in = new Scanner(System.in);
    private Restaurant rest;
    private boolean cash;
    private int amountDue;
    private Order order;
    private double creditCardT;
    private double cashInDrawer;
    private int amountGiven;

    public Till(Restaurant rest, int amountDue, Customer c){
        this.rest = rest;
        //TODO:this.order = rest.getCustomerOrder(c, rest); this needs to get the order of the customer
        //TODO:this.amountDue = (int) rest.getCustomerOrder(c, rest).getTotal();  this needs to get the total of the customers order
    }

    public int processPayment(){
        System.out.println("1) Cash or 2) Card");
        int choice = in.nextInt();
        System.out.println("Enter amount given ");
        amountGiven = in.nextInt();

        if(choice == 1 && amountGiven > amountDue){
            cash = true;
            cashInDrawer = cashInDrawer+amountDue;
        }else if(amountGiven > amountDue){
            creditCardT = creditCardT+amountDue;
        }
        int change = 0;
        if(cash && amountGiven>amountDue){
            change = amountGiven - amountDue;
        }
        return change;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public int getAmountGiven() {
        return amountGiven;
    }

    public double getCashInDrawer() {
        return cashInDrawer;
    }

    public double getCreditCardT() {
        return creditCardT;
    }

    public Order getOrder() {
        return order;
    }

    public Restaurant getRest() {
        return rest;
    }

   
}
