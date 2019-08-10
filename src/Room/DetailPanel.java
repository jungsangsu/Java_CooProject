package Room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Action.Protocol;

public class DetailPanel extends JPanel implements ActionListener{

	public static String labelName[] = { "방 번호 :", "      ", "방 주제 :", "      ", "인원 수 : ", "      ", "방 제목 : ",
			"      ", "      " };
	public JLabel labelArray[]; // 1(방번호),3(방주제),5(인원수),7(방제목)
	private JButton enterButton;

	private BufferedReader br;
	private PrintWriter pw;
	
	public DetailPanel(BufferedReader br,PrintWriter pw) {
		this.br = br;
		this.pw = pw;
	}

	public void init() {

		this.setLayout(new GridLayout(5, 2, 1, 1));

		labelArray = new JLabel[labelName.length];

		for (int i = 0; i < labelName.length; i++) {
			labelArray[i] = new JLabel(labelName[i]);
			this.add(labelArray[i]);
		}

		enterButton = new JButton("입 장");
		this.add(enterButton);
		enterButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("입장버튼 ");
		if(e.getSource() == enterButton) {
			//서버로 입장요청 -> 룸 ID
			String line = "";
			line+=(Protocol.ENTERROOM+"|"+labelArray[1].getText()); //Pro + 방번호
			pw.println(line);
			pw.flush();
		}
	}
	

}