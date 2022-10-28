import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 *
 * @author Bayan
 */

public class RestaurantChain {
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
    private ArrayList<FoodItem> menu = new ArrayList<>();

    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     */
    public RestaurantChain(String name, int amountOfRestaurants) {
        this.name = name;

    }
}
