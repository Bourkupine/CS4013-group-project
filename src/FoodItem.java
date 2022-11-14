//Bayan: A class to represent a food item at a restaurant

import java.util.Objects;

public class FoodItem {
    private String name;
    private double price;
    private String type;

    /**
     * Full-arg constructor
     * @param name the name of the item
     * @param price the item's price
     * @param type the type of the item (starter, main, etc.)
     * @author Bayan
     */
    public FoodItem(String name, double price, String type) {
        this.name = name; //Bayan: the name of the food item
        this.price = price; //Bayan: the price of the food item
        this.type = type; //Bayan: the type of the food item (starter, main, dessert, or drink)
    }

    /**
     * Gets the name of the food item
     * @return name as String
     * @author Bayan
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the food item
     * @return price as double
     * @author Bayan
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the type of the food item
     * @return type as String
     * @author Bayan
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of a FoodItem
     * @return FoodItem as String
     * @author Bayan
     */
    @Override
    public String toString() {
        return String.format("%-20s%5.2f%10s", name, price, type);
    }

    /**
     * Checks if a FoodItem is equal to another and returns true if so
     * @param o Object to be compared against
     * @return boolean
     * @author Bayan
     */
    @Override
    public boolean equals(Object o) {//todo: this only checks if the names are equal since you don't want to add the same name twice - check with team
        if (this == o) { 
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoodItem foodItem = (FoodItem) o;
        return Objects.equals(name, foodItem.name);
    }
}