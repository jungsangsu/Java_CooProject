package DO;

import java.util.ArrayList;

import Action.MainHandler;

public class Room {
	private int rID;
	private String title;
	private String rPassword;
	private String userCount;
	private String masterName;
	private String subject;
	private int condtionP;
	public ArrayList<MainHandler> roomInUserList;

	public Room() {
		this.rID = 0;
		this.title = "";
		this.rPassword = "";
		this.userCount = "";
		this.masterName = "";
		this.subject = "";
		this.condtionP = 0; // 공개방 0, 비공개 1
		roomInUserList = new ArrayList<MainHandler>();
	}

	public Room(int rID, String title, String rPassword, String userCount, String masterName, int condtionP) {
		this.rID = rID;
		this.title = title;
		this.rPassword = rPassword;
		this.userCount = userCount;
		this.masterName = masterName;
		this.condtionP = condtionP;
		roomInUserList = new ArrayList<MainHandler>();
	}

	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getrPassword() {
		return rPassword;
	}

	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public int getCondtionP() {
		return condtionP;
	}

	public void setCondtionP(int condtionP) {
		this.condtionP = condtionP;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ArrayList<MainHandler> getRoomInUserList() {
		return roomInUserList;
	}

	public void setRoomInUserList(ArrayList<MainHandler> roomInUserList) {
		this.roomInUserList = roomInUserList;
	}

	@Override
	public String toString() {
		return "Room [rID=" + rID + ", title=" + title + ", rPassword=" + rPassword + ", userCount=" + userCount
				+ ", masterName=" + masterName + ", condtionP=" + condtionP + "]";
	}

}
