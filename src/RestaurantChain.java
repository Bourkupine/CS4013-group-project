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
    File rest; //Contains details of all restaurants
    



    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     * @author Bayan, Ronan
     */
    public RestaurantChain(String name, int amountOfRestaurants, File rest,File booking, File money,File menuCsv, LocalDate d){
        this.name = name;
        this.rest=rest;
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant(15,this,i,d,booking,money,menuCsv);//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
            managers();

        }
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
