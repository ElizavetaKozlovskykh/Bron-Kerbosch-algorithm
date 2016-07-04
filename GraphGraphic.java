import javax.swing.*;
import java.awt.*;
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
    private ArrayList<Integer> clique;
    MyPoint[] coordinates;
    private BronKerbosh gr;
    private boolean check;

    GraphGraphic() {
        gr = new BronKerbosh();
    	gr.base();
        coordinates = new MyPoint[ gr.graph.numVert ];
        check = false;
        clique = new ArrayList<>();
    }

    public void SetCoordinate() {
        Double alpha = 2 * Math.PI / gr.graph.numVert;
        Double alpha0 = 0.0;
        Integer R = 15 * gr.graph.numVert;
        Integer x0 = 400;
        Integer y0 = 365;
        int a, b;
        for ( int i = 0; i < gr.graph.numVert; i++ ) {
            a =(int)Math.round(Math.cos(alpha0));
            b=(int)Math.round(Math.sin(alpha0));
            a= x0 + (int)(Math.round(Math.cos(alpha0) * R));
        	b = y0 + (int)(Math.round(Math.sin(alpha0) * R));
        	MyPoint tmp = new MyPoint(a, b);
        	coordinates[i] = tmp;
            alpha0 = alpha0 + alpha;
        }
    }
    public void setClique(ArrayList<Integer> curClique, boolean check) {
        clique.clear();
        clique.addAll(curClique);
        this.check = check;
    }
    public void DrawVert(int x, int y, Graphics p, int num) {
        String str = Integer.toString(num);
        p.drawOval(x, y, 30, 30);
        p.drawString(str, x + 12, y + 20);
    }
    public void DrawEdge(int x, int y, Graphics p) {
        p.drawLine(coordinates[x - 1].x + 25, coordinates[x - 1].y + 25, coordinates[y - 1].x + 25, coordinates[y - 1].y + 25);
    }
    public void paint(Graphics g) {
        SetCoordinate();
        g.setColor(new Color(0, 0, 0));
        
        for ( int i = 0; i < gr.graph.numEdge; i++ ) {
      	  DrawEdge(gr.graph.edges.get(i).x, gr.graph.edges.get(i).y, g);
        }
        for ( int i = 0; i < gr.graph.numVert; i++ ) {
            DrawVert(coordinates[i].x, coordinates[i].y, g, i + 1);
        }
        
        if ( check ) g.setColor(new Color(255, 0, 0));
        else g.setColor(new Color(0, 0, 0));

        for ( int i = 0; i < clique.size(); i++ ) {
            DrawVert(coordinates[clique.get(i)-1].x, coordinates[clique.get(i)-1].y, g, clique.get(i));
        }
    }
}