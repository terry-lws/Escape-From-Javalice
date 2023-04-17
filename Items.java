public class Items {

    private String name;
    private int chanceOfOccurring;

    public Items(String name, int chanceOfOccurring) {
        this.chanceOfOccurring = chanceOfOccurring;
    }

    public String getName() {
        return name;
    }

    public int chanceOfOccurring() {
        return chanceOfOccurring;
    }

    public static class Coins extends Items {

        public Coins(String name, int chanceOfOccurring) {
            super(name, chanceOfOccurring);
        }

    }

    public static class MagicPoliceAlarm extends Items {

        public MagicPoliceAlarm(String name, int chanceOfOccurring) {
            super(name, chanceOfOccurring);
        }

    }

    public static class InvisibilityCloak extends Items {
        public InvisibilityCloak(String name, int chanceOfOccurring) {
            super(name, chanceOfOccurring);
        }
    }
}