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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FunctionTest.Email.SendMail;

public class SearchidB extends JFrame  {
	private JLabel nameL, emailL, emailadductionL, emailcomL, ageL, ageYearL, ageMonthL, 
	ageDayL,phoneL,mainiconL;
	JButton okpwB, joinB, cancelB, emeilokB, emailB;
	JTextField newpwT, okpwT, nameT, idT, emailT, emailadductionT, tel2T, tel3T;
	JComboBox<String> telC, emailC, ageYearC, ageMonthC, ageDayC;
	ImageIcon mainicon;

	public SearchidB() {
		setTitle("아이디 찾기");
		String[] tel = { "010", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054",
				"055", "061", "062", "063", "064" };
		String[] email = { "naver.com", "gmail.com" };
		String[] ageYear = { "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990",
				"1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003",
				"2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019" };
		String[] ageMonth = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String[] ageDay = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		telC = new JComboBox<String>(tel);
		emailC = new JComboBox<String>(email);
		ageYearC = new JComboBox<String>(ageYear);
		ageMonthC = new JComboBox<String>(ageMonth);
		ageDayC = new JComboBox<String>(ageDay);

		nameL = new JLabel("이름");
		nameT = new JTextField(10);
		JPanel p1 = new JPanel();
		p1.add(nameL);
		p1.add(nameT);

		emailC = new JComboBox<String>(email);
		emailL = new JLabel("이메일");
		emailcomL = new JLabel("@");
		emailT = new JTextField(10);
		emailB = new JButton("인증번호 전송");
		emailB.setBackground(Color.WHITE);
		JPanel p2 = new JPanel();
		p2.add(emailL);
		p2.add(emailT);
		p2.add(emailcomL);
		p2.add(emailC);
		p2.add(emailB);

		ageL = new JLabel("생년월일");
		ageYearL = new JLabel("년도");
		ageMonthL = new JLabel("월");
		ageDayL = new JLabel("일");
		JPanel p3 = new JPanel();
		p3.add(ageL);
		p3.add(ageYearC);
		p3.add(ageYearL);
		p3.add(ageMonthC);
		p3.add(ageMonthL);
		p3.add(ageDayC);
		p3.add(ageDayL);

		phoneL = new JLabel("전화번호");
		tel2T = new JTextField(5);
		tel3T = new JTextField(5);
		JPanel p4 = new JPanel();
		p4.add(phoneL);
		p4.add(telC);
		p4.add(tel2T);
		p4.add(tel3T);

		emailadductionL = new JLabel("인증번호");// email-인증번호
		emailadductionT = new JTextField(10);
		emeilokB = new JButton("확인");
		emeilokB.setBackground(Color.WHITE);
		JPanel p5 = new JPanel();
		p5.add(emailadductionL);
		p5.add(emailadductionT);
		p5.add(emeilokB);

		joinB = new JButton("확인");
		joinB.setBackground(Color.WHITE);
		cancelB = new JButton("취소");
		cancelB.setBackground(Color.WHITE);
		JPanel p6 = new JPanel();
		p6.add(joinB);
		p6.add(cancelB);
		
	
		mainicon =new ImageIcon("img/CooSeriveicon3.jpg");
		mainiconL =new JLabel(mainicon);
		setSize(44,65);
		JPanel p7 = new JPanel();
		p7.add(mainiconL);
		
		JPanel p = new JPanel(new GridLayout(6, 1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);

		Container contentPane = this.getContentPane();
		contentPane.add("Center", p);
		contentPane.add("South", p6);
		contentPane.add("East", p7);


//      setVisible(true);
		setResizable(false);
		setBounds(400, 200, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}