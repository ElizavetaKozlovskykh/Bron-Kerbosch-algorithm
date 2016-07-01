import java.util.ArrayList;

public interface BronKerboshInterface {
	public boolean edgeExist(int a, int b);
	public boolean haveAnEdge(ArrayList<Integer> newNot, ArrayList<Integer> newCand);
	public void base();
	public void check(int v, ArrayList<Integer> newNot, ArrayList<Integer> newCand, int j);
	public void remove(ArrayList<Integer> newNot, ArrayList<Integer> newCand, Object s);
	public void algBK(ArrayList<Integer> cand, ArrayList<Integer> not);
}
