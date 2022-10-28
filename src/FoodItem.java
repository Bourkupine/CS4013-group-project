/**
 * A class to represent a food item
 *
 * @author Bayan
 */

public class FoodItem {
    private int id;
    private String name;
    private double price;
    private String type;

    public FoodItem(int id, String name, double price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
