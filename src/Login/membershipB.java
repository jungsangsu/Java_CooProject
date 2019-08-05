package Login;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.GapContent;

public class membershipB extends JFrame{
	private JTextField nameT,idT,pwT,ageT,tel2T,tel3T,emailT;
	private JButton nameB,idB,pwB,ageB,phoneB,emailB,joinB;
	private JComboBox<String> telC,emailC;
	
	
	public membershipB() {
		setTitle("회원가입");
		String[]tel= { "010", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052",
				"053", "054", "055", "061", "062", "063", "064" };
		String[]email= {"gagle.com","naver.com"};
		
		telC=new JComboBox<String>(tel);
		emailC=new JComboBox<String>(email);
		nameB=new JButton("이름");
		nameT=new JTextField(15);
		JPanel p1=new JPanel();
		p1.add(nameB);
		p1.add(nameT);
		
		
		idB=new JButton("아이디");
		idT=new JTextField(15);
		JPanel p2=new JPanel();
		p2.add(idB);
		p2.add(idT);

		
		pwB=new JButton("비밀번호");
		pwT=new JTextField(15);
		JPanel p3=new JPanel();
		p3.add(pwB);
		p3.add(pwT);
		
		ageB=new JButton("나이");
		ageT=new JTextField(15);
		JPanel p4=new JPanel();
		p4.add(ageB);
		p4.add(ageT);

		phoneB=new JButton("전화번호");
		tel2T=new JTextField(5);
		tel3T=new JTextField(5);
		JPanel p5=new JPanel();
		p5.add(phoneB);
		p5.add(telC);
		p5.add(tel2T);
		p5.add(tel3T);
		
		emailB=new JButton("e-mail");
		emailT=new JTextField(10);
		JPanel p6=new JPanel();
		p6.add(emailB);
		p6.add(emailT);
		p6.add(emailC);
		
		joinB=new JButton("가입");	
		JPanel p7=new JPanel();
		p7.add(joinB);
		
		JPanel p=new JPanel(new GridLayout(6,1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
	
		JPanel joinp=new JPanel();
		joinp.add(joinB);
		
		Container contentPane=this.getContentPane();
		contentPane.add("Center",p);
		contentPane.add("South",joinp);
		
		
		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
