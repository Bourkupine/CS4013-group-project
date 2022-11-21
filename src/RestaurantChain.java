import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 */

public class RestaurantChain implements ReadWrite{
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private final String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
     //todo: this needs to be moved to restaurant since each restaurant has its own menu
    private ArrayList<Restaurant> restaurants = new ArrayList<>();// ArrayList containing all restaurants in chain
    private File rest; //Contains details of all restaurants
    private File customer;
    



    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     * @author Bayan, Ronan
     */
    public RestaurantChain(String name, int amountOfRestaurants, File[] f, LocalDate d){
        this.name = name;
        this.rest=f[0];
        this.customer=f[4];
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant(15,this,i,d,f[1],f[2],f[3]);//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
            managers();

        }
        updateCustomers();
        writeDetails();
    }


    /**
     * Writes details of all restaurants in chain to their csv file
     */
    public void writeDetails(){

        clearFile(rest);
        writeFile(rest,"RestId,TableId,NumSeats");
        for(Restaurant r:restaurants){
            updateFile(rest,r.toCsv());//TODO fix formatting
        }



    }

    /**
     * Adds a customer to the arrayList of customers
     * @param c Customer to be added
     */
    public void addCustomer(Customer c){
        customers.add(c);
        updateFile(customer,c.toCsv());
    }

    /**
     * Populates customer arraylist using customer csv
     */
    public void updateCustomers(){
        ArrayList<String> temp = readFile(customer);
        if(temp.size()>1){
            for(int i=1;i<temp.size();i++){
                String[] split = temp.get(i).split(",");
                customers.add(new Customer(split[0],split[1],Integer.parseInt(split[2])));
            }
        }
    }

    /**
     * Writes customer csv using arraylist
     * Used whenever a customer pays, thus incrementing their loyalty
     */
    public void updateCustomerCsv(){
        ArrayList<String> s = new ArrayList<>();
        s.add("Name,PhoneNum,Loyalty");
        for (Customer c: customers){
            s.add(c.toCsv());
        }
        writeFile(customer,s);
    }

    /**
     * Populates each restaurant with a manager
     * @author Ronan
     */
    public void managers (){
        for(Restaurant r: restaurants){
            Manager m = new Manager("Chris","123",r);
        }
    }

    /**
     * gets customers
     * @return customers as array list
     * @author Ronan
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }

    public Customer findCustomer(Customer c) {
        return findCustomer(c.getName());
    }

    public Customer findCustomer(String name) {
        for (Customer cust : customers) {
            if (cust.getName().equals(name)) {
                return cust;
            }
        }
        return new Customer(name);
    }
}
