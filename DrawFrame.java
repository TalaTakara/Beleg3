/**
 * Created by saskia on 30.05.16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DrawFrame extends JFrame {



    JSlider sliderColor, sliderSize, sliderPlayer;
    int fieldsize = 30;
    Graphmaster M;
    private JFrame mainFrame;
    private JLabel newNodeLabel,newEdge1Label, newEdge2Label;
    private JButton newNodeButton, newEdge2Button, newEdge1Button,randomButton, saveButton, loadButton;
    private JTextField newNode,newEdge1,newEdge2,nodes,edges;
    public static final int GFW = 1300;
    public static final int GFH = 750;



    DrawFrame(Graphmaster M) {
        this.M = M;

        mainFrame = new JFrame();
        setTitle("Graph");
        setSize(1500, 1000);
        setLocation(100, 100);
        setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);




        add(newNodeButton = new JButton("neuer Knoten"));
        newNodeButton.setBounds(100, 20, 200, 40);
        newNodeButton.addActionListener(alNewNode);

        add(newNodeButton = new JButton("Knoten löschen "));
        newNodeButton.setBounds(100, 70, 200, 40);
        newNodeButton.addActionListener(alDeleteNode);


        newNode = new JTextField("Name", 8);
        newNode.setEditable(true);
        newNode.setBounds(100, 120, 200, 40);
        add(newNode);

        add( newEdge1Button = new JButton("neue Kante "));
         newEdge1Button.setBounds(600, 30, 200, 40);
         newEdge1Button.addActionListener(alNewEdge);

        newEdge1 = new JTextField("Knoten 1", 8);
        newEdge1.setEditable(true);
        newEdge1.setBounds(350, 30, 200, 40);
        add(newEdge1);

        add(newEdge2Button = new JButton("Kante löschen"));
        newEdge2Button.setBounds(600, 80, 200, 40);
        newEdge2Button.addActionListener(alDeleteEdge);

        newEdge2 = new JTextField("Knoten 2", 8);
        newEdge2.setEditable(true);
        newEdge2.setBounds(350, 80, 200, 40);
        add(newEdge2);

        add(randomButton = new JButton("Random"));
        randomButton.setBounds(850, 30, 200, 40);
        randomButton.addActionListener(alRandom);

        nodes = new JTextField("10", 8);
        nodes.setEditable(true);
        nodes.setBounds(850, 90, 90, 40);
        add(nodes);

        edges = new JTextField("15", 8);
        edges.setEditable(true);
        edges.setBounds(950, 90, 90, 40);
        add(edges);

        add(saveButton = new JButton("speichern"));
        saveButton.setBounds(1100, 30, 200, 40);
        saveButton.addActionListener(alSave);

        add(loadButton = new JButton("laden"));
        loadButton.setBounds(1100, 80, 200, 40);
        loadButton.addActionListener(alLoad);


        Graphfeld GF = new Graphfeld(M);
        GF.setLocation(100, 200);
        GF.setSize(GFW, GFH);
        GF.setBackground(Color.white);
        GF.addMouseListener(new mouseclick());
        add(GF);
//
//        PunkteP2 = new JLabel(M.getClaimedP2());
//        PunkteP2.setBounds(300, 100, 150, 50);
//        add(PunkteP2);
//
//        add(newNodeButton = new JButton("new Game"));
//        newNodeButton.setBounds(450, 80, 100, 50);
//        newNodeButton.addActionListener(alNewGame);
//
//
//        sliderColor = new JSlider(3, 6, 4);
//        sliderColor.setBounds(100, 20, 300, 50);
//        sliderColor.setPaintTicks(true);    //Striche werden angezeigt
//        sliderColor.setPaintLabels(true);  //Zahlen werden angezeigt
//        sliderColor.setMajorTickSpacing(1); //Abstände im Großraster
//        add(sliderColor);

//        addMouseListener(new mouseclick());
        mainFrame.pack();
        setVisible(true);

    }


    ActionListener alNewNode = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.newNode(newNode.getText(),0,0);
            repaint();
        }
    };

    ActionListener alDeleteNode = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.deleteNode(newNode.getText());
            repaint();
        }
    };

    ActionListener alNewEdge = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.newEdge(newEdge1.getText(), newEdge2.getText());
            repaint();
        }
    };

    ActionListener alDeleteEdge = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.deleteEdge(newEdge1.getText(), newEdge2.getText());
            repaint();
        }
    };




    ActionListener alRandom = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int nodesInt =  Integer.parseInt(nodes.getText());
            int edgesInt = Integer.parseInt(edges.getText());
            M.random(nodesInt,edgesInt);
            repaint();
        }
    };

    ActionListener alSave = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.save();
            repaint();
        }
    };

    ActionListener alLoad = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.load();
            repaint();
        }
    };


    public void paint(Graphics g) {
        super.paintComponents(g);

        System.out.print("bla");

    }

    /**
     * Draw the field with the Graph*
     */
    public class Graphfeld extends JPanel {

        JLabel PunkteP1;
        Graphmaster M;
        JLabel newNode;

        Graphfeld(Graphmaster M) {
            this.M = M;
            PunkteP1 = new JLabel("bla");
            PunkteP1.setBounds(100, 100, 150, 50);
            add(PunkteP1);

            setVisible(true);

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setClip(0,0,GFW,GFH);
            M.paint(g);

            //  int size = M.getSize();


//        for ( int i = 0; i < size; i++) {
//            Node node = M.getGraph(i);
//
//            g.fillOval(node.getX(), node.getY(), 10, 10);
//        }
            System.out.print("bla");

        }

    }


    class mouseclick extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int x, y;
            x = e.getX();
            y = e.getY();

            System.out.print("blabb");

//        if (newNode.getText() != null)
//            M.newNode(newNode.getText(),x,y);
//        newNode.setText(null);
//        System.out.print("blubb");
//        repaint();
        }
    }

}

// TODO wie bekommt man aus textfeldern den text raus
