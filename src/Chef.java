
public class Chef extends Staff {
    

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
        //Is below supposed to take an order as a parameter(name needs changing then)or the order data field(remove parameter then)?
    
    public void cooking(Order o){
        if(o.checkStatus("ORDERED")){
            o.setStatus("READY");
        }
    }
    
    public void isDeliverable(Order o) { // this will tell the waiter wether the order is ready to be dropped yet or not
        if ( o.checkStatus("READY" ) ){
            //if the enum value is ready then the waiter will drop it 
            super.getRest().getWaiter().bellRings() ;  // this tells the waiter the food is ready to be dropped by the chef ringing the bell
        }  
    }
    public void delivered (){
        order.setStatus("WAITING");
    }
}

