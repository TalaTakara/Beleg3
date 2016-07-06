import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by saskia on 21.06.16.
 */
public class Node implements Serializable {

    String name;
    List<Node> edges;
    int x, y;
    boolean visited;



    Node(String name, int x, int y) {
        edges = new ArrayList<Node>();
        this.name = name;
        this.x = x;
        this.y = y;
        visited = false;

    }


    public String getName() {
        return name;
    }

    public Collection<Node> getEdges() {
        return edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * gives the position back
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Set the position of the Graph
     */
    public void setPos(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public boolean existEgde(Node other) {
        if (edges.contains(other)) {
            return true;
        }
        return false;
    }


    public void addEdge(Node other) {
        edges.add(other);
    }

    public void removeEdge(Node other) {
        edges.remove(other);
    }

    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x - 5, y - 5, 10, 10);
        g.setColor(Color.red);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(name, x - metrics.stringWidth(name) / 2, y - 10);
    }

    public void paintEdges(Graphics g) {
        g.setColor(Color.black);
        for (Node other : edges) {

            g.drawLine(x, y, other.getX(), other.getY());

        }
    }


}