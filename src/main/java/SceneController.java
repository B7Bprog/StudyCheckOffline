import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;


//OFFLINE mod: see numbers.


public class SceneController implements Initializable {

    static Stage dialogStage;

    //String host = "86.1.51.222";

    //DatagramSocket socket = new DatagramSocket();
    // 1. mod: GuiMessageReceiver receiver = new GuiMessageReceiver(socket);
    //2. mod: GuiMessageSender guiMessageSender = new GuiMessageSender(socket, host);

    static boolean checkBoxChecked = false;

    protected static Label myDisplay;

    private static Button myExit;
    private static Button mySave;

    protected static Rectangle myPartnerState;
    protected static Circle mySign;
    protected static CheckBox myCheckBox;
    protected static Rectangle myMyState;
    protected static Rectangle myViewChart;
    protected static boolean currentState = false;
    private static TextField myGoal;
    protected static Label myCurrentGoal;
    private static Label myPerformanceLabel;
    private static ProgressBar myProgressBar;
    static long currentTime = 0;
    protected static boolean stageIsOff = true;


    //private static int freshGoal;

    /*static {
        try {
            freshGoal = ReadGoal.readMyGoal();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    private Label performanceLabel;

    @FXML
    private Label display;

    @FXML
    private Label currentGoal;

    @FXML
    private Button save;

    @FXML
    private Rectangle myState;

    @FXML
    private Rectangle viewChart;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField goal;

    @FXML
    private Circle sign;

    @FXML
    private ProgressBar progressBar;

    @FXML

    private Button exit;

    public SceneController() throws SocketException {
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {




        myCheckBox = checkBox;
        myDisplay = display;
        myExit = exit;
        //myPartnerState = partnerState;
        myMyState = myState;
        mySign = sign;
        myViewChart = viewChart;
        mySave = save;
        myGoal = goal;
        myCurrentGoal = currentGoal;
        myPerformanceLabel = performanceLabel;
        myProgressBar = progressBar;






        Trigger trigger = new Trigger();
        new Thread(trigger).start();

        TimeCounter timeCounter = new TimeCounter();
        new Thread(timeCounter).start();

        try {
            ReadGoal.readMyGoal();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setMyCurrentGoal();
        try {
            setAveragePerformanceLabel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        //System.out.println("This is time here: " + time);
        /*try {
            int goal = getFreshGoal();
            System.out.println("This is progress parts: " + time + goal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //((((getCurrentTime() * 100) / (getFreshGoal()))) / 100)


        try {
            setProgressBar();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void setLabelText(String text){
        myDisplay.setText(text);
    }
    public static void setLabelOffline(){
        myDisplay.setText("");
        myDisplay.setText("Server is offline." + "\n" + "Shutdown in 50 seconds.");

    }



    public static void setDisplayServerOffline() throws InterruptedException {
        

    }



    public void checkBox(){

        if(checkBoxChecked){
            checkBoxChecked = false;
        }
        else{
            checkBoxChecked = true;
        }
        if(checkBoxChecked){
            myMyState.setFill(Color.GREEN);
            currentState = true;
            Main.loadTime();
            //LoadFile.timeAtLoad = 0;
            //guiMessageSender.sendState(true);

        }else{
            myMyState.setFill(Color.RED);
            currentState = false;
            //guiMessageSender.sendState(false);

        }

    }
    public void setPartnerSquareToGreen(){
        myPartnerState.setFill(Color.GREEN);

        //guiMessageSender.sendOnConfirmation();

    }
    public void setPartnerSquareToRed(){
        myPartnerState.setFill(Color.RED);

       // guiMessageSender.sendOffConfirmation();

    }

    public void popup() throws FileNotFoundException {
        if(stageIsOff) {
            dialogStage = new Stage();
            System.out.println("in popup");
            //Stage dialogStage = new Stage();
            Parent root = null;
            ReadGoal.readMyGoal();


            try {
                root = FXMLLoader.load(getClass().getResource("chart.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);

            }


            //dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);


            if (TextToInt.averageDaily < ReadGoal.freshGoal - 2) {
                scene.getStylesheets().add("chartStyleRed.css");
            } else if (TextToInt.averageDaily >= ReadGoal.freshGoal) {
                scene.getStylesheets().add("chartStyleGreen.css");
            } else {
                scene.getStylesheets().add("chartStyleOrange.css");
            }


            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.initStyle(StageStyle.UNDECORATED);

            dialogStage.show();

            SceneControllerChart.setDailyAverage();


        }
        stageIsOff = false;

    }
    public void saveGoal() throws IOException {
        SaveGoal.SaveMyGoal("myGoal.txt",Integer.parseInt(goal.getText()));
        ReadGoal.readMyGoal();
        setMyCurrentGoal();
        setAveragePerformanceLabel();
        setProgressBar();
    }
    public static void setMyCurrentGoal(){
        myCurrentGoal.setText((Integer.toString(ReadGoal.freshGoal)) +" hours/day.");
    }

    public static void setAveragePerformanceLabel() throws FileNotFoundException {
        ReadGoal.readMyGoal();
        if(TextToInt.averageDaily < ReadGoal.freshGoal-2) {
            myPerformanceLabel.setText("Very Poor!");
        }
        else if(TextToInt.averageDaily >= ReadGoal.freshGoal){
            myPerformanceLabel.setText("Excellent!");
        }
        else{
            myPerformanceLabel.setText("Weak");
        }
    }

    public static void setProgressBar() throws FileNotFoundException {
        if(getFreshGoal() != 0) {
           // System.out.println("We're in set progress bar method");
            myProgressBar.setProgress((((getCurrentTime() * 100) / (getFreshGoal()))) / 100f);

            //System.out.println("This is calculation: " + ((((getCurrentTime() * 100) / (getFreshGoal()))))/100f);


            //System.out.println("Progress value is: " + getCurrentTime() + " and " + (getFreshGoal()));
        }

    }

    public static long getCurrentTime() throws FileNotFoundException {
        LoadFile.readFile();
        return Long.parseLong(LoadFile.splitWords2[1]);

    }
    public static int getFreshGoal() throws FileNotFoundException {
        ReadGoal.readMyGoal();
        return (ReadGoal.freshGoal * 60);
    }


    public void exit() {
        //guiMessageSender.sendState(false);
        //guiMessageSender.sendState(false);


        System.exit(0);
    }

}
