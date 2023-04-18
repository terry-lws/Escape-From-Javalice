import java.util.Scanner;

public class Game {

    public static boolean newTurn = true;
    public static int coins = 10;
    public static String playerName;
    public static int jumpbackCount = 3;

    public static void main(String[] args) {
        Portal.readExits("exits.txt");
        playerName = Player.createName();
        Player player = new Player(playerName, coins, jumpbackCount);
        coins = player.getCoins();
        player.setCoins(coins);
        System.out.println("Name: " + playerName + "\nCoins: " + coins);
        String choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // randomize which portal will be opened when entering a new turn
            if (newTurn) {
                Portal.randomizeOpenPortal();
                // sleep before showing options
                GenericMethods.sleep(1000);
                System.out.println("\n---------------------------------");
            }
            // set newTurn to false to prevent portal randomization on the same turn
            newTurn = false;
            // display closed and available portals and prompt user to select a portal
            while (newTurn == false) {
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
                // print statements based on user's selection
                switch (choice) {
                    case ("N"):
                        if (Portal.nPortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.nPortal.usePortal();
                            Portal.randomizePolicePresence(choice);
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("E"):
                        if (Portal.ePortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.ePortal.usePortal();
                            Portal.randomizePolicePresence(choice);
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("S"):
                        if (Portal.sPortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.sPortal.usePortal();
                            Portal.randomizePolicePresence(choice);
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("W"):
                        if (Portal.wPortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.wPortal.usePortal();
                            Portal.randomizePolicePresence(choice);
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    default:
                        System.out.println("No such portal, please choose again");
                }
            }
            // randomize magic box
            Items.randomizeMagicBox();
            System.out.println("Direction - Exit Probability (%), Police Encounterment Probability (%)");
            System.out.println("North - " + Portal.nPortal.getExitProbability() + ", " + Portal.nPortal.getPoliceProbability());
            System.out.println("East - " + Portal.ePortal.getExitProbability() + ", " + Portal.ePortal.getPoliceProbability());
            System.out.println("South - " + Portal.sPortal.getExitProbability() + ", " + Portal.sPortal.getPoliceProbability());
            System.out.println("West - " + Portal.wPortal.getExitProbability() + ", " + Portal.wPortal.getPoliceProbability());
        }
    }
}