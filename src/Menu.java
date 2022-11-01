//Bayan: A class to represent a menu for a restaurant chain

import java.util.ArrayList;

public class Menu {
    private final ArrayList<FoodItem> menu = new ArrayList<>(); //Bayan: An ArrayList of FoodItems to store the menu's data
    private static final String[] foodNames = {"Garlic bread", "Tomato soup", "Bruschetta",
                                         "Carbonara", "Margherita pizza", "Lasagne", "Mushroom risotto",
                                         "Gelato", "Tiramisu", "Cannoli",
                                         "Water", "Milk", "Cappucino"}; //Bayan: Names of each FoodItem in this menu
    private static final double[] foodPrices = {4.5, 4, 5.5, 10.5, 11.5, 12, 10, 7, 9, 7.5, 1, 1, 2.5}; //Bayan: Prices of each FoodItem in this menu

    /**
     * No-arg constructor that initialises the menu and populates the menu ArrayList
     * @author Bayan
     */
    public Menu() { //todo: look into using this constructor to make different menus
        int i = 0;
        while (i < 3) {
            menu.add(new FoodItem(i, foodNames[i], foodPrices[i], "starter"));
            i++;
        }

        while (i < 7) {
            menu.add(new FoodItem(i, foodNames[i], foodPrices[i], "main"));
            i++;
        }

        while (i < 10) {
            menu.add(new FoodItem(i, foodNames[i], foodPrices[i], "dessert"));
            i++;
        }

        while (i < 13) {
            menu.add(new FoodItem(i, foodNames[i], foodPrices[i], "drink"));
            i++;
        }
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
     * Returns a string representation of the Menu object
     * @return Menu object as a string
     * @author Bayan
     */
    @Override
    public String toString() {
        return String.format("---YUM MENU---%nSTARTERS:%n [1]  %-20s%5.2f%n [2]  %-20s%5.2f%n [3]  %-20s%5.2f%n" +
                        "%nMAINS:%n [4]  %-20s%5.2f%n [5]  %-20s%5.2f%n [6]  %-20s%5.2f%n [7]  %-20s%5.2f%n" +
                        "%nDESSERTS:%n [8]  %-20s%5.2f%n [9]  %-20s%5.2f%n [10] %-20s%5.2f%n" +
                        "%nDRINKS:%n [11] %-20s%5.2f%n [12] %-20s%5.2f%n [13] %-20s%5.2f%n",
                menu.get(0).getName(), menu.get(0).getPrice(),
                menu.get(1).getName(), menu.get(1).getPrice(),
                menu.get(2).getName(), menu.get(2).getPrice(),
                menu.get(3).getName(), menu.get(3).getPrice(),
                menu.get(4).getName(), menu.get(4).getPrice(),
                menu.get(5).getName(), menu.get(5).getPrice(),
                menu.get(6).getName(), menu.get(6).getPrice(),
                menu.get(7).getName(), menu.get(7).getPrice(),
                menu.get(8).getName(), menu.get(8).getPrice(),
                menu.get(9).getName(), menu.get(9).getPrice(),
                menu.get(10).getName(), menu.get(10).getPrice(),
                menu.get(11).getName(), menu.get(11).getPrice(),
                menu.get(12).getName(), menu.get(12).getPrice());
    }
}
