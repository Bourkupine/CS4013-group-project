//Euan: everytime a user creates a new booking, it will make an object here

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class Booking implements ReadWrite{

    private static int totalId = 0;
    private int id;
    private static int idCounter = 0; //used to keep a universal counter of all the bookings so we can set id = idCounter + 1
    private Customer customer; //Customer that makes the booking
    private int numberOfPeople; //number of people at the table
    private int time; //time of booking. We will need to run a checker for this
    private Table table ;
    private Restaurant rest; //booking is specific to a certain restaurant TODO initialise this
    //Ronan- possible solution to above is to have booking take a restaurant in constructor


    /**
     * Create Booking order
     * @param customer pass a customer
     * @param numberOfPeople number of people at a table
     * @author Euan
     */
    public Booking(Customer customer, int numberOfPeople, Restaurant rest) { //walk-in
        this.id = totalId;
        this.rest = rest;
        totalId++;
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now().getHour();//Ronan: this returns computers local time. Do we want this?
        idCounter++;
        this.id = idCounter;
    }

    /**
     * Create Booking order
     * @param customer pass a customer
     * @param numberOfPeople number of people for the booking
     * @param time time in hours
     * @author Euan
     */
    public Booking(Customer customer, int numberOfPeople, int time, Restaurant rest) { //Booking todo: account for days
        this(customer, numberOfPeople, rest);
        this.time = time;
    }


    /**
     * Sets a table to the booking
     * @param table the booking's Table
     * @author Euan
     */
    public void setTable(Table table) {
        this.table = table;
    }


    /**
     * Delete/cancel a booking
     * @param booking the booking you want to cancel
     * @author Euan
     */

    /**
     * Change time of booking
     * @param time time you wish to change your booking to
     * @author Euan
     */
    public void laterTime(int time){
        this.time = time;
    }

    //Setters & Getters

    /**
     * Gets booking id
     * @return id as int
     * @author Euan
     */
    public int getId() {
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
        return id+","+numberOfPeople+","+time+","+table.getTableNumber()+","+customer.getId();
    }

}
