//Euan: this can act like the main (overview?) class


import java.util.ArrayList;

public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    public Restaurant(int amountOfTables) { //Look back into this

        //very temporary solution
        for (int i = 0; i < amountOfTables; i++) {
            Table table = new Table(i, (int) Math.pow(2, (i%3)+1)); //see comment below method for this
            tables.add(table);
        }
    }
    //Math.pow(x, y) will return x^y, by using %3 we will return a number from 0-2 each time, we then +1.
    //so what this is effectively giving us is, Math.pow(2, (1/2/3)) i.e 2^(1/2/3)
    //where 2^(1/2/3) will cycle between the values in the bracket each time i is incremented
    //thus giving us 2^1, 2^2, 2^3, or 2, 4, 8
    //this allows us to generate tables of these sizes.

    //Getters and Setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}