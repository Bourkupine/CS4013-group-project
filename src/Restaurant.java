//Euan: this can act like the main (overview?) class


import java.util.ArrayList;

public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    public Restaurant(int amountOfTables) {
        
        int j = 1;
        int floor = amountOfTables/3;
        for (int i = 0; i < floor; i++) {
            Table t1 = new Table(j,2);
            j++;
            Table t2 = new Table(j,4);
            j++;
            Table t3 = new Table(j,5);
            j++;
            tables.add(t1);
            tables.add(t2);
            tables.add(t3);
        }
        //If numberOfTables is divisible by three, then above gives us exactly how many tables we need
        //Below loop will add either one or two tables if needed.
        while(amountOfTables!=tables.size()){
            Table t = new Table(j,2);
            j++;
            tables.add(t);
        }
    }


    //Getters and Setters

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public ArrayList<Table> getTables() {return tables;}
}
