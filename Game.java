import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Portal.readExits("exits.txt");
        String playerName = Player.createName();
        Player player = new Player(playerName);
        playerName = player.getPlayerName();
        int coins = player.getCoins();
        System.out.println("Name: " + playerName + "\nCoins: " + coins);
        String choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select a portal (only enter a character)");
            System.out.println("N - North");
            System.out.println("E - East");
            System.out.println("S - South");
            System.out.println("W - West");
            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case ("N"):
                    System.out.println("Your selection: " + choice + " portal");
                    Portal.nPortal.usePortal();
                    break;
                case ("E"):
                    System.out.println("Your selection: " + choice + " portal");
                    Portal.ePortal.usePortal();
                    break;
                case ("S"):
                    System.out.println("Your selection: " + choice + " portal");
                    Portal.sPortal.usePortal();
                    break;
                case ("W"):
                    System.out.println("Your selection: " + choice + " portal");
                    Portal.wPortal.usePortal();
                    break;
                default:
                    System.out.println("No such portal, please choose again");
            }
        }
    }
}