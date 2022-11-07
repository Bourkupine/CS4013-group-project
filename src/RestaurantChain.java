import java.util.ArrayList;

/**
 * A class to represent a chain of restaurants.
 */

//TODO add method to write details of all restaurants to csv(will likely need a method in restaurant too)

public class RestaurantChain {
    private ArrayList<Customer> customers = new ArrayList<>(); //An arraylist of all customers across every restaurant.
    private String name; //The name of the restaurant chain.
    private int amountOfRestaurants; //The amount of restaurants.
    private ArrayList<FoodItem> menu = new ArrayList<>();//TODO create menu
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
        }

    }

    /**
     * @author Ronan
     * @return menu of restaurant
     */
    public Menu getMenu(){
        Menu menu1 = new Menu(menu);
        return menu1;
    }
}
