//Bayan: A class to represent a menu for a restaurant chain

import java.util.ArrayList;
import java.util.Formatter;

public class Menu {
    private ArrayList<FoodItem> menu; //Bayan: An ArrayList of FoodItems to store the menu's data


    /**
     * Full-arg constructor
     * @param menu ArrayList of FoodItems
     * @author Bayan
     */
    public Menu(ArrayList<FoodItem> menu) {
        this.menu = menu;// this is the menu
        orderByType();//this will 
    }

    /**
     * Returns the FoodItem at the specified index
     * @param index index of the food item in the ArrayList
     * @return FoodItem as FoodItem
     * @author Bayan
     */
    public FoodItem getFoodItemAtIndex(int index) {
        return menu.get(index);//getting the item at the index
    }

    /**
     * Adds a specified FoodItem to the menu
     * @param f FoodItem to be added
     * @author Bayan
     */
    public boolean addFood(FoodItem f) {
        for (FoodItem food : menu) {
            if (food.equals(f)) {
                return false;
            }
        }
        menu.add(f);
        orderByType();
        return true;
    }

    /**
     * Removes a FoodItem with a specific name from the menu
     * @param name name of the FoodItem
     * @author Bayan
     */
    public boolean removeFood(String name) { 
        for (FoodItem f : menu) {
            if (f.getName().equalsIgnoreCase(name)) {
                return menu.remove(f);
            }
        }
        return false;
    }

    /**
     * Removes all FoodItems from the menu
     */
    public void clearMenu() {
        menu.clear();//clears the menu to remake it
    }

    /**
     * Orders the menu by type, i.e. starters, then mains, then desserts, then drinks
     * @author Bayan
     */
    private void orderByType() {
        ArrayList<FoodItem> tempMenu = new ArrayList<>();
        for (FoodItem f : menu) {
            if(f.getType().equals("starter")) { //if the type is starter add it, starter is first
                tempMenu.add(f);
            }
        }
        for (FoodItem f : menu) { //main is second
            if(f.getType().equals("main")) {
                tempMenu.add(f);
            }
        }
        for (FoodItem f : menu) { //
            if(f.getType().equals("dessert")) {
                tempMenu.add(f);
            }
        }
        for (FoodItem f : menu) {
            if(f.getType().equals("drink")) {
                tempMenu.add(f);
            }
        }
        menu = tempMenu;
    }

    /**
     * Returns the number of FoodItems in the menu
     * @return number of elements in the menu ArrayList as int
     * @author Bayan
     */
    public int getSize() {
        return menu.size();
    }

    /**
     * Returns a string representation of the Menu object
     * @return Menu object as a string
     * @author Bayan
     */
    @Override
    public String toString() {
        StringBuilder menuString = new StringBuilder("~~~~OUR MENU~~~~\n");
        Formatter fm = new Formatter(menuString);
        fm.format("%-5s%-20s%5s%10s\n", "ID", "Name", "Price", "Type");

        for (int i = 1; i < menu.size() + 1; i++) {
            fm.format("%-5s%3s\n", String.format("[%d]", i), menu.get(i - 1).toString());
        }

        return menuString.toString();
    }
}
