package baseSwing;

public class Item {
    private String name;
    private int count;

    public Item(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Item(String name) {
        this.name = name;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
