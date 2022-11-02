



public class Waiter extends Staff {
    private boolean canIDrop = false ;
    private Chef chef ;
    
    public Waiter(String name){
        super(name);
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

