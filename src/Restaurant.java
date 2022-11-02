//Euan: this can act like the main (overview?) class

import java.util.ArrayList;
import staff.*;

public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private Chef chef = new Chef("King");
    private Waiter waiter = new Waiter("Bob") ;
    
    public Restaurant(int amountOfTables) {
        int j = 1;
        int floor = amountOfTables/3;
        for (int i = 0; i < floor; i++) {
            Table t1 = new Table(j,2);
            j++;
            Table t2 = new Table(j,4);
            j++;
            Table t3 = new Table(j,5);
            j++;
            tables.add(t1);
            tables.add(t2);
            tables.add(t3);
        }
        //If numberOfTables is divisible by three, then above gives us exactly how many tables we need
        //Below loop will add either one or two tables if needed.
        while(amountOfTables!=tables.size()){
            Table t = new Table(j,2);
            j++;
            tables.add(t);
        }
    }

    /**
     * Gets table at time and has numOfPeople
     *
     * @param hour hour of booking
     * @param numOfPeople number of people at the table
     * @return table
     *
     * @author Euan
     */
    public Table getTable(int hour, int numOfPeople) {
        for (Table table : getTableList()) {
            if(!table.getReservedAtTime(hour) && table.getNumberOfSeats() == numOfPeople) {
                return table;
            }
        }
        //temporary response if no available tables:
        System.out.println("No available tables at that time");
        return null;
    }

    //Getters and Setters

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public ArrayList<Table> getTableList() {return tables;} //Euan: changed name to getTableList to not confuse with getTable
}