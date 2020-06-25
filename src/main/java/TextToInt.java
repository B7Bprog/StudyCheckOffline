import java.util.ArrayList;

public class TextToInt {


    static ArrayList<String> lastSeven = new ArrayList<>();
    static int[] hours = new int[7];
    static int[] minutes = new int[7];
    static String[] days = new String[7];
    static int averageDaily = 0;


    public static void getLastSevenDays() {


        for (int i = (LoadMainLog.lines.size() - 7); i < LoadMainLog.lines.size(); i++) {
            lastSeven.add(LoadMainLog.lines.get(i));
        }


    }

    public static void findDays(){
        String dateSub = "";
        for (int i = 0; i < lastSeven.size(); i++) {
            String tempLine = lastSeven.get(i);
            dateSub = tempLine.substring(0,10);
            days[i] = dateSub;
        }
    }

    public static void findHours() {
        String tempHour = "";
        for (int i = 0; i < lastSeven.size(); i++) {
            String tempLine = lastSeven.get(i);
            tempHour = Character.toString(tempLine.charAt(31));
            if (tempLine.charAt(32) == '0'
                    || tempLine.charAt(32) == '1'
                    || tempLine.charAt(32) == '2'
                    || tempLine.charAt(32) == '3'
                    || tempLine.charAt(32) == '4'
                    || tempLine.charAt(32) == '5'
                    || tempLine.charAt(32) == '6'
                    || tempLine.charAt(32) == '7'
                    || tempLine.charAt(32) == '8'
                    || tempLine.charAt(32) == '9'


            ) {
                tempHour += Character.toString(tempLine.charAt(32));
            }

            //System.out.println(tempHour);

            hours[i] = Integer.parseInt(tempHour);
        }
    }
    public static void findMinutes() {
        String tempMinute = "";
        for (int i = 0; i < lastSeven.size(); i++) {
            String tempLine = lastSeven.get(i);
            tempLine = tempLine.substring(44);
            for(int j = 0; j < tempLine.length(); j++) {
                if(tempLine.charAt(j) == '0'
                        || tempLine.charAt(j) == '1'
                        || tempLine.charAt(j) == '2'
                        || tempLine.charAt(j) == '3'
                        || tempLine.charAt(j) == '4'
                        || tempLine.charAt(j) == '5'
                        || tempLine.charAt(j) == '6'
                        || tempLine.charAt(j) == '7'
                        || tempLine.charAt(j) == '8'
                        || tempLine.charAt(j) == '9'

                ) {
                    tempMinute += Character.toString(tempLine.charAt(j));
                }

            }
            /*tempMinute = Character.toString(tempLine.charAt(32));
            if (tempLine.charAt(33) == '0'
                    || tempLine.charAt(33) == '1'
                    || tempLine.charAt(33) == '2'
                    || tempLine.charAt(33) == '3'


            ) {
                tempMinute += Character.toString(tempLine.charAt(33));


            }*/


            minutes[i] = Integer.parseInt(tempMinute);
            tempMinute = "";
        }

    }

    public static void roundHours(){
        for(int i = 0; i < hours.length; i++){
            if(minutes[i] > 29){
                hours[i]++;
            }
        }
    }

    public static void getAverageHours(){
        int sum = 0;
        for(int i = 0; i < hours.length; i++){
            sum += hours[i];
        }
        System.out.println("This is sum: " + sum);
        averageDaily = Math.round((sum/7.0f));
        System.out.println("This is daily average: " + averageDaily);
    }


    public static void viewHours(){
        for(int i = 0; i < hours.length; i++){
            System.out.print(hours[i] + " ");
        }
        System.out.println();
    }
    public static void viewMinutes(){
        for(int i = 0; i < minutes.length; i++){
            System.out.print(minutes[i] + " ");
        }
        System.out.println();
    }
}

