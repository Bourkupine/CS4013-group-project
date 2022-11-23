import java.awt.print.Book;
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
            System.out.println("C)reate booking, U)ndo booking, Q)uit");
            String selection = in.next();
            if (selection.equalsIgnoreCase("c")) {
                booking();
            } else if (selection.equalsIgnoreCase("u")) {
                undoBooking();
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
     * @author Thomas, Ronan, Bayan
     * @return false to log out, true otherwise
     */
    private boolean waiter(String str, Waiter w) {
        String s = str.toLowerCase();
        switch (s) {
            case "a"://Add order
                addOrder(w);
                return true;

            case "r"://Remove order
                removeOrder();
                return true;

            case "v"://View current orders
                w.printOrders();
                return true;

            case "t"://Take a booking
                booking();
                return true;

            case "u": //undo booking
                undoBooking();
                return true;

            case "s": //see all bookings
                w.printBookings(today);
                return true;

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
     * @author Bayan, Thomas, Ronan
     * @param m manager who is currently logged in
     * @return false to log out, true otherwise
     */

    private boolean manager(String str, Manager m) {
        String s = str.toLowerCase();
        String name; //Bayan: defined some variables used across multiple cases here to keep naming convention consistent
        switch (s) {
            case "a": //Add order
                addOrder(m);
                return true;

            case "r": //Remove order
                removeOrder();
                return true;

            case "v": //View orders
                m.printOrders();
                return true;

            case "t"://Take a booking
                booking();
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
                return true;

            case "s": //see all bookings
                m.printBookings(today);
                return true;

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
     * @author Ronan
     */
    private boolean validDate(LocalDate date){

        return today.until(date, ChronoUnit.DAYS) <= 6 && !date.isBefore(today);
    }

    /**
     * Creates a booking at a specific date and time
     * @author Ronan, Bayan
     */
    private void booking() {
        System.out.println("Enter customer name: ");
        String name = in.next();
        System.out.println("Enter phone number(type 0 for walk in): ");
        String phone = in.next();
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
        int selectedTime=0;
        int daysInAdvance = (int)today.until(d, ChronoUnit.DAYS);
        for (int i : r.getSuitableTimesAtDate(daysInAdvance, numberOfPeople)) {
            System.out.printf("%02d:00 ", i);
            System.out.println();
        }
        System.out.println("Input the time (in hours) you would like to book for, e.g. 18 to book 18:00");
        while (true) {
            selectedTime = in.nextInt();
            if (r.getSuitableTimesAtDate(daysInAdvance, numberOfPeople).contains(selectedTime)) {
                break;
            } else {
                System.out.println("Please input an available time in hours");
            }
        }
        Table t = r.assignTable(daysInAdvance, selectedTime, numberOfPeople);
        t.setReservedAtTime(selectedTime, daysInAdvance, true);
        Customer c = r.getRestaurantChain().findCustomer(name, phone);
        Booking booking = new Booking(c, numberOfPeople, selectedTime, r, today, t);
        r.addBooking(booking);
        booking.updateFile(bookings,booking.toCsv());
        System.out.println("Booking successful, your table number is " + t.getTableNumber());
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

    /**
     * Selects an order to be paid for
     * @author Bayan
     */
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

    /**
     * Selects a booking to be cancelled
     * @author Bayan
     */
    private void undoBooking() {
        System.out.println("Input customer name");
        String name = in.next();
        System.out.println("Input booking id");
        String bookingId = in.next();
        if (r.cancelBooking(name, bookingId)) {
            System.out.println("Booking removed successfully");
        } else {
            System.out.println("No booking found with those details");
        }
    }

    /**
     * A method to add an order for an existing or new customer
     * @param s a Staff object for accessing printBookings method in Staff
     * @author Bayan
     */
    private void addOrder(Staff s) {
        if(r.getMenu().getSize()==0){
            System.out.println("No menu currently exists for this restaurant");
            System.out.println("Please create one using manage menu");
        }
        else{
            s.printBookings(today);
            while(true){
                System.out.println("Enter booking id");
                String bookingId = in.next();
                Booking b = r.getBookingWithId(bookingId);
                if(b!=null){
                    b.setCompleted(true);
                    r.addOrder(new Order(b));
                    break;
                }
            }
        }

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
