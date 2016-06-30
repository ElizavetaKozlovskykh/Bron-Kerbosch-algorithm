import java.util.ArrayList;

public interface GraphInterface {
	public void clear();
	public void addEdge(Integer x, Integer y);
	public void basic();
	public void inputGraph();
	public void outputGraph();
	public boolean haveAnEdge(ArrayList<Integer> newNot, ArrayList<Integer> newCand);
	public boolean edgeExist(int a, int b);
	public void algBK(ArrayList<Integer> comsub, ArrayList<Integer> maxComsub,
					  ArrayList<Integer> cand, ArrayList<Integer> not/*, int k*/);
}
