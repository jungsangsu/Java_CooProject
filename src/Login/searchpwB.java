package Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchpwB extends JFrame {
	private JLabel nameL,idL,emailL,emailadductionL,ageL, ageYearL, ageMonthL, ageDayL, phoneL,emailcomL, mainiconL;
	JButton okpwB,joinB,cancleB,emailokB,emailB;
	JTextField newpwT,okpwT,nameT,idT,emailT,emailadductionT,tel2T, tel3T;
	JComboBox<String> telC, emailC, ageYearC, ageMonthC, ageDayC;
	ImageIcon mainicon;
	
	
	public SearchpwB() {
	setTitle("비밀번호 찾기");

	String[] email = { "naver.com", "gmail.com" };

	emailC = new JComboBox<String>(email);

	
	idL=new JLabel("아이디");
	idT=new JTextField(10);
	JPanel p1=new JPanel();
	p1.add(idL);
	p1.add(idT);
	
	nameL=new JLabel("이름");
	nameT=new JTextField(10);
	
	JPanel p2=new JPanel();
	p2.add(nameL);
	p2.add(nameT);
	
	emailC = new JComboBox<String>(email);
	emailL = new JLabel("이메일");
	emailcomL = new JLabel("@");
	emailT = new JTextField(10);
	emailB = new JButton("인증번호 전송");
	emailB.setBackground(Color.WHITE);
	JPanel p3 = new JPanel();
	p3.add(emailL);
	p3.add(emailT);
	p3.add(emailcomL);
	p3.add(emailC);
	p3.add(emailB);
	


	
	emailadductionL=new JLabel("임시비밀번호");
	emailadductionT=new JTextField(10);
	emailokB=new JButton("확인");
	emailokB.setBackground(Color.WHITE);
	JPanel p6=new JPanel();
	p6.add(emailadductionL);
	p6.add(emailadductionT);
	p6.add(emailokB);
	
	mainicon =new ImageIcon("img/CooSeriveicon3.jpg");
	mainiconL =new JLabel(mainicon);
	setSize(44,65);
	JPanel p7 = new JPanel();
	p7.add(mainiconL);
	
	joinB=new JButton("확인");
	joinB.setBackground(Color.WHITE);
	cancleB=new JButton("취소");
	cancleB.setBackground(Color.WHITE);
	JPanel p8=new JPanel();
	p8.add(joinB);
	p8.add(cancleB);

	 
	JPanel p= new JPanel(new GridLayout(6,1));
	p.add(p1);
	p.add(p2);
	p.add(p3);
//	p.add(p4);
//	p.add(p5);
	p.add(p6);
	
	Container contentPane=this.getContentPane();
	contentPane.add("Center",p);
	contentPane.add("South",p8);
	contentPane.add("East", p7);
	
//	setVisible(true);
	setResizable(false);
	setBounds(400, 200,500,500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
}

