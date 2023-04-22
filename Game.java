import java.util.Scanner;

public class Game {

    public static int price;
    public static boolean newTurn = true;
    public static String playerName;
    static Player player = new Player(playerName, 10, 3, 3, 0);
    static Items.Coins coins = new Items.Coins("Coins", 30, 10);
    static Items.MagicPoliceAlarm alarm = new Items.MagicPoliceAlarm("Magic Police Alarm", 25, 3);
    static Items.InvisibilityCloak cloak = new Items.InvisibilityCloak("Invisibility Cloak", 15);
    static Items.Coal coal = new Items.Coal("Coal", 30);

    public static void main(String[] args) {

        boolean policeEncounter;

        Portal.readExits("exits.txt");
        playerName = Player.createName();
        player.setplayerName(playerName);
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
            System.out.println("Coins: " + player.getCoins());
            System.out.println("Jumpbacks: " + player.jumpbackCount);
            System.out.println("Inventory space: " + player.inventorySpace);
            System.out.println("Cloak: " + player.cloakCount);
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
                if (!Portal.nPortal.portalIsOpen && !Portal.ePortal.portalIsOpen && !Portal.sPortal.portalIsOpen
                        && !Portal.wPortal.portalIsOpen && player.jumpbackCount > 0) {
                    System.out.println("\nAll portals are close, a jump back is used automatically");
                    player.jumpbackCount--;
                    System.out.println("You have " + player.jumpbackCount + " jump backs left\n");
                    GenericMethods.sleep(2000);
                    Portal.randomizeOpenPortal();
                } else if (!Portal.nPortal.portalIsOpen && !Portal.ePortal.portalIsOpen && !Portal.sPortal.portalIsOpen
                        && !Portal.wPortal.portalIsOpen && player.jumpbackCount <= 0) {
                    System.out.println("All portals are close and you don't have any jump backs, the game has ended");
                    GenericMethods.endGame(false);
                } else {
                    choice = scanner.nextLine().trim().toUpperCase();
                    // print statements based on user's selection
                    switch (choice) {
                        case ("N"):
                            if (Portal.nPortal.portalIsOpen) {
                                System.out.println("Your selection: " + choice + " portal");
                                Portal.nPortal.usePortal();
                                policeEncounter = Portal.randomizePolicePresence(choice);
                                if (Portal.randomizeExits(choice)) {
                                    GenericMethods.endGame(true);
                                }
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
                                if (Portal.randomizeExits(choice)) {
                                    GenericMethods.endGame(true);
                                }
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
                                if (Portal.randomizeExits(choice)) {
                                    GenericMethods.endGame(true);
                                }
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
                                if (Portal.randomizeExits(choice)) {
                                    GenericMethods.endGame(true);
                                }
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
            }

            // randomize magic box
            if (GenericMethods.randomizeOneToHundred() <= 50) {
                if (Items.foundMagicBox()) {
                    int coinsChance = coins.getprobability();
                    int alarmChance = alarm.getprobability();
                    int cloakChance = cloak.getprobability();
                    int randomNum = GenericMethods.randomizeOneToHundred();
                    System.out.println("You recieved...");
                    GenericMethods.sleep(1000);
                    if (randomNum <= coinsChance) {
                        // coins, 30%
                        System.out.println(coins.getName());
                        Items.Coins.activateCoinsItem(player);
                    } else if (randomNum <= coinsChance + alarmChance) {
                        // alarm, 25%
                        System.out.println(alarm.getName());
                        Items.MagicPoliceAlarm.activateAlarmItem();
                        System.out.println("+3% magic police encounterment probability for all portals");
                    } else if (randomNum <= coinsChance + alarmChance + cloakChance) {
                        // cloak, 15%
                        System.out.println(cloak.getName());
                        if (player.inventorySpace > 0 && player.cloakCount <= 3) {
                            player.cloakCount++;
                            player.inventorySpace--;
                        }
                    } else {
                        // coal, 30%
                        System.out.println(coal.getName());
                    }
                    GenericMethods.sleep(1000);
                }
            } else
                System.out.println("Magic box NOT found");

            // print portal and police probability
            System.out.println("\nDirection - Exit Probability (%), Police Encounterment Probability (%)");
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
        GenericMethods.sleep(1000);
        System.out.println("You have " + player.cloakCount + " invisibility cloaks");
        System.out.println("You have " + player.getCoins() + " coins");
        System.out.println("You have " + player.jumpbackCount + " jump backwards\n");
        if (Items.InvisibilityCloak.useCloak(player)) {
            return;
        } else if (Items.useBribe(player)) {
            return;
        } else if (Items.useJumpback(player)) {
            return;
        }
    }
}