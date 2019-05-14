import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class View extends GridPane {
    // What does it have

    Model model;
    Canvas canvas = new Canvas(800,600);
    GraphicsContext pen = canvas.getGraphicsContext2D();
    Button restart = new Button("Restart");
    Button pause = new Button("Pause");
    Image img = new Image(getClass().getResourceAsStream("Snake.png"));
    ImageView post = new ImageView(img);
    Label title = new Label("Snake");
    Button quit;
    GridPane titleBar = new GridPane();


    View(Model model){
        ImageView v = new ImageView(new Image(getClass().getResourceAsStream("quitButton.png")));

        quit = new Button("",v);

        this.model = model;
        this.add(titleBar,0,0,2,1);
        this.add(canvas,0,1,2,1);
        this.add(restart, 0,2);
        this.add(pause, 1,2);

        post.setFitHeight(20);
        post.setFitWidth(20);

        titleBar.setHgap(5);
        titleBar.setVgap(5);

        title.setPadding(new Insets(0,700,0,0));

        titleBar.setStyle("-fx-background-color: #282828;-fx-background-radius: 0;-fx-text-fill: #E8E8E8;");
        title.setStyle("-fx-background-color: #282828;-fx-background-radius: 0;-fx-text-fill: #E8E8E8;");
        quit.setStyle("-fx-background-color: #282828;-fx-background-radius: 0;-fx-text-fill: #E8E8E8;");

        v.setFitWidth(20);
        v.setFitHeight(20);

        titleBar.add(post,0,0);
        titleBar.add(title, 1,0);
        titleBar.add(quit,2,0);

        quit.setFocusTraversable(false);

        restart.setPrefSize(Integer.MAX_VALUE, 25);
        restart.setStyle("-fx-background-color: #282828;-fx-background-radius: 0;-fx-text-fill: #E8E8E8;");
        pause.setPrefSize(Integer.MAX_VALUE, 25);
        pause.setStyle("-fx-background-color: #282828;-fx-background-radius: 0;-fx-text-fill: #E8E8E8;");
        canvas.setFocusTraversable(true);

        this.setPrefSize(800, 625);


    }

    public void update(){
        pen.setFill(Color.BLACK);
        pen.fillRect(0,0, 800, 600);
        pen.setFill(Color.WHITE);

        for(Node e: model.getSnake()){
            pen.fillRect(e.x*10,e.y*10,10,10);
        }

        pen.setFill(Color.GOLD);
        pen.fillRect(model.coin.x*10, model.coin.y*10,10,10);

    }
}
