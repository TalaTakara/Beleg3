import java.lang.reflect.Array;

/**
 * Created by saskia on 21.06.16.
 */
public class Node {

    String name;
    Node[] edges;
    int x, y;

    Node(String name, int x, int y){
        edges = new Node[10];
        this.name = name;
        this.x = x;
        this.y = y;

    }


    public String getName() {
        return name;
    }

    public Node[] getEdges() {
        return edges;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setEdges(Node[] edges) {
        this.edges = edges;
    }
}
