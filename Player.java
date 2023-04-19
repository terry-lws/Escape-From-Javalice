import java.util.Scanner;

public class Player {

    public String playerName;
    public int coins;
    public int jumpbackCount;
    public int cloakCount;

    public Player(String playerName, int coins, int jumpbackCount, int cloakCount) {
        this.playerName = playerName;
        this.coins = coins;
        this.jumpbackCount = jumpbackCount;
        this.cloakCount = cloakCount;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getCoins() {
        return this.coins;
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