//Euan: everytime a user creates a new booking, it will make an object here

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    private int id;
    private static int idCounter = 0; //used to keep a universal counter of all the bookings so we can set id = idCounter + 1
    private Customer customer; //Customer that makes the booking
    private int numberOfPeople; //number of people at the table
    private LocalDateTime time; //time of booking. We will need to run a checker for this
    private Table table;
    private Restaurant rest; //booking is specific to a certain restaurant


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
        this.numberOfPeople = setPeople(numberOfPeople); //TODO make sure numOfPeople isnt > 8
        this.time = time;
        this.rest = rest;
        assignTable();
        idCounter++;
        this.id = idCounter;
    }
    public Booking(Customer customer, int numberOfPeople, Restaurant rest) { //walk-in
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now();
        this.rest = rest;
        assignTable();
        idCounter++;
        this.id = idCounter;
    }

    public int setPeople(int people) {

        NavigableSet<Integer> tableSeats = new TreeSet<Integer>();

        tableSeats.add(2);
        tableSeats.add(4);
        tableSeats.add(8);

        return tableSeats.ceiling(people);
    }

    /**
     * Assign a table to the booking
     *
     *@author Euan
     */
    private void assignTable() {

        rest.getTable(time.getHour(), numberOfPeople);
        table.setReservedAtTime(time.getHour(), true);


    }

    /**
     * Delete/cancel a booking
     *
     * @param booking: the booking you want to cancel
     * @author Euan
     */
    public void cancelBooking(Object booking) {
        booking = null; //doesnt work; needs testing
        table.setReservedAtTime(time.getHour(), false);
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
