//Euan: everytime a user creates a new booking, it will make an object here
//I had the idea that there would be two options, one with email and phone number and one without for contacting.

import java.time.LocalDateTime;
import java.util.Formatter;

public class Booking {

    private String name;
    private String email;
    private String phoneNumber; //NEEDS to be stored as a String as otherwise 083 will become 83
    private int amountOfPeople;
    private LocalDateTime time; //good suggestion from Ronan with storing it as LocalDateTime


    public Booking(String name, int amountOfPeople) {
        this.name = name;
        this.amountOfPeople = amountOfPeople;
    }
    public Booking(String name, int amountOfPeople, String email, String phoneNumber) {
        this(name, amountOfPeople);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        fm.format("Name: %s\nPeople: %d", name, amountOfPeople);

        return sb.toString();
    }
}
