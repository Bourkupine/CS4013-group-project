//Euan: everytime a user creates a new booking, it will make an object here

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class Booking {

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
     * @author Euan
     *
     * Create Booking order
     *
     * @param customer pass a customer //might need to change this to id or something
     * @param numberOfPeople number of people at a table
     */
    public Booking(Customer customer, int numberOfPeople) { //walk-in
        this.id = totalId;
        totalId++;
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;//TODO make sure numOfPeople isnt > 8
        this.time = LocalDateTime.now().getHour();//Ronan: this returns computers local time. Do we want this?
        idCounter++;
        this.id = idCounter;
    }

    public Booking(Customer customer, int numberOfPeople, int time) { //Booking
        this(customer, numberOfPeople);
        this.time = time;
    }


    /**
     * sets a table to the booking
     * @param table
     * @author Euan
     */
    public void setTable(Table table) {
        this.table = table;
    }


    /**
     * Delete/cancel a booking
     *
     * @param booking: the booking you want to cancel
     * @author Euan
     */

    /**
     * Change time of booking
     *
     * @param time time you wish to change your booking to
     * @author Euan
     */
    public void laterTime(int time){
        this.time = time;
    }

    //Setters & Getters


    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getTime() {
        return time;
    }

}
