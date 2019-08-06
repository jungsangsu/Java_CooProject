package Login;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Room.RoomFrame;

public class EnterFrame extends JFrame implements ActionListener {
	private JTextField idT, pwT;
	private JButton idB, pwB, accessB, searchidB, searchpwB, membershipB;
	private JLabel loginL, logoutL;
	private ImageIcon loginC, logoutC, modifiedC;

	MembershipB menbersShipF; //회원가입
	SearchidB searchF; //ID 찾기
	SearchpwB searchpwF; //PASSWORD 찾기
	RoomFrame RoomF; //대기실

	public EnterFrame() {

		menbersShipF = new MembershipB();
		searchF = new SearchidB();
		searchpwF = new SearchpwB();
		RoomF = new RoomFrame();
		
		
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

		loginC = new ImageIcon("img/로그인.png");
		loginL = new JLabel(loginC);

		JPanel p4 = new JPanel();
		p4.add(loginL);

		Container contentPane = this.getContentPane();
		contentPane.add("Center", p2);
		contentPane.add("South", p3);
		contentPane.add("East", p4);

		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		event();

	}

	public void event()
	{
		searchidB.addActionListener(this);
		membershipB.addActionListener(this);
		searchpwB.addActionListener(this);
		
		menbersShipF.calneB.addActionListener(this);
		searchF.calcleB.addActionListener(this);
		searchpwF.cancleB.addActionListener(this);
		
		accessB.addActionListener(this);
		RoomF.exitB.addActionListener(this);
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == membershipB) { // 회원가입
			this.setVisible(false);
			menbersShipF.setVisible(true);
		} else if (e.getSource() == searchpwB) { // 비번찾기
			this.setVisible(false);
			searchpwF.setVisible(true);
		} else if (e.getSource() == searchidB) { // 아이디 찾기
			this.setVisible(false);
			searchF.setVisible(true);
		} else if (e.getSource() == accessB) { // 입장
			this.setVisible(false);
			RoomF.setVisible(true);
		} else if (e.getSource() == menbersShipF.calneB) {
			menbersShipF.setVisible(false);
			this.setVisible(true);
		} else if (e.getSource() == searchF.calcleB) {
			searchF.setVisible(false);
			this.setVisible(true);
		} else if (e.getSource() == searchpwF.cancleB) {
			searchpwF.setVisible(false);
			this.setVisible(true);
		} else if (e.getSource() == RoomF.exitB) {
			RoomF.setVisible(false);
			this.setVisible(true);
		}

	}
}
