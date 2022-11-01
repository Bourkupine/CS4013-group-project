//Bayan: A class to represent a food item at a restaurant

public class FoodItem {
    private int id;
    private String name;
    private double price;
    private String type;

    /**
     * Full-arg constructor
     * @param id the id of the item
     * @param name the name of the item
     * @param price the item's price
     * @param type the type of the item (starter, main, etc.)
     * @author Bayan
     */
    public FoodItem(int id, String name, double price, String type) {
        this.id = id; //Bayan: the id number used to identify the food item
        this.name = name; //Bayan: the name of the food item
        this.price = price; //Bayan: the price of the food item
        this.type = type; //Bayan: the type of the food item (starter, main, dessert, or drink)
    }

    /**
     * Gets the id of the food item
     * @return id as int
     * @author Bayan
     */
    public int getId() {
        return id;
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
}
