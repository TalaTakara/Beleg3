import java.awt.*;
import java.io.*;
import java.util.*;



public class Graphmaster {
    HashMap<String, Node> nodes;

    Graphmaster() {

        nodes = new HashMap<String, Node>();
    }

    public void random(int numNodes, int numEdges) {

        nodes.clear();

        /** Create new Nodes */
        for (int i = 0; i < numNodes; i++) {
            newNode("Stadt " + i, 0, 0);
        }

        /** Create new Edges*/
        boolean created = false;
        for (int i = 0; i < numEdges; i++) {
            do {
                int index1, index2;
                do {
                    index1 = (int) (Math.random() * numNodes);
                    index2 = (int) (Math.random() * numNodes);
                } while (index1 == index2);
                created = newEdge("Stadt " + index1, "Stadt " + index2);
            } while (!created);
        }


    }

    public void newNode(String name, int x, int y) {
        if (!nodes.containsKey(name)) {
            Node node = new Node(name, x, y);
            nodes.put(name, node);
            System.out.println("Node created");
            autoArrange(DrawFrame.GFW, DrawFrame.GFH);

        }
    }

    public boolean newEdge(String name1, String name2) {

        if (nodes.containsKey(name1) && nodes.containsKey(name2)) {
            Node node1 = nodes.get(name1);
            Node node2 = nodes.get(name2);
            if (!node1.existEgde(node2)) {
                newEdge(node1, node2);
                autoArrange(DrawFrame.GFW, DrawFrame.GFH);
                return true;
            }
        }
        return false;
    }

    private void newEdge(Node node1, Node node2) {
        node1.addEdge(node2);
        node2.addEdge(node1);
    }

    public void deleteNode(String name) {
        if (nodes.containsKey(name)) {
            deleteNode(nodes.get(name));
            nodes.remove(name);
            autoArrange(DrawFrame.GFW, DrawFrame.GFH);
        }
    }

    private void deleteNode(Node node1) {

        ArrayList<Node> nodesCopy = new ArrayList<Node>(node1.getEdges());

        for (Node other : nodesCopy) {
            deleteEdge(node1, other);
        }
    }

    public void deleteEdge(String name1, String name2) {
        Node node1 = nodes.get(name1);
        Node node2 = nodes.get(name2);
        deleteEdge(node1, node2);
    }

    public void deleteEdge(Node node1, Node node2) {
        node1.removeEdge(node2);
        node2.removeEdge(node1);
        System.out.println("Edge deleted");
        autoArrange(DrawFrame.GFW, DrawFrame.GFH);
    }

    public void autoArrange(int witdh, int height) {
        int count = nodes.size();
        double arc = Math.PI * 2 / count;
        int middleX = witdh / 2;
        int middleY = height / 2;

        int radius = Math.min(witdh, height) * 40 / 100;

        int index = 0;
        for (Node node : nodes.values()) {
            double alpha = arc * index;
            int x = (int) (Math.cos(alpha) * radius) + middleX;
            int y = (int) (Math.sin(alpha) * -radius) + middleY;

            node.setPos(x, y);
            index++;
        }


    }

    public void paint(Graphics g) {
        for (Node node : nodes.values()) {
            node.paintEdges(g);
        }
        for (Node node : nodes.values()) {
            node.paint(g);
        }

    }
    /**
     * Can save a Map from nodes.ser
     */
    public void save() {
        ObjectOutputStream output = null;
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream("nodes.ser");
            output = new ObjectOutputStream(fileOutput);
            output.writeObject(nodes);
            output.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException e) {
            }
            if (fileOutput != null) try {
                fileOutput.close();
            } catch (IOException e) {
            }
        }
        System.out.print("gespeichert");

    }

    /**
     * Can load a Map from nodes.ser
     */
    public void load() {
        FileInputStream fileIn;
        ObjectInputStream in;
        try {
            fileIn = new FileInputStream("nodes.ser");
            in = new ObjectInputStream(fileIn);
            nodes = (HashMap) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        System.out.print("geladen");
    }

    /**
     * Depth-First-Search
     * @param start Startgraph for Search
     * @param goal  goalgraph for Search
     */
    public void DFS(String start,String goal) {
        System.out.println("Tiefensuche von " + start +" nach " + goal);

        for ( Node node : nodes.values()){
            node.setVisited(false);
        }


        Node startNode = nodes.get(start);
        Node goalNode = nodes.get(goal);

        Stack<Node> path= new Stack<>();

        if (startNode.depthFirstSearch(goalNode, path)){
            System.out.println("Ziel");
            while (!path.empty()){
                System.out.println("     " + path.pop().getName());
            }
            System.out.println("Start");
        }else{
            System.out.println("Kein Weg!");

        }
    }

    /**
     * Breath-First-Search
     * @param start Startgraph for Search
     * @param goal  goalgraph for Search
     */
 public void BFS(String start,String goal){
     System.out.println("Breitensuche von " + start +" nach " + goal);

     for ( Node node : nodes.values()){
         node.setVisited(false);
     }


     Node startNode = nodes.get(start);
     Node goalNode = nodes.get(goal);

     Stack<Node> path= new Stack<>();

     if (startNode.breathFirstSearch(startNode,goalNode, path)){
         System.out.println("Ziel");
         while (!path.empty()){
             System.out.println("     " + path.pop().getName());
         }
         System.out.println("Start");
     }else{
         System.out.println("Kein Weg!");

     }

 }
}
