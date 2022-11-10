/**
 * Class to represent a customer of the restaurant.
 *
 * @author Ronan
 */
public class Customer {
    private String name;//Customers name
    private int id;//Unique id number for customer
    private static int totalId = 0;
    private String phoneNumber;//Phone number of customer
    private int loyalty;//number of times customer has been to restaurant

    /**
     * Non phone number constructor (ideal for walk-ins)
     * @param name name of customer
     * @author Ronan
     */
    public Customer(String name){
        this.name=name;
        id = totalId;
        totalId++;
        loyalty = 0;
    }

    /**
     * Full arg constructor
     * @param name name of customer
     * @param phoneNumber phone number of customer
     * @author Ronan
     */
    public Customer(String name, String phoneNumber){
        this(name);
        if(!phoneNumber.equals("0")){
            this.phoneNumber=phoneNumber;
        }

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
     * @author Ronan
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns number of times customer has been to restaurant
     * @return number of visits as int
     * @author Ronan
     */
    public int getLoyalty() { //Euan: changed this to not be static
        return loyalty;
    }

    /**
     * Call this method to increment the loyalty
     * @author Euan
     */
    public void incrementLoyalty() {
        loyalty++; //this was needed to change to customer.loyalty as loyalty is now static because i need to access it in order to increment the loyalty and to use it in the if()
    }

    //Basic toString. Can modify if needed.

    /**
     * Returns Customer as a String
     * @return a String representation of the customer
     * @author Ronan
     */
    @Override
    public String toString(){
        return "Customer "+name+" (id "+id+") has made "+loyalty+" visits.";
    }
}
