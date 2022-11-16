//Euan: this can act like the main (overview?) class

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;


public class Restaurant implements ReadWrite{

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();
    private double totalEarnings = 0;
    private double dailyEarnings = 0;
    private int idNum ; //look bakc at this
    private int bookingId = 0;
    private RestaurantChain rc;
    private Restaurant r;
    private Order order;

    //todo: look back at these
    private Chef chef = new Chef("King","testPassword",r);//These passwords are just placeholders
    private Waiter waiter = new Waiter("Bob","testPassword",r) ;
    private Manager manager = new Manager("goopy", "testPassword", r);

    /**
     *
     * @param amountOfTables the amount of tables the restaurant has.
     * @param rc the chain the restaurant is apart of
     * @param idNum id number of the restaurant
     * @author Ronan, Thomas, Euan
     */
    public Restaurant(int amountOfTables, RestaurantChain rc, int idNum) {
        this.rc = rc;
        this.idNum = idNum;
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

    public Manager getManager() { //made by thomas but i moved here
        return manager;
    }

    /**
     * creates a booking for the restaurant and assigns it a table
     * @param c Customer the booking belongs to
     * @param people amount of people for the booking
     * @param time time of booking
     * @author Euan
     */
    /*
    public void makeBooking(Customer c, int people, int time)  {
        //run people check here maybe?
        Booking book = new Booking(c, setPeople(people), this);
        bookings.add(book);
        assignTable(time, setPeople(people), book);
        bookingId++;

    }
*///Ronan: I've commented this out cause it's not used but idk if we need it
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
     * Changes the amount of people at a table to match the amount of seats
     * @param people amount of people for booking
     * @return new number matching the table seats
     * @author Euan
     */
    public int setPeople(int people) {

        NavigableSet<Integer> tableSeats = new TreeSet<>();

        tableSeats.add(2);
        tableSeats.add(4);
        tableSeats.add(8);

        return tableSeats.ceiling(people);
    }

    /**
     * Assigns a table to a booking
     * @param daysInAdvance the amount of days in advance the booking is for
     * @param hour time of booking
     * @param people amount of people booking has
     * @author Bayan
     */
    public Table assignTable(int daysInAdvance, int hour, int people) {
        for (Table t : tables) {
            if (!t.getReservedAtTime(hour, daysInAdvance)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Gets table at given time and that has a certain amount of people
     *
     * @param hour hour of booking
     * @param numOfPeople number of people at the table
     * @return table table that matches the given parameters (if it exists)
     *
     * @author Euan
     */

    public Table getTable(int hour, int numOfPeople) {
        for (Table table : getTableList()) {
            if(!table.getReservedAtTime(hour) && table.getNumberOfSeats() >= numOfPeople) {
                return table;
            }
        }
        //temporary response if no available tables:
        System.out.println("No available tables at that time");
        return null;
    }

    /**
     * add orders to the order array list
     * @param o order to be added
     * @author Ronan
     */
    public void addOrder(Order o){
        orders.add(o);//Ronan: I don't think we ever actually added orders to the array anywhere? could be wrong
    }

    /**
     * remove an order from the order array list
     * @param o order to be removed
     * @author Ronan
     */
    public void removeOrder(Order o){
        orders.remove(o);
    }

    //Getters and Setters

    /**
     * returns the arraylist of orders
     * @return orders as arraylist
     * @author Euan
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * returns the arraylist of bookings
     * @return bookings as arraylist
     * @author Euan
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * returns the arraylist of tables
     * @return tables as arraylist
     * @author Euan
     */
    public ArrayList<Table> getTableList() {return tables;} //Euan: changed name to getTableList to not confuse with getTable

    /**
     * gets current menu
     * @return menu as a menu object
     * @author Ronan
     */
    public Menu getMenu() {return rc.getMenu();}

    /**
     * returns the arraylist of staff
     * @return staff as arraylist
     * @author Ronan
     */
    public ArrayList<Staff> getStaff() {
        return staff;
    }

    /**
     * returns the arraylist of customers
     * @return customers as arraylist
     * @author Ronan
     */
    public ArrayList<Customer> getCustomers(){
        return rc.getCustomers();
    }

    /**
     * add onto the daily earnings
     * @param amount amount to be added
     * @author Euan
     */
    public void addToDailyEarnings(double amount) {
        dailyEarnings += amount;
    }

    /**
     * updates the total earnings and resets daily amount
     * @author Euan
     */
    public void updateDailyAmount() {
        totalEarnings += dailyEarnings;
        dailyEarnings = 0;
    }

    /**
     * get the next booking ID number
     * @return returns ID as an int
     * @author Euan
     */
    public String getBookingId() {
        return String.format("%d_%d", idNum, bookingId);
    }

    /**
     *
     * @return ArrayList of Strings ideal for use in .csv file
     */
    public ArrayList<String> toCsv(){
        ArrayList<String> arr = new ArrayList<>();
        //arr.add("RestId,TableId,NumSeats");
        for(Table t:tables){
            arr.add(idNum+","+t.getTableNumber()+","+t.getNumberOfSeats());
        }
        return arr;
    }
}