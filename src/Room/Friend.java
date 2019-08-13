package Room;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Friend extends JFrame {
	private JLabel Friendid, mainiconL;
	private JTextField idT;
	private JButton okB,canbleB;
	ImageIcon mainicon;
	
	public Friend() {
		setTitle("친구 추가");
		Friendid = new JLabel("아이디");
		idT = new JTextField(10);
		
		JPanel p1=new JPanel();
		p1.add(Friendid);
		p1.add(idT);
		
		
		okB = new JButton("확인");
		okB.setBackground(Color.WHITE);
		canbleB = new JButton("취소");
		canbleB.setBackground(Color.WHITE);
		
		mainicon =new ImageIcon("img/CooSeriveicon3.jpg");
		mainiconL =new JLabel(mainicon);
		setSize(44,65);
		
		JPanel p7 = new JPanel();
		p7.add(mainiconL);
		
		JPanel  p = new JPanel();
		p.add(okB);
		p.add(canbleB);
		
		Container contentPane=this.getContentPane();
		contentPane.add("Center",p1);
		contentPane.add("South",p);
		contentPane.add("East", p7);
		
		
		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 280, 180);
		
	}
	public static void main(String[] args) {
		new Friend();
	}

}
