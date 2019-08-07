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
	
	public static final String ROOMSORT = "210"; //방정렬
	
	public static final String EXITWAITROOM = "220"; // 대기방 나가기
	
	public static final String ENTERWAITROOM = "230"; // 대기방 입장
	
	public static final String SENDWAITROOM = "250"; //대기방 메세지
	
	public static final String ENTERROOM = "300"; //방입장
	
	public static final String EXITROOM = "310"; //방나가기
	
	public static final String SENDMESSAGE = "400"; //메세지 보내기
}
