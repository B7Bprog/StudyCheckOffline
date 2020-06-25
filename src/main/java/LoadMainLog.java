import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadMainLog {


    static String[] splitWords;
    static String[] splitWords2;
    static String oneLine = "";
    static String oneLine2 = "";
    static int timeAtLoad = 0;
    static int timeAtNewLoad = 0;
    static boolean firstload = true;
    static public ArrayList<String> lines = new ArrayList<>();


    public static void readFile() throws FileNotFoundException {
        String filename = "mainLog.txt";
        Scanner scanner = new Scanner(new File(filename));



            while (scanner.hasNextLine()) {

                oneLine = scanner.nextLine();
                lines.add(oneLine);





            }

        }



    }

