import java.util.Scanner;
import java.util.Random;

public abstract class Items {

    protected String name;
    protected int probability;
    protected String description;
    // static Items.Coins coins = new Items.Coins("Coins", 30, 10);
    // static Items.MagicPoliceAlarm alarm = new Items.MagicPoliceAlarm("Magic
    // Police Alarm", 25, 3);
    // static Items.InvisibilityCloak cloak = new
    // Items.InvisibilityCloak("Invisibility Cloak", 15, 1);
    // static Items.Coal coal = new Items.Coal("Coal", 30);

    public Items(String name, int probability) {
        this.name = name;
        this.probability = probability;
    }

    public static class Coins extends Items {

        private int value;

        public Coins(String name, int probability, int value) {
            super(name, probability);
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static void activateCoinsItem(Player player) {
            Random rand = new Random();
            int newCoins = rand.nextInt(26) + 10;
            System.out.println("You get " + newCoins + " coins");
            player.setCoins(newCoins + player.getCoins());
            System.out.println("You now have " + player.getCoins() + " coins");
        }
    }

    public static class MagicPoliceAlarm extends Items {
        public MagicPoliceAlarm(String name, int probability, int raisedPercentage) {
            super(name, probability);
        }

        public static void activateAlarmItem() {
            Portal.nPortal.setPoliceProbability(Portal.nPortal.getPoliceProbability() + 3);
            Portal.ePortal.setPoliceProbability(Portal.ePortal.getPoliceProbability() + 3);
            Portal.sPortal.setPoliceProbability(Portal.sPortal.getPoliceProbability() + 3);
            Portal.wPortal.setPoliceProbability(Portal.wPortal.getPoliceProbability() + 3);
        }
    }

    public static class InvisibilityCloak extends Items {

        public InvisibilityCloak(String name, int probability) {
            super(name, probability);
        }

        public static boolean useCloak(Player player) {
            if (player.cloakCount > 0) {
                Scanner scanner = new Scanner(System.in);
                String choice;
                while (true) {
                    System.out.println("Do you wish to use an invisibility cloak? (y/n)");
                    choice = scanner.nextLine().trim().toLowerCase();
                    switch (choice) {
                        case "y":
                            System.out.println("You used an invisibility cloak");
                            player.cloakCount--;
                            player.inventorySpace++;
                            System.out.println("You have " + player.cloakCount + " invisibility cloak left");
                            return true;
                        case "n":
                            System.out.println("You didn't use the invisibility cloak");
                            return false;
                        default:
                            System.out.println("Please answer y or n only");
                    }
                }
            }
            System.out.println("You don't have any invisibility cloaks\n");
            return false;
        }
    }

    public static class Coal extends Items {

        public Coal(String name, int probability) {
            super(name, probability);
        }
    }

    public String getName() {
        return this.name;
    }

    public int getprobability() {
        return this.probability;
    }

    public static boolean foundMagicBox() {
        System.out.println("Magic box found!");
        Scanner scanner = new Scanner(System.in);
        String choice;
        while (true) {
            System.out.println("Open the box? (y/n)");
            choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "y":
                    System.out.println("You opened the box");
                    GenericMethods.sleep(1000);
                    return true;
                case "n":
                    System.out.println("You didn't open the box");
                    return false;
                default:
                    System.out.println("Please answer y or n only");
            }
        }
    }

    // -------------------------Magic box methods below-----------------------------

    // public static void randomizeMagicBoxItems() {
    // int coinsChance = coins.getprobability();
    // int alarmChance = alarm.getprobability();
    // int cloakChance = cloak.getprobability();
    // int randomNum = GenericMethods.randomizeOneToHundred();
    // System.out.println("You recieved...");
    // GenericMethods.sleep(500);
    // if (randomNum <= 100) {
    // // coins, 30%
    // System.out.println(coins.getName());
    // Coins.activateCoinsItem(player);
    // } else if (randomNum <= coinsChance + alarmChance) {
    // // alarm, 25%
    // System.out.println(alarm.getName());
    // MagicPoliceAlarm.activateAlarmItem();
    // } else if (randomNum <= coinsChance + alarmChance + cloakChance) {
    // // cloak, 15%
    // System.out.println(cloak.getName());
    // } else {
    // // coal, 30%
    // System.out.println(coal.getName());
    // }
    // }

    // -------------------------Other actions methods below-----------------------------

    public static double getBribeMultiplier() {
        Random rand = new Random();
        int randomNum = rand.nextInt(11) + 5;
        double multiplier = (double) randomNum;
        multiplier = multiplier / 10;
        return multiplier;
    }

    public static boolean useBribe(Player player) {
        int price = (int) Math.floor(player.getCoins() * Items.getBribeMultiplier());
        System.out.println("The coins you have to pay to bribe the police is " + price);
        if (player.getCoins() >= price && player.getCoins() != 0) {
            Scanner scanner = new Scanner(System.in);
            String choice;
            while (true) {
                System.out.println("Do you wish to bribe the police? (y/n)");
                choice = scanner.nextLine().trim().toLowerCase();
                switch (choice) {
                    case "y":
                        System.out.println("You bribed the police");
                        player.setCoins(player.getCoins() - price);
                        System.out.println("You have " + player.getCoins() + " coins left");
                        return true;
                    case "n":
                        System.out.println("You didn't bribe the police");
                        System.out.println("You get caught\n");
                        return false;
                    default:
                        System.out.println("Please answer y or n only");
                }
            }
        }
        if (player.getCoins() == 0) {
            System.out.println("You have no coins");
            System.out.println("You are caught\n");
        } else {
            System.out.println("You don't have enough coins to bribe");
            System.out.println("You are caught\n");
        }
        return false;
    }

    public static boolean useJumpback(Player player) {
        if (player.jumpbackCount > 0) {
            System.out.println("You land in jail, a jump back is used automatically\n");
            player.jumpbackCount--;
            System.out.println("You have " + player.jumpbackCount + " jump backs left");
            GenericMethods.sleep(2000);
            return true;
        } else {
            System.out.println("You are in jail and don't have any jump backs left, the game has ended");
            GenericMethods.endGame(false);
            return false;
        }
    }
    
}