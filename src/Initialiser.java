import java.io.File;
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
        boolean validNum=false;
        int num = 0;
        while(!validNum){
            System.out.println("Enter number of restaurants");
            if(in.nextInt()<=0){
                System.out.println("Must create 1 or more restaurants");
            }else{
                num = in.nextInt();
            }

        }

        System.out.println("Enter date");
        LocalDate date = LocalDate.parse(in.next());
        File[] f = files();
        RestaurantChain rc = new RestaurantChain("Yum's",num,f[0],f[1],f[2],date);//TODO: employ staff

        boolean validId=false;
        while(!validId){
            System.out.println("Enter restaurant id");
            int id = in.nextInt();
            if(id<num &&id>=0){
                validId=true;
                UserInterface ui = new UserInterface(rc.getRestaurants().get(id),date,f[1],f[2]);
            }
            else {
                System.out.println("Please enter an id between 0 and "+num);
                System.out.println("Note: 1st restaurant is id 0, 2nd is 1...");
            }
        }




    }
    public File[] files(){
        File restaurants = new File("restaurants.csv");
        File bookings = new File("bookings.csv");
        if(bookings.length()==0){
            writeFile(bookings,"ReservationId, numPeople, date, time, tableNo, customerId");
        }
        File money = new File("money.csv");
        if(money.length()==0){
            writeFile(money,"Date,Total");
        }
        File[] arr = new File[3];
        arr[0]=restaurants;
        arr[1]=bookings;
        arr[2]=money;

        return arr;
    }
}
