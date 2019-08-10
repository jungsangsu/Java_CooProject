package Action;

public class Protocol {
	public static final String REGISTER = "100"; //회원가입(request)
	
	public static final String IDSEARCH = "110"; //ID찾기 Join (request)
	
	public static final String IDSEARCH_OK = "111"; //ID찾기 (기존에 있는거) (ACK)
	
	public static final String IDSEARCH_NO = "112"; //ID찾기 (기존에 없음) (NACK)
	
	public static final String IDSEARCHCHECK = "113"; //(using 회원가입)ID찾기 중복확인(request)
	
	public static final String IDSEARCHCHECK_OK = "114"; //(using 회원가입)ID 중복확인 (사용가능) (ACK)
	
	public static final String IDSEARCHCHECK_NO = "115"; //(using 회원가입)ID 중복확인 (사용 불가능) (NACK)
	
	public static final String ENTERLOGIN = "120"; // 로그인(request)
	
	public static final String ENTERLOGIN_OK = "121"; // 로그인 성공(ACK)
	
	public static final String ENTERLOGIN_NO = "122"; //로그인 실패(NACK)
	
	public static final String PWSEARCH = "130"; //PW찾기
	
	public static final String ROOMMAKE = "200"; //방만들기
	
	public static final String ROOMMAKE_OK = "201"; //방만들기_ACK
	
	public static final String ROOMMAKE_OK1 = "202"; //방만들기_ACK(만든사람에게)
	
	public static final String ROOMSORT = "210"; //방정렬
	
	public static final String EXITWAITROOM = "220"; // 대기방 나가기(= logout)
	
	public static final String SENDWAITROOM = "250"; //대기방 메세지
	
	public static final String ENTERROOM = "300"; // 방입장
	
	public static final String ENTERROOM_OK = "301"; // 방입장 성공 
	
	public static final String ENTERROOM_OK1 = "302"; // 방입장 성공 //입장하는 당사자
			
	public static final String ENTERROOM_NO = "303"; // 방입장 실패
	
	public static final String ENTERROOM_USERLISTSEND = "305"; //방에 유저들을 보내줌
	
	public static final String EXITCHATTINGROOM = "310"; // 방나가기 (채팅방 나가기)
	
	public static final String SENDMESSAGE = "400"; //메세지 보내기
	
	public static final String SENDMESSAGE_ACK = "410"; //메세지 보내기(답장)
	
	public static final String CHATTINGSENDMESSAGE = "420"; //채팅방에서 메세지 보내기 (Request)
	
	public static final String CHATTINGSENDMESSAGE_OK = "430"; //채팅방에서 메세지 보내기 (Request)
}
