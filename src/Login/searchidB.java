package Login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FunctionTest.Email.SendMail;

public class SearchidB extends JFrame {
	private JLabel nameL,emailL,emailadductionL;
	JButton joinB,calcleB,emeilokB;
	private JTextField nameT,emailT,emailadductionT;
	private JComboBox<String>emailC;
	
	public SearchidB() {
		setTitle("아이디 찾기");
		String[]email= {"google.com","naver.com"};
		emailC=new JComboBox<String>(email);
		nameL=new JLabel("이름");
		nameT=new JTextField(10);
		JPanel p1=new JPanel();
		p1.add(nameL);
		p1.add(nameT);
		
		emailL=new JLabel("e-mail");
		emailT=new JTextField(10);
		JPanel p2=new JPanel();
		p2.add(emailL);
		p2.add(emailT);
		p2.add(emailC);
		
		emailadductionL=new JLabel("e-mail 인증번호");
		emailadductionT=new JTextField(10);
		emeilokB=new JButton("인증확인");
		
		JPanel p3=new JPanel();
		p3.add(emailadductionL);
		p3.add(emailadductionT);
		p3.add(emeilokB);
	
		joinB=new JButton("확인");
		calcleB=new JButton("취소");
		
		JPanel p4=new JPanel();
		p4.add(joinB);
		p4.add(calcleB);
		
		JPanel p=new JPanel(new GridLayout(3,1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
	
		
		Container contentPane=this.getContentPane();
		contentPane.add("Center",p);
		contentPane.add("South",p4);
		
//		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		emeilokB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String emailString = emailT.getText()+"@"+(String)emailC.getSelectedItem();
				System.out.println(emailString);
				SendMail.SendMail(emailString);
			}
		});
		
	}
}
