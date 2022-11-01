import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 */

public class RestaurantChain {
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
    private ArrayList<FoodItem> menu = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();// ArrayList containing all restaurants in chain

    /**
     * Full-arg constructor.
     * @param name name of chain
     * @param amountOfRestaurants amount of restaurants in the chain
     */
    public RestaurantChain(String name, int amountOfRestaurants) {
        this.name = name;
        for (int i = 0; i < amountOfRestaurants; i++) {
            Restaurant restaurant = new Restaurant{15};//Ronan: 15 tables is arbitrary and can be changed
            restaurants.add(restaurant);
        }

    }
}
