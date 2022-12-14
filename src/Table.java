//Bayan: A class to represent a table at a restaurant

import java.util.ArrayList;

/**
 * A class to represent a dining table in a restaurant
 */
public class Table {
    private final int tableNumber; //Bayan: Unique ID number for the table
    private final int numberOfSeats; //Bayan: Number of seats at the table
    private ArrayList<boolean[]> reserved = new ArrayList<>(); //Bayan: Indicates if the table is reserved at a specific time or not

    /**
     * Full-arg constructor.
     * @param tableNumber id number for the table
     * @param numberOfSeats number of seats at this table
     * @author Bayan, Euan
     */
    public Table(int tableNumber, int numberOfSeats) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        for (int i = 0; i < 7; i++) {
            reserved.add(new boolean[13]);
        }
        for (boolean[] b : reserved) {
            for (int j = 0; j < 13; j++) {
                b[j] = false;
            }
        }
    }

    /**
     * Sets the table to be reserved or not reserved at a given day.
     * @param hour the time in hours (24-hour format)
     * @param day days in advance of reservation
     * @param reserved reserved value of table.
     * @author Euan
     */
    public void setReservedAtTime(int hour, int day, boolean reserved) { //overloading
        this.reserved.get(day)[hour - 9] = reserved;
    }

    /**
     * Gets reservation status for the table at a given day.
     * @param hour the time in hours (24-hour format)
     * @param day day of reservation
     * @return reservation status (boolean)
     * @author Bayan, Euan
     */
    public boolean getReservedAtTime(int hour, int day) { //overloading
        return reserved.get(day)[hour-9];
    }


    /**
     * Gets table number for the table.
     * @return table number as int
     * @author Bayan
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Gets number of seats at the table.
     * @return number of seats as int
     * @author Bayan
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Returns a string representation of a Table
     * @return Table as String
     * @author Bayan, Ronan
     */
    public String toString(){
        return "Table "+ tableNumber +" has "+ numberOfSeats +" seats";
    }

}
