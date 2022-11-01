//Bayan: A class to represent a menu for a restaurant chain

import java.util.HashMap;

public class Menu {
    private HashMap<Integer, FoodItem> menu = new HashMap<>();

    /**
     * Adds a food item with the specified id into the menu
     * @param id the id of the food item
     * @param item the food item
     * @author Bayan
     */
    public void addFoodItem(int id, FoodItem item) {
        menu.put(id, item);
    }

    /**
     * Returns the FoodItem with the specified id
     * @param id id of the food item
     * @return FoodItem
     * @author Bayan
     */
    public FoodItem getFoodItemAtIndex(int id) {
        return menu.get(id);
    }

    /**
     * Returns a string representation of the menu
     * @return Menu as a string
     * @author Bayan
     */
    public String toString() {
    }
}
