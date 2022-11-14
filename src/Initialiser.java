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
                UserInterface ui = new UserInterface(rc.getRestaurants().get(id));
                //TODO pass files to userInterface
            }
            else {
                System.out.println("Please enter an id between 0 and "+num);
            }
        }




    }
}
