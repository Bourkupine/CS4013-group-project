public class Chef extends Staff {

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

