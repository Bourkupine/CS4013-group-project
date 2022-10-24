//Euan: everytime a user creates a new booking, it will make an object here
//I had the idea that there would be two options, one with email and phone number and one without for contacting.

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    private Customer customer;
    private int numberOfPeople;
    private LocalDateTime time;

    /**
     * @author Euan
     *
     * Create Booking order
     * @param customer pass a customer //might need to change this to id or something
     * @param numberOfPeople number of people at a table
     * @param time time of booking
     */
    public Booking(Customer customer, int numberOfPeople, LocalDateTime time) { //Booking
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }
    public Booking(Customer customer, int numberOfPeople) { //walk-in
        this.customer = customer;
        this.numberOfPeople = numberOfPeople;
        this.time = LocalDateTime.now();
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
