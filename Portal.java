import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public abstract class Portal {
    protected final String direction;
    protected final int openProb;
    protected int exitProb;
    protected int policeProb;
    protected boolean portalIsOpen;
    static Portal.NorthPortal nPortal = null;
    static Portal.SouthPortal sPortal = null;
    static Portal.EastPortal ePortal = null;
    static Portal.WestPortal wPortal = null;

    public Portal(String direction, int openProb, int exitProb, int policeProb, boolean portalIsOpen) {
        this.direction = direction;
        this.openProb = openProb;
        this.exitProb = exitProb;
        this.policeProb = policeProb;
        this.portalIsOpen = portalIsOpen;
    }

    public static class NorthPortal extends Portal {
        public NorthPortal(String direction, int openProb, int exitProb, int policeProb,
                boolean portalIsOpen) {
            super("North", openProb, exitProb, policeProb, portalIsOpen);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class EastPortal extends Portal {
        public EastPortal(String direction, int openProb, int exitProb, int policeProb, boolean portalIsOpen) {
            super("East", openProb, exitProb, policeProb, portalIsOpen);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class SouthPortal extends Portal {
        public SouthPortal(String direction, int openProb, int exitProb, int policeProb,
                boolean portalIsOpen) {
            super("South", openProb, exitProb, policeProb, portalIsOpen);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class WestPortal extends Portal {
        public WestPortal(String direction, int openProb, int exitProb, int policeProb, boolean portalIsOpen) {
            super("West", openProb, exitProb, policeProb, portalIsOpen);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public String getDirection() {
        return this.direction;
    }

    public int getPortalOpenProbability() {
        return this.openProb;
    }

    public int getExitProbability() {
        return this.exitProb;
    }

    public int getPoliceProbability() {
        return this.policeProb;
    }

    public void setExitProbability(int exitProb) {
        this.exitProb = exitProb;
    }

    public void setPoliceProbability(int policeProb) {
        this.policeProb = policeProb;
    }

    // -------------------------Gameplay methods below-----------------------------

    public static void readExits(String fileName) {
        try {
            String line = null;
            File file = new File(fileName);
            Scanner textReader = new Scanner(file);
            while (textReader.hasNextLine()) {
                line = textReader.nextLine();
                String[] tokens = line.split(",");
                String direction = tokens[0];
                int openProb = Integer.parseInt(tokens[1]);
                int exitProb = Integer.parseInt(tokens[2]);
                int policeProb = Integer.parseInt(tokens[3]);
                switch (direction) {
                    case "North":
                        nPortal = new Portal.NorthPortal(direction, openProb, exitProb, policeProb, false);
                        break;
                    case "East":
                        ePortal = new Portal.EastPortal(direction, openProb, exitProb, policeProb, false);
                        break;
                    case "South":
                        sPortal = new Portal.SouthPortal(direction, openProb, exitProb, policeProb, false);
                        break;
                    case "West":
                        wPortal = new Portal.WestPortal(direction, openProb, exitProb, policeProb, false);
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

    public static void randomizeOpenPortal() {
        int[] portalOpenProb = { nPortal.openProb, ePortal.openProb, sPortal.openProb, wPortal.openProb };
        int[] chanceArray = new int[4];
        for (int i = 0; i < chanceArray.length; i++) {
            chanceArray[i] = GenericMethods.randomizeOneToHundred();
        }
        nPortal.portalIsOpen = (chanceArray[0] <= portalOpenProb[0]) ? true : false;
        ePortal.portalIsOpen = (chanceArray[1] <= portalOpenProb[1]) ? true : false;
        sPortal.portalIsOpen = (chanceArray[2] <= portalOpenProb[2]) ? true : false;
        wPortal.portalIsOpen = (chanceArray[3] <= portalOpenProb[3]) ? true : false;
    }

    public static boolean randomizePolicePresence(String choice) {
        switch (choice) {
            case "N":
                if (GenericMethods.randomizeOneToHundred() <= nPortal.policeProb) {
                    System.out.println("Police is here!");
                    return true;
                }
            case "E":
                if (GenericMethods.randomizeOneToHundred() <= ePortal.policeProb) {
                    System.out.println("Police is here!");
                    return true;
                }
            case "S":
                if (GenericMethods.randomizeOneToHundred() <= sPortal.policeProb) {
                    System.out.println("Police is here!");
                    return true;
                }
            case "W":
                if (GenericMethods.randomizeOneToHundred() <= wPortal.policeProb) {
                    System.out.println("Police is here!");
                    return true;
                }
            default:
                return false;
        }
    }

    public void calculateNewProb(int exitProb) {
        int exitRandomNumber, policeRandomNumber;
        do {
            exitRandomNumber = (int) (Math.random() * 11) - 5;
        } while (exitRandomNumber == 0);
        int newExitProb = exitRandomNumber + exitProb;
        if (newExitProb < 0) {
            setExitProbability(0);
        } else if (newExitProb > 100) {
            setExitProbability(100);
        } else
            setExitProbability(newExitProb);

        do {
            policeRandomNumber = (int) (Math.random() * 11) - 5;
        } while (policeRandomNumber == 0);
        int newPoliceProb = policeRandomNumber + policeProb;
        if (newPoliceProb < 0) {
            setPoliceProbability(0);
        } else if (newPoliceProb > 100) {
            setPoliceProbability(100);
        } else
            setPoliceProbability(newPoliceProb);
    }

    public abstract void usePortal();
}