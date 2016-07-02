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
  private ArrayList<Integer> list1, list2, maxClique;
  File f; Scanner scan;

  public Window() {
    super("Bron-Kerbosh algorithm");
    //Подготавливаем компоненты объекта
    buttonsPanel = new JPanel(new FlowLayout());
    text1 = new JLabel("Brief description of the project...");
    text2 = new JLabel(" ");
    construct = new JButton("Сonstruct a graph");
    start = new JButton("Start");
    next = new JButton("Next");
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    maxClique = new ArrayList<>();
    
    File f = new File("note.txt");
	try {
		Scanner scan = new Scanner(f);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    add(text1, BorderLayout.NORTH); //расставляем компоненты по местам;
    add(text2);

    construct.setToolTipText("Click here to construct a graph");
    buttonsPanel.add(construct);
    add(buttonsPanel, BorderLayout.SOUTH);
    initListeners();
    
    setBounds(100, 100, 600, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void print(ArrayList<Integer> list) {
	  list.clear();
	  int a, size;
	  //File f = new File("note.txt");
	  //Scanner scan = new Scanner(f);
	  size = scan.nextInt();
	  //if ( size != 0 )
		  for ( int i = 0; i < size; i++ ) {
			  a = scan.nextInt();
			  list.add(a);
		  };
	  
  }
  private void paint() {}
  private void initListeners() {
	  construct.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(construct);
			  start.setToolTipText("Click here to start the algorithm");
			  buttonsPanel.add(start);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " + 0);
			  text2.setText("Сurrent clique: " + 0); 
			  
		  }
	  });
	  start.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(start);
			  next.setToolTipText("Click here to continue search clique");
			  buttonsPanel.add(next);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " + 0);
			  text2.setText("Сurrent clique: " + 0);
		  }
	  });
	  next.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  do {
				  print(list1); Integer a = list1.size();
				  print(list2); Integer b = list2.size();
				  if ( a.equals(b) ) {
					  System.out.println("a: " + a + " b: " + b);
					  maxClique.clear();
					  maxClique.addAll(list2);
				  }
				  text2.setText("Сurrent clique: " + list1.size() );
				  text1.setText("Maximal clique: " + maxClique.size() );
				  revalidate(); repaint();
				  paint();
			  } while ( !list2.isEmpty() );
			  
			  buttonsPanel.remove(next);
			  revalidate(); repaint();
			  text1.setText("The end" );
		  }
	  });
  }
}