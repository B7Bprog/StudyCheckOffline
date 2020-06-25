import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteAppend {
    public static void write(String filename, String text) throws IOException {
        File myFile = new File(filename);
        FileWriter myFileWriter = new FileWriter(myFile, true);
        BufferedWriter myBuff = new BufferedWriter(myFileWriter);
        myBuff.write(text);
        myBuff.close();
    }
}
