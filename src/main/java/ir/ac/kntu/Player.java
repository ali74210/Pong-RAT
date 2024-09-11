package ir.ac.kntu;

public class Player {
    private int point;
    private String name;

    public Player(int point, String name) {
        this.point = point;
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
