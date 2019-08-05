package Login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class searchidB extends JFrame {
	private JButton nameB,emailB,PreviousmenuB;
	private JTextField nameT,emailT;
	
	public searchidB() {
		setTitle("아이디 찾기");
		nameB=new JButton("이름 입력:");
		nameT=new JTextField();
		
		JPanel p1=new JPanel();
		
		
		emailB=new JButton("이메일 입력:");
		emailT=new JTextField();
		
	
		PreviousmenuB=new JButton("이전메뉴");
		
		
		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
}
