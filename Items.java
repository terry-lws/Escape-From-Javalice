import java.util.Scanner;
import java.util.Random;

public abstract class Items {

    protected String name;
    protected int chanceOfOccurring;
    protected String description;
    static Items.Coins coins = new Items.Coins("Coins", 30, 10);
    static Items.MagicPoliceAlarm alarm = new Items.MagicPoliceAlarm("Magic Police Alarm", 25, 3);
    static Items.InvisibilityCloak cloak = new Items.InvisibilityCloak("Invisibility Cloak", 15, 1);
    static Items.Coal coal = new Items.Coal("Coal", 30);

    public Items(String name, int chanceOfOccurring) {
        this.name = name;
        this.chanceOfOccurring = chanceOfOccurring;
    }

    public static class Coins extends Items {

        private int value;

        public Coins(String name, int chanceOfOccurring, int value) {
            super(name, chanceOfOccurring);
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class MagicPoliceAlarm extends Items {
        public MagicPoliceAlarm(String name, int chanceOfOccurring, int raisedPercentage) {
            super(name, chanceOfOccurring);
        }
    }

    public static class InvisibilityCloak extends Items {

        private int space = 1;

        public InvisibilityCloak(String name, int chanceOfOccurring, int space) {
            super(name, chanceOfOccurring);
            this.space = space;
        }

        public int getSpace() {
            return this.space;
        }
    }

    public static class Coal extends Items {
        public Coal(String name, int chanceOfOccurring) {
            super(name, chanceOfOccurring);
        }
    }

    public String getName() {
        return this.name;
    }

    public int getChanceOfOccurring() {
        return this.chanceOfOccurring;
    }

    public static void randomizeMagicBox() {
        if (GenericMethods.randomizeOneToHundred() <= 50) {
            System.out.println("Magic box found!");
            Scanner scanner = new Scanner(System.in);
            String choice;
            boolean valid = false;
            while (valid == false) {
                System.out.println("Open the box? (y/n)");
                choice = scanner.nextLine().trim().toLowerCase();
                switch (choice) {
                    case "y":
                        System.out.println("You opened the box");
                        GenericMethods.sleep(1000);
                        randomizeMagicBoxItems();
                        valid = !valid;
                        break;
                    case "n":
                        System.out.println("You didn't open the box");
                        valid = !valid;
                        break;
                    default:
                        System.out.println("Please answer y or n only");
                }
            }
        } else
            System.out.println("Magic box NOT found");
    }

    public static void randomizeMagicBoxItems() {
        int coinsChance = coins.getChanceOfOccurring();
        int alarmChance = alarm.getChanceOfOccurring();
        int cloakChance = cloak.getChanceOfOccurring();
        int randomNum = GenericMethods.randomizeOneToHundred();
        System.out.println("You recieved...");
        GenericMethods.sleep(500);
        if (randomNum <= coinsChance) {
            // coins, 30%
            System.out.println(coins.getName());
            activateCoinsItem();
        } else if (randomNum <= coinsChance + alarmChance) {
            // alarm, 25%
            System.out.println(alarm.getName());
            activateAlarmItem();
        } else if (randomNum <= coinsChance + alarmChance + cloakChance) {
            // cloak, 15%
            System.out.println(cloak.getName());
        } else {
            // coal, 30%
            System.out.println(coal.getName());
        }
    }

    public static void activateCoinsItem() {
        // Player player = new Player(Game.playerName, Game.coins);
        // Random rand = new Random();
        // int randomNum = rand.nextInt(26) + 10;
        // System.out.println(randomNum);
        // System.out.println(randomNum + player.coins);
        // player.setCoins(randomNum + player.coins);
    }

    public static void activateAlarmItem() {
        Portal.nPortal.setPoliceProbability(Portal.nPortal.getPoliceProbability() + 3);
        Portal.ePortal.setPoliceProbability(Portal.ePortal.getPoliceProbability() + 3);
        Portal.sPortal.setPoliceProbability(Portal.sPortal.getPoliceProbability() + 3);
        Portal.wPortal.setPoliceProbability(Portal.wPortal.getPoliceProbability() + 3);
    }
}