package Login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.GapContent;

public class MembershipB extends JFrame{
	private JTextField nameT,idT,pwT,tel2T,tel3T,emailT,emailadductionT;
	private JLabel nameL,idL,pwL,phoneL,ageL,ageYearL,ageMonthL,ageDayL,emailL,emailadductionL;
	JButton joinB,calneB,emeilokB,idoverlapB;
	private JComboBox<String> telC,emailC,ageYearC,ageMonthC,ageDayC;
	
	
	public MembershipB() {
		setTitle("회원가입");
		String[]tel= {  "010", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052",
						"053", "054", "055", "061", "062", "063", "064" };
		String[]email= {"gagle.com","naver.com"};
		String[]ageYear= { "1980","1981","1982","1983","1984","1985","1986","1987","1988","1989",
							"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001",
							"2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013",
							"2014","2015","2016","2017","2018","2019"};
		String[]ageMonth= {"01","02","03","04","05","06","07","08","09","10","11","12"};
		String[]ageDay= {"01","02","03","04","05","06","07","08","09","10","11","12","13",
					  "14","15","16","17","18","19","20","21","22","23","24","25","26",
					  "27","28","29","30","31"};
		
		
		
		telC=new JComboBox<String>(tel);
		emailC=new JComboBox<String>(email);
		ageYearC=new JComboBox<String>(ageYear);
		ageMonthC=new JComboBox<String>(ageMonth);
		ageDayC=new JComboBox<String>(ageDay);
		
		nameL=new JLabel("이름");
		nameT=new JTextField(15);
		JPanel p1=new JPanel();
		p1.add(nameL);
		p1.add(nameT);
		
		
		idL=new JLabel("아이디");
		idT=new JTextField(15);
		idoverlapB=new JButton("중복확인");
		JPanel p2=new JPanel();
		p2.add(idL);
		p2.add(idT);
		p2.add(idoverlapB);
		

		
		pwL=new JLabel("비밀번호");
		pwT=new JTextField(15);
		JPanel p3=new JPanel();
		p3.add(pwL);
		p3.add(pwT);
		
		ageL=new JLabel("생년월일");
		ageYearL=new JLabel("년도");
		ageMonthL=new JLabel("월");
		ageDayL=new JLabel("일");
		JPanel p4=new JPanel();
		p4.add(ageL);
		p4.add(ageYearC);
		p4.add(ageYearL);
		p4.add(ageMonthC);
		p4.add(ageMonthL);
		p4.add(ageDayC);
		p4.add(ageDayL);
		
	

		phoneL=new JLabel("전화번호");
		tel2T=new JTextField(5);
		tel3T=new JTextField(5);
		JPanel p5=new JPanel();
		p5.add(phoneL);
		p5.add(telC);
		p5.add(tel2T);
		p5.add(tel3T);
		
		emailL=new JLabel("e-mail");
		emailT=new JTextField(10);
		JPanel p6=new JPanel();
		p6.add(emailL);
		p6.add(emailT);
		p6.add(emailC);
		
		
		emailadductionL=new JLabel("e-mail 인증번호");
		emailadductionT=new JTextField(10);
		emeilokB=new JButton("인증확인");
		JPanel p7=new JPanel();
		p7.add(emailadductionL);
		p7.add(emailadductionT);
		p7.add(emeilokB);
		
		joinB=new JButton("가입");
		calneB=new JButton("취소");
		JPanel p8=new JPanel();
		p8.add(joinB);
		p8.add(calneB);
		
		JPanel p=new JPanel(new GridLayout(8,1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
	
		
		JPanel joinp=new JPanel();
		joinp.add(joinB);
		joinp.add(calneB);
		
		Container contentPane=this.getContentPane();
		contentPane.add("Center",p);
		contentPane.add("South",joinp);
		
		
//		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}

}
