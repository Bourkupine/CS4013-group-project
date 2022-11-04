import java.util.ArrayList;

public class Chef extends Staff {
    
    private Waiter waiter ;
    private Order order ;
   
    
    public Chef(String name){
        super(name);
        
    }
    public int timeToDelivery(FoodItem item){
        
        
        if(item.getType() == "Starter"){
            
            return 5;
        }else if(item.getType()=="Main"){
            return 10;
            
        }else if(item.getType()=="Dessert"){
            return 5;
            
        }else if(item.getType()=="Drinks"){
            return 1;
        }
        
    }
    
    public void isDeliverable(Order order) { // this will tell the waiter wether the order is ready to be dropped yet or not
        if ( order.checkStatus("READY" ) ){
            for(FoodItem item : order.getOrdered()){
                wait(timeToDelivery(item));
            }
            //if the enum value is ready then the waiter will drop it 
            waiter.bellRings() ;  // this tells the waiter the food is ready to be dropped by the chef ringing the bell
        }  
    }
    public void delivered (){
        order.setStatus("WAITING");
    }
}

