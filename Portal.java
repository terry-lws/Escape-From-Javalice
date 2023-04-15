import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Portal {
    protected final String direction;
    protected int openProb;
    protected int exitProb;
    protected int policeProb;
    static Portal.NorthPortal nPortal = null;
    static Portal.SouthPortal sPortal = null;
    static Portal.EastPortal ePortal = null;
    static Portal.WestPortal wPortal = null;

    public Portal(String direction, int openProb, int exitProb, int policeProb) {
        this.direction = direction;
        this.openProb = openProb;
        this.exitProb = exitProb;
        this.policeProb = policeProb;
    }

    public static class NorthPortal extends Portal {
        public NorthPortal(String direction, int openProb, int exitProb, int policeProb) {
            super("North", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class EastPortal extends Portal {
        public EastPortal(String direction, int openProb, int exitProb, int policeProb) {
            super("East", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class SouthPortal extends Portal {
        public SouthPortal(String direction, int openProb, int exitProb, int policeProb) {
            super("South", openProb, exitProb, policeProb);
        }

        @Override
        public void usePortal() {
            calculateNewProb(exitProb);
        }
    }

    public static class WestPortal extends Portal {
        public WestPortal(String direction, int openProb, int exitProb, int policeProb) {
            super("West", openProb, exitProb, policeProb);
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

    public void setPortalOpenProbability(int openProb) {
        this.openProb = openProb;
    }

    public void setExitProbability(int exitProb) {
        this.exitProb = exitProb;
    }

    public void setPoliceProbability(int policeProb) {
        this.policeProb = policeProb;
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
                int openProb = Integer.parseInt(tokens[1]);
                int exitProb = Integer.parseInt(tokens[2]);
                int policeProb = Integer.parseInt(tokens[3]);
                switch (direction) {
                    case "North":
                        nPortal = new Portal.NorthPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "East":
                        ePortal = new Portal.EastPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "South":
                        sPortal = new Portal.SouthPortal(direction, openProb, exitProb, policeProb);
                        break;
                    case "West":
                        wPortal = new Portal.WestPortal(direction, openProb, exitProb, policeProb);
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

    public void calculateNewProb(int exitProb) {
        int exitRandomNumber;
        int policeRandomNumber;
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
        System.out.println("North | " + nPortal.getExitProbability() + " | " + nPortal.getPoliceProbability());
        System.out.println("East  | " + ePortal.getExitProbability() + " | " + ePortal.getPoliceProbability());
        System.out.println("South | " + sPortal.getExitProbability() + " | " + sPortal.getPoliceProbability());
        System.out.println("West  | " + wPortal.getExitProbability() + " | " + wPortal.getPoliceProbability());
    }

    public abstract void usePortal();
}