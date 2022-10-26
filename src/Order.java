// Thomas : This is for the order 
// i need to increment the loayalty badge fro the customer. 
// i need to make a receipt with the customers details 


public class Order{
    
    
    private int total ;

     // this will increment the loyalty of said customer
    public void giveLoyaltyDiscount(){
    if (Customer.getLoyalty()%10 == 0){
        total = total - (total / 10);
        }
    }

    // make new receipt


    
}