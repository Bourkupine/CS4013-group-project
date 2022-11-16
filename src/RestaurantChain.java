import java.io.File;
import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 */

public class RestaurantChain implements ReadWrite{
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
    private Menu menu;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();// ArrayList containing all restaurants in chain
    File rest; //Contains details of all restaurants
    

    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     * @author Bayan, Ronan
     */
    public RestaurantChain(String name, int amountOfRestaurants) {
        this.name = name;
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant(15,this,i);//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
            generateMenu();

        }

    }

    public RestaurantChain(String name, int amountOfRestaurants, File rest){
        this(name,amountOfRestaurants);
        this.rest=rest;
        writeDetails();
    }

    /**
     * generates the menu
     * @author Bayan, Euan
     */
    public void generateMenu() {
        ArrayList<FoodItem> f = new ArrayList<>();
        f.add(new FoodItem("Garlic bread", 4, "starter"));
        f.add(new FoodItem("Tomato soup", 4, "starter"));
        f.add(new FoodItem("Hamburger", 4, "main"));
        f.add(new FoodItem("Water", 4, "drink"));
        f.add(new FoodItem("Lasagne", 4, "main"));
        f.add(new FoodItem("Ice-cream", 4, "dessert"));
        f.add(new FoodItem("Tea", 4, "drink"));
        f.add(new FoodItem("Creme-brulee", 4, "dessert"));
        f.add(new FoodItem("Pizza", 4, "main"));
        f.add(new FoodItem("Salad", 4, "starter"));
        sortMenu(f);
    }

    /**
     * sorts the menu by starters, then mains, then desserts, then drinks.
     * @param f the menu to be sorted
     * @author Euan
     */
    public void sortMenu(ArrayList<FoodItem> f) {

        ArrayList<FoodItem> starters = new ArrayList<>();
        ArrayList<FoodItem> mains = new ArrayList<>();
        ArrayList<FoodItem> desserts = new ArrayList<>();
        ArrayList<FoodItem> drinks = new ArrayList<>();

        //cycle through each item on menu, get its type and add it to its respective list
        f.forEach(foodItem -> {
            switch (foodItem.getType()) {
                case "starter" -> starters.add(foodItem);
                case "main" -> mains.add(foodItem);
                case "dessert" -> desserts.add(foodItem);
                default -> drinks.add(foodItem);
            }
        });

        //join all the lists to one
        starters.addAll(mains);
        starters.addAll(desserts);
        starters.addAll(drinks);

        //set menu equal to the final list
        menu = new Menu(starters);
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
     * gets menu
     * @return current menu as menu object
     * @author Bayan
     */
    public Menu getMenu() {
        return menu;
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
