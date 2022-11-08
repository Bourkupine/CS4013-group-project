import java.util.ArrayList;
import java.util.Scanner;

public class StaffInterface {

    private Restaurant r;

    Scanner in = new Scanner(System.in);
    public StaffInterface(Restaurant r) {
        this.r = r;
    }

    public void run(){

        ArrayList<Staff> arr = r.getStaff();


        boolean pass = false;
        while(!pass){

            System.out.println("Enter username and password");
            String str = in.nextLine();

            String[] splitted = str.split(" ");
            String name = splitted[0];
            String password = splitted[1];

            if(valid(name,password,arr)){
                Staff currentStaff;
                int index=0;
                for (Staff s: arr){
                    if(s.getName().equals(name)&&s.getPassword().equals(password)){
                        index = arr.indexOf(s);
                        break;
                    }
                }
                currentStaff = arr.get(index);
                if(currentStaff instanceof Waiter){
                    System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay");
                    String input = in.nextLine();
                    waiter(input);

                } else if (currentStaff instanceof Chef) {
                    System.out.println("V)iew orders, U)pdate order");
                    String input = in.nextLine();
                    chef(input);

                } else{//Currently manager
                    System.out.println("A)dd order, R)emove order, V)iew orders, T)ake booking, P)ay, C)reate menu, H)ire Staff, F)ire Staff");
                    String input = in.nextLine();
                    manager(input);

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

    public void waiter(String str){
        String s = str.toLowerCase();
        switch (s){
            case "a":
                System.out.println("Enter customer name: ");
                String name = in.nextLine();

        }
    }

    public void chef(String s){

    }

    public void manager(String s){

    }

}
