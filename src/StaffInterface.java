import java.util.ArrayList;
import java.util.Scanner;

public class StaffInterface {

    private Restaurant r;

    public StaffInterface(Restaurant r) {
        this.r = r;
    }

    public void run(){

        ArrayList<Staff> arr = r.getStaff();

        Scanner in = new Scanner(System.in);
        boolean pass = false;
        while(!pass){

            System.out.println("Enter username and password");
            String str = in.nextLine();

            String[] splitted = str.split(" ");
            String name = splitted[0];
            String password = splitted[1];

            if(valid(name,password,arr)){

            }
            else{
                System.out.println("Invalid username/password");
            }
        }
    }

    public static boolean valid(String name, String pass, ArrayList<Staff> arr){
        for(Staff s : arr){
            if(s.getName().equals(name) && s.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
