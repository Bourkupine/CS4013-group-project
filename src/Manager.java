//Bayan: a class to represent a manager at a restaurant

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


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
    }

    /**
     * Deleted the filed in the passed file array
     * @param files array of files to be deleted
     * @author Ronan
     */
    public void factoryReset(File[] files){
        for(File f1:files){
            deleteFile(f1);
        }
    }

    /**
     * Adds a Staff to the restaurant
     * @param staff Staff to be added
     * @author Ronan, Bayan
     */
    public void employStaff(Staff staff) {
        getRest().getStaff().add(staff); //calls the method from staff in said restaurant and adds a staff member
        updateFile(getRest().getStaffCsv(), staff.toCsv());
    }

    /**
     * Removes a Staff from the restaurant
     * @param name name of the staff to be fired
     * @author Ronan, Bayan
     */
    public boolean fireStaff(String name) {
        Restaurant r = getRest();
        for (Staff staff : r.getStaff()) {
            if (staff.getName().equalsIgnoreCase(name)) {
                r.getStaff().remove(staff);
                writeFile(getRest().getStaffCsv(),"RestId,Type,Name,Password");
                for(Staff s1:r.getStaff()){
                    updateFile(getRest().getStaffCsv(),s1.toCsv());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * A method for Managers to use to manage the menu using the CLI
     * @param in Scanner to read input
     * @author Bayan
     */
    public void manageMenu(Scanner in) {
        Restaurant r = getRest();
        boolean menuing = true;
        while (menuing) {
            System.out.println("""
                    [A] View menu
                    [B] Add item to menu
                    [C] Remove item from menu
                    [D] Clear menu
                    [E] Exit
                    """);

            String input = in.next();
            if (input.equalsIgnoreCase("A")) {
                System.out.println(r.getMenu());
            } else if (input.equalsIgnoreCase("B")) {
                System.out.println("Enter name of item");
                String name = in.next();
                System.out.println("Enter price of item");
                double foodPrice = in.nextDouble();
                System.out.println("Enter type of item (starter, main, dessert, drink)");
                String foodType = in.next();
                if (r.getMenu().addFood(new FoodItem(name, foodPrice, foodType))) {
                    System.out.println("Item added successfully");
                    updateFile(getRest().getMenuCsv(),getRest().getIdNum()+","+name+","+foodPrice+","+foodType);
                } else {
                    System.out.println("Item already exists on menu");
                }
            } else if (input.equalsIgnoreCase("C")) {
                System.out.println("Enter name of item");
                String item = in.next();
                if (r.getMenu().removeFood(item)) {
                    System.out.println("Item removed from the menu");
                    ArrayList<String> temp =readFile(getRest().getMenuCsv());
                    int index=0;
                    for(String s:temp){
                        String[] split = s.split(",");
                        if(split[0].equals(String.valueOf(getRest().getIdNum())) && split[1].equals(item)){
                            index=temp.indexOf(s);
                        }
                    }
                    temp.remove(index);
                    writeFile(getRest().getMenuCsv(),temp);
                } else {
                    System.out.println("Item not found");
                }
            } else if (input.equalsIgnoreCase("D")) {
                r.getMenu().clearMenu();
                writeFile(getRest().getMenuCsv(),"RestId,Food,Price,Type");
                System.out.println("Menu cleared");
            } else if (input.equalsIgnoreCase("E")) {
                menuing = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    /**
     * This method will generate a graph based on the restaurants daily earnings in a given time period
     * @param start start date
     * @param end end date
     * @return returns the graph as a String
     * @author Euan
     */
    public void generateGraph(LocalDate start, LocalDate end) {

        //styled as :
        // [2022-02-11]: €900.00   |=========
        // [2022-02-12]: €1,200.00 |============

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        double total = 0;
        LocalDate temp = start;

        String bars = "";

        if (start.isBefore(end)) {
            while (temp.isBefore(end.plusDays(1))) {

                bars = "=".repeat((int) (getRest().getDailyAmounts().get(temp) - (getRest().getDailyAmounts().get(temp) % 100)) / 100);
                total += getRest().getDailyAmounts().get(temp);
                fm.format("[%s]: €%,-8.2f |%s\n", temp, getRest().getDailyAmounts().get(temp), bars);
                start = start.plusDays(1);
            }

            fm.format("\nTotal earnings %s - %s: %f", start.toString(), end.toString(), total);
            System.out.println(sb.toString());
        } else {
            System.out.println("Start date is after end date");
        }
    }
}
