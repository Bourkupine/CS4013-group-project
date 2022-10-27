/**
 * A class for a table in a restaurant.
 *
 * @author Bayan
 */
public class Table {
    private final int tableNumber; //Unique ID number for the table
    private final int numberOfSeats; //Number of seats at the table
    private boolean reserved; //Indicates if the table is reserved or not

    /**
     * Full-arg constructor.
     * @param tableNumber id number for the table
     * @param numberOfSeats number of seats at this table
     */
    public Table(int tableNumber, int numberOfSeats) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        reserved = false; //Tables are not reserved by default
    }

    /**
     * Sets the table to be reserved or not reserved.
     * @param reserved reserved value for the table
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     * Gets reservation status for the table.
     * @return reserved as boolean
     */
    public boolean getReserved() {
        return reserved;
    }

    /**
     * Gets table number for the table.
     * @return table number as int
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Gets number of seats at the table.
     * @return number of seats as int
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
