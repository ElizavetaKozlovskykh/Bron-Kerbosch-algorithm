import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Window extends JFrame {
  private JLabel text1, text2;
  private JButton construct, start, next;
  private JPanel buttonsPanel;
  	private GraphGraphic gg;
  	private Container c;
  	private boolean flag;
  
  private ArrayList<Integer> list1;
  private ArrayList<Integer> list2;
  private ArrayList<Integer> maxClique;
  private File f; 
  private Scanner scan; 
  private boolean y, x;
  public Window() {
    super("Bron-Kerbosh algorithm");
    
    	c = getContentPane();
		gg = new GraphGraphic();
		
    buttonsPanel = new JPanel(new FlowLayout());
    text1 = new JLabel("Brief description of the project...");
    text2 = new JLabel(" ");
    
    construct = new JButton("Ñonstruct a graph");
    start = new JButton("Start");
    next = new JButton("Next");
    
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    maxClique = new ArrayList<>();
    
    y = true; x = true;
    File f = new File("note.txt");
	try {
		scan = new Scanner(f);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    
    add(text1, BorderLayout.NORTH); 
    add(text2, BorderLayout.BEFORE_LINE_BEGINS);

    construct.setToolTipText("Click here to construct a graph");
    buttonsPanel.add(construct);
    c.add(buttonsPanel, BorderLayout.SOUTH);
    initListeners();
    
    setBounds(0, 0, 800, 730);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void read(ArrayList<Integer> list) {
	  if ( scan.hasNextInt() ) {
		  list.clear();
		  int size = scan.nextInt();
		  for ( int i = 0; i < size; i++ ) {
			  int a = scan.nextInt();
			  list.add(a);
		  };
	  }
	  else x = false;
  }
  private void initListeners() {
	  construct.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(construct);
			  start.setToolTipText("Click here to start the algorithm");
			  buttonsPanel.add(start);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " + 0);
			  text2.setText("Ñurrent clique: " + 0); 	
			  c.add(gg);
		  }
	  });
	  start.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(start);
			  next.setToolTipText("Click here to continue search clique");
			  buttonsPanel.add(next);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " + 0);
			  text2.setText("Ñurrent clique: " + 0);
		  }
	  });
	  next.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  if ( y ) {
				  read(list1);
				  read(list2); 
				  y = false;
			  } 
			  else {
				  list1.clear(); 
				  list1.addAll(list2); 
 
				  if ( x ) read(list2); 
				  if ( list1.size() == list2.size() && !list1.isEmpty() ) {
					  maxClique.clear();
					  maxClique.addAll(list2);
				  }
			  }
			  revalidate(); repaint();
			  text2.setText("Ñurrent clique: " + list1.size() );
			  text1.setText("Maximal clique: " + maxClique.size() );
			  flag = true;
			  gg.setClique(list1, flag);
			  gg.repaint();
			  
			  if ( !x ) {
				  gg.setClique(maxClique, flag);
				  buttonsPanel.remove(next);
				  revalidate(); repaint();
				  text1.setText("Maximal clique: " + maxClique.size() );
				  text2.setText("The end");
			  }
		  }
	  });
  }
}