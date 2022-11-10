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
    public void pick(){
        boolean valid = false;
        while(!valid){
            System.out.println("Enter 1 for staff, 2 for customer or 3 to quit");
            int ans = in.nextInt();
            if(ans == 1){
                staffLogin();
            }
            else if(ans == 2){
                runCustomer();
            }
            else if(ans == 3){
                System.exit(0);
            }
            else{
                System.out.println("Please try again");
            }
        }
        
    }
    
    /**
    * This method runs if a customer is using the system
    */
    public void runCustomer(){//This is required by documentation. Perhaps we can use this for walk ins??
        boolean running = true;
        
        while(running){
            System.out.println("Enter a time: ");
            int time = in.nextInt();//Need to check this
            System.out.println("Enter number of people: ");
            int num = in.nextInt();
            
            if(r.getTable(time,num)!=null){
                System.out.println("Table "+r.getTable(time,num).getTableNumber()+" is available");
                System.out.println("Enter y to book, or n otherwise");
                if(in.nextLine().toLowerCase().equals("y")){
                    System.out.println("Enter name");
                    String name = in.nextLine();
                    boolean inList=false;
                    for(Customer c: customerArr){
                        if(c.getName().equals(name)){
                            Booking b = new Booking(c,num);
                            inList=true;
                            
                        }
                        
                    }
                    if(!inList){
                        Booking b = new Booking(new Customer(name),num);
                    }
                    running=false;
                }
            }
        }
        
        
        
    }
    
    /**
    * This method runs if a staff member is using the system
    */
    public void staffLogin(){
        
        boolean running = true;
        while(running){
            boolean pass = false;
            while(!pass){
                
                System.out.println("Enter username");//WHY THE FUCK DOES THIS SKIP SOMETIMES????????
                String name = in.nextLine();
                
                System.out.println("Enter password");
                String password = in.nextLine();

                if(valid(name,password,staffArr)){
                    Staff currentStaff;
                    pass=true;
                    int index=0;
                    for (Staff s: staffArr){
                        if(s.getName().equals(name)&&s.getPassword().equals(password)){
                            index = staffArr.indexOf(s);
                            break;
                        }
                    }
                    currentStaff = staffArr.get(index);
                    running=runStaff(currentStaff);
                }
                else{
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
                String input = in.nextLine();
                running = waiter(input, (Waiter) currentStaff);

            } else if (currentStaff instanceof Chef) {
                System.out.println("V)iew orders, U)pdate order, L)og out");
                String input = in.nextLine();
                running = chef(input, (Chef) currentStaff);

            } else {//Currently manager
                System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay, C)reate menu, H)ire Staff, F)ire Staff, L)og out");
                String input = in.nextLine();
                running = manager(input, (Manager) currentStaff);

            }
        }
        return false;
    }
    
    public boolean valid(String name, String pass, ArrayList<Staff> arr){
        for(Staff s : arr){
            if(s.getName().equals(name) && s.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
    
    public boolean waiter(String str, Waiter w){
        String s = str.toLowerCase();
        switch (s){
            case "a":
            //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
            System.out.println("Enter customer name: ");
            String name = in.nextLine();
            System.out.println("Enter phone number(type 0 for walk in): ");
            String phone = in.nextLine();
            Customer cust;
            Order o;//Ronan: implementation of order and customer here is not ideal, just wanted a working version
            boolean inList=false;
            for(Customer c: customerArr){
                if(c.getName().equals(name)){
                    cust = c;
                    o= new Order(cust,r);
                    r.addOrder(o);//need to check how we are doing this in waiter
                    inList = true;
                }
                
            }
            if(!inList){
                cust  =new Customer(name,phone);//Constructor for customer checks if phone is 0
                o = new Order(cust,r);
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
            return true;

            case "p":
            return true;
            case "l":
            return false;
        }
        return true;
    }
    
    public boolean chef(String str,Chef c){
        String s = str.toLowerCase();
        switch (s){
            case "v":
            System.out.println(r.getOrders().toString());
            case "u":
            //update order
            r.getChef().isDeliverable(r.getOrders().get(0));
            case "l":
            return false;
            
        }
        return true;
    }

    public boolean manager(String str, Manager m){
        String s = str.toLowerCase();
        switch (s){
            case "a": //Add order
                //Ronan: this could(probably should) be done in waiter and then just call waiter.addOrder() here
                System.out.println("Enter customer name: ");
                String name = in.nextLine();
                System.out.println("Enter phone number(type 0 for walk in): ");
                String phone = in.nextLine();
                Customer cust;
                Order o;//Ronan: implementation of order and customer here is not ideal, just wanted a working version
                boolean inList=false;
                for(Customer c: customerArr){
                    if(c.getName().equals(name)){
                        cust = c;
                        o= new Order(cust,r);
                        r.addOrder(o);//need to check how we are doing this in waiter
                        inList = true;
                    }

                }
                if(!inList){
                    cust  =new Customer(name,phone);//Constructor for customer checks if phone is 0
                    o = new Order(cust,r);
                    r.addOrder(o);
                }

            case "r": //Remove order
                System.out.println(r.getOrders().toString());//TODO: Better implementation here
                System.out.println("Enter the order you would like to remove");

            case "v": //View orders
                System.out.println(r.getOrders().toString());

            case "t": //Take booking
                //need to show what tables are available
                System.out.println(r.getTableList()); // table list needs a to string so the manager and waiters can see what tables are available
                //take booking

                //here i used ronans stuff and added to them
                System.out.println("Enter customer name: ");
                String Name = in.nextLine();
                System.out.println("Enter phone number(type 0 for walk in): ");
                String Phone = in.nextLine();
                System.out.println("Enter the amount of people you are booking for");
                int numberOfPeople = in.nextInt();
                System.out.println("Enter the time you want to book for");
                int time = in.nextInt();
                Customer Cust = new Customer("default","08748484848484");
                boolean InList=false;

                for(Customer c: customerArr){
                    if(c.getName().equals(Name)){
                        Cust = c;
                        InList = true;
                        Booking booking = new Booking(Cust, numberOfPeople, time);
                    }
                }
                if(!InList){
                    Cust  =new Customer(Name,Phone);//Constructor for customer checks if phone is 0
                    Booking booking = new Booking(Cust, numberOfPeople, time);
                }


                //TODO:Better implementation of booking above(called twice)

            case "p": //Pay

            case "c": //Create menu
                /*System.out.println("Enter the booking id you would like to cancel");
                int bookingId = in.nextInt();
                boolean exists = true;
                for(int i = 0; i < r.getBookings().size();i++){
                    if(r.getBookings().get(i).getId() == bookingId){
                        r.getBookings().remove(i);
                        exists = true;
                        break;

                    }
                }
                if(!exists){
                    System.out.println("Booking doesnt exist");

                }*/
                while (true) {
                    System.out.println("""
                            [A] View menu
                            [B] Add item to menu
                            [C] Remove item from menu
                            [D] Clear menu
                            [E] Exit
                            """);
                    while (true) {
                        if (in.nextLine().equalsIgnoreCase("A")) {
                            System.out.println(r.getMenu());
                            break;
                        } else if (in.nextLine().equalsIgnoreCase("B")) {
                            System.out.println("Enter name of item");
                            String foodName = in.nextLine();
                            System.out.println("Enter price of item");
                            double foodPrice = in.nextDouble();
                            System.out.println("Enter type of item (starter, main, dessert, drink)");
                            String foodType = in.nextLine();
                            r.getMenu().addFood(new FoodItem(foodName, foodPrice, foodType));
                            break;
                        } else if (in.nextLine().equalsIgnoreCase("C")) {
                            System.out.println("Enter name of item");
                            if (r.getMenu().removeFood(in.nextLine())) {
                                System.out.println("Item removed from the menu");
                            } else {
                                System.out.println("Item not found");
                            }
                            break;
                        } else if (in.nextLine().equalsIgnoreCase("D")) {
                            r.getMenu().clearMenu();
                            System.out.println("Menu cleared");
                            break;
                        } else if (in.nextLine().equalsIgnoreCase("E")) {
                            break;
                        } else {
                            System.out.println("Please enter a valid input");
                        }
                    }
                }

            case "h": //Bayan: Hire staff
                System.out.println("Enter staff name:");
                String newName = in.nextLine();
                System.out.println("\nEnter staff password:");
                String newPass = in.nextLine();
                System.out.println("""
                                Enter staff position:
                                [A] Manager
                                [B] Chef
                                [C] Waiter
                                [D] Exit
                                """);
                while (true) {
                    if (in.nextLine().equalsIgnoreCase("C")) {
                        m.employStaff(new Waiter(newName, newPass, r));
                        break;
                    } else if (in.nextLine().equalsIgnoreCase("B")) {
                        m.employStaff(new Chef(newName, newPass, r));
                        break;
                    } else if (in.nextLine().equalsIgnoreCase("A")) {
                        m.employStaff(new Manager(newName, newPass, r));
                        break;
                    } else if (in.nextLine().equalsIgnoreCase("D")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                }
                return true;

            case "f": //Bayan: Fire staff
                System.out.println("Enter staff name:");
                String staffName = in.nextLine();
                ArrayList<Staff> staffArr = r.getStaff();

                for (Staff staff : staffArr) {
                    if (staff.getName().equalsIgnoreCase(staffName)) {
                        System.out.println(staff.getName() + " has been fired");
                        r.getStaff().remove(staff);
                        break;
                    }
                }

                if (staffArr.size() == r.getStaff().size()) {
                    System.out.println("No staff found by that name");
                }

                return true;

            case "l":
                return false;

        }
    return true;
}

}
