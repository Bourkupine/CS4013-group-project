import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @param f files that data is read from
     * @param d the current date
     * @author Bayan, Ronan
     */
    public RestaurantChain(String name, int amountOfRestaurants, File[] f, LocalDate d){
        this.name = name;
        this.rest=f[0];
        this.customer=f[4];
        updateCustomers();
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant(15,this,i,d,f[1],f[2],f[3],f[5]);//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
            staff(restaurant);

        }

        writeDetails();
    }


    /**
     * Writes details of all restaurants in chain to their csv file
     * @author Ronan
     */
    public void writeDetails(){

        clearFile(rest);
        writeFile(rest,"RestId,TableId,NumSeats");
        for(Restaurant r:restaurants){
            updateFile(rest,r.toCsv());
        }
    }

    /**
     * Adds a customer to the arrayList of customers
     * @param c Customer to be added
     * @author Ronan
     */
    public void addCustomer(Customer c){
        customers.add(c);
        updateFile(customer,c.toCsv());
    }

    /**
     * Populates customer arraylist using customer csv
     * @author Ronan
     */

    public void updateCustomers(){//TODO some customers not entering array
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
     * @author Ronan
     */
    public void updateCustomerCsv(){
        ArrayList<String> s = new ArrayList<>();
        s.add("Name,PhoneNum,Loyalty,IdNum");
        for (Customer c: customers){
            s.add(c.toCsv());
        }
        writeFile(customer,s);
    }

    /**
     * Populates each restaurant with staff from csv
     * @param r the restaurant being populated
     * @author Ronan
     */
    public void staff (Restaurant r){
        ArrayList<String> temp = readFile(r.getStaffCsv());
        if(temp.size()>1){//ie: if any staff exist in restaurants
            for(int i=1;i<temp.size();i++){
                String[] split = temp.get(i).split(",");
                if(Integer.parseInt(split[0])==r.getIdNum()){
                    switch(split[1]){
                        case "Waiter":
                            r.getStaff().add(new Waiter(split[2],split[3],getRestaurants().get(r.getIdNum())));
                            break;
                        case "Chef":
                            r.getStaff().add(new Chef(split[2],split[3],getRestaurants().get(r.getIdNum())));
                            break;
                        case "Manager":
                            r.getStaff().add(new Manager(split[2],split[3],getRestaurants().get(r.getIdNum())));
                            break;
                    }
                }

            }
        }
        if(r.getStaff().size()==0){// if no staff were assigned to a given restaurant, give them a default manager and write it to csv
            Manager m = new Manager("Chris","123",getRestaurants().get(r.getIdNum()));
            r.getStaff().add(m);
            updateFile(r.getStaffCsv(),m.toCsv());
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

    /**
     * Returns an ArrayList of restaurants for this chain
     * @return restaurants as ArrayList
     * @author Ronan
     */
    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }

    /**
     * Returns all customers with the specified name to be chosen from. If no customer is found, one is created.
     * @author Bayan
     * @param name Name of the Customer
     * @param phoneNumber Phone number of the Customer
     * @return customer as Customer
     */
    public Customer findCustomer(String name, String phoneNumber) {
        ArrayList<Customer> arr = new ArrayList<>();
        for (Customer cust : customers) {
            if (cust.getName().equalsIgnoreCase(name)) {
                arr.add(cust);
            }
        }
        if (arr.size() == 0) {
            Customer c = new Customer(name, phoneNumber);
            customers.add(c);
            updateCustomerCsv();
            return c;
        } else if (arr.size() == 1) {
            return arr.get(0);
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Input the customer");
            for (int i = 0; i < arr.size(); i++) {
                System.out.println("[" + (i+1) + "] " + arr.get(i));
            }
            int selectedCustomer = in.nextInt();
            return arr.get(selectedCustomer-1);
        }
    }
}
