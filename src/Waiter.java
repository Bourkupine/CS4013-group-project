

public class Waiter extends Staff {
    private boolean canIDrop = false ;

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
    public void bellRings(){
      canIDrop = true ;
    }

    /**
     * if canIDrop = true, drops food to table and tells the chef its been delivered
     * @author Thomas
     */
    public void dropFood(){
        if (canIDrop){
            getRest().getChef().delivered();
        }
    }

    

}

