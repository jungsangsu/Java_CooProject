package Login;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Newpassword extends JFrame{
	private JLabel newpwL,okpwL;
	private JTextField newpwT,okpwT;
	JButton joinB,cancleB;
	
	public Newpassword() {
		setTitle("비밀번호 설정");
		newpwL =new JLabel("비밀번호");
		newpwT=new JTextField(10);
		JPanel p1=new JPanel();
		p1.add(newpwL);
		p1.add(newpwT);
		
		okpwL=new JLabel("비밀번호 확인");
		okpwT=new JTextField(10);
		JPanel p2=new JPanel();
		p2.add(okpwL);
		p2.add(okpwT);
		
		joinB=new JButton("확인");
		cancleB=new JButton("취소");
		JPanel p3=new JPanel();
		p3.add(joinB);
		p3.add(cancleB);
		
		JPanel p=new JPanel();
		p.add(p1);
		p.add(p2);
		
		Container contentPane=this.getContentPane();
		contentPane.add("Center",p);
		contentPane.add("South",p3);
		
		
//		setVisible(true);
		setResizable(false);
		setBounds(400, 200,250,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
