import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Utility {

    public static int randomNumberGenerator(int max, int min,int extra) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + extra;
    }

    public static boolean validName(String name) {
        if (name.equals("0"))
            return true;

        File file = new File("save.txt");

        try {
            Scanner scf = new Scanner(file);

            while (scf.hasNextLine()) {
                String data = scf.nextLine();
                if (data.equals(name)) {
                    System.out.println("User with that name already exists!");
                    scf.close();
                    return false;
                }
            }
            scf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (name.length() >= 3 && name.length() <= 20)
            return true;

        System.out.println("Name must be between 3 to 20 characters!");
        return false;
    }

    public static boolean validSelect(int select, int index, Player player) {
        if (select > index)
            return false;

        File file = new File("save.txt");
        String name = "";
        try {
            Scanner scf = new Scanner(file);
            int count = 1;

            while (scf.hasNextLine()) {
                String data = scf.nextLine();
                if (select == count) {
                    name = data;
                }
                count++;
            }
            scf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File playerFile = new File(name + ".txt");
        ArrayList<String> playerData = new ArrayList<String>();

        try {
            Scanner scf = new Scanner(playerFile);

            while (scf.hasNextLine()) {
                String data = scf.nextLine();
                playerData.add(data);
            }
            scf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        player.setPlayer(playerData.get(0), Integer.parseInt(playerData.get(1)), Integer.parseInt(playerData.get(2)),
                Integer.parseInt(playerData.get(3)), Integer.parseInt(playerData.get(4)),
                Integer.parseInt(playerData.get(5)), Integer.parseInt(playerData.get(6)),
                Integer.parseInt(playerData.get(7)), Integer.parseInt(playerData.get(8)),
                Integer.parseInt(playerData.get(9)), Integer.parseInt(playerData.get(10)),
                Integer.parseInt(playerData.get(11)), Integer.parseInt(playerData.get(12)),
                Integer.parseInt(playerData.get(13)), Integer.parseInt(playerData.get(14)),
                Integer.parseInt(playerData.get(15)), Integer.parseInt(playerData.get(16)),
                Integer.parseInt(playerData.get(17)), Integer.parseInt(playerData.get(18)),
                Integer.parseInt(playerData.get(19)), Integer.parseInt(playerData.get(20)));

        return true;
    }

    public static void setToyList(ArrayList<Toy> toyList) {
        toyList.add(new Toy(1, "Teddy Bear", 2));
        toyList.add(new Toy(2, "Toy Car", 5));
        toyList.add(new Toy(3, "Toy Plane", 7));
        toyList.add(new Toy(4, "RC Car", 15));
        toyList.add(new Toy(5, "Train Set", 25));
        toyList.add(new Toy(6, "Transform Robot", 55));
    }

    public static void setWorkerList(ArrayList<Worker> workerList) {
        workerList.add(new Worker(1, "workerlvl2", 600));
        workerList.add(new Worker(2, "workerlvl3", 800));
        workerList.add(new Worker(3, "workerlvl4", 1100));
        workerList.add(new Worker(4, "workerlvl5", 1500));
    }

    public static String ToySearch(int toy, ArrayList<Toy> toyList) {
        for (Toy item : toyList) {
            if (toy == item.getId()) {
                return item.getName();
            }
        }
        return "Invalid Toy";
    }

}
