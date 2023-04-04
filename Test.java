// import java.util.Scanner;

// public class Test {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String playerName;
//         while (true) {
//             System.out.print("Enter your player name (Player name must be between 3 and 12 characters long): ");
//             playerName = scanner.nextLine().trim();
//             if (playerName.length() >= 3 && playerName.length() <= 12) {
//                 System.out.println("Your player name is: " + playerName);
//                 break;
//             } else {
//                 System.out.println("Please enter a valid player name");
//             }
//         }
//         scanner.close();
//         //return playerName;
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String filePath = "prod_Flat.txt"; // Replace this with the path to your data file
        try {
            System.out.println("Sum of column 4: " + sumColumnFour(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sumColumnFour(String filePath) throws IOException {
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                int column4Value = Integer.parseInt(columns[3]);
                sum += column4Value;
            }
        }
        return sum;
    }
}