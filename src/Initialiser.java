import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class will be run at beginning to set everything up
 */
public class Initialiser implements ReadWrite{

    public Initialiser(){
        run();
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of restaurants");
        int num = in.nextInt();
        RestaurantChain rc = new RestaurantChain("Yum's",num);
        System.out.println("Enter date");
        LocalDate date = LocalDate.parse(in.next());

        boolean validId=false;
        while(!validId){
            System.out.println("Enter restaurant id");
            int id = in.nextInt();
            if(id<num &&id>0){
                validId=true;
                UserInterface ui = new UserInterface(rc.getRestaurants().get(id),date);
                //TODO pass files to userInterface
            }
            else {
                System.out.println("Please enter an id between 0 and "+num);
            }
        }




    }
    public File[] files(){
        File restaurants = new File("restaurants.csv");
        File bookings = new File("bookings.csv");
        if(bookings.length()==0){
            writeFile(bookings,"Fix");
        }
        File money = new File("money.csv");
        File[] arr = new File[3];
        arr[0]=restaurants;
        arr[1]=bookings;
        arr[2]=money;

        return arr;
    }
}
