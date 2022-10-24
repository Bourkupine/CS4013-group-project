//Euan: everytime a user creates a new booking, it will make an object here
//I had the idea that there would be two options, one with email and phone number and one without for contacting.

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    Customer customer;
    int numberOfPeople;
    LocalDateTime time;

    /**
     * @author Euan
     * @param customer
     * @param numberOfPeople
     * @param time
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

}
