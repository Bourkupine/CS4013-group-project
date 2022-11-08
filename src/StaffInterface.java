import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffInterface {

    private Restaurant r;

    Scanner in = new Scanner(System.in);
    ArrayList<Staff> staffArr = r.getStaff();
    ArrayList<Customer> customerArr = r.getCustomers();

    public StaffInterface(Restaurant r) {
        this.r = r;
    }

    /**
     * Determines staff or customer
     */
    public void pick(){
        boolean valid = false;
        while(!valid){
            System.out.println("Enter one for staff or two for customer");
            int ans = in.nextInt();
            if(ans == 1){
                runStaff();
            }
            else if(ans == 2){
                runCustomer();
            }
            else{
                System.out.println("Please try again");
            }
        }

    }

    /**
     * This method runs if a customer is using the system
     */
    public void runCustomer(){//This is required by documentation. Perhaps we can use this for walk ins?
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
    public void runStaff(){



        boolean pass = false;
        while(!pass){

            System.out.println("Enter username and password");
            String str = in.nextLine();

            String[] splitted = str.split(" ");
            String name = splitted[0];
            String password = splitted[1];

            if(valid(name,password,staffArr)){
                Staff currentStaff;
                int index=0;
                for (Staff s: staffArr){
                    if(s.getName().equals(name)&&s.getPassword().equals(password)){
                        index = staffArr.indexOf(s);
                        break;
                    }
                }
                currentStaff = staffArr.get(index);//TODO add quit options
                if(currentStaff instanceof Waiter){
                    System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay");
                    String input = in.nextLine();
                    waiter(input,(Waiter)currentStaff);

                } else if (currentStaff instanceof Chef) {
                    System.out.println("V)iew orders, U)pdate order");
                    String input = in.nextLine();
                    chef(input,(Chef)currentStaff);

                } else{//Currently manager
                    System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay, C)reate menu, H)ire Staff, F)ire Staff");
                    String input = in.nextLine();
                    manager(input,(Manager)currentStaff);

                }

            }
            else{
                System.out.println("Invalid username/password");
            }
        }
    }

    public boolean valid(String name, String pass, ArrayList<Staff> arr){
        for(Staff s : arr){
            if(s.getName().equals(name) && s.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public void waiter(String str, Waiter w){
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
                        r.addOrder(o);
                        inList = true;
                    }

                }
                if(!inList){
                    cust  =new Customer(name,phone);//Constructor for customer checks if phone is 0
                    o = new Order(cust,r);
                    r.addOrder(o);
                }

            case "r":
                System.out.println(r.getOrders().toString());//TODO: Better implementation here
                System.out.println("Enter the order you would like to remove");

            case "v":

            case "t":
                //w.takeBooking();
            case "p":

        }
    }

    public void chef(String str,Chef c){
        String s = str.toLowerCase();
        switch (s){
            case "v":

            case "u":

        }

    }

    public void manager(String str, Manager m){
        String s = str.toLowerCase();
        switch (s){
            case "a":

            case "r":

            case "v":

            case "t":

            case "p":

            case "c":

            case "h":

            case "f":

        }
    }

}
