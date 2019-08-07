package Room;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import CoControl.CoprocessFrame;
import Room.RoomMake;

/**
 * @author 형곤
 * @category 대기실 내용
 *
 */
public class RoomFrame extends JFrame implements ActionListener {
	public JButton makeB, exitB, sendB, enterB;
	private JComboBox<String> sortCB;
	private JPanel upP, chatP, chatP1, listP, list1P, list2P, roomP, roomP1, sumP, centerPanel;
	private JPanel[] sortrm;
	private JTextArea chatarea;
	private JTextField chattxt, tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8;
	private JLabel la1, la2, la3, la4, la5, la6;
	private String[] com = { "경영지원", "마케팅", "고객관리", "개발", "디자인" };
	private JList<String> entlist, frlist;
	private EtchedBorder eb;
	private JList<DetailPanel> list;
	private DefaultListModel<DetailPanel> model;
	RoomMake rmake;
	public RoomFrame() {
		// 상단버튼
		rmake = new RoomMake();
		
		upP = new JPanel(new FlowLayout());
		la6 = new JLabel("정 렬 : ");
		sortCB = new JComboBox<String>(com);
		sortCB.setPreferredSize(new Dimension(200, 30));
		makeB = new JButton("방 만 들 기");
		makeB.setPreferredSize(new Dimension(400, 30));
		exitB = new JButton("exit");
		exitB.setPreferredSize(new Dimension(200, 30));
		upP.add(la6);
		upP.add(sortCB);
		upP.add(makeB);
		upP.add(exitB);

		// 채팅방 목록
		roomP = new JPanel(new BorderLayout());
		la4 = new JLabel("채팅방 목록");
		la4.setFont(new Font("돋움", Font.PLAIN, 20));

		centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		for (int i = 0; i < 8; i++) {
			centerPanel.add(new DetailPanel());
		}

		roomP.add("Center", centerPanel);
		roomP.add("North", la4);

		// 대기자 채팅목록
		chatP = new JPanel(new BorderLayout());
		la1 = new JLabel("대기자 채팅방 ");
		la1.setFont(new Font("돋움", Font.PLAIN, 15));
		chatarea = new JTextArea();
		JScrollPane scroll = new JScrollPane(chatarea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(400, 250));

		chatP1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		chattxt = new JTextField(30);
		sendB = new JButton("보내기");

		chatP1.add(chattxt);
		chatP1.add(sendB);

		chatP.add("Center", scroll);
		chatP.add("South", chatP1);
		chatP.add("North", la1);

		// 채팅대기자 목록
		listP = new JPanel(new GridLayout(2, 1, 20, 20));

		list1P = new JPanel(new BorderLayout());
		la2 = new JLabel(" 들어온 사람  ");
		la2.setFont(new Font("돋움", Font.PLAIN, 15));
		entlist = new JList<String>(new DefaultListModel<String>());
		JScrollPane scroll1 = new JScrollPane(entlist);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		list1P.add("North", la2);
		list1P.add("Center", scroll1);

		list2P = new JPanel(new BorderLayout());
		list2P.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		la3 = new JLabel(" 친 구 목 록");
		la3.setFont(new Font("돋움", Font.PLAIN, 15));
		frlist = new JList<String>(new DefaultListModel<String>());
		JScrollPane scroll2 = new JScrollPane(frlist);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		list2P.add("North", la3);
		list2P.add("Center", scroll2);

		listP.add(list1P);
		listP.add(list2P);

		// 대기자목록 +대기자채팅
		sumP = new JPanel(new BorderLayout());

		sumP.add("Center", listP);
		sumP.add("South", chatP);

		// 종합
		Container contentPane = this.getContentPane();
		contentPane.add("East", sumP);
		contentPane.add("North", upP);
		contentPane.add("Center", roomP);

		setBounds(400, 200, 1000, 800);
//		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// DB에서 jlist연동 부분
		
//		sortCB.addActionListener(this);
		makeB.addActionListener(this);
		exitB.addActionListener(this);
//		enterB.addActionListener(this);
		

	}// 생성자

	private Object GridLayout(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return null;
	}

	// 이벤트
//	private void event() {
//		sortCB.addActionListener(this);
//		makeB.addActionListener(this);
//		exitB.addActionListener(this);
//		enterB.addActionListener(this);
//
//	}

	public void sortCB() {
		// String getItem = (String) sortCB.getSelectedItem();
		while (true) {
			for (String ss : com) {
				if (ss.equals("db값")) {
					for (int i = 0; i < 8; i++) {
						centerPanel.add(new DetailPanel());

					}

				}
			}
			break;
		}

	}

	// 실행부분
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortCB) {
			sortCB();

		} else if (e.getSource() == makeB) {
			this.setVisible(false);
			rmake.setVisible(true);
		} else if (e.getSource() == exitB) {
		} else if (e.getSource() == enterB) {
			CoprocessFrame Cc = new CoprocessFrame();
		}
	}

	// 들어온사람, 친구목록

//	public static void main(String[] args) {
//		new RoomFrame();
//	}
}