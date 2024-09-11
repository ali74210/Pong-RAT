package ir.ac.kntu;

public enum Ball {
    Blue(2) , Red(3) ;

    private Double speed;
    private boolean gameIsStarted = false;
    private Double ballMovementX , ballMovementY ;
    Ball(double speed) {
        this.speed = speed ;
        ballMovementX = this.speed;
        ballMovementY = 0.0;
        gameIsStarted = false ;
    }


    Ball(){
        ballMovementX = this.speed;
        ballMovementY = 0.0;
        gameIsStarted = false ;
    }
    public Double getBallMovementX() {
        return ballMovementX;
    }

    public Double getBallMovementY() {
        return ballMovementY;
    }

    public void setBallMovementX(Double ballMovementX) {
        this.ballMovementX = ballMovementX;
    }

    public void setBallMovementY(Double ballMovementY) {
        this.ballMovementY = ballMovementY;
    }

    public boolean isGameIsStarted() {
        return gameIsStarted;
    }

    public void setGameIsStarted(boolean gameIsStarted) {
        this.gameIsStarted = gameIsStarted;
    }

    public Double getSpeed() {
        return speed;
    }
}
