package Login;
import java.awt.Color;
//  회원정보 클래스 
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


	public class Information extends JFrame  {
		JTextField nameT, pwT, tel2T, tel3T, emailT, emailadductionT,idT;
		JLabel nameL,  pwL, phoneL, ageL, ageYearL, ageMonthL, ageDayL, emailL,emailcomL,
		idL, mainiconL;
		JButton joinB, calneB,deleteB,idoverlapB; 
		JComboBox<String> telC, emailC, ageYearC, ageMonthC, ageDayC;
		ImageIcon mainicon;

		public Information() {
			setTitle("회원정보");
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
			
		
			idL = new JLabel("아이디");
			
			idT = new JTextField(15);
			idT.setEditable(false); 
			
			idoverlapB = new JButton("중복확인");  
			idoverlapB.setVisible(false);
			JPanel p1 = new JPanel();
			p1.add(idL);
			p1.add(idT);
			p1.add(idoverlapB);

			nameL = new JLabel("이름");
			nameT = new JTextField(15);
			JPanel p2 = new JPanel();
			p2.add(nameL);
			p2.add(nameT);


			pwL = new JLabel("비밀번호");
			pwT = new JTextField(15);
			JPanel p3 = new JPanel();
			p3.add(pwL);
			p3.add(pwT);

			ageL = new JLabel("생년월일");
			ageYearL = new JLabel("년도");
			ageMonthL = new JLabel("월");
			ageDayL = new JLabel("일");
			JPanel p4 = new JPanel();
			p4.add(ageL);
			p4.add(ageYearC);
			p4.add(ageYearL);
			p4.add(ageMonthC);
			p4.add(ageMonthL);
			p4.add(ageDayC);
			p4.add(ageDayL);

			phoneL = new JLabel("전화번호");
			tel2T = new JTextField(5);
			tel3T = new JTextField(5);
			JPanel p5 = new JPanel();
			p5.add(phoneL);
			p5.add(telC);
			p5.add(tel2T);
			p5.add(tel3T);

			emailL = new JLabel("e-mail");
			emailT = new JTextField(10);
			emailcomL = new JLabel("@");

			JPanel p6 = new JPanel();
			p6.add(emailL);
			p6.add(emailT);
			p6.add(emailcomL);
			p6.add(emailC);



			joinB = new JButton("수정");
			joinB.setBackground(Color.WHITE);
			deleteB= new JButton("탈퇴");
			deleteB.setBackground(Color.WHITE);
			calneB = new JButton("취소");
			calneB.setBackground(Color.WHITE);
			JPanel p7 = new JPanel();
			p7.add(joinB);
			p7.add(deleteB);
			p7.add(calneB);
			
			mainicon =new ImageIcon("img/CooSeriveicon3.jpg");
			mainiconL =new JLabel(mainicon);
			setSize(44,65);
			JPanel p8 = new JPanel();
			p8.add(mainiconL);

			JPanel p = new JPanel(new GridLayout(7, 1));
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			p.add(p5);
			p.add(p6);
			p.add(p7);
			
	

			JPanel joinp = new JPanel();
			joinp.add(joinB);
			joinp.add(deleteB);
			joinp.add(calneB);

			Container contentPane = this.getContentPane();
			contentPane.add("Center", p);
			contentPane.add("South", joinp);
			contentPane.add("East", p8);
			

//			setVisible(true);
			setResizable(false);
			setBounds(400, 200, 1000, 800);
//			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		}
	
	

	}

	
