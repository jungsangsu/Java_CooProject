package Room;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailPanel extends JPanel {

	public static String labelName[] = { "规 锅龋 :", "      ", "规 林力 :", "      ", "牢盔 荐 : ", "      ", "规 力格 : ",
			"      ", "      " };
	private JLabel labelArray[];
	private JButton enterButton;

	public DetailPanel() {
		this.setLayout(new GridLayout(5, 2, 1, 1));

		labelArray = new JLabel[labelName.length];

		for (int i = 0; i < labelName.length; i++) {
			labelArray[i] = new JLabel(labelName[i]);
			this.add(labelArray[i]);
		}

		enterButton = new JButton("涝 厘");
		this.add(enterButton);

	}

}