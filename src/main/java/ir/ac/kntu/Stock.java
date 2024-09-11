package ir.ac.kntu;

public class Stock {

    public Stock(String type) {
        this.type = type;
        if (type == "yellow"){
            this.lenght = 1.0;
            this.speed = 6.0;
        } else {
            this.lenght = 1.5 ;
            this.speed = 3.0 ;
        }
    }

    private Double speed;
    private String type;
    private Double lenght;
    private Double topY;
    private Double bottomY;
    private Double centerY;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLenght() {
        return lenght;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }

    public Double getTopY() {
        return topY;
    }

    public void setTopY(Double topY) {
        this.topY = topY;
        this.bottomY = topY+this.lenght;
        this.centerY = topY + this.lenght/2;
    }

    public Double getBottomY() {
        return bottomY;
    }

    public Double getCenterY() {
        return centerY;
    }
}
