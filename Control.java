import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Control extends Application {

    Timeline time;
    View view;
    Model model;
    Node direction;
    KeyCode key;
    Label label;
    double change = 1;
    int speed = 1000/15;

    public void start(Stage prime){
        model = new Model();
        view = new View(model);
        direction = new Node(11, 10);
        key = KeyCode.T;


        view.restart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.snake.clear();
                model.snake.add(model.head);
                view.canvas.requestFocus();

                if(view.pause.isDisable()){
                    view.pause.setDisable(false);
                    view.getChildren().remove(label);
                    direction.setPosition(10,10);
                    time.play();
                }
            }
        });

        view.canvas.setOnKeyPressed(keyEvent -> {
            if(key == KeyCode.A && keyEvent.getCode()==KeyCode.D)return;
            if(key == KeyCode.D && keyEvent.getCode()==KeyCode.A)return;
            if(key == KeyCode.W && keyEvent.getCode()==KeyCode.S)return;
            if(key == KeyCode.S && keyEvent.getCode()==KeyCode.W)return;
            key = keyEvent.getCode();
            model.move(direction.x,direction.y);
        });

        view.pause.setOnAction(e->{
            if(time.getStatus().equals(Animation.Status.RUNNING)){
                time.stop();
                view.pause.setText("Play");
            } else{
                time.play();
                view.pause.setText("Pause");
            }


            view.canvas.requestFocus();
        });

        view.quit.setOnAction(e -> {
            prime.close();
        });

        time = new Timeline(new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                view.model.move((direction.x), (direction.y));

                if(key.equals(KeyCode.W))
                    direction.y -= change;
                else if(key.equals(KeyCode.S))
                    direction.y += change;
                else if(key.equals(KeyCode.D))
                    direction.x += change;
                else if(key.equals(KeyCode.A))
                    direction.x -= change;
                else if(key.equals(KeyCode.ESCAPE))
                    prime.close();


                if(view.model.collect())
                    view.model.newCoinLocation();

                if(view.model.impact(direction)){
                    time.stop();
                    view.pause.setDisable(true);
                    label = new Label("  Game Over!");
                    label.setStyle("-fx-text-fill: RED; -fx-font-size: 100px; -fx-font-family: Monospace;");
                    view.add(label,0,1,2,1);
                    key = KeyCode.T;
                }


                view.update();
            }
        }));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();


        prime.setScene(new Scene(view));
        prime.getIcons().add(new Image("Snake.png"));
        prime.setTitle("Snake");
        prime.initStyle(StageStyle.TRANSPARENT);
        prime.setResizable(false);
        prime.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
