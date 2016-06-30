
public class Test {
	public static void main(String[] args) {
		//GraphInterface i = new Graph();
		//i.output();
		BronKerboshInterface i = new BronKerbosh();
		i.base();
		
		Window app = new Window();
	    app.setVisible(true);
	}
}
