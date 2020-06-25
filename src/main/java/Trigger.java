import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trigger extends Task {
    protected static long startTimeMinutes = 0;
    protected static long currentTimeMinutes = 0;


    @Override
    protected Object call() throws Exception {

        do {
            try {
                LoadFile.readFile();
                System.out.println("updating timaAtLoad");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = new Date();

            System.out.println("In trigger middle");

            String totalTime = Long.toString(LoadFile.timeAtLoad + /*timePassed*/ TimeCounter.counter);

            System.out.println("time at load is: " + LoadFile.timeAtLoad);
            System.out.println("Total time now is: " + totalTime);
            Thread.sleep(20);

            if (!LoadFile.splitWords2[0].equals(formatter1.format(date1))) {
                try {
                    WriteAppend.write("mainLog.txt", "\n" + LoadFile.splitWords2[0] + " Total time learned: " + (Integer.toString(Integer.parseInt(LoadFile.splitWords2[1]) / 60)) + "hour(s) and " + (Integer.toString(Integer.parseInt(LoadFile.splitWords2[1]) % 60)) + " minute(s).");
                    LoadFile.timeAtLoad = 0;
                    TimeCounter.counter = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread.sleep(500);
                try {
                    //LoadFile.timeAtLoad = 0;
                    WriteFile.write("currentLog.txt", formatter1.format(date1) + ";" + "0");
                    totalTime = "0";
                    LoadFile.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            if (SceneController.currentState) {

                if (LoadFile.splitWords2[0].equals(formatter1.format(date1))) {

                    System.out.println("Writing file");
                    WriteFile.write("currentLog.txt", formatter1.format(date1) + ";" + totalTime);
                    currentTimeMinutes = startTimeMinutes;
                }
            }


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        //FX Stuff done here
                        LoadFile.readFile();
                        SceneController.setLabelText("Today's Study Time: \n" + "------------------------" + "\n" + (Long.toString(Long.parseLong(LoadFile.splitWords2[1]) / 60)) + " hour(s) and " + (Long.toString(Long.parseLong(LoadFile.splitWords2[1]) % 60)) + " min(s)");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            });

            Thread.sleep(10000);


        } while (true);


    }

}
