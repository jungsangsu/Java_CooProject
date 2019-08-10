package Action;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import DO.Room;

public class MainServer {
	private ServerSocket ss; // 서버 소켓
	private ArrayList<MainHandler> allUserList; // 전체 사용자
	private ArrayList<MainHandler> WaitUserList; // 대기실 사용자
	private ArrayList<Room> roomtotalList;// 전체 방리스트

	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "java";
	private String password = "tkdtn";

	public MainServer() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password); // DB 연결

			ss = new ServerSocket(9500);
			System.out.println("서버준비완료");

			allUserList = new ArrayList<MainHandler>(); // 전체 사용자
			WaitUserList = new ArrayList<MainHandler>(); // 대기실 사용자
			roomtotalList = new ArrayList<Room>(); // 전체 방리스트
			while (true) {
				Socket socket = ss.accept();
				MainHandler handler = new MainHandler(socket, allUserList, WaitUserList,roomtotalList, conn);// 스레드 생성
				handler.start();// 스레드 시작
				allUserList.add(handler);
			} // while
		} catch (IOException io) {
			io.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}
