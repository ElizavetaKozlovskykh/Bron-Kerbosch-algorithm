import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {
  private int maxClique = 0;
  private JLabel text;
  private JButton button;

  public Window() {
    super("Bron-Kerbosh algorithm");
    //�������������� ���������� �������
    text = new JLabel("Brief description of the project...");
    button = new JButton("Start");

    JPanel buttonsPanel = new JPanel(new FlowLayout()); //�������������� ��������� ����������;
    add(text, BorderLayout.NORTH); //����������� ���������� �� ������;

    button.setToolTipText("Click here to start the algorithm");
    buttonsPanel.add(button);
    add(buttonsPanel, BorderLayout.SOUTH);
    initListeners();
    
    setBounds(100, 100, 700, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void initListeners() {
	  button.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  button.setText("Next");
			  text.setText("Maximal clique: " + maxClique);
		  }
	  });
  }
}