import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneControllerChart implements Initializable {

    //static Stage dialogStage = new Stage();

    //String host = "86.1.51.222";
    //DatagramSocket socket = new DatagramSocket();


    static boolean checkBoxChecked = false;
    static String userName = "";
    static String messageToSend = "";
    protected static Label myDisplay;
    protected static Button mySend;
    private static Button myExit;
    private static TextField myMessage;
    private static BarChart myBarChart;
    private static CategoryAxis myAxisX;
    private static NumberAxis myAxisY;
    private static TextField myName;
    protected static Button myConnect;
    protected static Rectangle myPartnerState;
    protected static Circle mySign;
    protected static CheckBox myCheckBox;
    protected static Rectangle myMyState;
    protected static boolean currentState = false;
    private static Label myDailyAverage;

    @FXML
    private Label display;

    @FXML
    private BarChart studyChart;

    @FXML
    private Label dailyAverage;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @FXML
    private Circle sign;

    @FXML

    private Button exit;

    public SceneControllerChart() throws SocketException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //dialogStage.initStyle(StageStyle.UNDECORATED);
       // myCheckBox = checkBox;
        myDisplay = display;
        myExit = exit;
        //myPartnerState = partnerState;
        //myMyState = myState;
        mySign = sign;
        myBarChart = studyChart;
        myAxisX = x;
        myAxisY = y;
        myDailyAverage = dailyAverage;


        XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");











        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[0]  , TextToInt.hours[0]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[1]  , TextToInt.hours[1]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[2], TextToInt.hours[2]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[3]  , TextToInt.hours[3]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[4]  , TextToInt.hours[4]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[5], TextToInt.hours[5]));
        dataSeries1.getData().add(new XYChart.Data(TextToInt.days[6], TextToInt.hours[6]));


        for(Node n:myBarChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");
        }
        //second bar color
        for(Node n:myBarChart.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: green;");
        }

        myBarChart.getData().addAll(dataSeries1);




    }

    public static void setLabelOffline(){
        myDisplay.setText("");
        myDisplay.setText("Server is offline." + "\n" + "Shutdown in 50 seconds.");

    }
    public static void setDailyAverage(){
        myDailyAverage.setText(Integer.toString(TextToInt.averageDaily) + " hours.");
    }
    public void closeStage(){
        SceneController.dialogStage.hide();
    }

    public void exit() {

        System.exit(0);
    }

}
