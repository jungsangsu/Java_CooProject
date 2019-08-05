package DO;

public class User {
	private int pryNumber;
	private String idName;
	private String password;
	private String name;
	private int age;
	private String email;
	private String phoneNumberFirst;
	private String phoneNumberSecond;
	private String phoneNumberthird;
	private int adminCon;
	private int RoomNumber;
	
	public User() {
		this(0,"","","",0,"","","","",0,0);
	}

	public User(int pryNumber, String idName, String password, String name, int age, String email,
			String phoneNumberFirst, String phoneNumberSecond, String phoneNumberthird, int adminCon, int roonNumber) {
		this.pryNumber = pryNumber;
		this.idName = idName;
		this.password = password;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phoneNumberFirst = phoneNumberFirst;
		this.phoneNumberSecond = phoneNumberSecond;
		this.phoneNumberthird = phoneNumberthird;
		this.adminCon = adminCon;
		this.RoomNumber = roonNumber;
	}

	public int getPryNumber() {
		return pryNumber;
	}

	public void setPryNumber(int pryNumber) {
		this.pryNumber = pryNumber;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumberFirst() {
		return phoneNumberFirst;
	}

	public void setPhoneNumberFirst(String phoneNumberFirst) {
		this.phoneNumberFirst = phoneNumberFirst;
	}

	public String getPhoneNumberSecond() {
		return phoneNumberSecond;
	}

	public void setPhoneNumberSecond(String phoneNumberSecond) {
		this.phoneNumberSecond = phoneNumberSecond;
	}

	public String getPhoneNumberthird() {
		return phoneNumberthird;
	}

	public void setPhoneNumberthird(String phoneNumberthird) {
		this.phoneNumberthird = phoneNumberthird;
	}

	public int getAdminCon() {
		return adminCon;
	}

	public void setAdminCon(int adminCon) {
		this.adminCon = adminCon;
	}

	public int getRoomNumber() {
		return RoomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "User [pryNumber=" + pryNumber + ", idName=" + idName + ", password=" + password + ", name=" + name
				+ ", age=" + age + ", email=" + email + ", phoneNumberFirst=" + phoneNumberFirst
				+ ", phoneNumberSecond=" + phoneNumberSecond + ", phoneNumberthird=" + phoneNumberthird + ", adminCon="
				+ adminCon + ", RoomNumber=" + RoomNumber + "]";
	}
	
	
}
