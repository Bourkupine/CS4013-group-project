//Euan: everytime a user creates a new booking, it will make an object here
//I had the idea that there would be two options, one with email and phone number and one without for contacting.


import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    private Customer customer; //Customer that makes the booking
    private int numberOfPeople; //number of people at the table
    private LocalDateTime time; //time of booking. We will need to run a checker for this


    /**
     * @author Euan
     *
     * Create Booking order
     *
     * @param customer pass a customer //might need to change this to id or something
     * @param numberOfPeople number of people at a table
     * @param time time of booking
     */
    public Booking(Customer customer, int numberOfPeople, LocalDateTime time) { //Booking
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
        assignTable();
    }
    public Booking(Customer customer, int numberOfPeople) { //walk-in
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now();
        assignTable();
    }

    public void assignTable() {
        //Check for available tables
        //assign if available
        //  print out next available time and give option to book next time
        //set booked table to unavailable
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
