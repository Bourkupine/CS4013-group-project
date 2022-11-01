//Euan: this can act like the main (overview?) class


import java.util.ArrayList;

public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    public Restaurant(int amountOfTables) { //Look back into this

        int floor = amountOfTables/3;
        for (int i = 0; i < floor; i++) {
            Table t1 = new Table(1,2);//Ronan: all tables will have id num of 1
            Table t2 = new Table(1,4);// perhaps use rng here?
            Table t3 = new Table(1,5);
            tables.add(t1);
            tables.add(t2);
            tables.add(t3);
        }
        //If numberOfTables is divisible by three, then above gives us exactly how many tables we need
        //Below loop will add either one or two tables if needed.
        while(amountOfTables!=tables.size()){
            Table t = new Table(1,2);
            tables.add(t);
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