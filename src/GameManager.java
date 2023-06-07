import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

    public static void newGame(Scanner sc) throws InterruptedException {
        Printer.printTitleScreen();
        boolean checkName = false;
        String name = "";
        sc.nextLine();
        while (!checkName) {
            System.out.print("Input player's name [0 to go back]: ");
            name = sc.nextLine();
            checkName = Utility.validName(name);
        }

        if (!name.equals("0")) {
            File file = new File("save.txt");

            try {
                FileWriter writer = new FileWriter(file, true);
                String data = name + "\n";
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            File playerFile = new File(name + ".txt");

            Player player = new Player();
            player.setInitPlayer(name);

            try {
                FileWriter writer = new FileWriter(playerFile);
                String data = player.username + "\n" + player.money + "\n" + player.difficulty + "\n"
                        + player.orders_done + "\n" + player.workerlvl1 + "\n" + player.workerlvl2 + "\n"
                        + player.workerlvl3 + "\n" + player.workerlvl4 + "\n" + player.workerlvl5 + "\n" + player.toy
                        + "\n" + player.qty + "\n" + player.countdown + "\n" + player.level + "\n" + player.levelUp
                        + "\n" + player.teddyBear + "\n" + player.toyCar + "\n" + player.toyPlane + "\n" + player.rcCar
                        + "\n" + player.trainSet + "\n" + player.transformRobot + "\n" + player.levelUpCounter + "\n";
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadGame(Scanner sc) throws InterruptedException {
        File file = new File("save.txt");
        Printer.printTitleScreen();
        System.out.println("\n=================");
        System.out.println("Save Files");
        System.out.println("=================");
        int index = 0;

        try {
            Scanner scf = new Scanner(file);

            while (scf.hasNextLine()) {
                index++;
                String data = scf.nextLine();
                System.out.println(index + ". " + data);
            }
            scf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (index == 0) {
            System.out.println("No save files yet\n");
            System.out.println("Press enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        } else {
            int select = 0;
            boolean checkSelect = false;
            Player player = new Player();
            while (!checkSelect) {
                System.out.print("Please select a save file to load [0 to go back]: ");
                select = sc.nextInt();
                checkSelect = Utility.validSelect(select, index, player);
            }
            if (!(select == 0)) {
                playGame(player);
            }
        }
    }

    public static void playGame(Player player) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean gameEnded = false;
        int menu;
        ArrayList<Toy> toyList = new ArrayList<Toy>();
        Utility.setToyList(toyList);
        Printer.printTitleScreen();
        do {
            for (int j = 0; j < 100; j++) {
                System.out.println();
            }

            Printer.printCurrentOrder(player,  toyList);
            Printer.printPlayOption();
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    Printer.printToyList(player, toyList);
                    break;

                case 2:
                    GameValueManager.produceToys(player, toyList);
                    if (player.countdown == 0)
                    {
                        gameEnded = true;
                        gameEnded(player);
                    }
                    break;

                case 3:
                    GameValueManager.manageWorker(player, sc);
                    break;

                case 4:
                    saveExit(player);
                    gameEnded = true;
                    break;

                case 5:
                    String select = "";
                    boolean checkInput = false;

                    while (!checkInput) {
                        sc.nextLine();
                        System.out.print(
                                "\nAre you sure you want to end the game? (You cannot load this game after this!) [Y/N]: ");
                        select = sc.nextLine();
                        checkInput = (select.equals("Y") || select.equals("N")) ? true : false;
                    }

                    if (select.equals("Y")) {
                        gameEnded = true;
                        gameEnded(player);
                    }
                    break;
            }
        } while (!gameEnded);
    }

    public static void gameEnded(Player player) {
        Path tempFile = Paths.get("temp.txt");
        Path saveFile = Paths.get("save.txt");
        Path scoreFile = Paths.get("hiscore.txt");
        Path playerFile = Paths.get(player.username + ".txt");
        try {
            try (Writer fw = Files.newBufferedWriter(tempFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                 Writer fwscore = Files.newBufferedWriter(scoreFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                 BufferedReader br = Files.newBufferedReader(saveFile)) {
                fwscore.write(player.username + "#" + player.orders_done + "#" + player.money + "\n");
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(player.username))
                        continue;
                    fw.write(line);
                    fw.write("\n");
                }
            }

            Files.delete(saveFile);
            Files.delete(playerFile);
            Files.move(tempFile, saveFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n--Game Over--\n");
        System.out.println("Final Result");
        System.out.println("Order Done  : " + player.orders_done);
        System.out.println("Final Money : " + player.money);

        System.out.println("\nPress Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static void saveExit(Player player) {
        File playerFile = new File(player.username + ".txt");

        try {
            FileWriter writer = new FileWriter(playerFile);
            String data = player.username + "\n" + player.money + "\n" + player.difficulty + "\n" + player.orders_done
                    + "\n" + player.workerlvl1 + "\n" + player.workerlvl2 + "\n" + player.workerlvl3 + "\n"
                    + player.workerlvl4 + "\n" + player.workerlvl5 + "\n" + player.toy + "\n" + player.qty + "\n"
                    + player.countdown + "\n" + player.level + "\n" + player.levelUp + "\n" + player.teddyBear + "\n"
                    + player.toyCar + "\n" + player.toyPlane + "\n" + player.rcCar + "\n" + player.trainSet + "\n"
                    + player.transformRobot + "\n" + player.levelUpCounter + "\n";
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You finished " + player.orders_done + " orders");
        System.out.println("Your progress is successfuly saved!");

        System.out.println("\nPress Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
