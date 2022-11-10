import java.util.ArrayList;
import java.util.Scanner;

public class StaffInterface {

    private Restaurant r;

    Scanner in = new Scanner(System.in);
    ArrayList<Staff> staffArr;
    ArrayList<Customer> customerArr;

    public StaffInterface(Restaurant r) {

        this.r = r;
        staffArr = r.getStaff();
        customerArr = r.getCustomers();
        pick();
    }

    /**
     * Determines staff or customer
     */
    public void pick() {
        boolean valid = false;
        while (!valid) {
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
     */
    public void runCustomer() {//This is required by documentation. Perhaps we can use this for walk ins??
        boolean running = true;

        while (running) {
            System.out.println("Enter a time: ");
            int time = in.nextInt();//Need to check this
            System.out.println("Enter number of people: ");
            int num = in.nextInt();

            if (r.getTable(time, num) != null) {
                System.out.println("Table " + r.getTable(time, num).getTableNumber() + " is available");
                System.out.println("Enter y to book, or n otherwise");
                if (in.next().toLowerCase().equals("y")) {
                    System.out.println("Enter name");
                    String name = in.next();
                    boolean inList = false;
                    for (Customer c : customerArr) {
                        if (c.getName().equals(name)) {
                            Booking b = new Booking(c, num);
                            inList = true;

                        }

                    }
                    if (!inList) {
                        Booking b = new Booking(new Customer(name), num);
                    }
                    running = false;
                }
            }
        }


    }

    /**
     * This method runs if a staff member is using the system
     */
    public void staffLogin() {

        boolean running = true;
        while (running) {
            boolean pass = false;
            while (!pass) {

                System.out.println("Enter username");//WHY THE FUCK DOES THIS SKIP SOMETIMES????????
                String name = in.next();

                System.out.println("Enter password");
                String password = in.next();

                if (valid(name, password, staffArr)) {
                    Staff currentStaff;
                    pass = true;
                    int index = 0;
                    for (Staff s : staffArr) {
                        if (s.getName().equals(name) && s.getPassword().equals(password)) {
                            index = staffArr.indexOf(s);
                            break;
                        }
                    }
                    currentStaff = staffArr.get(index);
                    running = runStaff(currentStaff);
                } else {
                    System.out.println("Invalid username/password");
                }
            }
        }
        pick();

    }

    public boolean runStaff(Staff currentStaff) {
        boolean running = true;
        while (running) {
            if (currentStaff instanceof Waiter) {
                System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay, L)og out");
                String input = in.next();
                running = waiter(input, (Waiter) currentStaff);

            } else if (currentStaff instanceof Chef) {
                System.out.println("V)iew orders, A)cknowledge,U)pdate order, L)og out");
                String input = in.next();
                running = chef(input, (Chef) currentStaff);

            } else {//Currently manager
                System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, U)ndo Booking P)ay, C)reate menu, H)ire Staff, F)ire Staff, L)og out");
                String input = in.next();
                running = manager(input, (Manager) currentStaff);

            }
        }
        return false;
    }

    public boolean valid(String name, String pass, ArrayList<Staff> arr) {
        for (Staff s : arr) {
            if (s.getName().equals(name) && s.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public boolean waiter(String str, Waiter w) {
        String s = str.toLowerCase();
        switch (s) {
            case "a":
                //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
                System.out.println("Enter customer name: ");
                String name = in.next();
                System.out.println("Enter phone number(type 0 for walk in): ");
                String phone = in.next();
                Customer cust;
                Order o;//Ronan: implementation of order and customer here is not ideal, just wanted a working version
                boolean inList = false;
                for (Customer c : customerArr) {
                    if (c.getName().equals(name)) {
                        cust = c;
                        o = new Order(cust, r);
                        r.addOrder(o);//need to check how we are doing this in waiter
                        inList = true;
                    }

                }
                if (!inList) {
                    cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                    o = new Order(cust, r);
                    r.addOrder(o);
                }
                return true;

            case "r":
                System.out.println(r.getOrders().toString());//TODO: Better implementation here
                System.out.println("Enter the order you would like to remove");
                return true;

            case "v":
                System.out.println(r.getOrders().toString());
                return true;
            case "t":
                //w.takeBooking();
                //TODO: TAKE BOOKING
                return true;

            case "p":
                //TODO: THIS WILL BE THE SAME FOR MANAGER
                return true;
            case "l":

                return false;
        }
        return true;
    }

    public boolean chef(String str, Chef c) {
        String s = str.toLowerCase();
        switch (s) {
            case "v":
                System.out.println(r.getOrders().toString());
                return true;
            case "a":
                r.getChef().cooking(r.getOrders().get(0));
                return true;
            case "u":
                //update order
                r.getChef().isDeliverable(r.getOrders().get(0));
                return true;
            case "l":
                return false;

        }
        return true;
    }

    public boolean manager(String str, Manager m) {
        String s = str.toLowerCase();
        switch (s) {
            case "a": //Add order
                //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
                System.out.println("Enter customer name: ");
                String name = in.next();
                System.out.println("Enter phone number(type 0 for walk in): ");
                String phone = in.next();
                Customer cust;
                Order o;//Ronan: implementation of order and customer here is not ideal, just wanted a working version
                boolean inList = false;
                for (Customer c : customerArr) {
                    if (c.getName().equals(name)) {
                        cust = c;
                        o = new Order(cust, r);
                        r.addOrder(o);//need to check how we are doing this in waiter
                        inList = true;
                    }

                }
                if (!inList) {
                    cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                    o = new Order(cust, r);
                    r.addOrder(o);
                }
                return true;

            case "r": //Remove order
                System.out.println(r.getOrders().toString());//TODO: Better implementation here
                System.out.println("Enter the order you would like to remove");
                return true;

            case "v": //View orders
                System.out.println(r.getOrders().toString());
                return true;

            case "t": //Take booking
                //need to show what tables are available
                System.out.println(r.getTableList()); // table list needs a to string so the manager and waiters can see what tables are available
                //take booking

                //here i used ronans stuff and added to them
                System.out.println("Enter customer name: ");
                String Name = in.next();
                System.out.println("Enter phone number(type 0 for walk in): ");
                String Phone = in.next();
                System.out.println("Enter the amount of people you are booking for");
                int numberOfPeople = in.nextInt();
                System.out.println("Enter the time you want to book for");
                int time = in.nextInt();
                Customer Cust = new Customer("default", "08748484848484");
                boolean InList = false;

                for (Customer c : customerArr) {
                    if (c.getName().equals(Name)) {
                        Cust = c;
                        InList = true;
                        Booking booking = new Booking(Cust, numberOfPeople, time);
                    }
                }
                if (!InList) {
                    Cust = new Customer(Name, Phone);//Constructor for customer checks if phone is 0
                    Booking booking = new Booking(Cust, numberOfPeople, time);
                }

                return true;
                //TODO:Better implementation of booking above(called twice)
            case "u":
                System.out.println("Enter the booking id you would like to cancel");
                int bookingId = in.nextInt();
                boolean exists = true;
                for (int i = 0; i < r.getBookings().size(); i++) {
                    if (r.getBookings().get(i).getId() == bookingId) {
                        r.getBookings().remove(i);
                        exists = true;
                        break;

                    }
                }
                if (!exists) {
                    System.out.println("Booking doesnt exist");

                }
                return true;

            case "p": //Pay
                //TODO: make a pay thingy, do we need a till. would make it easier to add up details on money earned and shit
                return true;
            case "c": //Create menu

                boolean menuing = true;
                while (menuing) {
                    System.out.println("""
                            [A] View menu
                            [B] Add item to menu
                            [C] Remove item from menu
                            [D] Clear menu
                            [E] Exit
                            """);

                    String input = in.next();
                    if (input.equalsIgnoreCase("A")) {
                        System.out.println(r.getMenu());
                    } else if (input.equalsIgnoreCase("B")) {
                        System.out.println("Enter name of item");
                        String foodName = in.next();
                        System.out.println("Enter price of item");
                        double foodPrice = in.nextDouble();
                        System.out.println("Enter type of item (starter, main, dessert, drink)");
                        String foodType = in.next();
                        r.getMenu().addFood(new FoodItem(foodName, foodPrice, foodType));
                    } else if (input.equalsIgnoreCase("C")) {
                        System.out.println("Enter name of item");
                        if (r.getMenu().removeFood(in.next())) {
                            System.out.println("Item removed from the menu");
                        } else {
                            System.out.println("Item not found");
                        }
                    } else if (input.equalsIgnoreCase("D")) {
                        r.getMenu().clearMenu();
                        System.out.println("Menu cleared");
                    } else if (input.equalsIgnoreCase("E")) {
                        menuing = false;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                }
                return true;

            case "h": //Bayan: Hire staff
                System.out.println("Enter staff name:");
                String newName = in.next();
                System.out.println("\nEnter staff password:");
                String newPass = in.next();
                System.out.println("""
                        Enter staff position:
                        [A] Manager
                        [B] Chef
                        [C] Waiter
                        [D] Exit
                        """);
                while (true) {
                    if (in.next().equalsIgnoreCase("C")) {
                        m.employStaff(new Waiter(newName, newPass, r));
                        break;
                    } else if (in.next().equalsIgnoreCase("B")) {
                        m.employStaff(new Chef(newName, newPass, r));
                        break;
                    } else if (in.next().equalsIgnoreCase("A")) {
                        m.employStaff(new Manager(newName, newPass, r));
                        break;
                    } else if (in.next().equalsIgnoreCase("D")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                }
                return true;

            case "f": //Bayan: Fire staff
                System.out.println("Enter staff name:");
                String staffName = in.next();
                ArrayList<Staff> staffArr = r.getStaff();

                for (Staff staff : staffArr) {
                    if (staff.getName().equalsIgnoreCase(staffName)) {
                        System.out.println(staff.getName() + " has been fired");
                        r.getStaff().remove(staff);
                        return true;
                    }
                }

                System.out.println("No staff found by that name");
                return true;

            case "l":
                return false;

        }
        return true;
    }

}
