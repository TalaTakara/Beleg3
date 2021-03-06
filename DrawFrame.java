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
    private JButton newNodeButton, newEdge2Button, newEdge1Button,randomButton, saveButton, loadButton, SearchButton;
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

        add(SearchButton = new JButton("Suche"));
        SearchButton .setBounds(350, 120, 200, 40);
        SearchButton .addActionListener(alSearch);


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
        add(GF);
        mainFrame.pack();
        setVisible(true);

    }
    ActionListener alSearch = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            M.BFS(newEdge1.getText(), newEdge2.getText());
            M.DFS(newEdge1.getText(), newEdge2.getText());
            repaint();
        }
    };



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
    }

    /**
     * Draw the field with the Graph*
     */
    public class Graphfeld extends JPanel {

        Graphmaster M;

        Graphfeld(Graphmaster M) {
            this.M = M;
            setVisible(true);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setClip(0,0,GFW,GFH);
            M.paint(g);
        }
    }

}

