//Euan: everytime a user creates a new booking, it will make an object here

import java.time.LocalDate;

public class Booking implements ReadWrite{

    private final String id;
    private final Customer customer; //Customer that makes the booking
    private final int numberOfPeople; //number of people at the table
    private final int time; //time of booking. We will need to run a checker for this
    private final Table table ;
    private final Restaurant rest; //booking is specific to a certain restaurant
    private final LocalDate date;
    private boolean completed;

    /**
     * Create a Booking
     * @param customer pass a customer
     * @param numberOfPeople number of people for the booking
     * @param time time in hours
     * @author Euan
     */
    public Booking(Customer customer, int numberOfPeople, int time, Restaurant rest, LocalDate date, Table table) {
        this.rest = rest;
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.id = rest.getBookingId();
        this.date=date;
        this.time = time; //the time of the booking
        this.table = table;
        completed = false;
    }

    /**
     * Gets booking id
     * @return id as String
     * @author Euan
     */
    public String getId() {
        return id;
    }

    /**
     * Gets customer who made the booking
     * @return customer as Customer
     * @author Euan
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Allows booking details to be printed to csv
     * @return comma separated String for use in csv
     * @author Ronan
     */
    public String toCsv(){
        return id+","+numberOfPeople+","+date.toString()+","+time+","+table.getTableNumber()+","+customer.getId();
    }

    /**
     * gets the restaurant the booking is in
     * @return the restaurant as a Restaurant object
     * @author Euan
     */
    public Restaurant getRest() {
        return rest;
    }

    /**
     * Gets the date of the booking
     * @return the date of the booking as LocalDate
     * @author Bayan
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns if a booking has been completed or not
     * @return completed as boolean
     * @author Bayan
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the value of completed
     * @param completed the value to be set to completed
     * @author Bayan
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * returns a string representation of a booking
     * @return booking as String
     * @author Euan, Bayan
     */
    @Override
    public String toString() {
        return String.format("""
                Booking ID: %s
                Customer name: %s
                Number of people: %d
                Date & time: %s %02d:00
                Table Number: %d
                """, id, customer.getName(), numberOfPeople, date, time, table.getTableNumber());
    }
}
