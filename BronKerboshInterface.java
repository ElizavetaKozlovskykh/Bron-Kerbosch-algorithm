import java.util.ArrayList;

public interface BronKerboshInterface {
	public boolean edgeExist(int a, int b);
	public boolean haveAnEdge(ArrayList<Integer> newNot, ArrayList<Integer> newCand);
	public void base();
	public void algBK(ArrayList<Integer> cand, ArrayList<Integer> not);
}
