//import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.awt.*;
//import java.util.Vector;
import java.util.ArrayList;

public class GraphGraphic extends JPanel  {
    private class MyPoint {
        int x, y;
        MyPoint (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static Graphics2D p;
    //public BronKerboshInterface G;
    MyPoint[] coordinates;
    private BronKerbosh gr;
    ArrayList<Integer> curClique;

    GraphGraphic(/*BronKerboshInterface Gr*/) {
        //G = Gr;
    	gr = new BronKerbosh();
    	gr.base();
        coordinates = new MyPoint[ gr.graph.numVert ];
    }
    public void addList(ArrayList<Integer> list1)
    {curClique=list1;}
    public void DrawVert(int x, int y, Graphics p, int num) {
        String str = Integer.toString(num);
        //p.setStroke(new BasicStroke(10.0f));
        p.drawOval(x, y, 30, 30);
        p.drawString(str, x + 12, y + 20);

    }
    public void SetCoordinate() {
        Double alpha = 2 * Math.PI / gr.graph.numVert;
        Double alpha0 = Math.PI;
        Integer R = 20 * gr.graph.numVert;
        Integer x0 = 400;
        Integer y0 = 300;
        for ( int i = 0; i < gr.graph.numVert; i++ ) {
        	int a = x0 + (int)Math.round(Math.cos(alpha0)) * R; 
        	int b = y0 + (int)Math.round(Math.sin(alpha0)) * R; 
        	MyPoint tmp = new MyPoint(a, b);
        	
        	//MyPoint tmp = new MyPoint(x0+(int)Math.round(Math.cos(alpha0))*R,y0+(int)Math.round(Math.sin(alpha0))*R);
            coordinates[i] = tmp;
            alpha0 = alpha0 - alpha;
        }
    }
    public void DrawEdge(int x, int y, Graphics p) {
        //p.setStroke(new BasicStroke(8.0f));
        p.drawLine(coordinates[x - 1].x + 25, coordinates[x - 1].y + 25, coordinates[y - 1].x + 25, coordinates[y - 1].y + 25);
    }
 
    public void paint(Graphics g) {
        SetCoordinate();
        g.setColor(new Color(0, 0, 0));
        for ( int i = 0; i < gr.graph.numEdge/*G.GetNumEdges()*/; i++ ) {
            //DrawEdge(G.GetEdgesV1(i), G.GetEdgesV2(i), g);
      	  DrawEdge(gr.graph.edges.get(i).x, gr.graph.edges.get(i).y, g);
        }
        g.setColor(new Color(0, 100, 255));
        for ( int i = 0; i < gr.graph.numVert/*G.GetNumVert()*/; i++ ) {
            DrawVert(coordinates[i].x, coordinates[i].y, g, i + 1);
        }
    }
    public void repaint(Graphics g, /*ArrayList<Integer> maxClique,*/ ArrayList<Integer> curClique) {
        g.setColor(new Color(0, 0, 0));
        for ( int i = 0; i < gr.graph.numEdge/*G.GetNumEdges()*/; i++ ) {
            //DrawEdge(G.GetEdgesV1(i), G.GetEdgesV2(i), g);
      	  DrawEdge(gr.graph.edges.get(i).x, gr.graph.edges.get(i).y, g);
        }
        g.setColor(new Color(255, 0, 0));
        for ( int i = 0; i < curClique.size()/*G.GetNumVert()*/; i++ ) {
            DrawVert(coordinates[i].x, coordinates[i].y, g, curClique.get(i));
        }
    }

}