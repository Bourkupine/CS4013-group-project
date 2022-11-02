//Bayan: A class to represent a table at a restaurant

public class Table {
    private final int tableNumber; //Bayan: Unique ID number for the table
    private final int numberOfSeats; //Bayan: Number of seats at the table
    private boolean[] reserved = new boolean[13]; //Bayan: Indicates if the table is reserved at a specific time or not

    /**
     * Full-arg constructor.
     * @param tableNumber id number for the table
     * @param numberOfSeats number of seats at this table
     * @author Bayan
     */
    public Table(int tableNumber, int numberOfSeats) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        for (int i = 0; i < 13; i++) {
            reserved[i] = false; //Bayan: Tables are not reserved by default
        }
    }

    /**
     * Sets the table to be reserved or not reserved.
     * @param hour the time in hours in 24 hour clock
     * @param reserved reserved value for the table
     * @author Bayan
     */
    public void setReservedAtTime(int hour, boolean reserved) {
        this.reserved[hour - 9] = reserved;
    }

    /**
     * Gets reservation status for the table.
     * @param hour the time in hours in 24 hour clock
     * @return reserved as boolean
     * @author Bayan
     */
    public boolean getReservedAtTime(int hour) {
        return reserved[hour - 9];
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
}
