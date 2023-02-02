import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Lab {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello World Java Swing");
		//JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		JPanel buttonPanel = new JPanel();
		frame.setBackground(Color.white);
		//Border line = BorderFactory.createLineBorder(Color.black);
		frame.setMinimumSize(new Dimension(600,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel topText = new JLabel("Hello World", SwingConstants.LEADING);
		JButton areaButtons[] = new JButton[16];
		topText.setForeground(Color.black);
		topText.setBorder(new EmptyBorder(0, 300, 50, 50));
		topText.setOpaque(true);
		for (int i = 0; i < 16; i++) {
			areaButtons[i] = createButton(i* 25, i*25, 50, 50);//addActionListener, addMouseListener
			areaButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			 });
			//areaButtons[i].setBorder(new EmptyBorder(0, 0, i * 25, 25));
			buttonPanel.add(areaButtons[i]);
		}
		frame.add(buttonPanel);
		frame.pack();
		frame.setVisible(true);
	}

	public static JButton createButton(int x, int y, int w, int h) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(w, h));
		return button;
	}
}

