

public class Waiter extends Staff {
    private boolean canIDrop = false ;
    private Chef chef ;
    //Ronan: does this cause an error? chef never gets initialized
    
    public Waiter(String name,String password){
        super(name,password);
    }

    public void bellRings(){
      canIDrop = true ;
    }

    public void dropFood(){
        if (canIDrop){
            chef.delivered();
        }
    }
}

