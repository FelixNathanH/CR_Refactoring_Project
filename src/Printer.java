import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Printer {
    public static void printTitleScreen() {
        System.out.println("  _____            __     __         _                     __  __");
        System.out.println(" |_   _|__  _   _  \\ \\   / /_ _  ___| |_ ___  _ __ _   _  |  \\/  |");
        System.out.println("   | |/ _ \\| | | |  \\ \\ / / _` |/ __| __/ _ \\| '__| | | | | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|");
        System.out.println("   | | (_) | |_| |   \\ V / (_| | (__| || (_) | |  | |_| | | |  | | (_| | | | | (_| | (_| |  __/ |   ");
        System.out.println("   |_|\\___/ \\__, |    \\_/ \\__,_|\\___|\\__\\___/|_|   \\__, | |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   ");
        System.out.println("            |___/                                  |___/                            |___/          ");
    }
    public static void printHighScore(Scanner sc)
    {
        File file = new File("hiscore.txt");
        Printer.printTitleScreen();
        System.out.println("=================================");
        System.out.format("| %10s | %6s | %7s |\n", "Name", "Orders", "Money");
        System.out.println("=================================");

        try {
            Scanner scf = new Scanner(file);
            while (scf.hasNextLine()) {
                String data = scf.nextLine();
                String [] arrData = data.split("#", 3);
                System.out.format("| %10s | %6s | %7s |\n", arrData[0], arrData[1], arrData[2]);
            }
            System.out.println("=================================");
            scf.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save files yet\n");
        }

        System.out.println("Press enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static void printCurrentOrder(Player player,  ArrayList<Toy> toyList) {
        System.out.println();
        System.out.println("==========================");
        System.out.println("Current Order");
        System.out.println("==========================");
        System.out.println("Toy           : " + Utility.ToySearch(player.toy, toyList));
        System.out.println("Level         : " + player.level);
        System.out.println("Quantity      : " + player.qty);
        System.out.println("Time          : " + player.countdown);
        System.out.println("Difficulty    : " + player.difficulty);
        System.out.println("Money         : " + player.money);
        System.out.println("Orders Done   : " + player.orders_done);
        System.out.println("==========================\n");
    }

    public static void printPlayOption() {
        System.out.println("1. Show Toy List");
        System.out.println("2. Produce Toys");
        System.out.println("3. Manage Workers");
        System.out.println("4. Save and Exit Game");
        System.out.println("5. End Game");
        System.out.print(">> ");
    }

    public static void printMenuSelection(){
        System.out.println();
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. High Score");
        System.out.println("4. Exit");
        System.out.print(">> ");
    }

    public static void printToyList(Player player, ArrayList<Toy> toyList) {
        System.out.println("==============================================");
        System.out.format("| %11s     | %7s   | %10s   |\n", "Toy", "Price", "Quantity");
        System.out.println("==============================================");
        int[] qtyToys = player.getQtyToys();
        int index = 0;
        for (Toy toy : toyList) {
            System.out.format("| %15s | %7d   | %10d   |\n", toy.getName(), toy.getPrice(), qtyToys[index]);
            index++;
        }
        System.out.println("==============================================");
        System.out.println("\nPress Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static void printWorker(Player player) {
        System.out.println("\n\n===================");
        System.out.format("| %5s | %7s |\n", "Level", "Workers");
        System.out.println("===================");
        int[] qtyToys = player.getQtyWorker();
        for (int i = 0; i < qtyToys.length; i++) {
            System.out.format("| %5d | %7d |\n", i + 1, qtyToys[i]);
        }
        System.out.println("===================\n");
    }

    public static void printUpgradeList() {
        System.out.println("\n\n==========================");
        System.out.format("| %3s | %8s | %5s |\n", "No.", "Upgrade", "Price");
        System.out.println("==========================");
        int price = 500;
        for (int i = 1; i <= 4; i++) {
            price = price + (i * 100);
            System.out.format("| %3d | %d %s %d | %5d |\n", i, i, " -> ", i + 1, price);
        }
        System.out.println("==========================\n");
    }

}
