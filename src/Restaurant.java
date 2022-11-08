//Euan: this can act like the main (overview?) class

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;


public class Restaurant {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private double totalEarnings = 0;
    private double dailyEarnings = 0;



    private RestaurantChain rc;
    private Restaurant r;



    private ArrayList<Staff> staff = new ArrayList<>();
    private Chef chef = new Chef("King","testPassword",r);//These passwords are just placeholders
    private Waiter waiter = new Waiter("Bob","testPassword",r) ;
    
    public Restaurant(int amountOfTables, RestaurantChain rc) {
        this.rc = rc;

        int j = 1;
        int floor = amountOfTables/3;
        for (int i = 0; i < floor; i++) {
            Table t1 = new Table(j,2);
            j++;
            Table t2 = new Table(j,4);
            j++;
            Table t3 = new Table(j,8); //Euan: changed to 8
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

    public Chef getChef() {
        return chef;
    }

    public Waiter getWaiter() {
        return waiter;
    }



    public void makeBooking(Customer c, int people, int time) {

        Booking book = new Booking(c, setPeople(people));
        bookings.add(book);
        assignTable(time, setPeople(people), book);

    }

    /**
     * Cancel a booking
     *
     * @param booking the booking you wish to delete
     *
     * @author Euan
     */
    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Changes the
     * @param people
     * @return
     */
    public int setPeople(int people) {

        NavigableSet<Integer> tableSeats = new TreeSet<>();

        tableSeats.add(2);
        tableSeats.add(4);
        tableSeats.add(8);

        return tableSeats.ceiling(people);
    }

    /**
     * Assign a table to the booking
     *
     * @author Euan
     */
    private void assignTable(int hour, int people, Booking book) {



        if(getTable(hour, people) != null) {
            Table tempTable = getTable(hour, people);
            book.setTable(tempTable);
            tempTable.setReservedAtTime(hour, true);
            System.out.println("Your booking has been created");

        }
        else {
            System.out.printf("No available table at %d with %d people\n", hour, people);

        }

    }
    /**
     * Gets table at time and has numOfPeople
     *
     * @param hour hour of booking
     * @param numOfPeople number of people at the table
     * @return table
     *
     * @author Euan
     */

    public Table getTable(int hour, int numOfPeople) {
        for (Table table : getTableList()) {
            if(!table.getReservedAtTime(hour) && table.getNumberOfSeats() == numOfPeople) {
                return table;
            }
        }
        //temporary response if no available tables:
        System.out.println("No available tables at that time");
        return null;
    }

    public void addOrder(Order o){
        orders.add(o);//Ronan: I don't think we ever actually added orders to the array anywhere? could be wrong
    }

    public void removeOrder(Order o){
        orders.remove(o);
    }

    //Getters and Setters


    public ArrayList<Order> getOrders() {
        return orders;
    }
    

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public ArrayList<Table> getTableList() {return tables;} //Euan: changed name to getTableList to not confuse with getTable

    public Menu getMenu() {return rc.getMenu();}

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public ArrayList<Customer> getCustomers(){
        return rc.getCustomers();
    }

    /**
     * add onto the daily earningsju
     * @param amount amount to be added
     * @author Euan
     */
    public void addToDailyEarnings(double amount) {
        dailyEarnings += amount;
    }
    public void updateDailyAmount() {
        totalEarnings += dailyEarnings;
        dailyEarnings = 0;
    }

    public boolean areCustomersPresent(int hour){
        boolean present ;
        if(getTable(hour, 1).getReservedAtTime(hour) ){ //one person at least check if it is reserved 
            present = true;
        }else{
            present = false;
        }
        return present;

    }
}