import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadGoal {
    public static int freshGoal = 0;


    public static void readMyGoal() throws FileNotFoundException {
        String filename = "myGoal.txt";
        Scanner scanner = new Scanner(new File(filename));

        String goal = "";
        if(scanner.hasNextLine()) {
            goal = scanner.nextLine();
        }
        freshGoal= Integer.parseInt(goal);


    }
}
