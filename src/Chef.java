public class Chef extends Staff {
    private Order order ;

    /**
     * Creates Chef using full-arg constructor from Staff
     * @param name name of staff
     * @param password password of staff
     * @param rest restaurant of staff
     * @author Thomas
     */
    public Chef(String name,String password,Restaurant rest){
        super(name,password,rest);//the name of the chef his password and restaurant 
        
    }
//    public int timeToDelivery(FoodItem item){
//
//
//        if(item.getType().equalsIgnoreCase("Starter")){
//            return 5;
//        }else if(item.getType().equalsIgnoreCase("Main")){
//            return 10;
//
//        }else if(item.getType().equalsIgnoreCase("Dessert")){
//            return 5;
//
//        }else{
//            return 1;
//        }
//
//    }
        //Is below supposed to take an order as a parameter(name needs changing then)or the order data field(remove parameter then)?

    /**
     * Sets ordered orders to ready
     * @param o order
     * @author Thomas
     */
    public void cooking(Order o){
        if(o.isReady("ORDERED")){// chef receives order and cooks it
            System.out.println("Food is ready");
            o.setStatus("READY");
        }
    }

}

