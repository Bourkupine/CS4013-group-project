// this will print the receipt 

public class Receipt extends Order {

    private String receipt ;

    

    public Receipt(Order order){
        this.order = order ;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public generateReceipt(Order order){
        
    }

    @Override
    public String toString() {
        
        return super.toString() + "Total "+ getBill() + "Thank you for visiting BigBums" ;
    }
}
