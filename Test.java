import java.awt.*;
import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		GraphInterface i = new Graph();
		i.input();
		i.output();
		
		Window app = new Window();
	    app.setVisible(true);
	}
}
