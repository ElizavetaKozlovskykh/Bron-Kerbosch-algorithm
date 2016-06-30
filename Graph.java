import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Graph implements GraphInterface {
	public static class Edge {
		public Integer x;
		public Integer y;
		
		public Edge(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}
	}
	public Vector <Edge> edges;
	public  Integer numVert;
	public  Integer numEdge;
	
	public Graph() {
		edges = new Vector<>();
		File f = new File("2.txt");
		try {
			Scanner scan = new Scanner(f);
			numVert = scan.nextInt();
			numEdge = scan.nextInt();
			int a, b;
			for ( int i = 0; i < numEdge; i++ ) {
				a = scan.nextInt();
				b = scan.nextInt();
				addEdge(a, b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void clear() {
		edges.clear();
	}
	public void addEdge(Integer x, Integer y) {
		Edge e = new Edge(x, y);
		edges.add(e);
	}
	public void output() {
		System.out.println("edges.size(): " + edges.size());
		for ( int i = 0; i < edges.size(); i++ ) {
			System.out.println("edges[" + i + "]: " + edges.get(i).x + "; edges[" + i + "]: " + edges.get(i).y );
		}
	}
}
