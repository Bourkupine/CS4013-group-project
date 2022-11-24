//Euan: this can act like the main (overview?) class

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * A class to represent a restaurant in a restaurant chain
 */
public class Restaurant implements ReadWrite{
    
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();
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
     * Constructor for a restaurant
     * @param amountOfTables the amount of tables the restaurant has.
     * @param rc the chain the restaurant is apart of
     * @param idNum id number of the restaurant
     * @param date today's date
     * @param booking .csv file containing details of bookings
     * @param money .csv file containing details of money obtained
     * @param menuCsv .csv file containing details of menu
     * @param staffCsv .csv file containing details of staff
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
     * @return if the booking was cancelled successfully as boolean
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
    * @author Ronan
    */
    public void generateMenu() {
        ArrayList<FoodItem> f = new ArrayList<>();
        ArrayList<String> temp = readFile(menuCsv);
        for(String s: temp){
            String[] splitted = s.split(",");
            if(splitted[0].equals(String.valueOf(idNum))){
                f.add(new FoodItem(splitted[1],Double.parseDouble(splitted[2]),splitted[3]));
            }
            menu = new Menu(f,this);
        }
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
        if (readFile(booking).size() > 0) {
            for (int i = 1; i < readFile(booking).size(); i++) {
                String[] s = readFile(booking).get(i).split(",");
                String str = s[0];
                String[] temp = str.split("_");
                if (Integer.parseInt(temp[0]) == idNum) {
                    Customer customer = null;
                    Table table = null;
                    int time = Integer.parseInt(s[3]);
                    LocalDate date = LocalDate.parse(s[2]);
                    if ((int) this.date.until(date, ChronoUnit.DAYS) >= 0) {

                        for (Customer c : rc.getCustomers()) {
                            if (c.getId() == Integer.parseInt(s[5])) {
                                customer = c;
                            }
                        }
                        for (Table t : tables) {
                            if (t.getTableNumber() == Integer.parseInt(s[4])) {
                                table = t;
                                t.setReservedAtTime(time, (int) this.date.until(date, ChronoUnit.DAYS), true);
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
     * @return the table available for the booking as Table
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

        if(values.size() > 1) {
            for (int i = 1; i < values.size(); i++) {
                String[] s = values.get(i).split(",");
                LocalDate date = LocalDate.parse(s[1]);
                Double d = Double.parseDouble(s[2]);

                if (dailyAmounts.containsKey(date)) {
                    d += dailyAmounts.get(date);
                    dailyAmounts.replace(date, d);
                } else {
                    dailyAmounts.put(date, d);
                }

            }
        }
    }

    /**
     * Checks if a chef is employed
     * @author Ronan
     * @return true if a chef is employed, false otherwise
     */
    public boolean checkChef(){
        return getChef()!=null;
    }
    
    /**
    * Returns first Chef in Staff ArrayList
    * @author Ronan
    * @return Staff as Chef
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
     * Returns first Waiter in the Staff ArrayList
     * @return Staff as Waiter
     * @author Ronan
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
     * returns all the bookings that are currently made
     * @return arraylist of all the bookings
     * @author Euan
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    
    /**
    * returns the arraylist of bookings that are available
    * @return bookings available as an arraylist
    * @author Thomas
    */
    public ArrayList<Booking> getAvailableBookings() {
        ArrayList<Booking> availableBookings = new ArrayList<>();
        for (Booking r : this.getBookings()){
            for( int i = 0; i < this.getBookings().size(); i++){
                if(r != this.getBookings().get(i)){
                    availableBookings.add(r);
                }
            }
        }
        return availableBookings ;
    } 
    
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

    /**
     * Returns money
     * @return money as File
     * @author Ronan
     */
    public File getMoney() {
        return money;
    }

    /**
     * Returns LocalDate of the restaurant
     * @return date as LocalDate
     * @author Ronan
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a HashMap of daily earnings of the restaurant
     * @return dailyAmounts as HashMap
     * @author Euan
     */
    public HashMap<LocalDate, Double> getDailyAmounts() {
        return dailyAmounts;
    }

    /**
     * Returns menu csv file
     * @return menuCsv as File
     * @author Ronan
     */
    public File getMenuCsv() {
        return menuCsv;
    }

    /**
     * Returns id number of the restaurant
     * @return idNum as int
     * @author Ronan
     */
    public int getIdNum() {
        return idNum;
    }

    /**
     * Returns suitable times at a date where a booking can be made
     * @param daysInAdvance the number of days in advance the booking is for
     * @param numberOfPeople the number of people the booking is for
     * @return ArrayList of available times as int ArrayList
     * @author Bayan
     */
    public ArrayList<Integer> getSuitableTimesAtDate(int daysInAdvance, int numberOfPeople) {
        ArrayList<Integer> availableTimes = new ArrayList<>();
        for (int i = 9; i < 22; i++) {
            for (Table t : tables) {
                    if (t.getTableNumber() == setPeople(numberOfPeople) && !t.getReservedAtTime(i, daysInAdvance)) {
                        availableTimes.add(i);
                        break;
                    }
                }
            }
        return availableTimes;
    }

    /**
     * Returns the booking with the specified id number
     * @param idNum id number of the booking
     * @return b as Booking
     * @author Ronan
     */
    public Booking getBookingWithId(String idNum){
        for(Booking b: bookings){
            if(b.getId().equals(idNum)){
                return b;
            }
        }
        System.out.println("Id doesn't exist");
        return null;
    }

    /**
     * Returns the staff csv file
     * @return staffCsv as File
     * @author Ronan
     */
    public File getStaffCsv() {
        return staffCsv;
    }

    /**
     * Returns deletable csv files
     * @return deletable csvs as File array
     * @author Ronan
     */
    public File[] getDeletableCsv(){
        File[] f = {booking,money,staffCsv};
        return f;
    }

    /**
     * Returns the restaurant's data in csv format
     * @return ArrayList of Strings ideal for use in .csv file
     * @author Ronan
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