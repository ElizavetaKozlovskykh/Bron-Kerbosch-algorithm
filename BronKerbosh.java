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
		//boolean y = false;
		if ( newNot.isEmpty() ) 
			return false;
			
		for ( int i = 0; i < newNot.size(); i++ ) {
			int a = newNot.get(i);
			for ( int j = 0; j < newCand.size(); j++ ) {
				int b = newCand.get(j);
				if ( !edgeExist(a, b) ) return false; //y = true;
				//else y = false;
			}
		}
		return true;
	};
	public void base() {
		algBK(candidates, not);
		System.out.println( "Maximal clique: " + maxComsub.size() );
	}
	public void check(int v, ArrayList<Integer> newNot, ArrayList<Integer> newCand, int j) {
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
	public void remove(ArrayList<Integer> newNot, ArrayList<Integer> newCand, Object s) {
		if ( newCand.contains(s) ) { 
			newCand.remove(s);
			newCand.trimToSize(); 
			}
		if ( newNot.contains(s) ) { 
			newNot.remove(s); 
			newNot.trimToSize(); 
			}
	}
	public void algBK(ArrayList<Integer> cand, ArrayList<Integer> not) {
		//Thread t = new Thread();
		//---------------------------------------------------------
		/*System.out.print("cand: ");
		for ( int p = 0; p < cand.size(); p++ )	
			System.out.print(cand.get(p) + " ");
		System.out.println("");
		
		System.out.print("not: ");
		for ( int q = 0; q < not.size(); q++ ) 
			System.out.print( not.get(q) + " " );
		System.out.println("");*/
		//---------------------------------------------------------
		while ( !cand.isEmpty() && !haveAnEdge(not, cand) ) {
			int v = cand.get(0); 
			Object s = (Object) v; 
			comsub.add(v); 
			/*try { 
				Thread.sleep(1000);
				System.out.print("comsub: ");
				for ( int i = 0; i < comsub.size(); i++ ) 
					System.out.print(comsub.get(i) + " " );
				System.out.println("");
			} 
			catch (InterruptedException e) { 
				e.printStackTrace(); 
				}*/
			ArrayList<Integer> newCand = new ArrayList<>(); 
			ArrayList<Integer> newNot = new ArrayList<>();
			newCand.addAll(cand); 
			newNot.addAll(not); 
			remove(newNot, newCand, s);
			for ( int j = 0; j < graph.edges.size(); j++ ) 
				check(v, newNot, newCand, j);
			if ( newCand.isEmpty() && newNot.isEmpty() ) { 
				if ( maxComsub.isEmpty() ) maxComsub.addAll(comsub);
				else if ( comsub.size() > maxComsub.size() ) { 
					maxComsub.clear(); 
					maxComsub.addAll(comsub); 
					}
				try { 
					Thread.sleep(1000);
					System.out.print("maxComsub.get: ");
					for ( int j = 0; j < maxComsub.size(); j++ ) 
						System.out.print(maxComsub.get(j) + " " );
					System.out.println("");
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); 
					}
			}
			else { 
				algBK(newCand, newNot);
				//System.out.println("The output from the algorithm");
			}
			comsub.remove(s); 
			comsub.trimToSize();	
			
			/*try { 
				Thread.sleep(1000);
				/*System.out.print( "comsub: " );
				for ( int k = 0; k < comsub.size(); k++ ) 
					System.out.print(comsub.get(k) + " ");
				System.out.println("");
			} 
			catch (InterruptedException e) { 
				e.printStackTrace(); 
				}*/
			cand.remove(s); 
			cand.trimToSize(); 
			not.add(v);
			//---------------------------------------
			/*System.out.print("cand: ");
			for ( int p = 0; p < cand.size(); p++ )	
				System.out.print(cand.get(p) + " ");
			System.out.println("");
			
			System.out.print("not: ");
			for ( int q = 0; q < not.size(); q++ ) 
				System.out.print( not.get(q) + " " );
			System.out.println("");*/
			//---------------------------------------
		}
	}
}
