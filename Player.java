import java.util.Scanner;

public class Player{

    private String playerName;
    private int coins = 10;
    //private Room room;

    public Player(String playerName) {
        this.playerName = playerName;
        this.coins = coins;
        //this.room = null;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public static String createName() {
        Scanner scanner = new Scanner(System.in);
        String playerName;
        while (true) {
            System.out.print("Enter your player name (Player name must be between 3 and 12 characters long): ");
            playerName = scanner.nextLine().trim();
            if (playerName.length() >= 3 && playerName.length() <= 12) {
                System.out.println("Your player name is: " + playerName);
                break;
            } else {
                System.out.println("Please enter a valid player name");
            }
        }
        return playerName;
    }
}