import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Graph implements GraphInterface {
	public static class Edge {
		private Integer x;
		private Integer y;
		
		public Edge(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private Vector<Edge> edges;
	
	public Graph() {
		edges = new Vector<>();
	}
	public void clear() {
		edges.clear();
	}
	public void addEdge(Integer x, Integer y) {
		Edge e = new Edge(x, y);
		edges.add(e);
	}
	
	public void input() {
        try( FileReader reader = new FileReader("C://java/Bron_Kerbosh/1.txt") ) {
           // читаем посимвольно
        	int a, b;
        	int n = reader.read() - '0';
        	int m = reader.read() - '0'; m = reader.read() - '0';
            while ( ( a = reader.read() ) != -1 && ( a = reader.read() ) != -1 && ( b = reader.read() ) != -1 && ( b = reader.read() ) != -1 ) {
            	a = a - '0'; b = b - '0';
            	addEdge(a, b);
            }
        }
        catch( IOException ex ) { 
            System.out.println( ex.getMessage() );
        }   
    }
	public void output() {
		System.out.println("edges.size(): " + edges.size());
		for ( int i = 0; i < edges.size(); i++ ) {
			System.out.println("edges[" + i + "]: " + edges.get(i).x + "; edges[" + i + "]: " + edges.get(i).y );
		}
	}
}
