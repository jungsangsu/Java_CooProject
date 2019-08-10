package Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DO.Room;
import DO.User;

public class MainHandler extends Thread {
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	private Connection conn;
	private PreparedStatement pstmt;
	private User user;

	private ArrayList<MainHandler> allUserList; // 전체사용자
	private ArrayList<MainHandler> waitUserList; // 대기실사용자
	private ArrayList<Room> roomtotalList; // 전체 방리스트

	private Room priRoom; // 사용자가 있는 방

	// 소켓, 전체사용자,대기방,방리스트,JDBC
	public MainHandler(Socket socket, ArrayList<MainHandler> allUserList, ArrayList<MainHandler> waitUserList,
			ArrayList<Room> roomtotalList, Connection conn) throws IOException {
		this.user = new User();
		this.priRoom = new Room();
		this.socket = socket;
		this.allUserList = allUserList;
		this.waitUserList = waitUserList;
		this.roomtotalList = roomtotalList;
		this.conn = conn;

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	@Override
	public void run() {
		// 데이터 입력받음 데이터파싱 -> 결과 실행해줘야함
		try {

			String[] line = null;
			while (true) {
				line = br.readLine().split("\\|");

				if (line == null) {
					break;
				}
				if (line[0].compareTo(Protocol.REGISTER) == 0) // [회원가입]
				{
					String userContent[] = line[1].split("%");

					String sql = "Insert into UserContent values(num.nextval,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userContent[0]);
					pstmt.setString(2, userContent[1]);
					pstmt.setString(3, userContent[2]);
					pstmt.setString(4, userContent[3]);
					pstmt.setString(5, userContent[4]);
					pstmt.setString(6, userContent[5]);

					int su = pstmt.executeUpdate(); // 항상 몇개를 실행(CRUD)한지 갯수를 return
					System.out.println(su + "회원가입[DB]");

				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK) == 0) // 회원가입 ID 중복체크
				{
					System.out.println(line[0] + "/" + line[1]);
					String sql = "select * from UserContent where IDNAME = '" + line[1] + "'";
					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);
					String name = null;
					int count = 0;
					while (rs.next()) {
						name = rs.getString("IDNAME");
						if (name.compareTo(line[1]) == 0) {
							count++;
						}
					}
					System.out.println(count);
					if (count == 0) // 중복안되서 가입가능
					{
						pw.println(Protocol.IDSEARCHCHECK_OK + "|" + "MESSAGE");
						pw.flush();
					} else {
						pw.println(Protocol.IDSEARCHCHECK_NO + "|" + "MESSAGE");
						pw.flush();
					}
				} else if (line[0].compareTo(Protocol.IDSEARCH) == 0) // ID 찾기
				{
					System.out.println("ID찾기");
					String userContent[] = line[1].split("%");

					System.out.println(userContent[0]);
					System.out.println(userContent[1]);
					System.out.println(userContent[2]);
					System.out.println(userContent[3]);

					String sql = "select * from UserContent where (NAME = '" + userContent[0] + "' and age = '"
							+ userContent[1] + "' and email ='" + userContent[2] + "' and phoneNumber1 = '"
							+ userContent[3] + "')";

					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);
					String name = null;
					String id = null;
					int count = 0;
					while (rs.next()) {
						name = rs.getString("NAME");
						id = rs.getString("IDNAME");
						if (name.compareTo(userContent[0]) == 0) {
							count++;
						}
					}
					System.out.println(count);

					if (count == 0) // ID가 없음
					{
						pw.println(Protocol.IDSEARCH_NO + "|" + "등록된 아이디가 없습니다.");
						pw.flush();
					} else { // 기존에ID 찾음
						StringBuffer stb = new StringBuffer(id);
						stb.replace(stb.length() - 4, stb.length() - 1, "***");
						pw.println(Protocol.IDSEARCH_OK + "|" + "ID : " + stb.toString());
						pw.flush();
					}

				} else if (line[0].compareTo(Protocol.ENTERLOGIN) == 0) // [login]
				{

					boolean con = true; // 기존에 로그인되어있는지 안되어있는지 변수
					System.out.println("login");
					String userContent[] = line[1].split("%");

					System.out.println(userContent[0] + "/" + userContent[1]);

					for (int i = 0; i < waitUserList.size(); i++) {
						if ((waitUserList.get(i).user.getIdName()).compareTo(userContent[0]) == 0) {
							con = false;
						}
					}
					if (con) {
//						String sql = "select * from UserContent where (IDNAME = '" + userContent[0] + "' and PASSWORD = '"
//						+ userContent[1] + "')";

						String sql = "select * from UserContent where idname = '" + userContent[0]
								+ "' and password = '" + userContent[1] + "'";

						pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery(sql);
						int count = 0;

						while (rs.next()) {
							user.setName(rs.getString("NAME"));
							user.setIdName(rs.getString("IDNAME"));
							user.setAge(rs.getString("AGE"));
							user.setPassword(rs.getString("PASSWORD"));
							user.setPryNumber(rs.getInt("priNumber"));
							user.setPhoneNumber(rs.getString("phoneNumber1"));
							user.setEmail(rs.getString("email"));

							count++;
						}

						System.out.println(count);

						if (count == 0) // ID,PW 틀리면
						{
							pw.println(Protocol.ENTERLOGIN_NO + "|" + "로그인에 실패하였습니다");
							pw.flush();

							user.setName("");
							user.setIdName("");
							user.setAge("");
							user.setPassword("");
							user.setPryNumber(0);
							user.setPhoneNumber("");
							user.setEmail("");

						} else { // 로그인 되었을때
							waitUserList.add(this); // 대기방 인원수 추가
							String userline = "";
							for (int i = 0; i < waitUserList.size(); i++) {
								userline += (waitUserList.get(i).user.getIdName() + ":");
							}

							for (int i = 0; i < waitUserList.size(); i++) {
								waitUserList.get(i).pw.println(
										Protocol.ENTERLOGIN_OK + "|" + user.getIdName() + "|님이 입장하였습니다.|" + userline);
								waitUserList.get(i).pw.flush();
							}
							System.out.println("[대기방 인원수] :" + waitUserList.size());

							System.out.println("[Room 정보]");
							for (Room room : roomtotalList) {
								System.out.println(room.toString() + "현재방에 인원수 : " + room.roomInUserList.size());
							}
							System.out.println("[전체 Room 갯수 ]" + roomtotalList.size());

							// RoomtotalList 전체 정보를 Message로 만들어서 보내야된다
							String roomListMessage = "";

							for (int i = 0; i < roomtotalList.size(); i++) {
								roomListMessage += (roomtotalList.get(i).getrID() + "%"
										+ roomtotalList.get(i).getTitle() + "%" + roomtotalList.get(i).getrPassword()
										+ "%" + roomtotalList.get(i).getUserCount() + "%"
										+ roomtotalList.get(i).getMasterName() + "%" + roomtotalList.get(i).getSubject()
										+ "%" + roomtotalList.get(i).getCondtionP() + "%"
										+ roomtotalList.get(i).roomInUserList.size() + "-");
							}

							System.out.println(roomListMessage);

							if (roomListMessage.length() != 0) {
								for (int i = 0; i < waitUserList.size(); i++) {
									waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
									waitUserList.get(i).pw.flush();
								}
							}

						}

						System.out.println(user.toString());
					} else {
						pw.println(Protocol.ENTERLOGIN_NO + "|" + "이미 로그인 중입니다.");
						pw.flush();
					}

				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) { // 대기실방에서 로그인페이지(logout);

					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName(); // -여기 다시하기

					waitUserList.remove(this); //
					System.out.println("[대기방 인원수] :" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}

					System.out.println("대기자 인원 :" + userline);
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw
								.println(Protocol.EXITWAITROOM + "|" + thisName + "|님이 퇴장하였습니다.|" + userline);// 대기방에
						// Message
						// 전송;
						waitUserList.get(i).pw.flush();
					}
					user.setName("");
					user.setIdName("");
					user.setAge("");
					user.setPassword("");
					user.setPryNumber(0);
					user.setPhoneNumber("");
					user.setEmail("");

				} else if (line[0].compareTo(Protocol.SENDMESSAGE) == 0) { // 대기실방에서 메세지보내기

					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw
								.println(Protocol.SENDMESSAGE_ACK + "|" + user.getIdName() + " |" + line[1]);// 대기방에
																												// Message
																												// 전송;
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ROOMMAKE) == 0) { // 방만들기
					String userContent[] = line[1].split("%");

					String sql = "";
					Room tempRoom = new Room();
					if (userContent.length == 5) { // 비공개방
						sql = "Insert into Room values(num.nextval,?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(sql);

						pstmt.setString(1, userContent[0]); // title
						pstmt.setString(2, userContent[1]); // password
						pstmt.setString(3, userContent[2]); // count
						pstmt.setString(4, user.getIdName()); // 방장이름
						pstmt.setString(5, userContent[3]); // 주제
						pstmt.setString(6, userContent[4]); // condition 1

						tempRoom.setTitle(userContent[0]);
						tempRoom.setrPassword(userContent[1]);
						tempRoom.setUserCount(userContent[2]);
						tempRoom.setMasterName(user.getIdName());
						tempRoom.setSubject(userContent[3]);
						tempRoom.setCondtionP(Integer.parseInt(userContent[4]));

						sql = "select * from Room where title = '" + userContent[0] + "' and password = '"
								+ userContent[1] + "' and  userCount= '" + userContent[2] + "' and  admin= '"
								+ user.getIdName() + "' and  subject= '" + userContent[3] + "'";

					} else { // 공개방
						sql = "Insert into Room values(num.nextval,?,'',?,?,?,?)";
						pstmt = conn.prepareStatement(sql);

						pstmt.setString(1, userContent[0]); // title
						pstmt.setString(2, userContent[1]); // count
						pstmt.setString(3, user.getIdName()); // 방장이름
						pstmt.setString(4, userContent[2]); // 주제
						pstmt.setString(5, userContent[3]); // condition 0;

						tempRoom.setTitle(userContent[0]);
						tempRoom.setUserCount(userContent[1]);
						tempRoom.setMasterName(user.getIdName());
						tempRoom.setSubject(userContent[2]);
						tempRoom.setCondtionP(Integer.parseInt(userContent[3]));

						sql = "select * from Room where title = '" + userContent[0] + "' and  userCount= '"
								+ userContent[1] + "' and  admin= '" + user.getIdName() + "' and  subject= '"
								+ userContent[2] + "'";
					}

					int su = pstmt.executeUpdate(); // 항상 몇개를 실행(CRUD)한지 갯수를 return
					System.out.println(su + "Room 만듬[DB]");

					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);

					int count = 0;
					int priNumber = 0;

					while (rs.next()) {
						count++;
						priNumber = rs.getInt("RID");
					}

					if (count != 0) {
						tempRoom.setrID(priNumber);
						tempRoom.roomInUserList.add(this);
						roomtotalList.add(tempRoom);
						priRoom = tempRoom; // 현재 룸을 지정함
					}

					System.out.println("[Room 정보]");
					for (Room room : roomtotalList) {
						System.out.println(room.toString() + "현재방에 인원수 : " + room.roomInUserList.size());
					}
					System.out.println("[전체 Room 갯수 ]" + roomtotalList.size());

					// RoomtotalList 전체 정보를 Message로 만들어서 보내야된다
					String roomListMessage = "";

					for (int i = 0; i < roomtotalList.size(); i++) {
						roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle() + "%"
								+ roomtotalList.get(i).getrPassword() + "%" + roomtotalList.get(i).getUserCount() + "%"
								+ roomtotalList.get(i).getMasterName() + "%" + roomtotalList.get(i).getSubject() + "%"
								+ roomtotalList.get(i).getCondtionP() + "%" + roomtotalList.get(i).roomInUserList.size()
								+ "-");
					}

					System.out.println(roomListMessage);

					for (int i = 0; i < waitUserList.size(); i++) {
						if (waitUserList.get(i).user.getIdName().compareTo(tempRoom.getMasterName()) == 0) { // 방만든사람에게는
																												// 바로
																												// 채팅화면으로
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK1 + "|" + tempRoom.getMasterName());
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방사람들에게는 대기방만 새로고침
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
							waitUserList.get(i).pw.flush();
						}

					}

					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("대기방 인원수 누군가 방만들었을때" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ENTERLOGIN_OK + "|" + tempRoom.getMasterName() + "|님이"
								+ tempRoom.getrID() + "번 방을 만들었습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}

				} else if (line[0].compareTo(Protocol.ENTERROOM) == 0) { // [방 입장버튼]

					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName(); //

					int roomid = Integer.parseInt(line[1]); // 룸ID

					int index = 0;
					for (int i = 0; i < roomtotalList.size(); i++) {
						if (roomtotalList.get(i).getrID() == roomid) {
							roomtotalList.get(i).roomInUserList.add(this); // 방에 유저 넣고
							priRoom = roomtotalList.get(i);
							index = i;
						}
					}

					String roomListMessage = "";
					for (int i = 0; i < roomtotalList.size(); i++) {
						roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle() + "%"
								+ roomtotalList.get(i).getrPassword() + "%" + roomtotalList.get(i).getUserCount() + "%"
								+ roomtotalList.get(i).getMasterName() + "%" + roomtotalList.get(i).getSubject() + "%"
								+ roomtotalList.get(i).getCondtionP() + "%" + roomtotalList.get(i).roomInUserList.size()
								+ "-");
					}

					System.out.println(roomListMessage);
					System.out.println(thisName);

					String roomMember = ""; // --->여기 방마다 방에 유저를 넣어주는 것 추가 방에 입장한 유저를 가지고와야해 ㅁ몇번방인지 찾아야함

					for (int i = 0; i < roomtotalList.get(index).roomInUserList.size(); i++) { // 룸안에 유저의 수만큼
						roomMember += (roomtotalList.get(index).roomInUserList.get(i).user.getIdName() + "%");
					}

					for (int i = 0; i < waitUserList.size(); i++) {
						if (waitUserList.get(i).user.getIdName().compareTo(thisName) == 0) { // 방들어가는사람에게는
																								// 바로
																								// 채팅화면으로\
							waitUserList.get(i).pw.println(Protocol.ENTERROOM_OK1 + "|" + "message");
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방사람들에게는 대기방만 새로고침
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage); // 룸리스트 새로고침
							waitUserList.get(i).pw.flush();
						}

					}

					System.out.println("특정방에 사람수 : " + roomtotalList.get(index).roomInUserList.size());
					System.out.println(roomMember);
					for (int i = 0; i < roomtotalList.get(index).roomInUserList.size(); i++) {
						roomtotalList.get(index).roomInUserList.get(i).pw.println(Protocol.ENTERROOM_USERLISTSEND + "|"
								+ roomMember + "|" + user.getIdName() + "님이 입장하셨습니다.");
						roomtotalList.get(index).roomInUserList.get(i).pw.flush();
					}

					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("방입장동작 부분  -->>[대기실 인원수 ]" + waitUserList.size());

					String userline = ""; // 채팅창에

					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(
								Protocol.EXITWAITROOM + "|" + thisName + "|님이 " + roomid + "방에 입장하였습니다.|" + userline);// 대기방에
																														// 바꿔주고
						// Message
						// 전송;
						waitUserList.get(i).pw.flush();
					}

				} else if (line[0].compareTo(Protocol.EXITCHATTINGROOM) == 0) // 방 나가기 버튼.
				{

					int roomIndex = 0;
					boolean con = true;
					
					for (int i = 0; i < roomtotalList.size(); i++) {
						if (roomtotalList.get(i).getrID() == priRoom.getrID()) {
							
							if(roomtotalList.get(i).roomInUserList.size() == 1) //나올떄 자기가 마지막일 때.
							{
								System.out.println("나올때 내가 마지막일때");
								roomtotalList.remove(priRoom);
								priRoom = new Room();
								con = false;
								
							}else { //최소 2명일 때
								System.out.println("나올때 내가 마지막아닐때!! XXX");
								roomtotalList.get(i).roomInUserList.remove(this); // 방에 유저 빼고
								priRoom = new Room();// 현재룸을 비워주고
								roomIndex = i;
								
							}
							
						}
					}


					if(con) //남아있는방에 최소 2명이상일때
					{
						String roomMember = ""; // --->여기 방마다 방에 유저를 넣어주는 것 추가 방에 입장한 유저를 가지고와야해 ㅁ몇번방인지 찾아야함

						for (int i = 0; i < roomtotalList.get(roomIndex).roomInUserList.size(); i++) { // 룸안에 유저의 수만큼
							roomMember += (roomtotalList.get(roomIndex).roomInUserList.get(i).user.getIdName() + "%");
						}


						System.out.println("특정방에 사람수 : " + roomtotalList.get(roomIndex).roomInUserList.size());
						System.out.println(roomMember);
						for (int i = 0; i < roomtotalList.get(roomIndex).roomInUserList.size(); i++) {
							roomtotalList.get(roomIndex).roomInUserList.get(i).pw.println(Protocol.ENTERROOM_USERLISTSEND + "|"
									+ roomMember + "|" + user.getIdName() + "님이 퇴장하셨습니다.");
							roomtotalList.get(roomIndex).roomInUserList.get(i).pw.flush();
						}
					} 
					
					
					String roomListMessage = "";

					System.out.println(roomListMessage);
					
					waitUserList.add(this); // 대기방에서 추가
					if(roomtotalList.size()>0)
					{
						roomListMessage = "";
						for (int i = 0; i < roomtotalList.size(); i++) {
							roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle() + "%"
									+ roomtotalList.get(i).getrPassword() + "%" + roomtotalList.get(i).getUserCount() + "%"
									+ roomtotalList.get(i).getMasterName() + "%" + roomtotalList.get(i).getSubject() + "%"
									+ roomtotalList.get(i).getCondtionP() + "%" + roomtotalList.get(i).roomInUserList.size()
									+ "-");
						}
					}else {
						roomListMessage="-";
					}
					
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage); // 룸리스트 새로고침
						waitUserList.get(i).pw.flush();
					}

					System.out.println("방퇴실동작 부분  -->>[대기실 인원수 ]" + waitUserList.size());
					String userline = ""; // 채팅창에
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(
								Protocol.EXITWAITROOM + "|" + user.getIdName() + "|님이 대기실에에 입장하였습니다.|" + userline);// 대기방에
																														// 바꿔주고
						// Message
						// 전송;
						waitUserList.get(i).pw.flush();
					}

				} else if(line[0].compareTo(Protocol.CHATTINGSENDMESSAGE)==0) //채팅방에서 메세지 보내기
				{
					
					int roomUserSize = roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.size();
					
					for (int i = 0; i < roomUserSize; i++) {
						 roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.get(i).pw.println(Protocol.CHATTINGSENDMESSAGE_OK+"|"+user.getIdName()+"|"+line[1]); //채팅방 사람들에게 메세지
						 roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.get(i).pw.flush();
					}
					
				}

			} // while

			br.close();
			pw.close();
			socket.close();

		} catch (IOException io) {
			io.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
