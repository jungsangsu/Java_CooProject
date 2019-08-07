package Room;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CoControl.CoprocessFrame;
import oracle.net.aso.c;

public class RoomMake extends JFrame {

	private JLabel title, subject, pw, num;
	private JTextField tf, pf;
	private JCheckBox cb;
	JButton makeB, canB;
	private JComboBox<String> combo, combo1;

	CoprocessFrame cpf;
	
	public RoomMake() {
		
		cpf = new CoprocessFrame();
		
		title = new JLabel("방제목 :");
		subject = new JLabel("방주제 :");
		pw = new JLabel("비밀번호 :");
		num = new JLabel("인원수 :");

		tf = new JTextField(15);
		pf = new JTextField(15);

		String[] com = { "경영", "마케팅", "고객관리", "개발", "디자인" };
		combo = new JComboBox<String>(com);

		cb = new JCheckBox();

		String[] com1 = { "2명", "3명", "4명", "5명", "6명", "7명", "8명", "9명", };
		combo1 = new JComboBox<String>(com1);

		makeB = new JButton("만들기");
		canB = new JButton("취 소");

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(title);
		panel1.add(tf);

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(pw);
		panel2.add(pf);
		panel2.add(cb);

		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(subject);
		panel3.add(combo);

		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(num);
		panel4.add(combo1);

		JPanel totpanel = new JPanel(new GridLayout(4, 1, 0, 0));
		totpanel.add(panel1);
		totpanel.add(panel2);
		totpanel.add(panel3);
		totpanel.add(panel4);

		JPanel btpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btpanel.add(makeB);
		btpanel.add(canB);

		Container c = this.getContentPane();
		c.add("Center", totpanel);
		c.add("South", btpanel);

		setResizable(false);
		setBounds(400, 200, 400, 300);
//		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		makeB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cpf.setVisible(true);
			}
		});
		
	}
	
//	public static void main(String[] args) {
//		new RoomMake();
//	}
}