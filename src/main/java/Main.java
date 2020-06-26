import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    protected static String[] splitTime;


    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public static void loadTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        splitTime = formatter.format(date).split(":");

        Trigger.startTimeMinutes = ((Integer.parseInt(splitTime[0]) * 60) + Integer.parseInt(splitTime[1]));

    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        //loadTime();

        LoadMainLog.readFile();
        TextToInt.getLastSevenDays();
        TextToInt.findHours();
        TextToInt.findMinutes();
        TextToInt.roundHours();
        TextToInt.findDays();
        TextToInt.getAverageHours();
        //TextToInt.viewHours();
        //TextToInt.viewMinutes();



        this.stage = stage;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(0);
        stage.setY(0);
        stage.show();



    }
}
