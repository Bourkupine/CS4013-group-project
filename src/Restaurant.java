//Euan: this can act like the main (overview?) class

import java.awt.print.Book;
import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.TreeSet;


public class Restaurant implements ReadWrite{

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();
    private double totalEarnings = 0;//TODO: Check if we can get total earnings in user interface.
    private double dailyEarnings = 0;
    private int idNum ; //look back at this
    private int bookingId = 0;
    private RestaurantChain rc;
    private Menu menu;
    private File menuCsv;
    private LocalDate date;//Today's date
    private File booking;
    private File money;
    private File staffCsv;

    private HashMap<LocalDate, Double> dailyAmounts = new HashMap<>();



    /**
     *
     * @param amountOfTables the amount of tables the restaurant has.
     * @param rc the chain the restaurant is apart of
     * @param idNum id number of the restaurant
     * @param date today's date
     * @param booking .csv file containing details of bookings
     * @param money .csv file containing details of money obtained
     * @author Ronan, Thomas, Euan
     */
    public Restaurant(int amountOfTables, RestaurantChain rc, int idNum,LocalDate date, File booking, File money,File menuCsv,File staffCsv){
        this.booking=booking;
        this.money=money;
        this.date=date;
        this.rc = rc;
        this.idNum = idNum;
        this.menuCsv=menuCsv;
        fillHashMap();
        this.staffCsv=staffCsv;
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
        generateMenu();
        fillBookings();
    }

    /**
     * Cancel a booking
     * @param name the name of the customer removing the booking
     * @param bookingId the unique id of the booking
     * @author Bayan
     */
    public boolean cancelBooking(String name, String bookingId) {
        for (Booking b : bookings) {
            if (b.getId().equals(bookingId) && b.getCustomer().getName().equalsIgnoreCase(name)) {
                return bookings.remove(b);
            }
        }
        return false;
    }

    /**
     * Generates menu from csv file
     * @author ronan
     */
    public void generateMenu() { //todo: this was for testing, should be done in UI, need to store menu items in csv
        ArrayList<FoodItem> f = new ArrayList<>();
        ArrayList<String> temp = readFile(menuCsv);
        for(String s: temp){
            String[] splitted = s.split(",");
            if(splitted[0].equals(String.valueOf(idNum))){
                f.add(new FoodItem(splitted[1],Double.parseDouble(splitted[2]),splitted[3]));
            }

        }

        menu = new Menu(f);
    }



    /**
     * Adds a booking to bookings
     * @param booking the booking to be added
     * @author Bayan
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Fill table 2d array with current bookings from csv
     * @author Euan
     */
    public void fillBookings() {
        for (int i = 1; i < readFile(booking).size(); i++) {
            String[] s = readFile(booking).get(i).split(",");
            String str = s[0];
            String[] temp = str.split("_");
            if(Integer.parseInt(temp[0]) == idNum) {
                Customer customer = null;
                Table table = null;
                int time = Integer.parseInt(s[3]);
                LocalDate date = LocalDate.parse(s[2]);
                if ((int)this.date.until(date, ChronoUnit.DAYS) >= 0) {

                    for (Customer c : rc.getCustomers()) {
                        if (c.getId() == Integer.parseInt(s[5])) {
                            customer = c;
                        }
                    }
                    for (Table t : tables) {
                        if (t.getTableNumber() == Integer.parseInt(s[4])) {
                            table = t;
                            t.setReservedAtTime(time, (int)this.date.until(date, ChronoUnit.DAYS), true);
                        }
                    }
                    int numOfPeople = Integer.parseInt(s[1]);

                    Booking b = new Booking(customer, numOfPeople, time, this, date, table);
                    bookings.add(b);
                    bookingId = Integer.parseInt(temp[1]);
                }

            }

        }
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
            if (!t.getReservedAtTime(hour, daysInAdvance) && t.getNumberOfSeats() >= people) {
                return t;
            }
        }
        return null;
    }

    /**
     * Fills values into Hashmap from CSV file
     * @author Euan
     */
    public void fillHashMap() {

        ArrayList<String> values = readFile(money);

        values.forEach(row -> {
            String[] s = row.split(",");
            dailyAmounts.put(LocalDate.parse(s[0]),Double.parseDouble(s[1]));
        });

    }

    /**
     * Returns first Chef in Staff ArrayList
     * @author ronan
     * @return a chef
     */
    public Chef getChef(){
        for(Staff s:staff){
            if(s instanceof Chef){
                return (Chef)s;
            }
        }
        return null;
    }
    /**
     * Returns first waiter in Staff ArrayList
     * @author ronan
     * @return a waiter
     */
    public Waiter getWaiter(){
        for(Staff s:staff){
            if(s instanceof Waiter){
                return (Waiter)s;
            }
        }
        return null;
    }

    /**
     * add orders to the order array list
     * @param o order to be added
     * @author Ronan
     */
    public void addOrder(Order o){
        orders.add(o);
    }

    /**
     * remove an order from the order array list
     * @param o order to be removed
     * @author Ronan
     */
    public void removeOrder(Order o){
        orders.remove(o);
    }

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
    } //TODO: IMPLEMENT VIEW BOOKINGS HEY DO IT NOW PLS THANKS BOOB

    /**
     * gets current menu
     * @return menu as a menu object
     * @author Ronan
     */
    public Menu getMenu() {return menu;}

    /**
     * returns the arraylist of staff
     * @return staff as arraylist
     * @author Ronan
     */
    public ArrayList<Staff> getStaff() {
        return staff;
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
    }//TODO: WHY NO USEY EUAN SEEMS TO KNOW THIS IS FOR EUAN

    /**
     * Increments and get the next booking ID number
     * @return returns ID as an int
     * @author Euan
     */
    public String getBookingId() {
        bookingId++;
        return String.format("%d_%d", idNum, bookingId);
    }

    /**
     * Gets the restaurant chain this restaurant is a part of
     * @return rc as RestaurantChain
     * @author Bayan
     */
    public RestaurantChain getRestaurantChain() {
        return rc;
    }


    public File getMoney() {
        return money;
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<LocalDate, Double> getDailyAmounts() {
        return dailyAmounts;
    }

    public File getMenuCsv() {
        return menuCsv;
    }

    public int getIdNum() {
        return idNum;
    }

    public File getStaffCsv() {
        return staffCsv;
    }

    public File[] getDeletableCsv(){
        File[] f = {booking,money,staffCsv};
        return f;
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