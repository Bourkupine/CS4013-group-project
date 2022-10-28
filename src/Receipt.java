// this will print the receipt 

public class Receipt extends Order {

    private String receipt ;

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        
        return super.toString() + "Total "+ getBill() + "Thank you for visiting BigBums" ;
    }
}
