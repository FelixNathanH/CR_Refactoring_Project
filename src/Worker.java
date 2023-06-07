public class Worker {
    int id;
    String name;
    int upgradePrice;

    Worker(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.upgradePrice = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public void setUpgradePrice(int upgradePrice) {
        this.upgradePrice = upgradePrice;
    }
}
