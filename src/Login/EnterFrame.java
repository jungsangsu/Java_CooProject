package Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Action.Protocol;
import CoControl.CoprocessFrame;
import FunctionTest.Email.SendMail;
import Room.RoomFrame;
import Room.RoomMake;

public class EnterFrame extends JFrame implements ActionListener, Runnable, ListSelectionListener {
	private JPasswordField pwT;
	private JTextField idT;
	private JButton idB, pwB, accessB, searchidB, searchpwB, membershipB;
	private JLabel loginL, logoutL, mainL;
	private ImageIcon loginC, logoutC, modifiedC, mainC;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	MembershipB menbersShipF; // 회원가입
	SearchidB searchF; // ID 찾기
	SearchpwB searchpwF; // PASSWORD 찾기
	RoomFrame RoomF; // 대기실
	RoomMake rMakeF; // 방만들기
	CoprocessFrame chattingF;
	Information MemberInfoF; // 회원정보

	private String sNumber = "><^^"; // default 시크릿넘버
	private boolean condition_S = false; // 이메일 인증확인
	private boolean condition_Id = false; // ID 중복체크

	public EnterFrame() {
		setTitle("CooSerivice");

		network();

		menbersShipF = new MembershipB();
		searchF = new SearchidB();
		searchpwF = new SearchpwB();
		RoomF = new RoomFrame(br, pw);
		rMakeF = new RoomMake();
		chattingF = new CoprocessFrame();
		MemberInfoF = new Information(); // 회원정보

		idB = new JButton("아이디");
		idB.setBackground(Color.WHITE);
		idT = new JTextField(15);

		pwB = new JButton("패스워드");
		pwB.setBackground(Color.WHITE);
		pwT = new JPasswordField(15);
		pwT.setEchoChar('*');

		JPanel p = new JPanel(new FlowLayout());
		p.add(idB);
		p.add(idT);
		p.add(pwB);
		p.add(pwT);

		searchidB = new JButton("아이디 찾기");
		searchidB.setBackground(Color.WHITE);
		searchpwB = new JButton("비밀번호 찾기");
		searchpwB.setBackground(Color.WHITE);
		membershipB = new JButton("회원가입");
		membershipB.setBackground(Color.WHITE);
		accessB = new JButton("입장");
		accessB.setBackground(Color.WHITE);

		JPanel p2 = new JPanel();
		p2.add(membershipB);
		p2.add(searchidB);
		p2.add(searchpwB);
		p2.add(accessB);

		mainC = new ImageIcon("img/CooSeriveMain.png"); // main 배경
		mainL = new JLabel(mainC);

		JPanel main = new JPanel();
		main.add(mainL);

		Container contentPane = this.getContentPane();
		contentPane.add("South", p);
		contentPane.add("North", p2);
		contentPane.add("Center", main);

		setBackground(Color.white);
		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		event();

	}

	public void event() {
		// --------------------회원가입관련----------------------------------
		membershipB.addActionListener(this); // 회원가입(버튼)
		menbersShipF.calneB.addActionListener(this); // 회원가입 취소(로그인화면으로)
		menbersShipF.joinB.addActionListener(this); // 회원가입 화면에서 join
		menbersShipF.idoverlapB.addActionListener(this);// 회원가입 화면 중복확인
		menbersShipF.emailB.addActionListener(this);// 회원가입 이메일 전송
		menbersShipF.emeilokB.addActionListener(this); // 이메일 인증확인

		// --------------------ID찾기관련----------------------------------
		searchidB.addActionListener(this); // 아이디 찾기
		searchF.joinB.addActionListener(this); // 아이디찾기 (join버튼)
		searchF.emailB.addActionListener(this); // 아이디찾기 (Email 인증전송)
		searchF.emeilokB.addActionListener(this); // 아이디찾기(Email 인증확인)
		searchF.cancelB.addActionListener(this); // ID찾기 취소

		// --------------------PW찾기관련----------------------------------
		searchpwB.addActionListener(this); // PW 찾기
		searchpwF.cancleB.addActionListener(this); // PW찾기 취소
		searchpwF.joinB.addActionListener(this); // PW찾기 확인
		searchpwF.emailokB.addActionListener(this); // PW찾기 이메일 인증 확인 --->임시 비밀번호 로그인
		searchpwF.emailB.addActionListener(this);// PW찾기 이메일 인증 전송
		// --------------------로그인관련----------------------------------
		accessB.addActionListener(this); // 입장(Login)
		RoomF.exitB.addActionListener(this); // Room -> 로그인Page

		// ---------------------회원정보 수정 및 탈퇴 관련------------------------
		RoomF.InformationB.addActionListener(this); // Room-->회원정보
		MemberInfoF.joinB.addActionListener(this);
		MemberInfoF.calneB.addActionListener(this);
		MemberInfoF.deleteB.addActionListener(this);
		MemberInfoF.idoverlapB.addActionListener(this);

		// ---------------------메세지 관련---------------------------------
		RoomF.sendB.addActionListener(this); // 대기방에서 전송
		//RoomF.friendB.addActionListener(this); // 대기방에서 친구추가

		// ----------------------방 관련 ------------------------------------
		RoomF.makeB.addActionListener(this);
		rMakeF.makeB.addActionListener(this);
		rMakeF.canB.addActionListener(this);
		chattingF.exitB.addActionListener(this);
		chattingF.sendB.addActionListener(this);
		// ----------------------채팅방 관련 ---------------------------------
		chattingF.openB.addActionListener(this);
		chattingF.saveB.addActionListener(this);
		chattingF.loadB.addActionListener(this);
		chattingF.list2.addListSelectionListener(this);

		//
	}

	public void network() {

		// 소켓 생성
		try {
			socket = new Socket("192.168.0.43", 9500); // 192.168.0.43 -->Room502 아이피 주소
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}

		// 이벤트

		// 스레드 생성
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == membershipB) { // 메인페이지 -----------> 회원가입버튼
			this.setVisible(false);
			menbersShipF.setVisible(true);
		} else if (e.getSource() == menbersShipF.joinB) { // 회원가입페이지 -----------> 가입버튼

			String name = menbersShipF.nameT.getText();
			String id = menbersShipF.idT.getText();
			String pw1 = menbersShipF.pwT.getText();
			String ageYear = (String) menbersShipF.ageYearC.getSelectedItem();
			String ageMonth = (String) menbersShipF.ageMonthC.getSelectedItem();
			String ageDay = (String) menbersShipF.ageDayC.getSelectedItem();
			String tel = (String) menbersShipF.telC.getSelectedItem();
			String tel2 = menbersShipF.tel2T.getText();
			String tel3 = menbersShipF.tel3T.getText();
			String email = menbersShipF.emailT.getText();
			String email1 = (String) menbersShipF.emailC.getSelectedItem();
			String emailok = menbersShipF.emailadductionT.getText();

			if (name.length() == 0 || id.length() == 0 || pw1.length() == 0 || tel2.length() == 0 || tel3.length() == 0
					|| email.length() == 0 || emailok.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈간을 입력해주세요");
			} else if (condition_S && condition_Id) { // -> 이메일, 중복확인 인증이 된거

				String line = "";
				line += (menbersShipF.idT.getText() + "%" + menbersShipF.pwT.getText() + "%"
						+ menbersShipF.nameT.getText() + "%" + menbersShipF.ageYearC.getSelectedItem()
						+ menbersShipF.ageMonthC.getSelectedItem() + menbersShipF.ageDayC.getSelectedItem() + "%"
						+ menbersShipF.emailT.getText() + "@" + menbersShipF.emailC.getSelectedItem()) + "%"
						+ menbersShipF.telC.getSelectedItem() + "" + menbersShipF.tel2T.getText()
						+ menbersShipF.tel3T.getText();
				System.out.println(line);

				pw.println(Protocol.REGISTER + "|" + line);
				pw.flush();
				JOptionPane.showMessageDialog(this, "회원가입 완료");
				menbersShipF.setVisible(false);
				this.setVisible(true);

				menbersShipF.nameT.setText("");
				menbersShipF.idT.setText("");
				menbersShipF.pwT.setText("");
				menbersShipF.ageYearC.setSelectedIndex(0);
				menbersShipF.ageMonthC.setSelectedIndex(0);
				menbersShipF.ageDayC.setSelectedIndex(0);
				menbersShipF.telC.setSelectedIndex(0);
				menbersShipF.tel2T.setText("");
				menbersShipF.tel3T.setText("");
				menbersShipF.emailT.setText("");
				menbersShipF.emailC.setSelectedIndex(0);
				menbersShipF.emailadductionT.setText("");

				condition_S = false;
				condition_Id = false;
				sNumber = "><^^";

			} else if (!condition_Id && condition_S) {
				JOptionPane.showMessageDialog(this, "ID 중복확인 해주세요");
			} else if (!condition_S && condition_Id) {
				JOptionPane.showMessageDialog(this, "이메일 인증을 해주세요");
			} else if (!condition_Id && !condition_S) {
				JOptionPane.showMessageDialog(this, "ID 중복,이메일 인증을 해주세요");
			}

		} else if (e.getSource() == menbersShipF.calneB) { // 회원가입페이지 -----------> 취소
			menbersShipF.setVisible(false);
			this.setVisible(true);
			condition_S = false;
			sNumber = "><^^";

		} else if (e.getSource() == menbersShipF.idoverlapB) { // 회원가입 페이지ID -----------> 중복확인

			if (menbersShipF.idT.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디 입력하세요");
			} else {
				pw.println(Protocol.IDSEARCHCHECK + "|" + menbersShipF.idT.getText());
				pw.flush();
			}

		} else if (e.getSource() == menbersShipF.emailB) // 회원가입 페이지 -----------> 인증번호 전송
		{
			if (menbersShipF.emailT.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "이메일 입력하세요");
			} else {
				JOptionPane.showMessageDialog(this, "인증번호가 전송되었습니다.");
				String emailString = menbersShipF.emailT.getText() + "@"
						+ (String) menbersShipF.emailC.getSelectedItem();
				System.out.println(emailString);
				sNumber = String.valueOf(SendMail.SendMail(emailString));
			}

		} else if (e.getSource() == menbersShipF.emeilokB) { // 회원가입 페이지 -----------> 인증번호확인
			if (sNumber.compareTo(menbersShipF.emailadductionT.getText()) == 0) {
				JOptionPane.showMessageDialog(this, "인증되었습니다");
				condition_S = true;
			} else {
				JOptionPane.showMessageDialog(this, "인증번호가 틀렸습니다");
			}
		} else if (e.getSource() == searchpwB) { // 메인페이지 -----------> 비번찾기 버튼
			this.setVisible(false);
			searchpwF.setVisible(true);
		} else if (e.getSource() == searchidB) { // 메인페이지 -----------> 아이디 찾기
			this.setVisible(false);
			searchF.setVisible(true);
		} else if (e.getSource() == searchF.joinB) { // ID 찾기 -----------> 확인
			String name = searchF.nameT.getText();
			String ageYear = (String) searchF.ageYearC.getSelectedItem();
			String ageMonth = (String) searchF.ageMonthC.getSelectedItem();
			String ageDay = (String) searchF.ageDayC.getSelectedItem();
			String tel2 = searchF.tel2T.getText();
			String tel3 = searchF.tel3T.getText();
			String email = searchF.emailT.getText();
			String email1 = (String) searchF.emailC.getSelectedItem();
			String emailok = searchF.emailadductionT.getText();

			if (name.length() == 0 || tel2.length() == 0 || tel3.length() == 0 || email.length() == 0
					|| emailok.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요");
			} else if (condition_S) {
				String line = "";

				line += (searchF.nameT.getText() + "%" + searchF.ageYearC.getSelectedItem()
						+ searchF.ageMonthC.getSelectedItem() + searchF.ageDayC.getSelectedItem() + "%"
						+ searchF.emailT.getText() + "@" + searchF.emailC.getSelectedItem()) + "%"
						+ searchF.telC.getSelectedItem() + "" + searchF.tel2T.getText() + searchF.tel3T.getText();
				System.out.println(line);

				pw.println(Protocol.IDSEARCH + "|" + line);
				pw.flush();

				searchF.nameT.setText("");
				searchF.ageYearC.setSelectedIndex(0);
				searchF.ageMonthC.setSelectedIndex(0);
				searchF.ageDayC.setSelectedIndex(0);
				searchF.telC.setSelectedIndex(0);
				searchF.tel2T.setText("");
				searchF.tel3T.setText("");
				searchF.emailT.setText("");
				searchF.emailC.setSelectedIndex(0);
				searchF.emailadductionT.setText("");
				searchF.emailadductionT.setText("");
				condition_S = false;
				sNumber = "><^^";
			} else if (!condition_S) {
				JOptionPane.showMessageDialog(this, "이메일 인증을 해주세요");
			}

		} else if (e.getSource() == searchF.emeilokB) // ID찾기페이지 -----------> 인증번호 확인
		{
			if (sNumber.compareTo(searchF.emailadductionT.getText()) == 0) {
				JOptionPane.showMessageDialog(this, "인증되었습니다");
				condition_S = true;
			} else {
				JOptionPane.showMessageDialog(this, "인증번호가 틀렸습니다");
			}
		} else if (e.getSource() == searchF.emailB) // ID찾기 페이지 -----------> 인증번호 전송
		{
			if (searchF.emailT.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "이메일 입력하세요");
			} else {
				JOptionPane.showMessageDialog(this, "인증번호가 전송되었습니다.");
				String emailString = searchF.emailT.getText() + "@" + (String) searchF.emailC.getSelectedItem();
				System.out.println(emailString);
				sNumber = String.valueOf(SendMail.SendMail(emailString));
			}
		} else if (e.getSource() == searchF.cancelB) { // ID찾기페이지 -----------> ID찾기 취소

			searchF.setVisible(false);
			this.setVisible(true);
			searchF.nameT.setText("");
			searchF.ageYearC.setSelectedIndex(0);
			searchF.ageMonthC.setSelectedIndex(0);
			searchF.ageDayC.setSelectedIndex(0);
			searchF.telC.setSelectedIndex(0);
			searchF.tel2T.setText("");
			searchF.tel3T.setText("");
			searchF.emailT.setText("");
			searchF.emailC.setSelectedIndex(0);
			searchF.emailadductionT.setText("");
			searchF.emailadductionT.setText("");
			condition_S = false;
			sNumber = "><^^";

		} else if (e.getSource() == searchpwF) {// PW찾기

		} else if (e.getSource() == searchpwF.joinB) {// PW비밀번호 찾기(확인)
			String name = searchpwF.nameT.getText();
			String id = searchpwF.idT.getText();
			String email = searchpwF.emailT.getText();
			String email1 = (String) searchpwF.emailC.getSelectedItem();
			String emailok = searchpwF.emailadductionT.getText();

			if (name.length() == 0 || id.length() == 0 || email.length() == 0 || emailok.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요");

			} else if (condition_S) {
				String line = "";
				line += (id + "%" + name + "%" + email + "@" + email1 + "%" + emailok);
				System.out.println(line);

				pw.println(Protocol.PWSEARCH + "|" + line);
				pw.flush();

				searchpwF.nameT.setText("");
				searchpwF.idT.setText("");
				searchpwF.ageYearC.setSelectedIndex(0);
				searchpwF.ageMonthC.setSelectedIndex(0);
				searchpwF.ageDayC.setSelectedIndex(0);
				searchpwF.telC.setSelectedIndex(0);
				searchpwF.tel2T.setText("");
				searchpwF.tel3T.setText("");
				searchpwF.emailT.setText("");
				searchpwF.emailC.setSelectedIndex(0);
				searchpwF.emailadductionT.setText("");
				condition_S = false;
				sNumber = "><^^";

			}

		} else if (e.getSource() == searchpwF.emailokB) { // -->pw찾기 -->이메일 인증OK
			{
				if (sNumber.compareTo(searchpwF.emailadductionT.getText()) == 0) {
					JOptionPane.showMessageDialog(this, "임시비밀 번호로 수정되었습니다.");
					condition_S = true;

				} else {
					JOptionPane.showMessageDialog(this, "임시비밀번호가 틀렸습니다");
				}
			}

		} else if (e.getSource() == searchpwF.emailB) { // PW찾기 페에지 ----->인증번호 전송
			if (searchpwF.emailT.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "이메일 입력하세요");
			} else {
				String emailString = searchpwF.emailT.getText() + "@" + (String) searchpwF.emailC.getSelectedItem();
				System.out.println(emailString);
				sNumber = String.valueOf(SendMail.SendMail(emailString));
				JOptionPane.showMessageDialog(this, "임시 비밀번호가 발급되었습니다");
			}

		} else if (e.getSource() == searchpwF.cancleB) { // PW찾기페이지 -----------> ID찾기 취소

			searchF.setVisible(false);
			this.setVisible(true);

		} else if (e.getSource() == RoomF.InformationB) {
			String str = "";

			MemberInfoF.setVisible(true); // 회원정보 프레임
			MemberInfoF.idT.getText();

			MemberInfoF.nameT.getText();
			pw.println(Protocol.MEMBERINFO + "|" + str);
			pw.flush();

		} else if (e.getSource() == MemberInfoF.joinB) {
			String id = MemberInfoF.idT.getText();
			String name = MemberInfoF.nameT.getText();
			String pw1 = MemberInfoF.pwT.getText();
			String ageYear = (String) MemberInfoF.ageYearC.getSelectedItem();
			String ageMonth = (String) MemberInfoF.ageMonthC.getSelectedItem();
			String ageDay = (String) MemberInfoF.ageDayC.getSelectedItem();
			String tel = (String) MemberInfoF.telC.getSelectedItem();
			String tel2 = MemberInfoF.tel2T.getText();
			String tel3 = MemberInfoF.tel3T.getText();
			String email = MemberInfoF.emailT.getText();
			String email1 = (String) MemberInfoF.emailC.getSelectedItem();

			if (name.length() == 0 || pw1.length() == 0 || tel2.length() == 0 || tel3.length() == 0
					|| email.length() == 0) {

				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요");
			} else {
				System.out.println("SSSS");
				String line = "";
				line += (id + "%" + name + "%" + pw1 + "%" + ageYear + ageMonth + ageDay + "%" + tel + tel2 + tel3 + "%"
						+ email + "@" + email1 + "%");
				System.out.println(line);

				pw.println(Protocol.MEMBERINFO_OK + "|" + line);
				pw.flush();

				JOptionPane.showConfirmDialog(this, "수정하시겠습니까?", "CooSerive", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				MemberInfoF.nameT.setText("");
				MemberInfoF.pwT.setText("");
				MemberInfoF.ageYearC.setSelectedIndex(0);
				MemberInfoF.ageMonthC.setSelectedIndex(0);
				MemberInfoF.ageDayC.setSelectedIndex(0);
				MemberInfoF.telC.setSelectedIndex(0);
				MemberInfoF.tel2T.setText("");
				MemberInfoF.tel3T.setText("");
				MemberInfoF.emailT.setText("");
				MemberInfoF.emailC.setSelectedIndex(0);

				condition_S = false;
				sNumber = "><^^";

				System.out.println("수정할 회원정보를 전송");
			}

		} else if (e.getSource() == MemberInfoF.calneB) { // 회원수정(취소)
			RoomF.setVisible(true);
			condition_S = false;
			sNumber = "><^^";
		} else if (e.getSource() == MemberInfoF.deleteB) { // 회원수정-->탈퇴
			String id = MemberInfoF.idT.getText();
			String name = MemberInfoF.nameT.getText();
			String pw1 = MemberInfoF.pwT.getText();
			String ageYear = (String) MemberInfoF.ageYearC.getSelectedItem();
			String ageMonth = (String) MemberInfoF.ageMonthC.getSelectedItem();
			String ageDay = (String) MemberInfoF.ageDayC.getSelectedItem();
			String tel = (String) MemberInfoF.telC.getSelectedItem();
			String tel2 = MemberInfoF.tel2T.getText();
			String tel3 = MemberInfoF.tel3T.getText();
			String email = MemberInfoF.emailT.getText();
			String email1 = (String) MemberInfoF.emailC.getSelectedItem();

			if (name.length() == 0 || pw1.length() == 0 || tel2.length() == 0 || tel3.length() == 0
					|| email.length() == 0) {

				JOptionPane.showMessageDialog(this, "빈간을 입력해주세요");
			} else {
				String line = "";
				line += (id + "%" + name + "%" + pw1 + "%" + ageYear + ageMonth + ageDay + "%" + tel + tel2 + tel3 + "%"
						+ email + "@" + email1 + "%");
				System.out.println(line);

				pw.println(Protocol.MEMBERINFO_DELETE_OK + "|" + line);
				pw.flush();

				JOptionPane.showConfirmDialog(this, "탈퇴하시겠습니까??", "CooSerive", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				MemberInfoF.nameT.setText("");
				MemberInfoF.pwT.setText("");
				MemberInfoF.ageYearC.setSelectedIndex(0);
				MemberInfoF.ageMonthC.setSelectedIndex(0);
				MemberInfoF.ageDayC.setSelectedIndex(0);
				MemberInfoF.telC.setSelectedIndex(0);
				MemberInfoF.tel2T.setText("");
				MemberInfoF.tel3T.setText("");
				MemberInfoF.emailT.setText("");
				MemberInfoF.emailC.setSelectedIndex(0);

				condition_S = false;
				sNumber = "><^^";

				System.out.println("회원 탈퇴가 완료되었습니다");
			}
		} else if (e.getSource() == accessB) { // 메인페이지 --> 대기방 (Login)

			String id = idT.getText();
			String pwss = pwT.getText();

			if (id.length() == 0 || pwss.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요");
			} else {
				String line = id + "%" + pwss;
				pw.println(Protocol.ENTERLOGIN + "|" + line);
				pw.flush();
			}
			idT.setText("");
			pwT.setText("");

		} else if (e.getSource() == searchpwF.cancleB) { // PW찾기페이지 -->PW 찾기 취소
			searchpwF.setVisible(false);
			this.setVisible(true);
		} else if (e.getSource() == RoomF.exitB) { // 대기실 -> 로그인Page (로그아웃)

			RoomF.setVisible(false);
			this.setVisible(true);

			pw.println(Protocol.EXITWAITROOM + "|" + "message");
			pw.flush();

		} else if (e.getSource() == RoomF.sendB) // 대기실 페이지 -----------> MESSAGE 전송
		{
			String line = RoomF.chattxt.getText();
			if (RoomF.chattxt.getText().length() != 0) {
				pw.println(Protocol.SENDMESSAGE + "|" + line);
				pw.flush();
				RoomF.chattxt.setText("");
			}

		} else if (e.getSource() == RoomF.makeB) {
			RoomF.setVisible(false);
			rMakeF.setVisible(true);
		} else if (e.getSource() == rMakeF.makeB) { // 방만들기 페이지 -----> 방만들기 버튼
			String title = rMakeF.tf.getText();
			String rPassword = rMakeF.pf.getText();
			String userCount = (String) rMakeF.combo1.getSelectedItem();
			String subject = (String) rMakeF.combo.getSelectedItem();
			int condition = rMakeF.cb.isSelected() ? 1 : 0;

			if (title.length() == 0) {
				JOptionPane.showMessageDialog(this, "제목을 입력해주세요");
			} else {
				if (condition == 1 && rPassword.length() == 0) // PW를 쓴다고했놓고 안넣을때
				{
					JOptionPane.showMessageDialog(this, "비밀번호을 입력해주세요");
				} else if (condition == 1 && rPassword.length() != 0) {// PW를 쓴다고했놓고 넣은경우

					String line = "";
					line += (title + "%" + rPassword + "%" + userCount + "%" + subject + "%" + condition);
					pw.println(Protocol.ROOMMAKE + "|" + line);
					pw.flush();

//					rMakeF.setVisible(false);
//					RoomF.setVisible(true);

					rMakeF.tf.setText("");
					rMakeF.pf.setText("");
					rMakeF.combo1.setSelectedIndex(0);
					rMakeF.combo.setSelectedIndex(0);
					rMakeF.cb.setSelected(false);

				} else if (condition == 0 && rPassword.length() != 0) {
					JOptionPane.showMessageDialog(this, "비밀번호 사용을 선택해주세요.");
				} else if (condition == 0) // 공개방
				{
					String line = "";
					line += (title + "%" + userCount + "%" + subject + "%" + condition);
					pw.println(Protocol.ROOMMAKE + "|" + line);
					pw.flush();

//					rMakeF.setVisible(false);
//					RoomF.setVisible(true);
					rMakeF.tf.setText("");
					rMakeF.pf.setText("");
					rMakeF.combo1.setSelectedIndex(0);
					rMakeF.combo.setSelectedIndex(0);
					rMakeF.cb.setSelected(false);
				}

			}

		} else if (e.getSource() == rMakeF.canB) { // 방만들기페이지 ----> 취소버튼
			rMakeF.setVisible(false);
			RoomF.setVisible(true);
			rMakeF.tf.setText("");
			rMakeF.pf.setText("");
			rMakeF.combo1.setSelectedIndex(0);
			rMakeF.combo.setSelectedIndex(0);
			rMakeF.cb.setSelected(false);
		} else if (e.getSource() == chattingF.exitB) { // 채팅방에서 나가기 버튼

			chattingF.setVisible(false);
			RoomF.setVisible(true);
			chattingF.model.removeAllElements();

			pw.println(Protocol.EXITCHATTINGROOM + "|" + "Message");
			pw.flush();

			chattingF.partList.setText("asd");

		} else if (e.getSource() == chattingF.sendB) {
			pw.println(Protocol.CHATTINGSENDMESSAGE + "|" + chattingF.field.getText()); // 메세지를 보냄
			pw.flush();
			chattingF.field.setText("");
		} else if (e.getSource() == chattingF.openB) // 채팅방에서 ------> 내컴터 파일 열기
		{
			chattingF.openDialog();
			chattingF.fileRead();

		} else if (e.getSource() == chattingF.saveB) // 채팅방에서 ------> 내컴터 파일저장
		{
			chattingF.fileSave();
			chattingF.fileWrite();
//			listUpload();
			chattingF.openB.setEnabled(true);
			chattingF.saveB.setEnabled(true);
			chattingF.loadB.setEnabled(true);
			chattingF.deleteB.setEnabled(false);
			chattingF.exitB.setEnabled(true);

		} else if (e.getSource() == chattingF.loadB) {
			chattingF.openDialog();
			pw.println(Protocol.CHATTINGFILESEND_SYN + "|" + chattingF.file.getName());
			pw.flush();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("Listlistioner");
		for (int i = 0; i < chattingF.model.getSize(); i++) {
			if (chattingF.list2.isSelectedIndex(i)) {
				chattingF.fileSave();
				pw.println(Protocol.CHATTINGFILEDOWNLOAD_SYN + "|" + chattingF.list2.getSelectedValue());
				pw.flush();
			}
		}
	}

	@Override
	public void run() {
		// 받는쪽
		String line[] = null;
		while (true) {
			try {
				line = br.readLine().split("\\|");
				if (line == null) {
					br.close();
					pw.close();
					socket.close();

					System.exit(0);
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_OK) == 0) { // 회원가입 ID 중복 안됨
					JOptionPane.showMessageDialog(this, "사용가능");
					condition_Id = true;
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_NO) == 0) { // 회원가입 ID 중복 됨
					JOptionPane.showMessageDialog(this, "사용 불가능");
					condition_Id = false;
				} else if (line[0].compareTo(Protocol.IDSEARCH_OK) == 0) // ID 찾기 기존에 있음
				{
					JOptionPane.showMessageDialog(this, line[1]);
					searchF.setVisible(false);
					this.setVisible(true);
				} else if (line[0].compareTo(Protocol.IDSEARCH_NO) == 0) // ID가 없음
				{
					JOptionPane.showMessageDialog(this, line[1]);
					searchF.setVisible(false);
					this.setVisible(true);
				} else if (line[0].compareTo(Protocol.ENTERLOGIN_OK) == 0) // 로그인 성공
				{
					this.setVisible(false);
					RoomF.setVisible(true);
					RoomF.chatarea.append(line[1] + line[2] + '\n');

					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					RoomF.usertxt.setText(userlist);

				} else if (line[0].compareTo(Protocol.ENTERLOGIN_NO) == 0) // 로그인 실패
				{
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("로그인실패");
				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) // 로그아웃 [대기실 -> 로그인페이지]
				{
					RoomF.chatarea.append(line[1] + line[2] + '\n');

					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					RoomF.usertxt.setText(userlist);

				} else if ((line[0].compareTo(Protocol.ENTERLOGIN_NO) == 0)) {

					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("로그인실패");
				} else if (line[0].compareTo(Protocol.PWSEARCH_OK) == 0) {// 임시비밀 번호 OK
					this.setVisible(false);
					RoomF.setVisible(true);
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("로그인 성공");
				} else if (line[0].compareTo(Protocol.PWSEARCH_NO) == 0) { // 임시비밀 번호 NO
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("임시비밀번호가 맞지 않습니다.");
				} else if (line[0].compareTo(Protocol.MEMBERINFO_OK) == 0) { // 회원수정 OK
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("회원정보가 수정되었습니다.");

				} else if (line[0].compareTo(Protocol.MEMBERINFO_DELETE_OK) == 0) { // 회원탈퇴 OK
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("회원이 탈퇴되었습니다.");

				} else if (line[0].compareTo(Protocol.MEMBERINFO) == 0) { // 현재 회원 정보 OK
					System.out.println(
							line[0] + " , " + line[1] + "," + line[2] + "," + line[3] + "," + line[4] + "," + line[5]);
					System.out.println("현재 접속된 회원의 정보를 보여드립니다.");
					// 번호, 아이디, 이름,생년월일, 휴대폰
					MemberInfoF.idT.setText(line[1]);
					MemberInfoF.nameT.setText(line[2]);

					String age = line[3]; // 나이
					String ageyear = age.substring(age.length() - 8, age.length() - 4);
					MemberInfoF.ageYearC.setSelectedItem(ageyear);

					String agemonth = age.substring(age.length() - 4, age.length() - 2);
					MemberInfoF.ageMonthC.setSelectedItem(agemonth);

					String ageday = age.substring(age.length() - 2, age.length());
					MemberInfoF.ageDayC.setSelectedItem(ageday);

					String email = line[4];

					int atIndex = email.indexOf("@"); // 4
					String emailId = email.substring(0, atIndex);
					System.out.println("emailId: " + emailId);
					MemberInfoF.emailT.setText(emailId);

					// String emailcom=email.substring(email.length()-9, email.length());
					String emailcom = email.substring(atIndex + 1);
					MemberInfoF.emailC.setSelectedItem(emailcom);

					String phone = line[5];
					String telc = phone.substring(phone.length() - 11, phone.length() - 8);
					MemberInfoF.telC.setSelectedItem(telc);

					String tel1 = phone.substring(phone.length() - 8, phone.length() - 4);
					MemberInfoF.tel2T.setText(tel1);
					System.out.println(tel1);

					String tel3 = phone.substring(phone.length() - 4, phone.length());
					MemberInfoF.tel3T.setText(tel3);

				} else if (line[0].compareTo(Protocol.SENDMESSAGE_ACK) == 0) // 서버로 메세지 받음 [대기실]
				{
					RoomF.chatarea.append("[" + line[1] + "] :" + line[2] + '\n');

				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK) == 0) // 방만들어짐
				{
					//System.out.println("이거 되냐?");
					String roomList[] = line[1].split("-"); // 방 갯수
					for (int i = 0; i < roomList.length; i++) {
						System.out.print(roomList[i] + "/");
					}

					String roomListDetail[]; // 방세부
					System.out.println("RoomList size : " + roomList.length);

					RoomF.containPanelClear(); // 룸 프레임에 컨테이너를 비워주고
					for (int i = 0; i < roomList.length; i++) {

						RoomF.dp[i].init(); // 방리스트를 받은거로 다시 생성해주고
						roomListDetail = roomList[i].split("%");
						String userNumber = "";

						if (roomListDetail.length == 8) // 비공개방
						{
							userNumber += (roomListDetail[7] + "/" + roomListDetail[3]);

							RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // 방번호
							RoomF.dp[i].labelArray[3].setText(roomListDetail[5]); // 방주제
							RoomF.dp[i].labelArray[5].setText(userNumber); // 인원수
							RoomF.dp[i].labelArray[7].setText(roomListDetail[1]); // 방제목
							RoomF.dp[i].labelArray[8].setText("개설자 : " + roomListDetail[4]); // 개설자
						} else if (roomListDetail.length == 7) // 공개방
						{
							userNumber += (roomListDetail[6] + "/" + roomListDetail[2]);
							RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // 방번호
							RoomF.dp[i].labelArray[3].setText(roomListDetail[5]); // 방주제
							RoomF.dp[i].labelArray[5].setText(userNumber); // 인원수
							RoomF.dp[i].labelArray[7].setText(roomListDetail[1]); // 방제목
							RoomF.dp[i].labelArray[8].setText("개설자 : " + roomListDetail[3]); // 개설자
						}
						System.out.println("userNumber : " + userNumber);

					}
					chattingF.area.setText("");
					chattingF.area1.setText("");
					rMakeF.setVisible(false); // 대기방 화면 끄고
					RoomF.setVisible(true);

				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK1) == 0) // 방만들어짐 (만든 당사자) // 입장
				{
					rMakeF.setVisible(false); // 대기방 화면 끄고
					chattingF.area.setText("");
					chattingF.setVisible(true);
					chattingF.partList.setText(line[1] + "\n");

				} else if (line[0].compareTo(Protocol.ENTERROOM_OK1) == 0) // 방입장 입장하는 당사자
				{
					System.out.println("입장화면 변환");
					RoomF.setVisible(false);
					chattingF.area1.setText("");
					chattingF.area.setText("");
					chattingF.setVisible(true);
//					System.out.println(line[2]);
//					String roomMember[] = line[2].split("%");//룸에 들어온사람들
//					chattingF.partList.append(line[1]); //자기 추가해주고
//					for (int i = 0; i < roomMember.length; i++) {
//						chattingF.partList.append(roomMember[i] + "\n");
//					}

				} else if (line[0].compareTo(Protocol.ENTERROOM_USERLISTSEND) == 0) // 채팅방 리스트 새로고침
				{

					String roomMember[] = line[1].split("%");// 룸에 들어온사람들
					String lineList = "";
					for (int i = 0; i < roomMember.length; i++) {
						lineList += (roomMember[i] + "\n");
					}

					chattingF.partList.setText(lineList);
					chattingF.area1.append(line[2] + "\n");

					if (line.length == 4) {
						String fileList[] = line[3].split("%");
						chattingF.model.removeAllElements();
						for (int i = 0; i < fileList.length; i++) {
							chattingF.model.addElement(fileList[i]);
						}
					}

				} else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE_OK) == 0) {
					chattingF.area1.append("[" + line[1] + "] :" + line[2] + "\n");
				} else if (line[0].compareTo(Protocol.CHATTINGFILESEND_SYNACK) == 0) {

					pw.println(Protocol.CHATTINGFILESEND_FILE + "|" + chattingF.file.length());
					pw.flush();

					OutputStream os = socket.getOutputStream();

					System.out.println("파일 보내기 시작 !!!");
					// 보낼 파일의 입력 스트림 객체 생성
					FileInputStream fis = new FileInputStream(chattingF.file.getAbsoluteFile());

					// 파일의 내용을 보낸다
					byte[] b = new byte[512];
					int n;
					while ((n = fis.read(b, 0, b.length)) > 0) {
						os.write(b, 0, n);
						System.out.println(n + "bytes 보냄 !!!");
					}

					// 소켓에서 보낼 출력 스트림을 구한다.
				} else if (line[0].compareTo(Protocol.CHATTINGFILESEND_FILEACK) == 0) {

					String[] fileList = line[1].split("%");

					chattingF.model.removeAllElements();
					for (int i = 0; i < fileList.length; i++) {
						chattingF.model.addElement(fileList[i]);
					}

				} else if (line[0].compareTo(Protocol.CHATTINGFILEDOWNLOAD_SEND) == 0) { // 파일을 받음
					String path = chattingF.file.getAbsolutePath();

					FileOutputStream fos = new FileOutputStream(path);
					InputStream is = socket.getInputStream();

					System.out.println("파일 다운로드 시작 !!!");

					// 보내온 파일 내용을 파일에 저장

					byte[] b = new byte[512];

					int n = 0;
					long filesize = Long.parseLong(line[1]);

					while ((n = is.read(b, 0, b.length)) > 0) {

						fos.write(b, 0, n);
						System.out.println("N:" + n);
						System.out.println(n + "bytes 다운로드 !!!");
						n += n;
						if (n >= filesize)
							break;
					}

					fos.close();
					System.out.println("파일 다운로드 끝 !!!");
				}

			} catch (IOException io) {
				io.printStackTrace();
			}

		} // while
	}
}
