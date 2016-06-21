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


    JButton bu1, bu2;
    JSlider sliderColor, sliderSize, sliderPlayer;
    int fieldsize = 30;
    Graphmaster M;
    private JFrame mainFrame;
    private JLabel GraphLabel;
    private JTextField newNode;

    DrawFrame( Graphmaster M) {
        this.M = M;

        mainFrame = new JFrame();
        setTitle("Graph");
        setSize(1500, 1000);
        setLocation(100, 100);
        setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        GraphLabel = new JLabel("Graph");

        newNode = new JTextField("test", 8);
        newNode.setEditable(true);
        newNode.setBounds(30, 30, 100, 50);
        add(newNode);


        Graphfeld GF = new Graphfeld();
        GF.setLocation(100,200);
        GF.setSize(1300,750);
        GF.setBackground(Color.white);
        add(GF);
//
//        PunkteP2 = new JLabel(M.getClaimedP2());
//        PunkteP2.setBounds(300, 100, 150, 50);
//        add(PunkteP2);
//
//        add(bu1 = new JButton("new Game"));
//        bu1.setBounds(450, 80, 100, 50);
//        bu1.addActionListener(alNewGame);
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



    ActionListener alNewGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            repaint();
        }
    };

    ActionListener alUNDO = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            repaint();
        }
    };


    public void paint(Graphics g) {
        super.paintComponents(g);

       System.out.print("bla");

        }





}
class Graphfeld extends JPanel{

    JLabel PunkteP1;
    Graphmaster M;
    JLabel newNode;

    void Graphfeld(){
        PunkteP1 = new JLabel("bla");
        PunkteP1.setBounds(100, 100, 150, 50);
        add(PunkteP1);



        addMouseListener(new Graphfeld.mouseclick());
    }

    class mouseclick extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int x, y;
            x = e.getX();
            y = e.getY();

            System.out.print("blabb");

            if (newNode.getText() != null)
            M.newNode(newNode.getText(),x,y);
            newNode.setText(null);
            System.out.print("blubb");
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(20, 20, 80, 80);
      //  int size = M.getSize();



//        for ( int i = 0; i < size; i++) {
//            Node node = M.getGraph(i);
//
//            g.fillOval(node.getX(), node.getY(), 10, 10);
//        }
        System.out.print("bla");

        }

        }

