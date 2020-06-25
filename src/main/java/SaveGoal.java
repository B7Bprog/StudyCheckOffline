import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGoal {


    public static void SaveMyGoal(String filename, int goal) throws IOException {
        String text = Integer.toString(goal);
        File myFile = new File(filename);
        FileWriter myFileWriter = new FileWriter(myFile);
        BufferedWriter myBuff = new BufferedWriter(myFileWriter);
        myBuff.write(text);
        myBuff.close();
    }
}
