/**
 * Class to represent a customer of the restaurant.
 *
 * @author Ronan
 */
public class Customer {
    private String name;//Customers name
    private int id;//Unique id number for customer
    private String phoneNumber;//Phone number of customer
    private int loyalty=1;//number of times customer has been to restaurant

    /**
     * Non phone number constructor (ideal for walk-ins)
     * @param name name of customer
     * @param id id number for customer
     * @author Ronan
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
     * @author Ronan
     */
    public Customer(String name, int id, String phoneNumber){
        this(name,id);
        this.phoneNumber=phoneNumber;
    }

    //Below are getters and setters as neccessary

    /**
     * Gets name of customer
     * @return name as String
     * @author Ronan
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id number for customer
     * @return id number as int
     * @author Ronan
     */
    public int getId() {
        return id;
    }

    /**
     * Gets phone number of customer
     * @return phone number as String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns number of times customer has been to restaurant
     * @return number of visits as int
     */
    public int getLoyalty() {
        return loyalty;
    }

    /**
     * Sets number of times customer has been to restaurant
     * @param loyalty number of visits
     */
    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    //Basic toString. Can modify if needed.

    /**
     *
     * @return a String representation of the customer
     */
    @Override
    public String toString(){
        return "Customer "+name+" (id "+id+") has made "+loyalty+" visits.";
    }
}
