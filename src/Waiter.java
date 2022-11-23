

public class Waiter extends Staff {

    //Ronan: does this cause an error? chef never gets initialized

    /**
     * waiter constructor (creates a waiter)
     * @param name name of waiter
     * @param password their password
     * @param rest restaurant they work at
     * @author Thomas
     */
    public Waiter(String name,String password,Restaurant rest){
        super(name,password,rest);
    }

    /**
     * when called, the order is ready so the waiter gets permission to bring the order to a table
     * @author Thomas
     */
    public void getReadyOrders(){
       for(Order r: getRest().getOrders()){
          if(r.isReady("Ready")){
            dropFood(r);
            System.out.println("Food has been dropped");
          }
       }
    }

    /**
     * drops food to table and tells the chef it's been delivered
     * @param r the order being delivered
     * @author Thomas
     */
    public void dropFood(Order r){
        
       r.setStatus("Delivered");
      r.getR().getOrders().remove(r); //removing order from array list because the order is done
    }

}

