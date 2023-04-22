import java.util.Random;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class GenericMethods {

    // generate one double number
    public static int randomizeOneToHundred() {
        int randomNum = 0;
        Random rand = new Random();
        randomNum = rand.nextInt(100) + 1;
        ;
        return randomNum;
    }

    // sleep for predetermined miliseconds
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void endGame(boolean escaped) {
        if (escaped) {
            System.out.println("You escaped");
            writeOutcome(Game.player, escaped);
            System.exit(0);
        } else {
            System.out.println("You didn't escape");
            writeOutcome(Game.player, escaped);
            System.exit(0);
        }
    }

    public static void writeOutcome(Player player, boolean escaped) {

        // Specify the path of the file
        String filePath = "Outcome.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write a string to the file
            if (escaped) {
                bufferedWriter.write("You win\n");
            } else {
                bufferedWriter.write("You lose\n");
            }
            bufferedWriter.write("---------------------\n");
            bufferedWriter.write("Player name: " + player.playerName + "\n");
            bufferedWriter.write("---------------------\n");
            bufferedWriter.write("Coins left : " + player.coins + "\n");
            bufferedWriter.write("Jumpbacks left : " + player.coins + "\n");
            bufferedWriter.write("Cloaks left: " + player.cloakCount + "\n");

            // Close the buffered writer
            bufferedWriter.close();

            System.out.println("outcome.txt created for your view");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}