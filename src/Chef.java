

public class Chef extends Staff {
    
    private Waiter waiter ;
    private Order order ;
    
    public Chef(String name){
        super(name);
    }
    
    public void isDeliverable() { // this will tell the waiter wether the order is ready to be dropped yet or not
        if ( order.checkStatus("READY") ){ //if the enum value is ready then the waiter will drop it 
            waiter.bellRings() ;  // this tells the waiter the food is ready to be dropped by the chef ringing the bell
            
        }
      
    }
    public void delivered (){
        order.setStatus("WAITING");
    }
}