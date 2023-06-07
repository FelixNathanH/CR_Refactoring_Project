import jdk.jshell.execution.Util;

import java.util.Random;

public class Player {
    String username;
    int money;
    int difficulty;
    int orders_done;
    int workerlvl1;
    int workerlvl2;
    int workerlvl3;
    int workerlvl4;
    int workerlvl5;
    int toy;
    int countdown;
    int level;
    int qty;
    int levelUp;
    int teddyBear;
    int toyCar;
    int toyPlane;
    int rcCar;
    int trainSet;
    int transformRobot;
    int levelUpCounter;

    void hireWorker() {
        this.workerlvl1++;
        this.money -= 500;
    }

    void increasePlayerMoney(int money) {
        this.money += money;
    }

    void decreasePlayerMoney(int money) {
        this.money -= money;
    }

    void setNewToyQty(int id, int amount) {
        if (id == 1)
            this.teddyBear -= this.qty;
        else if (id == 2)
            this.toyCar -= this.qty;
        else if (id == 3)
            this.toyPlane -= this.qty;
        else if (id == 4)
            this.rcCar -= this.qty;
        else if (id == 5)
            this.trainSet -= this.qty;
        else if (id == 6)
            this.transformRobot -= this.qty;
    }

    void increaseOrderDone() {
        this.orders_done++;
    }

    void setToyAmount(int id, int amount) {
        if (id == 1)
            this.teddyBear += amount;
        else if (id == 2)
            this.toyCar += amount;
        else if (id == 3)
            this.toyPlane += amount;
        else if (id == 4)
            this.rcCar += amount;
        else if (id == 5)
            this.trainSet += amount;
        else if (id == 6)
            this.transformRobot += amount;
    }

    void decreaseWorkerAmount(int id) {
        if (id == 1)
            this.workerlvl1--;
        else if (id == 2)
            this.workerlvl2--;
        else if (id == 3)
            this.workerlvl3--;
        else if (id == 4)
            this.workerlvl4--;
        else if (id == 5)
            this.workerlvl5--;
    }

    void increaseWorkerAmount(int id) {
        if (id == 1)
            this.workerlvl1++;
        else if (id == 2)
            this.workerlvl2++;
        else if (id == 3)
            this.workerlvl3++;
        else if (id == 4)
            this.workerlvl4++;
        else if (id == 5)
            this.workerlvl5++;
    }

    int getToyAmount(int id) {
        if (id == 1)
            return this.teddyBear;
        else if (id == 2)
            return this.toyCar;
        else if (id == 3)
            return this.toyPlane;
        else if (id == 4)
            return this.rcCar;
        else if (id == 5)
            return this.trainSet;
        else if (id == 6)
            return this.transformRobot;
        return -1;
    }

    void decreaseCountdown() {
        this.countdown--;
    }

    int getWorkerLevel() {
        int lvl1 = 1 * this.workerlvl1;
        int lvl2 = 2 * this.workerlvl2;
        int lvl3 = 3 * this.workerlvl3;
        int lvl4 = 4 * this.workerlvl4;
        int lvl5 = 5 * this.workerlvl5;

        return lvl1 + lvl2 + lvl3 + lvl4 + lvl5;
    }

    int[] getQtyWorker() {
        int[] qtyWorker = { this.workerlvl1, this.workerlvl2, this.workerlvl3, this.workerlvl4, this.workerlvl5 };
        return qtyWorker;
    }

    int[] getQtyToys() {
        int[] qtyToys = { this.teddyBear, this.toyCar, this.toyPlane, this.rcCar, this.trainSet, this.transformRobot };
        return qtyToys;
    }

    int genQty(int difficulty) {
        int u = Utility.randomNumberGenerator(difficulty, 0, 0);
        int d = Utility.randomNumberGenerator((difficulty + 55), 1, 100);
        return u + d;
    }

    int sumAllWorker() {
        return this.workerlvl1 + this.workerlvl2 + this.workerlvl3 + this.workerlvl4 + this.workerlvl5;
    }

    int genCountdown(int difficulty) {
        int randomDiff = Utility.randomNumberGenerator(difficulty,1,0);
        int secRandom = randomDiff == 0 ? 1 : randomDiff;
        int total = (this.qty / sumAllWorker()) / secRandom;

        return total;
    }

    int genLevel(int difficulty) {
        int level = Utility.randomNumberGenerator(difficulty,1,0);
        int levRandom = level == 0 ? 1 : level;
        return levRandom;
    }

    int setNewLevelUpTarget(int level) {
        if (level == 1 || level == 2) return 3;
        return (int)(setNewLevelUpTarget(level - 1) + setNewLevelUpTarget(level - 2) + (0.5 * Math.floor(setNewLevelUpTarget(level - 1))));
    }

    int generateLevelUp() {
        this.levelUpCounter++;

        if (this.levelUpCounter == this.levelUp)
        {
            this.levelUpCounter = 0;
            this.difficulty++;
            return setNewLevelUpTarget(this.difficulty);
        }

        return this.levelUp;
    }

    void setInitPlayer(String username) {
        this.username = username;
        this.money = 0;
        this.difficulty = 1;
        this.orders_done = 0;
        this.workerlvl1 = 5;
        this.workerlvl2 = 0;
        this.workerlvl3 = 0;
        this.workerlvl4 = 0;
        this.workerlvl5 = 0;
        this.toy = (int) (Math.random() * (6 - 1 + 1) + 1);
        this.qty = genQty(this.difficulty);
        this.countdown = genCountdown(this.difficulty);
        this.level = genLevel(this.difficulty);
        this.levelUp = 3;
        this.teddyBear = 0;
        this.toyCar = 0;
        this.toyPlane = 0;
        this.rcCar = 0;
        this.trainSet = 0;
        this.transformRobot = 0;
        this.levelUpCounter = 0;
    }

    void setNewToy() {
        this.toy = (int) (Math.random() * (6 - 1 + 1) + 1);
        this.qty = genQty(this.difficulty);
        this.countdown = genCountdown(this.difficulty);
        this.level = genLevel(this.difficulty);
        this.levelUp = generateLevelUp();
    }

    void setPlayer(
            String username,
            int money, int difficulty, int orders_done,
            int workerlvl1, int workerlvl2, int workerlvl3, int workerlvl4, int workerlvl5,
            int toy, int qty, int countdown, int level,
            int levelUp, int teddyBear, int toyCar, int toyPlane,
            int rcCar, int trainSet, int transformRobot, int levelUpCounter
    ){
        this.username = username;
        this.money = money;
        this.difficulty = difficulty;
        this.orders_done = orders_done;
        this.workerlvl1 = workerlvl1;
        this.workerlvl2 = workerlvl2;
        this.workerlvl3 = workerlvl3;
        this.workerlvl4 = workerlvl4;
        this.workerlvl5 = workerlvl5;
        this.toy = toy;
        this.qty = qty;
        this.countdown = countdown;
        this.level = level;
        this.levelUp = levelUp;
        this.teddyBear = teddyBear;
        this.toyCar = toyCar;
        this.toyPlane = toyPlane;
        this.rcCar = rcCar;
        this.trainSet = trainSet;
        this.transformRobot = transformRobot;
        this.levelUpCounter = levelUpCounter;
    }

}
