public class Node {
    double x, y;

    Node(double x, double y){
        this.x = x;
        this.y = y;
    }

    Node(Node e){
        setPosition(e);
    }

    void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    void setPosition(Node e){
        this.x = e.x;
        this.y = e.y;
    }

    public String toString(){
        return "Point("+x+","+y+")";
    }
}
