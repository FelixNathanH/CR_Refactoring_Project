import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int menu;
        do {
            Printer.printTitleScreen();
            Printer.printMenuSelection();;
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    GameManager.newGame(sc);
                    break;

                case 2:
                    GameManager.loadGame(sc);
                    break;

                case 3:
                    Printer.printHighScore(sc);
                    break;
            }
        } while (menu != 4);
    }
}
