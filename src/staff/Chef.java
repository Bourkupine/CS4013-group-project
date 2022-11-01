package staff;



public class Chef extends Staff {
    
    private FOODSTATUS status; // creating a variable to store ordered ready and delivered
    private Waiter waiter ;
    
    public Chef(String name){
        super(name);
    }
    
    public void setStatus(FOODSTATUS status) {
        this.status = status;
    }
    
    public enum FOODSTATUS { //assigning the enums to FOODSTATUS
        WAITING,
        ORDERED,
        READY, 
        DELIVERED; 
    }
    public void ordered(){
        setStatus(FOODSTATUS.ORDERED);
        
    }
    
    public void isDeliverable() { // this will tell the waiter wether the order is ready to be dropped yet or not
        if (getStatus() == FOODSTATUS.READY) { //if the enum value is ready then the waiter will drop it 
            waiter.bellRings(); // this tells the waiter the food is ready to be dropped by the chef ringing the bell
        }
    }
    
    public FOODSTATUS getStatus() {
        return status;
    }
    
    public void delivered(){ //tells the chef the food has been delivered
        setStatus(FOODSTATUS.WAITING);
    }
    
}
