import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Restaurant r;
    private File bookings;
    private File money;

    private Scanner in = new Scanner(System.in);

    private LocalDate today;

    /**
     * creates a user interface for restaurant
     * @param r restaurant the interface belongs to
     * @author Ronan
     */
    public UserInterface(Restaurant r,LocalDate date, File bookings, File money) {

        this.r = r;
        today=date;
        this.bookings=bookings;
        this.money=money;
        pick();
    }

    /**
     * Determines staff or customer
     * @author Ronan
     */
    private void pick() {
        while (true) {
            System.out.println("Enter 1 for staff, 2 for customer or 3 to quit");
            int ans = in.nextInt();
            if (ans == 1) {
                staffLogin();
            } else if (ans == 2) {
                runCustomer();
            } else if (ans == 3) {
                System.exit(0);
            } else {
                System.out.println("Please try again");
            }
        }

    }

    /**
     * This method runs if a customer is using the system
     * @author Ronan, Bayan
     */
    private void runCustomer() {
        boolean running = true;

        while (running) {
            System.out.println("Enter name");
            String name = in.next();
            System.out.println("C)reate booking, U)ndo booking, Q)uit");
            String selection = in.next();
            if (selection.equalsIgnoreCase("c")) {
                int num = 0;
                boolean lessthen8 = false;
                while (!lessthen8) {
                    System.out.println("Enter number of people: ");
                    num = in.nextInt();
                    if (num > 8) {
                        System.out.println("No more then 8 people per booking");
                    } else {
                        num = r.setPeople(num); //change the amount of people to fit the table seats
                        lessthen8 = true;
                    }
                }
                LocalDate d = today;
                boolean validDate = false;
                while (!validDate) {
                    d = valiDate();
                    validDate = validDate(d);
                    if (!validDate) {
                        System.out.println("Bookings no more than 6 days in advance");
                    }
                }
                //time
                int time = 0;
                do {
                    System.out.println("Enter the hour in 24hr clock between 9 and 21: ");
                    time = in.nextInt();//Need to check this
                } while (time < 9 || time > 21);
                running = booking(d, time, num, name);
            } else if (selection.equalsIgnoreCase("u")) {
                running = !undoBooking();
            } else if (selection.equalsIgnoreCase("q")) {
                running = false;
            } else {
                System.out.println("Please input a valid option");
            }
        }


    }

    /**
     * This method runs if a staff member is using the system to allow them to login
     * @author Ronan
     */
    private void staffLogin() {

        boolean running = true;
        while (running) {
            boolean pass = false;
            while (!pass) {

                System.out.println("Enter username");
                String name = in.next();

                System.out.println("Enter password");
                String password = in.next();

                if (valid(name, password, r.getStaff())) {//If a valid username was entered
                    Staff currentStaff;
                    pass = true;
                    int index = 0;
                    for (Staff s : r.getStaff()) {
                        if (s.getName().equals(name) && s.getPassword().equals(password)) {
                            index = r.getStaff().indexOf(s);
                            break;
                        }
                    }
                    currentStaff = r.getStaff().get(index);
                    running = runStaff(currentStaff);//When you log out, this sets running to false, exiting the loop
                } else {
                    System.out.println("Invalid username/password");
                }
            }
        }
        pick();

    }

    /**
     * When a staff member logs in, this is called
     * @param currentStaff Staff that is currently logged in
     * @return false once logout, allowing staffLogin() loop to break
     * @author Ronan, Bayan, Thomas
     */
    private boolean runStaff(Staff currentStaff) {
        boolean running = true;
        while (running) {
            if (currentStaff instanceof Waiter) {
                System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, U)ndo booking, S)ee bookings P)ay, L)og out");
                String input = in.next();
                running = waiter(input, (Waiter) currentStaff);

            } else if (currentStaff instanceof Chef) {
                System.out.println("V)iew orders, A)cknowledge, U)pdate order, L)og out");
                String input = in.next();
                running = chef(input, (Chef) currentStaff);

            } else {//Currently manager
                System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, M)oney generated, U)ndo booking, S)ee bookings, P)ay, C)reate menu, H)ire Staff, F)ire Staff, D)elete CSVs, L)og out");
                String input = in.next();
                running = manager(input, (Manager) currentStaff);

            }
        }
        return false;
    }

    /**
     * checks if a member is actually a staff member
     * @param name name of member
     * @param pass members password
     * @param arr array of staff members
     * @return true/false if they are a valid member
     * @author Ronan
     */
    private boolean valid(String name, String pass, ArrayList<Staff> arr) {
        for (Staff s : arr) {
            if (s.getName().equals(name) && s.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method runs if a waiter is logged in
     * @param str option passed from runStaff()
     * @param w waiter who is logged in
     * @author Thomas, Ronan
     * @return false to log out, true otherwise
     */
    private boolean waiter(String str, Waiter w) {
        String s = str.toLowerCase();
        switch (s) {
            case "a"://Add order
                addOrder();
                return true;

            case "r"://Remove order
                removeOrder();
                return true;

            case "v"://View current orders
                w.printOrders();
                return true;

            case "t"://Take a booking
                //need to show what tables are available
                //todo: find a way to return only available tables - also need to specify when the booking is for!!
                //take booking
                boolean running=true;
                while(running) {
                    //here I used ronans stuff and added to them
                    System.out.println("Enter customer name: ");
                    String name = in.next();
                    System.out.println("Enter phone number(type 0 for walk in): ");
                    String phone = in.next();
                    int numberOfPeople = 0;
                    boolean lessthen8 = false;
                    while(!lessthen8) {
                        System.out.println("Enter number of people: ");
                        numberOfPeople = in.nextInt();
                        if(numberOfPeople > 8){
                            System.out.println("No more then 8 people per booking");
                        } else {
                            lessthen8 = true;
                        }
                    }
                    boolean validDate = false;
                    LocalDate d = today;
                    while (!validDate){
                        System.out.println("Enter the date you want to book for");
                        d = LocalDate.parse(in.next());
                        validDate = validDate(d);
                        if (!validDate) {
                            System.out.println("Bookings no more than 6 days in advance");
                        }
                    }
                    int time;
                    do {
                        System.out.println("Enter the hour in 24hr clock between 9 and 21: ");
                        time = in.nextInt();//Need to check this
                    }while (time < 9 || time > 21);
                    running = booking(d, time, numberOfPeople, name);

                }
                return true;

            case "u": //undo booking
                undoBooking();

            case "s": //see all bookings
                w.printBookings(today);

            case "p"://Take payment
                pay();
                return true;

            case "l"://Log out
                return false;

        }
        return true;
    }

    /**
     * This runs if a chef is logged in
     * @param str option passed from runStaff()
     * @author Thomas, Ronan
     * @param c chef who is currently logged in
     * @return false to log out, true otherwise
     */
    private boolean chef(String str, Chef c) {
        String s = str.toLowerCase();
        switch (s) {
            case "v"://View orders
                c.printOrders();
                return true;

            case "a"://Acknowledge order (ie: cook order)
                c.cooking(r.getOrders().get(0));
                r.getWaiter().getReadyOrders();
                return true;

            case "l"://log out
                return false;
        }
        return true;
    }

    /**
     * This runs if a manager is logged in
     * @param str option passed from runStaff()
     * @author Thomas, Ronan
     * @param m manager who is currently logged in
     * @return false to log out, true otherwise
     */

    private boolean manager(String str, Manager m) {
        String s = str.toLowerCase();
        String name; //Bayan: defined some variables used across multiple cases here to keep naming convention consistent
        String phone;
        boolean inList;
        switch (s) {
            case "a": //Add order
                addOrder();
                return true;

            case "r": //Remove order
                removeOrder();
                return true;

            case "v": //View orders
                m.printOrders();
                return true;

            case "t"://Take a booking
                //need to show what tables are available
                //todo: find a way to return only available tables - also need to specify when the booking is for!!
                //take booking
                boolean running=true;
                while (running){
                    //here I used ronans stuff and added to them
                    System.out.println("Enter customer name: ");
                    name = in.next();
                    System.out.println("Enter phone number(type 0 for walk in): ");
                    phone = in.next();
                    int numberOfPeople=0;
                    boolean lessthen8 = false;
                    while(!lessthen8){
                        System.out.println("Enter number of people: ");
                        numberOfPeople = in.nextInt();
                        if(numberOfPeople>8){
                            System.out.println("No more then 8 people per booking");
                        }else{
                            lessthen8 = true;
                        }
                    }
                    System.out.println("These are all the bookings we currently have available ");
                    r.getAvailableBookings();
                    System.out.println("Please select a date within the next 6 days");
                    boolean validDate=false;
                    LocalDate d = today;
                    while(!validDate){
                        d = valiDate();
                        validDate = validDate(d);
                        if(!validDate){
                            System.out.println("Bookings no more than 6 days in advance");
                        }
                    }
                    int time=0;
                    do {
                        try{
                            System.out.println("Enter the hour in 24hr clock between 9 and 21: ");
                            time = in.nextInt();//Need to check this
                            r.getBookings().get(time);
                            System.out.println("There is no tables available at this time choose another time.");
                        }catch(IndexOutOfBoundsException ex){
                            System.out.println("Your booking is for " + time + " on " + d + ". Please arrive on time" + "\n" + "If you are later then 15 minutes your booking will be forefeited");
                            break ;
                        }
                    }while (time < 9 || time > 21);
                    running = booking(d, time, numberOfPeople, name);

                }
                return true;

            case "m":
                System.out.println("please give when you want the graph the start");
                LocalDate start = valiDate();
                System.out.println("please print when you want the graph to end");
                LocalDate end = valiDate();

                m.generateGraph(start,end);
                return true;

            case "u": //undo booking
                undoBooking();

            case "s": //see all bookings
                m.printBookings(today);

            case "p": //Pay
                pay();
                return true;


            case "c": //Create menu
                m.manageMenu(in);
                return true;

            case "h": //Bayan: Hire staff
                System.out.println("Enter staff name:");
                name = in.next();
                System.out.println("\nEnter staff password:");
                String password = in.next();
                System.out.println("""
        Enter staff position:
        [A] Manager
        [B] Chef
        [C] Waiter
        [D] Exit
        """);
                while (true) {
                    String type = in.next();
                    if (type.equalsIgnoreCase("C")) {
                        m.employStaff(new Waiter(name, password, r));
                        break;
                    } else if (type.equalsIgnoreCase("B")) {
                        m.employStaff(new Chef(name, password, r));
                        break;
                    } else if (type.equalsIgnoreCase("A")) {
                        m.employStaff(new Manager(name, password, r));
                        break;
                    } else if (type.equalsIgnoreCase("D")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                }
                return true;

            case "f": //Bayan: Fire staff
                System.out.println("Enter staff name:");
                name = in.next();
                if (m.fireStaff(name)) {
                    System.out.println(name + "has been fired");
                } else {
                    System.out.println("No staff found by that name");
                }
                return true;

            case "d"://Clear csvs
                m.factoryReset(r.getDeletableCsv());
                System.exit(0);
                return true;

            case "l"://logout
                return false;

        }
        return true;
    }

    /**
     * Checks if a given date is within 6 days of today
     * @param date date to be checked
     * @return true if within 6 days, false otherwise
     */
    private boolean validDate(LocalDate date){

        return today.until(date, ChronoUnit.DAYS) <= 6 && !date.isBefore(today);
    }

    /**
     * Creates a booking at a specific date and time
     * @param d the date of the booking
     * @param time the time of the booking in hours
     * @param num the number of people for the booking
     * @param name the name of the customer making the booking
     * @return false after booking is complete
     * @author Ronan, Bayan
     */
    private boolean booking(LocalDate d, int time, int num, String name) {
        int daysInAdvance = (int)today.until(d, ChronoUnit.DAYS);
        Table t = r.assignTable(daysInAdvance, time, num);
        if (t != null) {//If there is an available table
            System.out.println("Table " + t.getTableNumber() + " is available");
            System.out.println("Enter y to book, or n otherwise");
            if (in.next().equalsIgnoreCase("y")) {
                t.setReservedAtTime(time, daysInAdvance, true);
                Customer c = r.getRestaurantChain().findCustomer(name);
                Booking booking = new Booking(c, num, time, r, today, t);
                r.addBooking(booking);
                booking.updateFile(bookings,booking.toCsv());
                System.out.println("Booking successful");
            }
        }
        return false;
    }

    /**
     * Overloaded booking method that creates a booking for today
     * @param time time of booking
     * @param num number of people booking is for
     * @param name name of customer making booking
     * @return false after booking is complete
     * @author Bayan
     */
    private boolean booking(int time, int num, String name) {
        return booking(today, time, num, name);
    }

    /**
     * Ensures dates are written in correct format
     * @return LocalDate
     * @author Ronan
     */
    private LocalDate valiDate(){
        boolean valid = false;
        LocalDate date = LocalDate.now();
        while(!valid){
            try{
                System.out.println("Enter date");
                date = LocalDate.parse(in.next());
                valid=true;
            }
            catch (DateTimeParseException dtpe){
                System.out.println("Enter date in format YYYY-MM-DD");
            }
        }
        return date;
    }

    private void pay() {
        if(r.getOrders().isEmpty()){
            System.out.println("No orders currently");
        }
        else{
            System.out.println("Select an order");
            System.out.println(r.getOrders().toString());
            int o = in.nextInt();
            Till t =new Till(r);
            t.processPayment(r.getOrders().get(o));
        }
    }
    private boolean undoBooking() {
        System.out.println("Input customer name");
        String name = in.next();
        System.out.println("Input booking id");
        String bookingId = in.next();
        if (r.cancelBooking(name, bookingId)) {
            System.out.println("Booking removed successfully");
            return true;
        } else {
            System.out.println("No booking found with those details");
            return false;
        }
    }

    /**
     * A method to add an order for an existing or new customer
     * @author Bayan
     */
    private void addOrder() {
        System.out.println("Enter customer name: ");
        String name = in.next();
        System.out.println("Enter phone number (type 0 for walk in): ");
        String phone = in.next();
        r.addOrder(new Order(r.getRestaurantChain().findCustomer(name), r));
    }

    /**
     * A method to remove an existing order from the restaurant
     * @author Bayan
     */
    private void removeOrder() {
        System.out.println(r.getOrders().toString());
        System.out.println("Enter the number of the order you would like to remove");
        int remove;
        while(true) {
            remove = in.nextInt();
            if (remove > 0 || remove < r.getOrders().size()) {
                break;
            } else {
                System.out.println("Please input a valid order number");
            }
        }
        System.out.println("Order" + r.getOrders().get(remove-1) + "has been removed");
        r.removeOrder(r.getOrders().get(remove-1));
    }
}
