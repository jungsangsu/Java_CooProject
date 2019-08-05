package DO;

public class Room {
	private int RID;
	private String title;
	private String password;
	private int userCount;
	private String adminName;
	
	public Room() {
		this(0,"","",0,"");
	}
	
	public Room(int rID, String title, String password, int userCount, String adminName) {
		this.RID = rID;
		this.title = title;
		this.password = password;
		this.userCount = userCount;
		this.adminName = adminName;
	}

	public int getRID() {
		return RID;
	}

	public void setRID(int rID) {
		RID = rID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Room [RID=" + RID + ", title=" + title + ", password=" + password + ", userCount=" + userCount
				+ ", adminName=" + adminName + "]";
	}
	
}
