import java.util.Scanner;

public class Game {

    public static boolean newRound = true;

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
            if (newRound) {
                Portal.randomizeOpenPortal();
                System.out.println("---------------------------------");
            }
            newRound = false;
            System.out.println("Select a portal (enter one character only)");
            if (Portal.nPortal.portalIsOpen)
                System.out.println("N - North");
            else
                System.out.println("N - North (Closed)");
            if (Portal.ePortal.portalIsOpen)
                System.out.println("E - East");
            else
                System.out.println("E - East (Closed)");
            if (Portal.sPortal.portalIsOpen)
                System.out.println("S - South");
            else
                System.out.println("S - South (Closed)");
            if (Portal.wPortal.portalIsOpen)
                System.out.println("W - West");
            else
                System.out.println("W - West (Closed)");
            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case ("N"):
                    if (Portal.nPortal.portalIsOpen) {
                        System.out.println("Your selection: " + choice + " portal");
                        Portal.nPortal.usePortal();
                        Portal.randomizePolicePresence(choice);
                        newRound = true;
                    } else
                        System.out.println("This portal is closed!");
                    break;
                case ("E"):
                    if (Portal.ePortal.portalIsOpen) {
                        System.out.println("Your selection: " + choice + " portal");
                        Portal.ePortal.usePortal();
                        Portal.randomizePolicePresence(choice);
                        newRound = true;
                    } else
                        System.out.println("This portal is closed!");
                    break;
                case ("S"):
                    if (Portal.sPortal.portalIsOpen) {
                        System.out.println("Your selection: " + choice + " portal");
                        Portal.sPortal.usePortal();
                        Portal.randomizePolicePresence(choice);
                        newRound = true;
                    } else
                        System.out.println("This portal is closed!");
                    break;
                case ("W"):
                    if (Portal.wPortal.portalIsOpen) {
                        System.out.println("Your selection: " + choice + " portal");
                        Portal.wPortal.usePortal();
                        Portal.randomizePolicePresence(choice);
                        newRound = true;
                    } else
                        System.out.println("This portal is closed!");
                    break;
                default:
                    System.out.println("No such portal, please choose again");
            }
        }
    }
}