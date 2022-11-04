// this will print the receipt 

public class Bill{

    private String receipt ;
    private Order order;

    public Bill(Order order){//thomas bill will take an object order
       order.getCustomer().incrementLoyalty(); // incrementing the loyalty as they have requested the bill and are about to pay
       this.order=order; 
    }
    

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }


    @Override
    public String toString() { //printing the bill
        
        return  "Total "+ "Thank you for visiting Yum" ;  
    }
}