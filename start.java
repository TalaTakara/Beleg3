/**
 * Created by saskia on 30.05.16.
 */
public class start {

    public static void main(String args[]) {

        int n = 1;
        for (;n<10;n++){
            System.out.print("bla");
        }

        Graphmaster M = new Graphmaster();
        DrawFrame D = new DrawFrame(M);



        M.random(10,15);
        M.DFS("Stadt 3", "Stadt 7");
        M.BFS("Stadt 3", "Stadt 7");

        M.newNode("Berlin",0,0);
        M.newEdge("Berlin", "Stadt 1");
        M.deleteNode("Berlin");

//        M.newNode("berlin",100,100 );
//        M.newNode("berlin",100,100 );
//        M.newNode("Köln", 300,300);
//        M.newNode("Bielefeld", 400, 300);
//
//        M.newEdge("berlin","Köln" );
//        M.newEdge("berlin","Köln" );
//        M.newEdge("Köln", "Bielefeld");
//
//        for (int i = 1; i < 20 ;  i++){
//            M.newNode("bla"+i, 0,0 );
//        }
//
//        M.deleteNode("berlin");
//        M.deleteEdge("Köln", "Bielefeld");
//        M.deleteEdge("Köln", "Bielefeld");
//        M.autoArrange(DrawFrame.GFW, DrawFrame.GFH);
        D.repaint();
    }
}

// TODO Breiten und TiefenSuche
// TODO GUI eingaben

