//Euan: everytime a user creates a new booking, it will make an object here

import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    private Customer customer; //Customer that makes the booking
    private int numberOfPeople; //number of people at the table
    private LocalDateTime time; //time of booking. We will need to run a checker for this
    private Table table;
    private Restaurant rest;


    /**
     * @author Euan
     *
     * Create Booking order
     *
     * @param customer pass a customer //might need to change this to id or something
     * @param numberOfPeople number of people at a table
     * @param time time of booking (second constructor sets time to now)
     */
    public Booking(Customer customer, int numberOfPeople, LocalDateTime time, Restaurant rest) { //Booking
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
        this.rest = rest;
        assignTable();
    }
    public Booking(Customer customer, int numberOfPeople, Restaurant rest) { //walk-in
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now();
        this.rest = rest;
        assignTable();
    }

    /**
     * Assign a table to the booking
     *
     *@author Euan
     */
    private void assignTable() {
        //Check for available tables        
        //assign if available
        //print out next available time and give option to book next time
        //set booked table to unavailable


        for(Table table : rest.getTables())

            if(table.getReservedAtTime(time.getHour())) {
                table = getTable(); //TODO Make this method
            } else {
                System.out.printf("No available tables at %d", time.getHour());
            }

    }

    /**
     * Delete/cancel a booking
     *
     * @param booking: the booking you want to cancel
     * @author Euan
     */
    public void cancelBooking(Object booking) {
        booking = null; //doesnt work; needs testing
    }

    /**
     * Change time of booking
     *
     * @param time time you wish to change your booking to
     * @author Euan
     */
    public void laterTime(LocalDateTime time){
        this.time = time;
    }

    //Setters & Getters

    public Customer getCustomer() {
        return customer;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getTime() {
        return time;
    }

}
