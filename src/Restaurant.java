//Euan: this can act like the main (overview?) class

import java.util.ArrayList;

public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    public Restaurant(int tableNumber) { //Look back into this

        for (int i = 0; i < tableNumber; i++) {
            Table table = new Table();
            tables.add(table);
        }

    }

    //Getters and Setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}