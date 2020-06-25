import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;


//OFFLINE mod: see numbers.


public class SceneController implements Initializable {


    String host = "86.1.51.222";

    DatagramSocket socket = new DatagramSocket();
    // 1. mod: GuiMessageReceiver receiver = new GuiMessageReceiver(socket);
    //2. mod: GuiMessageSender guiMessageSender = new GuiMessageSender(socket, host);

    static boolean checkBoxChecked = false;

    protected static Label myDisplay;

    private static Button myExit;

    protected static Rectangle myPartnerState;
    protected static Circle mySign;
    protected static CheckBox myCheckBox;
    protected static Rectangle myMyState;
    protected static boolean currentState = false;



    @FXML
    private Label display;

    @FXML
    private Rectangle myState;

    @FXML
    private Rectangle partnerState;

    @FXML
    private CheckBox checkBox;



    @FXML
    private Circle sign;

    @FXML

    private Button exit;

    public SceneController() throws SocketException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myCheckBox = checkBox;
        myDisplay = display;
        myExit = exit;
        myPartnerState = partnerState;
        myMyState = myState;
        mySign = sign;


        Trigger trigger = new Trigger();
        new Thread(trigger).start();

        TimeCounter timeCounter = new TimeCounter();
        new Thread(timeCounter).start();



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

    public void exit() {
        //guiMessageSender.sendState(false);
        //guiMessageSender.sendState(false);


        System.exit(0);
    }

}
