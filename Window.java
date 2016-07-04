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
    super("Алгоритм Брона-Кербоша");
    
    	c = getContentPane();
	gg = new GraphGraphic();
		
    buttonsPanel = new JPanel(new FlowLayout());
    text1 = new JLabel("<html> Алгоритм Брона-Кербоша — метод ветвей и границ для поиска всех клик (а также максимальных по включению независимых <br> множеств вершин) неориентированного графа. В данном проекте производится поиск клики максимального размера. Алгоритм использует тот факт, что всякая клика в графе является его максимальным по включению полным подграфом. Начиная с одиночной вершины (образующей полный подграф), алгоритм на каждом шаге пытается увеличить уже построенный полный подграф, добавляя в него вершины из множества кандидатов. Высокая скорость обеспечивается отсечением при переборе вариантов, которые заведомо не приведут к построению клики, для чего используется дополнительное множество, в которое помещаются вершины, которые уже были использованы для увеличения полного подграфа. </html>");
    text2 = new JLabel(" ");
    
    construct = new JButton("Построить граф");
    start = new JButton("Начать поиск максимальной клики");
    next = new JButton("Далее");
    
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

    construct.setToolTipText("Нажмите здесь, чтоб построить граф");
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
			  start.setToolTipText("Нажмите здесь, чтоб начать работу алгоритма");
			  buttonsPanel.add(start);
			  revalidate(); repaint();
			  text1.setText("Размер максимальной клики: " + 0);
			  text2.setText("Размер текущей клики: " + 0); 	
			  c.add(gg);
		  }
	  });
	  start.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(start);
			  next.setToolTipText("Нажмите здесь, чтоб начать поиск клики");
			  buttonsPanel.add(next);
			  revalidate(); repaint();
			  text1.setText("Размер максимальной клики: " + 0);
			  text2.setText("Размер текущей клики: " + 0);
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
			  text2.setText("Размер максимальной клики: " + list1.size() );
			  text1.setText("Размер текущей клики: " + maxClique.size() );
			  flag = true;
			  gg.setClique(list1, flag);
			  gg.repaint();
			  
			  if ( !x ) {
				  gg.setClique(maxClique, flag);
				  buttonsPanel.remove(next);
				  revalidate(); repaint();
				  text1.setText("Размер максимальной клики: " + maxClique.size() );
				  text2.setText("Работа алгоритма завершена.");
			  }
		  }
	  });
  }
}
