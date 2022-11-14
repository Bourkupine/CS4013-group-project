//Bayan: a class to represent a manager at a restaurant

public class Manager extends Staff {

    /**
     * Full-arg constructor
     * @param name name of staff
     * @param password password of staff
     * @param rest restaurant of staff
     * @author Thomas
     */
    public Manager(String name,String password, Restaurant rest){
        super(name,password,rest);//managers name password and restaurant
    }

    /**
     * Adds a FoodItem to the menu of the restaurant
     * @param food FoodItem to be added
     * @author Bayan
     */
    public void addToMenu(FoodItem food) {
        getRest().getMenu().addFood(food);//goes to the restaurant and gets menu in the restaurant then adds fooditem to said menu
    }

    /**
     * Removes a FoodItem from the menu of the restaurant
     * @param food FoodItem to be removed
     * @author Bayan
     */
    public void removeFromMenu(FoodItem food) {
        getRest().getMenu().removeFood(food);//goes to the restaurant and gets menu in the restaurant then takes the fooditem out of said menu
    }

    /**
     * Adds a Staff to the restaurant
     * @param staff Staff to be added
     * @author Ronan, Bayan
     */
    public void employStaff(Staff staff) {
        getRest().getStaff().add(staff); //calls the method from staff in said restaurant and adds a staff member
    }

    /**
     * Removes a Staff from the restaurant
     * @param staff Staff to be removed
     * @author Ronan, Bayan
     */
    public void fireStaff(Staff staff) {
        getRest().getStaff().remove(staff);//calls the method from staff in said restaurant and adds a staff member
    }
}
