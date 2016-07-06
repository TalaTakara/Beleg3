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

        ArrayList<Node> nodes_copy = new ArrayList<Node>(node1.getEdges());

        for (Node other : nodes_copy) {
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

    public Collection<Node> getNodes() {
        return nodes.values();
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
    public void save() {
        ObjectOutputStream output = null;
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream("nodes.ser");
            output = new ObjectOutputStream(fileOutput);
            output.writeObject(nodes);
            output.close();
            fileOutput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (output != null) try {output.close();} catch (IOException e){}
            if (fileOutput != null) try {fileOutput.close();} catch (IOException e){}
        }
        System.out.print("gespeichert");

    }

    public void load(){
        FileInputStream fileIn;
        ObjectInputStream in;
        try {
            fileIn = new FileInputStream("nodes.ser");
            in = new ObjectInputStream(fileIn);
            nodes = (HashMap) in.readObject();
        }catch (IOException i){
            i.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c){
            c.printStackTrace();
            return;
        }
        System.out.print("geladen");
    }

}

//    public void dfs(Node root)
//    {
//        //Avoid infinite loops
//        if(root == null) return;
//
//        System.out.print(root.getVertex() + "\t");
//        root.state = State.Visited;
//
//        //for every child
//        for(Node n: root.getChild())
//        {
//            //if childs state is not visited then recurse
//            if(n.state == State.Unvisited)
//            {
//                dfs(n);
//            }
//        }
//    }
//
//    public void bfs(Node root)
//    {
//        //Since queue is a interface
//        Queue<Node> queue = new LinkedList<Node>();
//
//        if(root == null) return;
//
//        root.state = State.Visited;
//        //Adds to end of queue
//        queue.add(root);
//
//        while(!queue.isEmpty())
//        {
//            //removes from front of queue
//            Node r = queue.remove();
//            System.out.print(r.getVertex() + "\t");
//
//            //Visit child first before grandchild
//            for(Node n: r.getChild())
//            {
//                if(n.state == State.Unvisited)
//                {
//                    queue.add(n);
//                    n.state = State.Visited;
//                }
//            }
//        }
//
//
//    }
//
//    public static Graph createNewGraph()
//    {
//        Graph g = new Graph();
//        Node[] temp = new Node[8];
//
//        temp[0] = new Node("A", 3);
//        temp[1] = new Node("B", 3);
//        temp[2] = new Node("C", 1);
//        temp[3] = new Node("D", 1);
//        temp[4] = new Node("E", 1);
//        temp[5] = new Node("F", 1);
//
//        temp[0].addChildNode(temp[1]);
//        temp[0].addChildNode(temp[2]);
//        temp[0].addChildNode(temp[3]);
//
//        temp[1].addChildNode(temp[0]);
//        temp[1].addChildNode(temp[4]);
//        temp[1].addChildNode(temp[5]);
//
//        temp[2].addChildNode(temp[0]);
//        temp[3].addChildNode(temp[0]);
//        temp[4].addChildNode(temp[1]);
//        temp[5].addChildNode(temp[1]);
//
//        for (int i = 0; i < 7; i++)
//        {
//            g.addNode(temp[i]);
//        }
//        return g;
//    }
//
//    public static void main(String[] args) {
//
//        Graph gDfs = createNewGraph();
//        Graphmaster s = new Graphmaster();
//
//        System.out.println("--------------DFS---------------");
//        s.dfs(gDfs.getNode()[0]);
//        System.out.println();
//        System.out.println();
//        Graph gBfs = createNewGraph();
//        System.out.println("---------------BFS---------------");
//        s.bfs(gBfs.getNode()[0]);
//
//    }

