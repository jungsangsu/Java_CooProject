package Action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class MainChat extends JFrame implements ActionListener, Runnable {

	JList<String> roomInfo, roomInwon, waitInfo;

	JScrollPane sp_roomInfo, sp_roomInwon, sp_waitInfo;

	JButton bt_create, bt_enter, bt_exit;

	JPanel p;

	ChatClient cc;

	// 소켓 입출력객체

	BufferedReader in;

	OutputStream out;

	String selectedRoom;

	public MainChat() {

		setTitle("대기실");

		cc = new ChatClient();

		roomInfo = new JList<String>();

		roomInfo.setBorder(new TitledBorder("방정보"));

		roomInfo.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				String str = roomInfo.getSelectedValue(); // "자바방--1"

				if (str == null)
					return;

				System.out.println("STR=" + str);

				selectedRoom = str.substring(0, str.indexOf("-"));

				// "자바방" <---- substring(0,3)

				// 대화방 내의 인원정보

				sendMsg("170|" + selectedRoom);

			}

		});

		roomInwon = new JList<String>();

		roomInwon.setBorder(new TitledBorder("인원정보"));

		waitInfo = new JList<String>();

		waitInfo.setBorder(new TitledBorder("대기실정보"));

		sp_roomInfo = new JScrollPane(roomInfo);

		sp_roomInwon = new JScrollPane(roomInwon);

		sp_waitInfo = new JScrollPane(waitInfo);

		bt_create = new JButton("방만들기");

		bt_enter = new JButton("방들어가기");

		bt_exit = new JButton("나가기");

		p = new JPanel();

		sp_roomInfo.setBounds(10, 10, 300, 300);

		sp_roomInwon.setBounds(320, 10, 150, 300);

		sp_waitInfo.setBounds(10, 320, 300, 130);

		bt_create.setBounds(320, 330, 150, 30);

		bt_enter.setBounds(320, 370, 150, 30);

		bt_exit.setBounds(320, 410, 150, 30);

		p.setLayout(null);

		p.setBackground(Color.orange);

		p.add(sp_roomInfo);

		p.add(sp_roomInwon);

		p.add(sp_waitInfo);

		p.add(bt_create);

		p.add(bt_enter);

		p.add(bt_exit);

		add(p);

		setBounds(300, 200, 500, 500);

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		connect();// 서버연결시도 (in,out객체생성)

		new Thread(this).start();// 서버메시지 대기

		sendMsg("100|");// (대기실)접속 알림

		String nickName = JOptionPane.showInputDialog(this, "대화명:");

		sendMsg("150|" + nickName);// 대화명 전달

		eventUp();

	}// 생성자

	private void eventUp() {// 이벤트소스-이벤트처리부 연결

		// 대기실(MainChat)

		bt_create.addActionListener(this);

		bt_enter.addActionListener(this);

		bt_exit.addActionListener(this);

		// 대화방(ChatClient)

		cc.sendTF.addActionListener(this);

		cc.bt_change.addActionListener(this);

		cc.bt_exit.addActionListener(this);

	}

	@Override

	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == bt_create) {// 방만들기 요청

			String title = JOptionPane.showInputDialog(this, "방제목:");

			// 방제목을 서버에게 전달

			sendMsg("160|" + title);

			cc.setTitle("채팅방-[" + title + "]");

			sendMsg("175|");// 대화방내 인원정보 요청

			setVisible(false);

			cc.setVisible(true); // 대화방이동

		} else if (ob == bt_enter) {// 방들어가기 요청

			if (selectedRoom == null) {

				JOptionPane.showMessageDialog(this, "방을 선택!!");

				return;

			}

			sendMsg("200|" + selectedRoom);

			sendMsg("175|");// 대화방내 인원정보 요청

			setVisible(false);

			cc.setVisible(true);

		} else if (ob == cc.bt_exit) {// 대화방 나가기 요청

			sendMsg("400|");

			cc.setVisible(false);

			setVisible(true);

		} else if (ob == cc.sendTF) {// (TextField입력)메시지 보내기 요청

			String msg = cc.sendTF.getText();

			if (msg.length() > 0) {

				sendMsg("300|" + msg);

				cc.sendTF.setText("");

			}

		}

		else if (ob == bt_exit) {// 나가기(프로그램종료) 요청

			System.exit(0);// 현재 응용프로그램 종료하기

		}

	}// actionPerformed

	public void connect() {// (소켓)서버연결 요청

		try {

			// Socket s = new Socket(String host<서버ip>, int port<서비스번호>);

			Socket s = new Socket("localhost", 5000);// 연결시도

			in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			// in: 서버메시지 읽기객체 서버-----msg------>클라이언트

			out = s.getOutputStream();

			// out: 메시지 보내기, 쓰기객체 클라이언트-----msg----->서버

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}// connect

	public void sendMsg(String msg) {// 서버에게 메시지 보내기

		try {

			out.write((msg + "\n").getBytes());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}// sendMsg

	public void run() {// 서버가 보낸 메시지 읽기

		// 왜 run메소드 사용? GUI프로그램실행에 영향 미치지않는 코드 작성.

//메소드호출은 순차적인 실행!!  스레드메소드는 동시실행(기다리지 않는 별도 실행)!!

		try {

			while (true) {

				String msg = in.readLine();// msg: 서버가 보낸 메시지

				// msg==> "300|안녕하세요" "160|자바방--1,오라클방--1,JDBC방--1"

				String msgs[] = msg.split("\\|");

				String protocol = msgs[0];

				switch (protocol) {

				case "300":

					cc.ta.append(msgs[1] + "\n");

					cc.ta.setCaretPosition(cc.ta.getText().length());

					break;

				case "160":// 방만들기

					// 방정보를 List에 뿌리기

					if (msgs.length > 1) {

						// 개설된 방이 한개 이상이었을때 실행

						// 개설된 방없음 ----> msg="160|" 였을때 에러

						String roomNames[] = msgs[1].split(",");

						// "자바방--1,오라클방--1,JDBC방--1"

						roomInfo.setListData(roomNames);

					}

					break;

				case "170":// (대기실에서) 대화방 인원정보

					String roomInwons[] = msgs[1].split(",");

					roomInwon.setListData(roomInwons);

					break;

				case "175":// (대화방에서) 대화방 인원정보

					String myRoomInwons[] = msgs[1].split(",");

					cc.li_inwon.setListData(myRoomInwons);

					break;

				case "180":// 대기실 인원정보

					String waitNames[] = msgs[1].split(",");

					waitInfo.setListData(waitNames);

					break;

				case "200":// 대화방 입장

					cc.ta.append("=========[" + msgs[1] + "]님 입장=========\n");

					cc.ta.setCaretPosition(cc.ta.getText().length());

					break;

				case "400":// 대화방 퇴장

					cc.ta.append("=========[" + msgs[1] + "]님 퇴장=========\n");

					cc.ta.setCaretPosition(cc.ta.getText().length());

					break;

				case "202":// 개설된 방의 타이틀 제목 얻기

					cc.setTitle("채팅방-[" + msgs[1] + "]");

					break;

				}// 클라이언트 switch

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}// run

	public static void main(String[] args) {

		new MainChat();

	}

}