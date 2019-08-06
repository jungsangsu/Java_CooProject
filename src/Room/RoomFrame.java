package Room;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CoControl.CoprocessFrame;

/**
 * @author 형곤
 * @category 대기실 내용
 *
 */
public class RoomFrame extends JFrame implements ActionListener {

	private JPanel upP, chatP, chatP1, listP, list1P, list2P, roomP, roomP1, sumP, r1P, r2P, r3P, r4P, r5P, r6P;
	private JTextArea chatarea, entarea, listarea;
	private JTextField chattxt, tx1, tx2, tx3, tx4, tx5, tx6;
	private JLabel la1, la2, la3, la4, la5;

	public JButton sortB, makeB, exitB, sendB;

	RoomMake Roommake;
	CoprocessFrame cooprocessF;

	public RoomFrame() {

		Roommake = new RoomMake();
		cooprocessF = new CoprocessFrame();

		// 상단버튼
		upP = new JPanel(new FlowLayout());

		sortB = new JButton("정렬");
		sortB.setPreferredSize(new Dimension(200, 50));
		makeB = new JButton("방 만 들 기");
		makeB.setPreferredSize(new Dimension(400, 50));
		exitB = new JButton("exit");
		exitB.setPreferredSize(new Dimension(200, 50));

		upP.add(sortB);
		upP.add(makeB);
		upP.add(exitB);

		// 채팅방 목록
		roomP = new JPanel(new BorderLayout());
		la4 = new JLabel("채팅방 목록");
		la4.setFont(new Font("돋움", Font.PLAIN, 20));
		roomP1 = new JPanel(new GridLayout(3, 2, 5, 5));
		r1P = new JPanel();
		tx1 = new JTextField(20);
		r1P.add(tx1);
		r2P = new JPanel();
		tx2 = new JTextField(20);
		r2P.add(tx2);
		r3P = new JPanel();
		tx3 = new JTextField(20);
		r3P.add(tx3);
		r4P = new JPanel();
		tx4 = new JTextField(20);
		r4P.add(tx4);
		r5P = new JPanel();
		tx5 = new JTextField(20);
		r5P.add(tx5);
		r6P = new JPanel();
		tx6 = new JTextField(20);
		r6P.add(tx6);

		roomP1.add(r1P);
		roomP1.add(r2P);
		roomP1.add(r3P);
		roomP1.add(r4P);
		roomP1.add(r5P);
		roomP1.add(r6P);

		roomP.add("North", la4);
		roomP.add("Center", roomP1);

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
		entarea = new JTextArea(30, 20);
		JScrollPane scroll1 = new JScrollPane(entarea);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		list1P.add("North", la2);
		list1P.add("Center", scroll1);

		list2P = new JPanel(new BorderLayout());
		list2P.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		la3 = new JLabel(" 친 구 목 록");
		la3.setFont(new Font("돋움", Font.PLAIN, 15));
		listarea = new JTextArea(30, 20);
		JScrollPane scroll2 = new JScrollPane(listarea);
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

		makeB.addActionListener(this);
		Roommake.canB.addActionListener(this);
		cooprocessF.deleteB.addActionListener(this);
		Roommake.makeB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makeB) {
			this.setVisible(false);
			Roommake.setVisible(true);
		} else if (e.getSource() == Roommake.canB) {
			Roommake.setVisible(false);
			this.setVisible(true);
		} else if (e.getSource() == Roommake.makeB) {
			Roommake.setVisible(false);
			cooprocessF.setVisible(true);
		} else if(e.getSource() == cooprocessF.deleteB){
			cooprocessF.setVisible(false);
			this.setVisible(true);
		}
			
		

	}

//	public static void main(String[] args) {
//		new RoomFrame();
//	}
}
