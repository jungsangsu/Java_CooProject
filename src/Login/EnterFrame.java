package Login;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CoControl.CoprocessFrame;

public class EnterFrame extends JFrame implements ActionListener {
	private JTextField idT, pwT;
	private JButton idB, pwB, accessB, searchidB, searchpwB, membershipB;

	public EnterFrame() {
		
		idB = new JButton("아이디");
		idT = new JTextField(15);
		pwB = new JButton("패스워드");
		pwT = new JTextField(15);

		

		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(idB);
		p2.add(idT);
		p2.add(pwB);
		p2.add(pwT);

		searchidB = new JButton("아이디 찾기");
		searchpwB = new JButton("비밀번호 찾기");
		membershipB = new JButton("회원가입");
		accessB = new JButton("입장");

		JPanel p3 = new JPanel();
		p3.add(searchidB);
		p3.add(searchpwB);
		p3.add(membershipB);
		p3.add(accessB);

		Container contentPane = this.getContentPane();
		contentPane.add("Center", p2);
		contentPane.add("South", p3);

		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		accessB.addActionListener(this);
		searchidB.addActionListener(this);
		membershipB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == membershipB) {
			System.out.println("dfsd");
			new Login.membershipB();
		}else if(e.getSource()==searchpwB) {
			new Login.searchpwB();
		} else if (e.getSource() == membershipB) {
			
		} else if (e.getSource() == accessB) {
			new  CoprocessFrame();
		}

	}
}
