
public interface GraphInterface {
	public void clear();
	public void addEdge(Integer x, Integer y);
	public void input();
	public static void main(String[] args) {
		Graph i = new Graph();
		i.input();
		i.output();
	}
}
