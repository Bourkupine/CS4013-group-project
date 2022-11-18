//Euan: everytime a user creates a new booking, it will make an object here

import java.time.LocalDate;

public class Booking implements ReadWrite{

    private final String id;
    private final Customer customer; //Customer that makes the booking
    private int numberOfPeople; //number of people at the table
    private int time; //time of booking. We will need to run a checker for this
    private Table table ;
    private final Restaurant rest; //booking is specific to a certain restaurant
    private LocalDate date;




    /**
     * Create Booking order
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
    }


    //Getters & Setters:


    /**
     * Sets a table to the booking
     * @param table the booking's Table
     * @author Euan
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Change time of booking
     * @param time time you wish to change your booking to
     * @author Euan
     */
    public void setTime(int time){
        this.time = time;
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
     * Gets number of people in the booking
     * @return numberOfPeople as int
     * @author Euan
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Gets time of the booking in hours
     * @return time as int
     * @author Euan
     */
    public int getTime() {
        return time;
    }

    /**
     * Allows booking details to be printed to csv
     * @return comma separated String for use in csv
     * @author Ronan
     */
    public String toCsv(){
        return id+","+numberOfPeople+","+date.toString()+","+time+","+table.getTableNumber()+","+customer.getId();
    }

    public Restaurant getRest() {
        return rest;
    }
}
