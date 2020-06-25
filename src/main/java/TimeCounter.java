import javafx.concurrent.Task;

public class TimeCounter extends Task {

    public static long counter = 0L;

    @Override
    protected Object call() throws Exception {

        while(true){
            if(SceneController.currentState){
                counter++;
            }
            Thread.sleep(60000);

        }


    }
}
