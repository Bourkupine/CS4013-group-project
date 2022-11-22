/**
 * Class to represent a customer of the restaurant.
 *
 * @author Ronan
 */
public class Customer implements ReadWrite{
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
        this.name=name;// this is the customers name
        this.id = totalId;
        totalId++;//incrementing the id after a customer has been created so the next customer will have a different id
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
        if(!phoneNumber.equals("0")){//this is for testing purposes
            this.phoneNumber=phoneNumber;
        }

    }

    /**
     * Constructor for customers who have already been to restaurant
     * @param name name of customer
     * @param phoneNumber phone number of customer
     * @param loyalty amount of visits to restaurant
     */
    public Customer(String name, String phoneNumber, int loyalty){
        this(name,phoneNumber);
        this.loyalty=loyalty;
    }

    //Below are getters and setters as necessary

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


    public String sendMessage(Booking booking){ //if we could implement this it would send a message to the customer 
        String message = "Your booking is on the ----------";
        return message;
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
        loyalty++; 
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

    /**
     * @author ronan
     * @return name phone number and loyalty of the customer
     */
    public String toCsv(){
        return name+","+phoneNumber+","+loyalty;
    }
}
