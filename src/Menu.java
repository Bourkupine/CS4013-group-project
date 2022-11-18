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
    public void addFood(FoodItem f) {
        boolean exists = false; //a boolean for
        for (FoodItem food : menu) { //looping through the menu to see if it exists
            if (food.equals(f)) {//if it is there you can tadd it again
                exists = true;
                break;//get out and dont add it
            }
        }
        if (!exists) { //if it doesnt exist add it
            menu.add(f);
            orderByType();//this is ordering the menu by its type, starter,main,dessert,drinks 
        }
    }

    //TODO boolean value is never used for removeFood()
    /**
     * Removes a specified FoodItem from the menu
     * @param f FoodItem to be removed
     * @author Bayan
     */
    public boolean removeFood(FoodItem f) {
        return menu.remove(f); //removing an item from the menu
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
    public void orderByType() {
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
        fm.format("%-4s%-20s%5s%10s\n", "ID", "Name", "Price", "Type");

        for (int i = 1; i < menu.size() + 1; i++) {
            fm.format("[%d] %s\n", i, menu.get(i - 1).toString());
        }

        return menuString.toString();
    }
}
