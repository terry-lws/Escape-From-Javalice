import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Portal {
    protected String direction;
    protected double openProb;
    protected double exitProb;
    protected double policeProb;

    public Portal(String direction, double openProb, double exitProb, double policeProb) {
        this.direction = direction;
        this.openProb = openProb;
        this.exitProb = exitProb;
        this.policeProb = policeProb;
    }

    public static class NorthPortal extends Portal {
        public NorthPortal(String direction, double openProb, double exitProb, double policeProb) {
            super("North", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            System.out.println("North");
        }
    }

    public static class SouthPortal extends Portal {
        public SouthPortal(String direction, double openProb, double exitProb, double policeProb) {
            super("South", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            System.out.println("South");
        }
    }

    public static class EastPortal extends Portal {
        public EastPortal(String direction, double openProb, double exitProb, double policeProb) {
            super("East", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            System.out.println("East");
        }
    }

    public static class WestPortal extends Portal {
        public WestPortal(String direction, double openProb, double exitProb, double policeProb) {
            super("West", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            System.out.println("West");
        }
    }

    public String getDirection() {
        return this.direction;
    }

    public double getPortalOpenProbability() {
        return this.openProb;
    }

    public double getExitProbability() {
        return this.exitProb;
    }

    public double getMagicPoliceEncounterProbability() {
        return this.policeProb;
    }

    public static void readExits(String fileName) {
        try {
            String line = null;
            File file = new File(fileName);
            Scanner textReader = new Scanner(file);
            while (textReader.hasNextLine()) {
                line = textReader.nextLine();
                String[] tokens = line.split(",");
                String direction = tokens[0];
                Double openProb = Double.parseDouble(tokens[1]);
                Double exitProb = Double.parseDouble(tokens[2]);
                Double policeProb = Double.parseDouble(tokens[3]);
                switch (direction) {
                    case "North":
                        Portal.NorthPortal nPortal = new Portal.NorthPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "South":
                        Portal.SouthPortal sPortal = new Portal.SouthPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "East":
                        Portal.EastPortal ePortal = new Portal.EastPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "West":
                        Portal.WestPortal wPortal = new Portal.WestPortal(direction, openProb, exitProb, policeProb);
                        break;
                    default:
                        System.out.println("No such direction");
                }
            }
            textReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public abstract void usePortal();
}