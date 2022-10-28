// this will print the receipt 

public class Receipt{

    private String receipt ;
    private Order order;

    public Receipt(Order order){
       order.getC().incrementLoyalty();
       this.order=order;
    }
    

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        
        return super.toString() + "Total "+ order.getSb() + "Thank you for visiting Yum" ;  
    }
}
