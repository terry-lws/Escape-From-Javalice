// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.Scanner;

public class Game {

    private static Portal.NorthPortal nPortal = new Portal.NorthPortal(null, 0, 0, 0);
    private static Portal.SouthPortal sPortal = new Portal.SouthPortal(null, 0, 0, 0);
    private static Portal.EastPortal ePortal = new Portal.EastPortal(null, 0, 0, 0);
    private static Portal.WestPortal wPortal = new Portal.WestPortal(null, 0, 0, 0);

    public static void main(String[] args) {
        Portal.readExits("exits.txt");
        //System.out.println(nPortal.getDirection());
        String playerName = Player.createName();
        Player player = new Player(playerName);
        playerName = player.getPlayerName();
        int coins = player.getCoins();
        System.out.println("Name: " + playerName + "\nCoins: " + coins);
    }
}