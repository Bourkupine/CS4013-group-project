//Bayan: a class to represent a manager at a restaurant

import java.time.LocalDate;
import java.util.Formatter;


public class Manager extends Staff implements ReadWrite{


    /**
     * Full-arg constructor
     * @param name name of staff
     * @param password password of staff
     * @param rest restaurant of staff
     * @author Thomas
     */
    public Manager(String name,String password, Restaurant rest){
        super(name,password,rest);//managers name password and restaurant
        employStaff(this);
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

    /**
     * This method will generate a graph based on the restaurants daily earnings in a given time period
     * @param start start date
     * @param end end date
     * @return returns the graph as a String
     * @author Euan
     */
    public String generateGraph(LocalDate start, LocalDate end) {

        //styled as :
        // [2022-02-11]: €900.00   |=========
        // [2022-02-12]: €1,200.00 |============

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        String bars = "";

        assert start.isBefore(end) : "Start date is after end date"; //not sure if this is required, but it should make sure end is not before start
        while (start.isBefore(end.plusDays(1))) {

            bars = "=".repeat((int) (getRest().getDailyAmounts().get(start) - (getRest().getDailyAmounts().get(start)%100)) / 100);

            fm.format("[%s]: €%,-8.2f |%s\n",start.toString(), getRest().getDailyAmounts().get(start), bars);
            start = start.plusDays(1);
        }

        return sb.toString();
    }
}
