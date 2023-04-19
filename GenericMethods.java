import java.util.Random;

public class GenericMethods {

    //generate one double number
    public static int randomizeOneToHundred() {
        int randomNum = 0;
        Random rand = new Random();
        randomNum = rand.nextInt(100) + 1;
        ;
        return randomNum;
    }

    //sleep for predetermined miliseconds
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}