//Bayan: A class to represent a menu for a restaurant chain

import java.util.ArrayList;

public class Menu {
    private ArrayList<FoodItem> menu; //Bayan: An ArrayList of FoodItems to store the menu's data


    public Menu(ArrayList<FoodItem> menu) {
        this.menu = menu;
        orderByType(menu);
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
        menu.add(f);
        orderByType(menu);
    }

    /**
     * Removes a specified FoodItem from the menu
     * @param f FoodItem to be removed
     * @author Bayan
     */
    public void removeFood(FoodItem f) {
        menu.remove(f);
        orderByType(menu);
    }

    /**
     * Orders the menu by type, i.e. starters, then mains, then desserts, then drinks
     * @param menu the ArrayList of FoodItems representing the menu
     * @author Bayan
     */
    public void orderByType(ArrayList<FoodItem> menu) {
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
        menuString.append(String.format("%-4s%-20s%5s%10s%n", "ID", "Name", "Price", "Type"));

        for (int i = 1; i < menu.size() + 1; i++) {
            menuString.append("[" + i + "] " + menu.get(i-1).toString()).append("\n");
        }

        return menuString.toString();
    }
}
