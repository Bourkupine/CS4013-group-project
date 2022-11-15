import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    
    private Restaurant r;
    File bookings;
    File money;
    
    Scanner in = new Scanner(System.in);
    ArrayList<Staff> staffArr;
    ArrayList<Customer> customerArr;
    
    /**
    * creates a user interface for restaurant
    * @param r restaurant the interface belongs to
    * @author Ronan
    */
    public UserInterface(Restaurant r,String day, String month, String year) {
        
        this.r = r;
        staffArr = r.getStaff();
        customerArr = r.getCustomers();
        pick();
    }
    
    /**
    * Determines staff or customer
    * @author Ronan
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
    * @author Ronan
    */
    public void runCustomer() {//This is required by documentation. Perhaps we can use this for walk ins??
        boolean running = true;
        
        while (running) {
            System.out.println("Enter a time: ");
            int time = in.nextInt();//Need to check this
            int num = 0 ;
            boolean lessthen8 = false;
            while(lessthen8){
                System.out.println("Enter number of people: ");
                num = in.nextInt();
                if(num>8){
                    System.out.println("No more then 8 people per booking");
                }else{
                    lessthen8 = true;
                }
            }
            
            if (r.getTable(time, num) != null) {//If there is an available table
                System.out.println("Table " + r.getTable(time, num).getTableNumber() + " is available");
                System.out.println("Enter y to book, or n otherwise");
                if (in.next().toLowerCase().equals("y")) {
                    System.out.println("Enter name");
                    String name = in.next();
                    boolean inList = false;
                    for (Customer c : customerArr) {
                        if (c.getName().equals(name)) {//If customer has already visited, assign booking to them
                            Booking b = new Booking(c, num,r);
                            inList = true;
                            
                        }
                        
                    }
                    if (!inList) {//Otherwise, create new customer
                        Booking b = new Booking(new Customer(name), num,r);//Is this customer added to the array?
                    }
                    running = false;
                }
            }
        }
        
        
    }
    
    /**
    * This method runs if a staff member is using the system to allow them to login
    * @author Ronan
    */
    public void staffLogin() {
        
        boolean running = true;
        while (running) {
            boolean pass = false;
            while (!pass) {
                
                System.out.println("Enter username");
                String name = in.next();
                
                System.out.println("Enter password");
                String password = in.next();
                
                if (valid(name, password, staffArr)) {//If a valid username was entered
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
    
    /**
    * checks if a member is actually a staff member
    * @param name name of member
    * @param pass members password
    * @param arr array of staff members
    * @return true/false if they are a valid member
    * @author Ronan
    */
    public boolean valid(String name, String pass, ArrayList<Staff> arr) {
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
    public boolean waiter(String str, Waiter w) {
        String s = str.toLowerCase();
        switch (s) {
            case "a"://Add order
            //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
            System.out.println("Enter customer name: ");
            String name = in.next();
            System.out.println("Enter phone number(type 0 for walk in): ");
            String phone = in.next();
            boolean inList = false;
            for (Customer c : customerArr) {
                if (c.getName().equals(name)) {
                    r.addOrder(new Order(c, r));//need to check how we are doing this in waiter
                    inList = true;//If the customer is in the list of customers, the booking is assigned to them
                }
                
            }
            if (!inList) {
                Customer cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                r.addOrder(new Order(cust, r));//This is for new customers
            }
            return true;
            
            case "r"://Remove order
            System.out.println(r.getOrders().toString());//TODO: Better implementation here
            System.out.println("Enter the order you would like to remove");
            int remove =in.nextInt();
            r.removeOrder(r.getOrders().get(remove-1));
            System.out.println("Order" + r.getOrders().get(remove-1) + "has been removed");
            
            return true;
            
            case "v"://View current orders
            System.out.println(r.getOrders().toString());
            return true;
            case "t"://Take a booking
            //need to show what tables are available
            System.out.println(r.getTableList()); // table list needs a to string so the manager and waiters can see what tables are available
            //todo: find a way to return only available tables - also need to specify when the booking is for!!
            //take booking
            
            //here I used ronans stuff and added to them
            System.out.println("Enter customer name: ");
            name = in.next();
            System.out.println("Enter phone number(type 0 for walk in): ");
            phone = in.next();
            System.out.println("Enter the amount of people you are booking for");
            int numberOfPeople = in.nextInt();
            System.out.println("Enter the time you want to book for");
            int time = in.nextInt();
            Customer cust = new Customer("default", "08748484848484");
            inList = false;
            
            for (Customer c : customerArr) {
                if (c.getName().equals(name)) {
                    cust = c;
                    inList = true;
                    Booking booking = new Booking(cust, numberOfPeople, time,r);
                }//If customer has already visited, assigns booking to them
            }
            if (!inList) {
                cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                Booking booking = new Booking(cust, numberOfPeople, time,r);//Otherwise creates a new customer
            }
            
            return true;
            
            
            case "p"://Take payment
            
            if(r.getOrders().isEmpty()){
                System.out.println("No orders currently");
            }
            else{
                System.out.println("Select an order");
                System.out.println(r.getOrders().toString());
                int o = in.nextInt();
                Till t =new Till(r,r.getOrders().get(o));
                t.processPayment();
            }
            
            return true;
            case "l"://Log out
            
            return false;
        }
        return true;//Ronan:this is never used, perhaps change above return trues to break?
    }
    
    /**
    * This runs if a chef is logged in
    * @param str option passed from runStaff()
    * @author Thomas, Ronan
    * @param c chef who is currently logged in
    * @return false to log out, true otherwise
    */
    public boolean chef(String str, Chef c) {
        String s = str.toLowerCase();
        switch (s) {
            case "v"://View orders
            System.out.println(r.getOrders().toString());
            return true;
            case "a"://Acknowledge order (ie: cook order)
            r.getChef().cooking(r.getOrders().get(0));
            return true;
            case "u"://update order
            r.getChef().isDeliverable(r.getOrders().get(0));
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
    public boolean manager(String str, Manager m) {
        String s = str.toLowerCase();
        String name; //Bayan: defined some variables used across multiple cases here to keep naming convention consistent
        String phone;
        boolean inList;
        switch (s) {
            case "a": //Add order
            //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
            System.out.println("Enter customer name: ");
            name = in.next();
            System.out.println("Enter phone number(type 0 for walk in): ");
            phone = in.next();
            inList = false;
            for (Customer c : customerArr) {
                if (c.getName().equals(name)) {
                    r.addOrder(new Order(c, r));//need to check how we are doing this in waiter
                    inList = true;//If the customer is in the list of customers, the booking is assigned to them
                }
                
            }
            if (!inList) {
                Customer cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                r.addOrder(new Order(cust, r));//This is for new customers
            }
            return true;
            
            case "r": //Remove order
            System.out.println(r.getOrders().toString());//TODO: Better implementation here
            System.out.println("Enter the order you would like to remove");
            int remove =in.nextInt();
            r.removeOrder(r.getOrders().get(remove-1));
            System.out.println("Order" + r.getOrders().get(remove-1) + "has been removed");
            
            return true;
            
            case "v": //View orders
            System.out.println(r.getOrders().toString());
            return true;
            
            case "t": //Take booking
            //need to show what tables are available
            System.out.println(r.getTableList()); // table list needs a to string so the manager and waiters can see what tables are available
            //todo: find a way to return only available tables - also need to specify when the booking is for!!
            //take booking
            
            //here I used ronans stuff and added to them
            System.out.println("Enter customer name: ");
            name = in.next();
            System.out.println("Enter phone number(type 0 for walk in): ");
            phone = in.next();
            System.out.println("Enter the amount of people you are booking for");
            int numberOfPeople = in.nextInt();
            System.out.println("Enter the time you want to book for");
            int time = in.nextInt();
            Customer cust = new Customer("default", "08748484848484");
            inList = false;
            
            for (Customer c : customerArr) {
                if (c.getName().equals(name)) {
                    cust = c;
                    inList = true;
                    Booking booking = new Booking(cust, numberOfPeople, time,r);
                }//If customer has already visited, assigns booking to them
            }
            if (!inList) {
                cust = new Customer(name, phone);//Constructor for customer checks if phone is 0
                Booking booking = new Booking(cust, numberOfPeople, time,r);//Otherwise creates a new customer
            }
            
            return true;
            //TODO:Better implementation of booking above(called twice)
            case "u"://Undo booking
            System.out.println("Enter the booking ID you would like to cancel");
            String bookingId = in.nextLine();
            boolean exists = false;
            for (int i = 0; i < r.getBookings().size(); i++) {
                if (r.getBookings().get(i).getId().equals(bookingId)) { //TODO error with types
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
            System.out.println("Select an order");
            System.out.println(r.getOrders().toString());
            int o = in.nextInt();
            Till t =new Till(r,r.getOrders().get(o));
            t.processPayment();
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
                    name = in.next();
                    System.out.println("Enter price of item");
                    double foodPrice = in.nextDouble();
                    System.out.println("Enter type of item (starter, main, dessert, drink)");
                    String foodType = in.next();
                    r.getMenu().addFood(new FoodItem(name, foodPrice, foodType));
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
                if (in.next().equalsIgnoreCase("C")) {
                    m.employStaff(new Waiter(name, password, r));
                    break;
                } else if (in.next().equalsIgnoreCase("B")) {
                    m.employStaff(new Chef(name, password, r));
                    break;
                } else if (in.next().equalsIgnoreCase("A")) {
                    m.employStaff(new Manager(name, password, r));
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
            name = in.next();
            ArrayList<Staff> staffArr = r.getStaff();
            
            for (Staff staff : staffArr) {
                if (staff.getName().equalsIgnoreCase(name)) {
                    System.out.println(staff.getName() + " has been fired");
                    r.getStaff().remove(staff);
                    return true;
                }
            }
            
            System.out.println("No staff found by that name");
            return true;
            
            case "l"://logout
            return false;
            
        }
        return true;
    }
    
}
