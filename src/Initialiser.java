import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class will be run at beginning to set everything up
 */
public class Initialiser {

    public Initialiser(){
        run();
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of restaurants");
        int num = in.nextInt();
        RestaurantChain rc = new RestaurantChain("Yum's",num);
        System.out.println("Enter date");
        String[] split = in.next().split("/");
        String day =split[0];
        String month = split[1];
        String year = split[2];

        boolean validId=false;
        while(!validId){
            System.out.println("Enter restaurant id");
            int id = in.nextInt();
            if(id<num &&id>0){
                validId=true;
                UserInterface ui = new UserInterface(rc.getRestaurants().get(id),day,month,year);
                //TODO pass files to userInterface
            }
            else {
                System.out.println("Please enter an id between 0 and "+num);
            }
        }




    }
    public static File[] files(){
        File restaurants = new File("restaurants.csv");
        File bookings = new File("bookings.csv");
        File money = new File("money.csv");
        File[] arr = new File[3];
        arr[0]=restaurants;
        arr[1]=bookings;
        arr[2]=money;

        return arr;
    }
}
