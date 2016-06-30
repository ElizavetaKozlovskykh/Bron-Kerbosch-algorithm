import java.util.ArrayList;

public class BronKerbosh implements BronKerboshInterface {
	private ArrayList<Integer> comsub;
	private ArrayList<Integer> candidates;
	private ArrayList<Integer> not;
	private ArrayList<Integer> maxComsub;
	private Graph graph;
	
	public BronKerbosh() {
		comsub = new ArrayList<>();
		candidates = new ArrayList<>();		
		not = new ArrayList<>();
		maxComsub = new ArrayList<>();
		graph = new Graph();
		for( int i = 1; i < graph.numVert + 1; i++ ) 
			candidates.add(i);
	}	
	public boolean edgeExist(int a, int b) {
		for ( int k = 0; k < graph.edges.size(); k++ ) {
			if ( graph.edges.get(k).x.equals(a) && graph.edges.get(k).y.equals(b) )
				return true;
			if ( graph.edges.get(k).x.equals(b) && graph.edges.get(k).y.equals(a) )
				return true;
		}
		return false;
	}
	public boolean haveAnEdge(ArrayList<Integer> newNot, ArrayList<Integer> newCand) {
		if ( newNot.isEmpty() ) 
			return false;
			for ( int i = 0; i < newNot.size(); i++ ) {
				int a = newNot.get(i);
				for ( int j = 0; j < newCand.size(); j++ ) {
					int b = newCand.get(j);
					if ( edgeExist(a, b) ) return true;
				}
			}
		return false;
	};
	public void base() {	
		algBK(candidates, not);
		System.out.println( "Maximal clique: " + maxComsub.size() );
	}
	public void algBK(ArrayList<Integer> cand, ArrayList<Integer> not) {
		while ( !cand.isEmpty() && !haveAnEdge(not, cand) ) {
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
			for ( int j = 0; j < graph.edges.size(); j++ ) {
				if ( !edgeExist(v, graph.edges.get(j).x) ) {
					if ( newCand.contains( graph.edges.get(j).x ) ) { 
						newCand.remove( graph.edges.get(j).x ); 
						newCand.trimToSize(); 
						}
					if ( newNot.contains( graph.edges.get(j).x ) ) { 
						newNot.remove( graph.edges.get(j).x ); 
						newNot.trimToSize(); 
						}	
				}
				if ( !edgeExist(v, graph.edges.get(j).y) ) {
					if ( newCand.contains( graph.edges.get(j).y ) ) { 
						newCand.remove( graph.edges.get(j).y ); 
						newCand.trimToSize(); 
						}
					if ( newNot.contains( graph.edges.get(j).y ) ) { 
						newNot.remove( graph.edges.get(j).y ); 
						newNot.trimToSize(); 
						}
				}
			}
			if ( newCand.isEmpty() && newNot.isEmpty() ) { 
				if ( maxComsub.isEmpty() ) maxComsub.addAll(comsub);
				else if ( comsub.size() > maxComsub.size() ) {
					maxComsub.clear();
					maxComsub.addAll(comsub);
				}
			}
			else algBK(newCand, newNot);
			comsub.remove(s); 
			cand.remove(s); 
			not.add(v);
		}
	}
}
