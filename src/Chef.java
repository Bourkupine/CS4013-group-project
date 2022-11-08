import java.util.ArrayList;





public class Chef extends Staff {
    
    private Waiter waiter ;
    private Order order ;
   
    
    public Chef(String name,String password,Restaurant rest){
        super(name,password,rest);
        
    }
    public int timeToDelivery(FoodItem item){
        
        
        if(item.getType() == "Starter"){
            return 5;
        }else if(item.getType()=="Main"){
            return 10;
            
        }else if(item.getType()=="Dessert"){
            return 5;
            
        }else{
            return 1;
        }
        
    }
    //this is going to have the chef start making food
    //aka change ordered to ready
    //todo: i need to figure out how to get the hour to be now like present.
    //Ronan: the int hour param and try catch block are temp just so this method doesnt throw an error
    public void MakeFood(int hour){
        if(getRest().areCustomersPresent(hour)){
            for(FoodItem item : order.getOrdered()){
                try {
                    wait(timeToDelivery(item));
                }catch (Exception ex){
                    System.err.println("hlep");
                }
            }
            order.setStatus("READY");
        }
    }
    
    public void isDeliverable(Order order) throws InterruptedException { // this will tell the waiter wether the order is ready to be dropped yet or not
        if ( order.checkStatus("READY" ) ){
            //if the enum value is ready then the waiter will drop it 
            waiter.bellRings() ;  // this tells the waiter the food is ready to be dropped by the chef ringing the bell
        }  
    }
    public void delivered (){
        order.setStatus("WAITING");
    }
}

