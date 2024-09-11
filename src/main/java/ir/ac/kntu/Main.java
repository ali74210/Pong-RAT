package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Seyed Ali Toliat
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400, Color.WHEAT);
        Player player1=new Player(0 , "player1") , player2=new Player(0 , "player2");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong");
        primaryStage.setResizable(false);
        primaryStage.show();


        Button startButton = new Button("start");
        Button exitButton = new Button("Exit");
        startButton.setTranslateX(190);
        startButton.setTranslateY(130);
        startButton.setScaleX(2);
        startButton.setScaleY(2);
        exitButton.setTranslateX(190);
        exitButton.setTranslateY(200);
        exitButton.setScaleX(2);
        exitButton.setScaleY(2);

        Text welcomeText = new Text("Welcome to Pong");
        welcomeText.setFont(Font.font(15));
        welcomeText.setX(135);
        welcomeText.setY(75);

        Rectangle borderRec = new Rectangle(100,50,200 , 300);
        borderRec.setFill(Color.TRANSPARENT);
        borderRec.setStroke(Color.CHOCOLATE);
        borderRec.setStrokeWidth(5);

        Line centerLine = new Line();
        centerLine.setStartX(200);
        centerLine.setStartY(0);
        centerLine.setEndX(200);
        centerLine.setEndY(400);
        centerLine.setFill(Color.CHOCOLATE);
        centerLine.setStrokeWidth(1);

        Line sideLine1 = new Line();
        sideLine1.setStartX(0);
        sideLine1.setStartY(0);
        sideLine1.setEndX(400);
        sideLine1.setEndY(0);
        sideLine1.setFill(Color.CHOCOLATE);
        sideLine1.setStrokeWidth(3);

        Text point1 = new Text(""+player1.getPoint());
        Text point2 = new Text(""+player2.getPoint());
        point1.setY(200);
        point2.setY(200);
        point1.setX(40);
        point2.setX(360);
        point1.setFont(Font.font(20));
        point2.setFont(Font.font(20));

        Line sideLine2 = new Line();
        sideLine2.setStartX(0);
        sideLine2.setStartY(400);
        sideLine2.setEndX(400);
        sideLine2.setEndY(400);
        sideLine2.setFill(Color.CHOCOLATE);
        sideLine2.setStrokeWidth(3);

        Ball ball ;
        Circle ballCircle;
        Stock stock1 , stock2 ;
        Rectangle stock1Rec , stock2Rec ;
        Random random = new Random();
        if (random.nextBoolean()){
            stock1 = new Stock("yellow");
        }else {
            stock1 = new Stock("green");
        }
        stock1Rec = buildStock(stock1);
        stock1Rec.setX(3);
        stock1Rec.setY(200-stock1Rec.getHeight()/2);
        stock1.setTopY(200-stock1Rec.getHeight()/2);

        if (random.nextBoolean()){
            stock2 = new Stock("yellow");
        }else {
            stock2 = new Stock("green");
        }
        stock2Rec = buildStock(stock2);
        stock2Rec.setX(387);
        stock2Rec.setY(200-stock2Rec.getHeight()/2);
        stock2.setTopY(200-stock2Rec.getHeight()/2);

        if (random.nextBoolean()){
            ball = Ball.Red;
        }else {
            ball = Ball.Blue;
        }
        ballCircle = buildBall(ball);
        ballCircle.setCenterX(200);
        ballCircle.setCenterY(200);

        Rectangle shadowPlayGround = new Rectangle();
        shadowPlayGround.setHeight(400);
        shadowPlayGround.setWidth(200);
        shadowPlayGround.setFill(Color.YELLOWGREEN);
        shadowPlayGround.setOpacity(0.2);
        shadowPlayGround.setY(0);


        root.getChildren().addAll(borderRec, welcomeText, startButton, exitButton);

        startButton.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                root.getChildren().clear();
                root.getChildren().addAll(centerLine,sideLine1,sideLine2,point1,point2,ballCircle,stock1Rec,stock2Rec,shadowPlayGround);
                ball.setGameIsStarted(true) ;
            }
        });

        exitButton.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                stop();
            }
        });
        new AnimationTimer(){
            @Override
            public void handle(long l) {

                if (ballCircle.getCenterX()<200){
                    shadowPlayGround.setX(201);
                }
                else if (ballCircle.getCenterX()>200){
                    shadowPlayGround.setX(0);
                }else {
                    shadowPlayGround.setX(400);
                }
            }
        }.start();
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W && stock1Rec.getY()-stock1.getSpeed()>=0 && ballCircle.getCenterX()<=200){
                stock1Rec.setY(stock1Rec.getY()-stock1.getSpeed());
                stock1.setTopY(stock1Rec.getY());
            }if (keyEvent.getCode() == KeyCode.S && stock1Rec.getY()+stock1.getSpeed()<=400-stock1.getLenght() && ballCircle.getCenterX()<=200){
                stock1Rec.setY(stock1Rec.getY()+stock1.getSpeed());
                stock1.setTopY(stock1Rec.getY());
            }if (keyEvent.getCode() == KeyCode.UP && stock2Rec.getY()-stock2.getSpeed()>=0 && ballCircle.getCenterX()>=200){
                stock2Rec.setY(stock2Rec.getY()-stock2.getSpeed());
                stock2.setTopY(stock2Rec.getY());
            }if (keyEvent.getCode() == KeyCode.DOWN && stock2Rec.getY()+stock2.getSpeed()<=400-stock2.getLenght() && ballCircle.getCenterX()>=200){
                stock2Rec.setY(stock2Rec.getY()+stock2.getSpeed());
                stock2.setTopY(stock2Rec.getY());
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run () {

                Platform.runLater(() -> {
                    stock1.setTopY(stock1Rec.getY());
                    stock2.setTopY(stock2Rec.getY());
                    ballCircle.setCenterY(ballCircle.getCenterY() + ball.getBallMovementY());
                    ballCircle.setCenterX(ballCircle.getCenterX() + ball.getBallMovementX());
                    if (ballCircle.getCenterX() >= 15 && ballCircle.getCenterX() <= 20) {
                        double ballRec1Dist;
                        if (ballCircle.getCenterY()<=stock1.getBottomY()+5 && ballCircle.getCenterY()>=stock1.getTopY()-5 ) {
                            if (ballCircle.getCenterY() <= stock1.getCenterY()+5 && ballCircle.getCenterY() >= stock1.getCenterY() - 5) {
                                ball.setBallMovementX(ball.getSpeed());
                                ball.setBallMovementY(0.0);
                                ballCircle.setCenterX(21);
//                                System.out.println("ball hit middle stock1");
                            } else if (ballCircle.getCenterY() < stock1.getCenterY()) {
                                ballRec1Dist = stock1.getCenterY() - ballCircle.getCenterY();
                                ball.setBallMovementX(Math.sin((0.01 * ballRec1Dist) + 90) * ball.getSpeed());
                                ball.setBallMovementY(Math.cos((0.01 * ballRec1Dist) + 90) * ball.getSpeed());
                                ballCircle.setCenterX(21);
//                                System.out.println("ball hit up stock1");
                            } else {
                                ballRec1Dist = ballCircle.getCenterY() - stock1.getCenterY() ;
                                ball.setBallMovementX(Math.sin((0.01 * ballRec1Dist) + 90) * ball.getSpeed());
                                ball.setBallMovementY((-1)*Math.cos((0.01 * ballRec1Dist) + 90) * ball.getSpeed());
                                ballCircle.setCenterX(21);
//                                System.out.println("ball hit down stock1");
                            }
                        }
                    }else if (ballCircle.getCenterX() < 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        score(ballCircle,ball,player2,point2,stock1Rec,stock2Rec);
                        ballCircle.setCenterY(200);
                        ballCircle.setCenterX(200);
                        ball.setBallMovementX(ball.getSpeed());
                        ball.setBallMovementY(0.0);
                        player2.setPoint(player2.getPoint() + 1);
                        point2.setText("" + player2.getPoint());
                        stock1Rec.setY(200-stock1Rec.getHeight()/2);
                        stock2Rec.setY(200-stock2Rec.getHeight()/2);
//                        System.out.println("Score Line: player 1 : "+player1.getPoint()+" pts , player 2 : "+player2.getPoint()+" pts");
                    }
                    if (ballCircle.getCenterX() >= 380 && ballCircle.getCenterX() <= 385) {
                        double ballRec2Dist;
                        if (ballCircle.getCenterY()<=stock2.getBottomY()+5 && ballCircle.getCenterY()>=stock2.getTopY()-5 ) {
                            if (ballCircle.getCenterY() <= stock2.getCenterY() + 5 && ballCircle.getCenterY() >= stock2.getCenterY() - 5) {
                                ball.setBallMovementX(ball.getSpeed() * (-1));
                                ball.setBallMovementY(0.0);
                                ballCircle.setCenterX(379);
//                                System.out.println("ball hit middle stock2");
                            } else if (ballCircle.getCenterY() < stock2.getCenterY()) {
                                ballRec2Dist = stock2.getCenterY() - ballCircle.getCenterY();
                                ball.setBallMovementX((-1) * Math.sin((0.01 * ballRec2Dist) + 90) * ball.getSpeed());
                                ball.setBallMovementY(Math.cos((0.01 * ballRec2Dist) + 90) * ball.getSpeed());
                                ballCircle.setCenterX(379);
//                                System.out.println("ball hit up stock2");
                            } else {
                                ballRec2Dist =  ballCircle.getCenterY() - stock2.getCenterY() ;
                                ball.setBallMovementX((-1) * Math.sin((0.01 * ballRec2Dist) + 90) * ball.getSpeed());
                                ball.setBallMovementY((-1) * Math.cos((0.01 * ballRec2Dist) + 90) * ball.getSpeed());
                                ballCircle.setCenterX(379);
//                                System.out.println("ball hit down stock2");
                            }
                        }
                    }else if (ballCircle.getCenterX() > 400 ){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        score(ballCircle,ball,player1,point1,stock1Rec,stock2Rec);
                        ballCircle.setCenterY(200);
                        ballCircle.setCenterX(200);
                        ball.setBallMovementX(ball.getSpeed());
                        ball.setBallMovementY(0.0);
                        player1.setPoint(player1.getPoint() + 1);
                        point1.setText("" + player1.getPoint());
                        stock1Rec.setY(200-stock1Rec.getHeight()/2);
                        stock2Rec.setY(200-stock2Rec.getHeight()/2);
                        System.out.println("Score Line: player 1 : "+player1.getPoint()+" pts , player 2 : "+player2.getPoint()+" pts"+stock1.getCenterY());
                    }

                    if (ballCircle.getCenterY() <= 10 || ballCircle.getCenterY() >= 390) {
                        ball.setBallMovementY(ball.getBallMovementY() * (-1));
                    }
                });
            }
        },0,15);
    }
    /*----------------------------------------------------------------------------------------*/
    @Override
    public void init() {
        System.out.println("Initializing...");
    }

    @Override
    public void stop() {
        System.out.println("Closing....");
    }
    /*----------------------------------------------------------------------------------------*/
    public static void score(Circle ballCircle , Ball ball , Player player , Text point , Rectangle stock1Rec , Rectangle stock2Rec){
        ballCircle.setCenterY(200);
        ballCircle.setCenterX(200);
        ball.setBallMovementX(ball.getSpeed());
        ball.setBallMovementY(0.0);
        player.setPoint(player.getPoint() + 1);
        point.setText("" + player.getPoint());
        stock1Rec.setY(200-stock1Rec.getHeight()/2);
        stock2Rec.setY(200-stock2Rec.getHeight()/2);
    }
    /*----------------------------------------------------------------------------------------*/
    public static Rectangle buildStock(Stock stock){
        Rectangle stockRec = new Rectangle();
        if(stock.getType().equals("yellow")){
            stockRec.setWidth(10);
            stockRec.setHeight(100*stock.getLenght());
            stock.setLenght(stockRec.getHeight());
            stockRec.setFill(Color.YELLOW);

        }else {
            stockRec.setWidth(10);
            stockRec.setHeight(100*stock.getLenght());
            stock.setLenght(stockRec.getHeight());
            stockRec.setFill(Color.GREEN);
        }
        return stockRec;
    }
    /*----------------------------------------------------------------------------------------*/
    public static Circle buildBall(Ball ball){
        Circle ballCircle = new Circle(7);

        if (ball.equals(Ball.Blue)){
            ballCircle.setFill(Color.LIGHTSKYBLUE);
        }else {
            ballCircle.setFill(Color.RED);
        }
        return ballCircle;
    }
    /*----------------------------------------------------------------------------------------*/
    public static void main(String[] args) {
        new Thread(() -> {
            _____F.main(args);
        }).start();
        System.out.println("main launched.");
        launch(args);
    }
}
