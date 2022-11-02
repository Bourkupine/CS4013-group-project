import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Interface to allow objects to read and write to .csv files.
 *
 * When using, I recommend using readFile first to store the csv file in an arraylist,
 * then updating that arraylist, then writing that arraylist back to file with writeFile.
 *
 * Also, each element in the arraylist returned from readFile and used in writeFile is one row,
 * so needs to be comma-separated before passing to method.
 * @author Ronan
 */
public interface ReadWrite {

    /**
     * This method takes a file and returns contents in a comma-separated arraylist
     * Eg: if first element in arraylist is "s1,s2", then the first cell in the csv file was s1, and the second cell in that row was s2
     *
     * @param f .csv file to read from
     * @return an ArrayList of comma-separated strings, with each element representing one row
     * @throws java.io.FileNotFoundException
     */
    public default ArrayList<String> readFile(File f)throws java.io.FileNotFoundException{

        Scanner in = new Scanner(f);
        ArrayList<String>arr=new ArrayList<>();
        while(in.hasNextLine()){
            arr.add(in.nextLine());
        }
        in.close();
        return arr;
    }

    /**
     * This method takes a file and an arraylist of comma-separated strings to write to that file
     * Eg: if first element is "s1,s2" and second element is "s3,s4", then output: A1="s1",B1="s2",A2="s3",B2="s4".
     *
     * @param f .csv file to write to
     * @param s an arraylist of comma-separated strings to write to file
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public default void writeFile(File f,ArrayList<String> s)throws java.io.FileNotFoundException, java.io.IOException{
        PrintWriter out = new PrintWriter(f);

        for(String test: s){
            out.print(test);
            out.print("\n");
        }
        out.close();
    }



}