import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 */

public class RestaurantChain {
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
    private Menu menu;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();// ArrayList containing all restaurants in chain

    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     */
    public RestaurantChain(String name, int amountOfRestaurants) {
        this.name = name;
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant(15,this);//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
            generateMenu();
        }

    }

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
        menu = new Menu(f);
    }

    public Menu getMenu() {
        return menu;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
