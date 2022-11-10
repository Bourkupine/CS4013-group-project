//Bayan: A class to represent a menu for a restaurant chain

import java.util.ArrayList;

public class Menu {
    private ArrayList<FoodItem> menu; //Bayan: An ArrayList of FoodItems to store the menu's data


    public Menu(ArrayList<FoodItem> menu) {
        this.menu = menu;
        orderByType();
    }

    /**
     * Returns the FoodItem at the specified index
     * @param index index of the food item in the ArrayList
     * @return FoodItem as FoodItem
     * @author Bayan
     */
    public FoodItem getFoodItemAtIndex(int index) {
        return menu.get(index);
    }

    /**
     * Adds a specified FoodItem to the menu
     * @param f FoodItem to be added
     * @author Bayan
     */
    public void addFood(FoodItem f) {
        boolean exists = false;
        for (FoodItem food : menu) {
            if (food.equals(f)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            menu.add(f);
            orderByType();
        }
    }

    /**
     * Removes a specified FoodItem from the menu
     * @param f FoodItem to be removed
     * @author Bayan
     */
    public boolean removeFood(FoodItem f) {
        return menu.remove(f);
    }

    /**
     * Removes a FoodItem with a specific name from the menu
     * @param name name of the FoodItem
     */
    public boolean removeFood(String name) {
        for (FoodItem f : menu) {
            if (f.getName().equalsIgnoreCase(name)) {
                return menu.remove(f);
            }
        }
        return false;
    }

    public void clearMenu() {
        menu.clear();
    }

    /**
     * Orders the menu by type, i.e. starters, then mains, then desserts, then drinks
     * @author Bayan
     */
    public void orderByType() {
        ArrayList<FoodItem> tempMenu = new ArrayList<>();
        for (FoodItem f : menu) {
            if(f.getType().equals("starter")) {
                tempMenu.add(f);
            }
        }
        for (FoodItem f : menu) {
            if(f.getType().equals("main")) {
                tempMenu.add(f);
            }
        }
        for (FoodItem f : menu) {
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
     * Returns a string representation of the Menu object
     * @return Menu object as a string
     * @author Bayan
     */
    @Override
    public String toString() {
        StringBuilder menuString = new StringBuilder("~~~~OUR MENU~~~~\n");
        menuString.append(String.format("%-4s%-20s%5s%10s\n", "ID", "Name", "Price", "Type"));

        for (int i = 1; i < menu.size() + 1; i++) {
            menuString.append("[" + i + "] " + menu.get(i-1).toString()).append("\n");
        }

        return menuString.toString();
    }
}
