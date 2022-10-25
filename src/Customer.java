/**
 * Class to represent a customer of the restaurant.
 *
 * @author Ronan
 */
public class Customer {
    private String name;
    private int id;
    private String phoneNumber;
    private int loyalty=1;

    /**
     * Non phone number constructor (ideal for walk-ins)
     * @param name name of customer
     * @param id id number for customer
     */
    public Customer(String name, int id){
        this.name=name;
        this.id=id;
    }

    /**
     * Full arg constructor
     * @param name name of customer
     * @param id id number for customer
     * @param phoneNumber phone number of customer
     */
    public Customer(String name, int id, String phoneNumber){
        this(name,id);
        this.phoneNumber=phoneNumber;
    }

    //Below are getters and setters as neccessary

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    //Basic toString. Can modify if needed.
    @Override
    public String toString(){
        return "Customer "+name+" (id "+id+") has made "+loyalty+" visits.";
    }
}
