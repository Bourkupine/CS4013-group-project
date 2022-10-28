// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 


public class Order{
    
    private Customer c;
    private int total = 0;

     // this will increment the loyalty of said customer
    public void giveLoyaltyDiscount(){
    if (c.getLoyalty()%10 == 0){
        total -= total / 10;
        }
    }
    // make new receipt


    
}