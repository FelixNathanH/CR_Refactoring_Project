import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameValueManager {
    public static void manageWorker(Player player, Scanner sc) {
        int menu;
        ArrayList<Worker> workerList = new ArrayList<Worker>();
        Utility.setWorkerList(workerList);
        do {
            Printer.printWorker(player);
            System.out.println("Money : " + player.money);

            System.out.println("\nManage Workers");
            System.out.println("======================");
            System.out.println("1. Hire new worker");
            System.out.println("2. Upgrade worker");
            System.out.println("3. Go back");
            System.out.print(">> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    String select;
                    Printer.printWorker(player);
                    System.out.println("Money : " + player.money);

                    System.out.print("Hire a level 1 worker for 500 [Y/N]: ");
                    sc.nextLine();
                    select = sc.nextLine();

                    if (select.equals("Y")) {
                        if (player.money < 500) {
                            System.out.println("You dont have enough money");
                        } else {
                            player.hireWorker();
                        }

                        System.out.println("\nPress Any Key To Continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                    break;

                case 2:
                    int selectUpgrade;
                    Printer.printUpgradeList();
                    System.out.print("Input which upgrade you want to buy [only applies to one worker]: ");
                    selectUpgrade = sc.nextInt();
                    Worker worker = workerList.get(selectUpgrade - 1);

                    if (player.money >= worker.getUpgradePrice()) {
                        int[] qtyWorker = player.getQtyWorker();
                        if (qtyWorker[selectUpgrade - 1] == 0)
                            System.out.println("You dont have any worker with that level");
                        else {
                            player.decreaseWorkerAmount(selectUpgrade);
                            player.increaseWorkerAmount(selectUpgrade + 1);
                            player.decreasePlayerMoney(worker.getUpgradePrice());
                        }
                    } else {
                        System.out.println("You dont have enough money");
                    }

                    System.out.println("\nPress Any Key To Continue...");
                    new java.util.Scanner(System.in).nextLine();
                    break;
            }
        } while (menu != 3);
    }

    public static void produceToys(Player player, ArrayList<Toy> toyList) throws InterruptedException {
        Random rand = new Random();
        int low = 6, high = 13, percent = 10;
        int workhour = rand.nextInt(high - low) + low;
        double produceToy = Math.floor(player.getWorkerLevel() * workhour / player.level);
        StringBuilder progress = new StringBuilder("[                    ]");
        for (int i = 1; i <= 20; i += 2) {
            for (int j = 0; j < 100; j++) {
                System.out.println();
            }
            progress.setCharAt(i, '#');
            progress.setCharAt(i + 1, '#');
            System.out.println("Producing Toys...");
            System.out.print(progress);
            System.out.println("   " + percent + "%");
            percent += 10;
            Thread.sleep(1000);
        }

        Printer.printCurrentOrder(player,  toyList);


        System.out.println("Your Factory has produced " + (int) produceToy + " " + Utility.ToySearch(player.toy, toyList) + " in " + workhour + " hours");

        player.setToyAmount(player.toy, (int) produceToy);
        int amount = player.getToyAmount(player.toy);
        Toy toyGet = toyList.get(player.toy - 1);
        player.decreaseCountdown();
        boolean orderComplete = false;

        if (amount >= player.qty)
            orderComplete = true;

        if (orderComplete) {
            int money = player.qty * toyGet.getPrice() * (100 + (player.level - 1) * 10) / 100;
            player.increasePlayerMoney(money);
            player.increaseOrderDone();
            player.setNewToyQty(player.toy, player.qty);
            player.setNewToy();
            System.out.println("You have finished an order and earned " + money);
        }

        System.out.println("\nPress Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
