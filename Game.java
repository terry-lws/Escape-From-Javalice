import java.util.Scanner;

public class Game {

    public static boolean newTurn = true;
    public static int coins;
    public static String playerName;
    static Player player = new Player(playerName, 10, 3, 0);

    public static void main(String[] args) {

        boolean policeEncounter;

        Portal.readExits("exits.txt");
        playerName = Player.createName();
        coins = player.getCoins();
        player.setCoins(coins);
        System.out.println("Name: " + playerName + "\nCoins: " + coins);
        String choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            coins = Items.player.getCoins();
            // randomize which portal will be opened when entering a new turn
            if (newTurn) {
                Portal.randomizeOpenPortal();
                // sleep before showing options
                GenericMethods.sleep(1000);
                System.out.println("\n---------------------------------");
            }
            // set newTurn to false to prevent portal randomization on the same turn
            newTurn = false;
            System.out.println("Coins: " + coins);
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
                            policeEncounter = Portal.randomizePolicePresence(choice);
                            if (policeEncounter)
                                encounterPolice();
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("E"):
                        if (Portal.ePortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.ePortal.usePortal();
                            policeEncounter = Portal.randomizePolicePresence(choice);
                            if (policeEncounter)
                                encounterPolice();
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("S"):
                        if (Portal.sPortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.sPortal.usePortal();
                            policeEncounter = Portal.randomizePolicePresence(choice);
                            if (policeEncounter)
                                encounterPolice();
                            newTurn = true;
                        } else
                            System.out.println("This portal is closed!");
                        break;
                    case ("W"):
                        if (Portal.wPortal.portalIsOpen) {
                            System.out.println("Your selection: " + choice + " portal");
                            Portal.wPortal.usePortal();
                            policeEncounter = Portal.randomizePolicePresence(choice);
                            if (policeEncounter)
                                encounterPolice();
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
            // print portal statement
            System.out.println("Direction - Exit Probability (%), Police Encounterment Probability (%)");
            System.out.println(
                    "North - " + Portal.nPortal.getExitProbability() + ", " + Portal.nPortal.getPoliceProbability());
            System.out.println(
                    "East - " + Portal.ePortal.getExitProbability() + ", " + Portal.ePortal.getPoliceProbability());
            System.out.println(
                    "South - " + Portal.sPortal.getExitProbability() + ", " + Portal.sPortal.getPoliceProbability());
            System.out.println(
                    "West - " + Portal.wPortal.getExitProbability() + ", " + Portal.wPortal.getPoliceProbability());
        }
    }

    public static void encounterPolice() {
        System.out.println("You have " + player.cloakCount + " invisibility cloaks");
        if (player.cloakCount > 0) {
            Scanner scanner = new Scanner(System.in);
            String choice;
            boolean valid = false;
            while (valid == false) {
                System.out.println("Do you wish to use an invisibility cloak? (y/n)");
                choice = scanner.nextLine().trim().toLowerCase();
                switch (choice) {
                    case "y":
                        System.out.println("You used an invisibility cloak");
                        valid = !valid;
                        break;
                    case "n":
                        System.out.println("You didn't use the invisibility cloak");
                        valid = !valid;
                        break;
                    default:
                        System.out.println("Please answer y or n only");
                }
            }
        } else {
            System.out.println("You didn't have any invisibility cloak");
            double price = coins * Items.getBribeMultiplier();
            System.out.println("The coins you have to pay to bribe the police is " + price);
            System.out.println("You have " + coins + " coins");
            GenericMethods.sleep(1000);
            if ((double) coins >= price) {
                Scanner scanner = new Scanner(System.in);
                String choice;
                boolean valid = false;
                while (valid == false) {
                    System.out.println("Do you wish to bribe the police? (y/n)");
                    choice = scanner.nextLine().trim().toLowerCase();
                    switch (choice) {
                        case "y":
                            System.out.println("You bribed the police");
                            valid = !valid;
                            break;
                        case "n":
                            System.out.println("You didn't bribe the police");
                            valid = !valid;
                            break;
                        default:
                            System.out.println("Please answer y or n only");
                    }
                }
            } else {
                System.out.println("You didn't have enough coins");
                System.out.println("You have " + player.jumpbackCount + " jump backwards");
                if (player.jumpbackCount > 0) {
                    Scanner scanner = new Scanner(System.in);
                    String choice;
                    boolean valid = false;
                    while (valid == false) {
                        System.out.println("Do you wish to use a jump back? (y/n)");
                        System.out.println("Warning: if you decide not to use a jump back, you will loss immediately");
                        choice = scanner.nextLine().trim().toLowerCase();
                        switch (choice) {
                            case "y":
                                System.out.println("You used a jump back");
                                valid = !valid;
                                break;
                            case "n":
                                System.out
                                        .println("You ended up in jail without using a jump back, the game has ended");
                                valid = !valid;
                                break;
                            default:
                                System.out.println("Please answer y or n only");
                        }
                    }
                } else {
                    System.out.println("You ended up in jail with no jump back, the game has ended");
                }
            }
        }
    }
}