import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph implements GraphInterface {
	public static class Edge {
		private Integer x;
		private Integer y;
		
		public Edge(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}
	}
	public  Integer NumVert;
	public  Integer NumEdge;
	public Vector<Edge> edges;
	
	public Graph() {
		edges = new Vector<>();
		inputGraph();
	}
	public void clear() {
		edges.clear();
	}
	public void addEdge(Integer x, Integer y) {
		Edge e = new Edge(x, y);
		edges.add(e);
	}
	public void inputGraph()
	{
		File f = new File("2.txt");
		try {
			Scanner scan = new Scanner(f);
			NumVert = scan.nextInt();
			NumEdge = scan.nextInt();
			int a,b;
			for (int i = 0; i < NumEdge; i++) {
				a=scan.nextInt();
				b=scan.nextInt();
				addEdge(a, b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void outputGraph() {
		System.out.println("edges.size(): " + edges.size());
		for ( int i = 0; i < edges.size(); i++ ) {
			System.out.println("edges[" + i + "]: " + edges.get(i).x 
					+ "; edges[" + i + "]: " + edges.get(i).y );
		}
	}
	
	public boolean edgeExist(int a, int b) {
		System.out.println("edgeExist");
		for ( int k = 0; k < edges.size(); k++ ) {
			if ( edges.get(k).x.equals(a) && edges.get(k).y.equals(b) )
				return true;
			if ( edges.get(k).x.equals(b) && edges.get(k).y.equals(a) )
				return true;
		}
		return false;
	}
	
	public boolean haveAnEdge(ArrayList<Integer> newNot, ArrayList<Integer> newCand) {
		System.out.println("haveAnEdge");
		if ( newNot.isEmpty() ) 
			return false;
		//if ( !newCand.isEmpty() ) {
			for ( int i = 0; i < newNot.size(); i++ ) {
				int a = newNot.get(i);
				for ( int j = 0; j < newCand.size(); j++ ) {
					int b = newCand.get(j);
					if ( edgeExist(a, b) ) return true;
				}
			}
		//}
		return false;
	};
	
	public void basic() {
		System.out.println("basic");
		ArrayList<Integer> comsub = new ArrayList<>();
		ArrayList<Integer> candidates = new ArrayList<>();
		ArrayList<Integer> not = new ArrayList<>();
		ArrayList<Integer> maxComsub = new ArrayList<>();
		
		for(int i=1; i<=NumVert; i++){
			candidates.add(i);}
		System.out.println("candidates.size() " + candidates.size());
		System.out.println("k " + 0);
		algBK(comsub, maxComsub, candidates, not/*, 0*/);
		System.out.println( maxComsub.size() + "tt" );
	}
	
	public void algBK(ArrayList<Integer> comsub, ArrayList<Integer> maxComsub, 
			ArrayList<Integer> cand, ArrayList<Integer> not/*, int k*/) {
		System.out.println("algBK");
		while ( !cand.isEmpty() && !haveAnEdge(not, cand) ) {
			//if ( cand.size() > k ) { 
				int v = cand.get(0); 
				Object s = (Object) v;
				comsub.add(v);
				ArrayList<Integer> newCand = new ArrayList<>(); 
				ArrayList<Integer> newNot = new ArrayList<>();
				newCand.addAll(cand); 
				newNot.addAll(not);
				if ( newCand.contains(s) ) { 
					newCand.remove(s);
					newCand.trimToSize(); 
					}
				if ( newNot.contains(s) ) { 
					newNot.remove(s); 
					newNot.trimToSize(); 
					}
				for ( int j = 0; j < edges.size(); j++ ) {
					if ( !edgeExist(v, edges.get(j).x) ) {
						if ( newCand.contains( edges.get(j).x ) ) { 
							newCand.remove( edges.get(j).x ); 
							newCand.trimToSize(); 
							}
						if ( newNot.contains( edges.get(j).x ) ) { 
							newNot.remove( edges.get(j).x ); 
							newNot.trimToSize(); 
							}	
					}
					if ( !edgeExist(v, edges.get(j).y) ) {
						if ( newCand.contains( edges.get(j).y ) ) { 
							newCand.remove( edges.get(j).y ); 
							newCand.trimToSize(); 
							}
						if ( newNot.contains( edges.get(j).y ) ) { 
							newNot.remove( edges.get(j).y ); 
							newNot.trimToSize(); 
							}
					}
				}
				if ( newCand.isEmpty() && newNot.isEmpty() ) {
					if ( maxComsub.isEmpty() ) maxComsub = comsub;
					else if ( comsub.size() > maxComsub.size() ) maxComsub = comsub;
				}
				else algBK(comsub, maxComsub, newCand, newNot/*, k++*/);
				comsub.remove(s); 
				cand.remove(s); 
				not.add(v);
			}
		//}
	}
}
