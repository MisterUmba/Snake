import java.util.ArrayList;
import java.util.Random;

public class Model{

    // Fields
    ArrayList<Node> snake;
    Node head;
    Node coin;
    Random rand;
    long score;

    final int CANVASX = 80;
    final int CANVASY = 60;

    // Default constructor
    Model(){


        head = new Node(10,10);
        snake = new ArrayList<>();
        snake.add(head);

        rand = new Random();
        coin = new Node(rand.nextInt(CANVASX-2)+1,rand.nextInt(CANVASY-2)+1);
    }

    // Method for adding new nodes
    void add(){
        snake.add(1, new Node(head));
    }

    // Method for updating snake
    void move(double headx, double heady){
        head.setPosition(headx, heady);
        for(int x = snake.size()-1; x>0; x--){
            snake.get(x).setPosition(snake.get(x-1));
        }
    }


    // Get method
    ArrayList<Node> getSnake(){return snake;}

    // check if collect
    boolean collect(){
        return (head.x==coin.x && head.y==coin.y);
    }

    // coin collected
    void newCoinLocation(){
        for(int x = 0; x<5; x++){
            this.add();
        }
        while(true){
            coin.setPosition(rand.nextInt(CANVASX-22)+11,rand.nextInt(CANVASY-22)+11);
            if(!selfImpact(coin))
                break;
        }

        score += 100;
    }

    // impact with walls
    boolean impact(Node d){
        return(head.x>CANVASX || head.x<0 || head.y>CANVASY || head.y<0 || selfImpact(d));
    }

    // impact with self
    boolean selfImpact(Node d){
        for(Node e: snake){
            if((d.x == e.x && d.y == e.y) && !e.equals(head) )
                return true;
        }
        return false;
    }




    // toString method for debugging
    public String toString(){
        String temp = "";
        for(Node e: snake)
            temp += e.toString()+"\n";
        return temp;
    }
}