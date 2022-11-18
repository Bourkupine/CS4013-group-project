// this will print the receipt 

public class Bill{

    private final Order order;

    /**
     * Bill constructor
     * @param order the order the Bill is for
     * @author Thomas
     */
    public Bill(Order order){//thomas bill will take an object order
       order.getCustomer().incrementLoyalty(); // incrementing the loyalty as they have requested the bill and are about to pay
       this.order=order;
       Receipt r = new Receipt(order.getR(),order);
       order.updateRestaurantTotal(order.getTotal());
    }


    /**
     * prints the bill
     * @return returns the bill as a formatted String
     */
    @Override
    public String toString() { //printing the bill
        return  String.format("Total  €%.2f. Thank you for visiting Yum", order.getTotal());
    }
}