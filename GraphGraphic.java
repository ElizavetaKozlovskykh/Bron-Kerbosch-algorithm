import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GraphGraphic extends JPanel  {
    private class MyPoint {
        int x, y;
        MyPoint (int x1, int y1)
        {
            x=x1;
            y=y1;
        }
    }
    public static Graphics2D p;
    public BronKerboshInterface G;
    MyPoint[] coordinates;
    GraphGraphic(BronKerboshInterface Gr)
    {
        G=Gr;
        MyPoint[] coordinates= new MyPoint[G.GetNumVert()];
    }
    public void DrawVert(int x, int y, Graphics p, int num)
    {
        String str = Integer.toString(num);
        //p.setStroke(new BasicStroke(10.0f));
        p.drawOval(x, y, 30, 30);
        p.drawString(str, x+12, y+20);

    }
    public void SetCoordinate()
    {
        Double alpha = 2*Math.PI/G.GetNumVert();
        Double alpha0=Math.PI;
        Integer R = 20*G.GetNumVert();
        Integer x0= 400;
        Integer y0 = 300;
        for (int i=0; i<G.GetNumVert(); i++)
        {
            MyPoint tmp = new MyPoint(x0+(int)Math.round(Math.cos(alpha0))*R,y0+(int)Math.round(Math.sin(alpha0))*R);
            coordinates[i] = tmp;
            alpha0=alpha0-alpha;
        }

    }
    public void DrawEdge(int x, int y, Graphics p)
    {
        //p.setStroke(new BasicStroke(8.0f));
        p.drawLine(coordinates[x-1].x+25, coordinates[x-1].y+25, coordinates[y-1].x+25, coordinates[y-1].y+25);
    }
    public void paint(Graphics g)
    {
        SetCoordinate();
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i<G.GetNumEdges();i++)
        {
            DrawEdge(G.GetEdgesV1(i), G.GetEdgesV2(i), g);
        }
        g.setColor(new Color(0, 100, 255));
        for (int i = 0; i<G.GetNumVert();i++)
        {
            DrawVert(coordinates[i].x,coordinates[i].y, g, i+1);
        }
    }
    public void repaint(Graphics g)
    {
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i<G.GetNumEdges();i++)
        {
            DrawEdge(G.GetEdgesV1(i), G.GetEdgesV2(i), g);
        }
        g.setColor(new Color(255, 0, 0));
        for (int i = 0; i<G.GetNumVert();i++)
        {
            DrawVert(coordinates[i].x,coordinates[i].y, g, i);
        }
    }
}
