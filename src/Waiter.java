

public class Waiter extends Staff {
    private boolean canIDrop = false ;
    private Chef chef ;
    //Ronan: does this cause an error? chef never gets initialized
    
    public Waiter(String name,String password,Restaurant rest){
        super(name,password,rest);
    }

    public void bellRings(){
      canIDrop = true ;
    }

    public void dropFood(){
        if (canIDrop){
            chef.delivered();
        }
    }

    public void takingOrder(Customer c,int hour){
        if (getRest().areCustomersPresent(hour)){
        Order order = new Order(c, getRest());
        getRest().addOrder(order);//adds order to array in restaurant
        }
    }

}

