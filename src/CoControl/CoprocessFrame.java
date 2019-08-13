package CoControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * @author 수병
 * @category 방 세부내용
 *
 */

public class CoprocessFrame extends JFrame {

	public JButton openB, saveB, loadB, deleteB, exitB, sendB;
	public JTextArea area, area1, partList;
	public JTextField field;

	public JList<String> list2;
	public File file, folder;
	private BufferedReader br;
	private ArrayList<String> arr;
	public DefaultListModel<String> model;

	public CoprocessFrame() {

		openB = new JButton("내PC 파일열기");
		openB.setBackground(Color.WHITE);
		saveB = new JButton("내PC 파일저장");
		saveB.setBackground(Color.WHITE);
		loadB = new JButton("업로드");
		loadB.setBackground(Color.WHITE);
		exitB = new JButton("나가기");
		exitB.setBackground(Color.WHITE);
		

		openB.setEnabled(true);
		saveB.setEnabled(true);
		loadB.setEnabled(true);
		exitB.setEnabled(true);

		JPanel wpanel = new JPanel(new GridLayout(1, 5, 5, 0));
		wpanel.add(openB);
		wpanel.add(saveB);
		wpanel.add(loadB);
		wpanel.add(exitB);

		JPanel wpanel1 = new JPanel();
		area = new JTextArea();
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		area.setEditable(false);
		scroll.setPreferredSize(new Dimension(700, 735));
		wpanel1.add(scroll);

		JPanel totwpanel = new JPanel(new BorderLayout());
		totwpanel.add("North", wpanel);
		totwpanel.add("Center", wpanel1);

		// *************************************************
		JPanel epanel = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel();
		JLabel user = new JLabel("                                   참여 인원");
		p1.add(user);

		partList = new JTextArea();
		partList.setEditable(true);
		JScrollPane scroll1 = new JScrollPane(partList);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		epanel.add("North", user);
		epanel.add("Center", scroll1);

		JPanel epanel1 = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel();
		JLabel file = new JLabel("                               업로드한 파일");
		p2.add(file);

		list2 = new JList<String>(new DefaultListModel<String>());
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model = (DefaultListModel<String>) list2.getModel();
		// list2.setSelectedIndex(0);
		JScrollPane scroll2 = new JScrollPane(list2);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		epanel1.add("North", file);
		epanel1.add("Center", scroll2);

		JPanel epanel12 = new JPanel(new GridLayout(2, 1, 0, 10));
		epanel12.add(epanel);
		epanel12.add(epanel1);

		JPanel epanel3 = new JPanel(new BorderLayout());

		JPanel p3 = new JPanel();
		JLabel chat = new JLabel("                                        채 팅");
		p3.add(chat);

		area1 = new JTextArea();
		JScrollPane scroll3 = new JScrollPane(area1);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		area1.setEditable(false);
		epanel3.add("North", chat);
		epanel3.add("Center", scroll3);

		JPanel epanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		field = new JTextField(18);
		sendB = new JButton("Enter");
		epanel4.add(field);
		epanel4.add(sendB);

		JPanel totepanel = new JPanel(new BorderLayout());
		totepanel.add("North", epanel12);
		totepanel.add("Center", epanel3);
		totepanel.add("South", epanel4);
		// *************************************************
		Container c = this.getContentPane();
		c.add("Center", totwpanel);
		c.add("East", totepanel);

		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}// 생성자

	public void openDialog() {

		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		} else if (result != JFileChooser.APPROVE_OPTION) {
			area.setText("");
		}
	}

	public void fileRead() {// 구현
		if (file == null)
			return;
		area.setText("");
		String line = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				area.append(line + "\n");
			}
			br.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void fileSave() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
	}

	public void fileWrite() {
		if (file == null)
			return;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String data = area.getText();
			bw.write(data);
			bw.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void listUpload() {
		String fileName = folder.getName();
		String fileExtention = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		File[] contents = folder.listFiles();

		if (contents != null) {
			for (int i = 0; i < contents.length; i++) {
				contents[i].getName();
				// model.addElement(file.getName());
				// System.out.println(contents[i].getName());
			}
		} else {
			System.out.println("파일이 없습니다.");
		}

	}

	public void fileDelete() {
		model.remove(list2.getSelectedIndex());
		area.setText("");

	}
}