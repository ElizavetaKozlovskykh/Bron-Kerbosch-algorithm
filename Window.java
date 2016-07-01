import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Window extends JFrame {
  private JLabel text1, text2;
  private JButton construct, start, next;
  private JPanel buttonsPanel;

  public Window() {
    super("Bron-Kerbosh algorithm");
    //Подготавливаем компоненты объекта
    buttonsPanel = new JPanel(new FlowLayout());
    text1 = new JLabel("Brief description of the project...");
    text2 = new JLabel(" ");
    construct = new JButton("Сonstruct a graph");
    start = new JButton("Start");
    next = new JButton("Next");

    add(text1, BorderLayout.NORTH); //расставляем компоненты по местам;
    add(text2);

    construct.setToolTipText("Click here to construct a graph");
    buttonsPanel.add(construct);
    add(buttonsPanel, BorderLayout.SOUTH);
    initListeners();
    
    setBounds(100, 100, 600, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void initListeners() {
	  construct.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(construct);
			  start.setToolTipText("Click here to start the algorithm");
			  buttonsPanel.add(start);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " );
			  text2.setText("Сurrent clique: " ); 
			  
		  }
	  });
	  start.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  buttonsPanel.remove(start);
			  next.setToolTipText("Click here to continue search clique");
			  buttonsPanel.add(next);
			  revalidate(); repaint();
			  text1.setText("Maximal clique: " );
			  text2.setText("Сurrent clique: " );
		  }
	  });
	  next.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  //for ( ;; ) {
			  text1.setText("Maximal clique: " );
			  text2.setText("Сurrent clique: " );
			  //}
		  }
	  });
  }
}