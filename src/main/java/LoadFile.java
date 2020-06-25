import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFile {
    static String[] splitWords;
    static String[] splitWords2;
    static String oneLine = "";
    static String oneLine2 = "";
    static int timeAtLoad = 0;
    static int timeAtNewLoad = 0;

    static boolean firstload = true;
    public static void readFile() throws FileNotFoundException {
        String filename = "currentLog.txt";
        Scanner scanner = new Scanner(new File(filename));
        Scanner scanner2 = new Scanner(new File(filename));
        //String oneLine;
        if(firstload) {
            while (scanner.hasNextLine()) {

                oneLine = scanner.nextLine();


                splitWords = oneLine.split(";");
                //if(timeAtLoad == 0) {
                timeAtLoad = Integer.parseInt(splitWords[1]);
                // }


            }
            firstload = false;
        }
        while (scanner2.hasNextLine()) {

            oneLine2 = scanner2.nextLine();


            splitWords2 = oneLine2.split(";");
            //if(timeAtLoad == 0) {
            timeAtNewLoad = Integer.parseInt(splitWords[1]);
            // }


        }


    }
}
