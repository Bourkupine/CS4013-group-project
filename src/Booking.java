//Euan: everytime a user creates a new booking, it will make an object here
//I had the idea that there would be two options, one with email and phone number and one without for contacting.

public class Booking {

    private String name;
    private String email;
    private String phoneNumber; //NEEDS to be stored as a String as otherwise 083 will become 83
    private int amountOfPeople;


    public Booking(String name, int amountOfPeople) {
        this.name = name;
        this.amountOfPeople = amountOfPeople;
    }
    public Booking(String name, int amountOfPeople, String email, String phoneNumber) {
        this(name, amountOfPeople);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
