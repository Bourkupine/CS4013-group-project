import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Interface to allow objects to read and write to .csv files.
 *
 * When using, I recommend using readFile first to store the csv file in an arraylist,
 * then updating that arraylist, then writing that arraylist back to file with writeFile.
 * The updateFile method may also be used which does the above.
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
     */
    public default ArrayList<String> readFile(File f){

        Scanner in = null;
        try {
            in = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Error finding file");
        }
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
     */
    public default void writeFile(File f,ArrayList<String> s){
        PrintWriter out = null;
        try {
            out = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file");
        }

        for(String test: s){
            out.print(test);
            out.print("\n");
        }
        out.close();
    }

    /**
     * Takes a non-empty file and adds an arrayList of comma seperated strings to it
     * @param f file to write to
     * @param s arrayList of strings to be added to file
     */
    public default void updateFile(File f, ArrayList<String> s){
        try{
            if(f.length()==0){
                FileWriter out = new FileWriter(f,false);


                for(String s1: s){
                    out.write(s1);
                    out.write("\n");
                }
                out.close();
            }
            else{
                FileWriter out = new FileWriter(f,true);


                for(String s1: s){
                    out.append(s1);
                    out.append("\n");
                }
                out.close();
            }
        }catch (IOException ioe){
            System.out.println("Error updating file");
        }



    }

    /**
     * This method takes a file and writes a single comma-seperated string to it
     * @param f file to write to
     * @param s String to be written
     */
    public default void writeFile(File f,String s){
        PrintWriter out = null;
        try {
            out = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file");;
        }

        out.println(s);

        out.close();
    }

    /**
     * This method takes a file and updates it with a single comma-seperated string
     * @param f file to write to
     * @param s String to be written
     */
    public default void updateFile(File f, String s){
        ArrayList<String> arr = new ArrayList<>();
        arr.add(s);
        updateFile(f,arr);
    }

    /**
     * Clears a given file
     * @param f file to be cleared
     */
    public default void clearFile(File f) {
        writeFile(f,"");
    }



}