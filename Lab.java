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
		JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		frame.setBackground(Color.white);
		//Border line = BorderFactory.createLineBorder(Color.black);
		frame.setMinimumSize(new Dimension(600,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel topText = new JLabel("Hello World", SwingConstants.LEADING);
		JButton areaButtons[] = new JButton[25];
		topText.setForeground(Color.black);
		topText.setBorder(new EmptyBorder(0, 300, 50, 50));
		topText.setOpaque(true);
		for (int i = 0; i < 25; i++) {//AlexH741/JAPLab1.2
			areaButtons[i] = new JButton();
			areaButtons[i].setPreferredSize(new Dimension(25,25));
			//areaButtons[i].setBorder(new EmptyBorder(0, 0, i * 25, 25));
			buttonPanel.add(areaButtons[i]);
		}
		frame.add(buttonPanel);
		frame.pack();
		frame.setVisible(true);
	}
	//alex is here
}

