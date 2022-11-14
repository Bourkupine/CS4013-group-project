//Euan: everytime a user creates a new booking, it will make an object here

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class Booking implements ReadWrite{

    private String id;
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
        this.rest = rest;
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now().getHour();//Ronan: this returns computers local time. Do we want this?
        this.id = rest.getBookingId();
    }

    /**
     * Create Booking order
     * @param customer pass a customer
     * @param numberOfPeople number of people for the booking
     * @param time time in hours
     * @author Euan
     */
    public Booking(Customer customer, int numberOfPeople, int time, Restaurant rest) { //Booking todo: account for days
        this(customer, numberOfPeople, rest);//the customer, number of people and restaurant the booking is in
        this.time = time; //the time of the booking
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
        return id+","+numberOfPeople+","+time+","+table.getTableNumber()+","+customer.getId();
    }

}
