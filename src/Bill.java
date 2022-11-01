// this will print the receipt 

public class Bill{

    private String receipt ;
    private Order order;

    public Bill(Order order){
       order.getC().incrementLoyalty();
       this.order=order;
    }
    

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public void setTableAvailable(){ //after the bill is payed
        //set reserved false

    }

    @Override
    public String toString() {
        
        return  "Total "+ order.getSb() + "Thank you for visiting Yum" ;  
    }
}
