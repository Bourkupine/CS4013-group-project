// this will print the receipt 

public class Bill implements ReadWrite{

    private final Order order;

    /**
     * Bill constructor
     * @param order the order the Bill is for
     * @author Thomas
     */
    public Bill(Order order){//thomas bill will take an object order
       order.getCustomer().incrementLoyalty(); // incrementing the loyalty as they have requested the bill and are about to pay
       this.order=order;
       updateFile(order.getR().getMoney(),toCsv());
       order.getR().getRestaurantChain().updateCustomerCsv();
       order.updateRestaurantTotal(order.getTotal());
    }


    /**
     * prints the bill
     * @return returns the bill as a formatted String
     */
    @Override
    public String toString() { //printing the bill
        return  String.format(order.toString()+ "/n" + "Total  €%.2f. Thank you for visiting Yum", order.getTotal());
    }

    private String toCsv(){
        return order.getDate().toString()+","+order.getTotal();
    }
}