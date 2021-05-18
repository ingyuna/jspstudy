package naver.captcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.captcha.CaptchaImage;
import login.captcha.CaptchaKey;

/**
 * http://localhost:9090/03_CAPTCHA/Login
 */
@WebServlet("/Login")	// URL맵핑값
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1. 네이버에 캡차 키 요청하기
		 * 2. 네이버에 캡차 이미지 요청하기
		 * 3. 로그인 페이지(login.jsp)로 이동하기
		 */
			
		// 1. 네이버에 캡차 키 요청하기
		String key = CaptchaKey.getCaptchaKey();
		// response.getWriter().write(key);
		
		// Login.java -> LoginValidation.java	key전달하기
		// 1. request 이용
		// Login.java -> login.jsp -> LoginValidation.java 
		// request.setAttribute("key", key);
		// 				<input type="hidden" name="key" value="<%=request.getAttribute("key")>">
		// 							request.getParameter("key")			// -> 이렇게 request를 2번을 써야 한다. 세션은 이렇게 계속 요청 안해도 저장이 되어있다.
		// 2. session 이용
		// Login.java -> login.jsp -> LoginValidation.java 
		// session.setAttribute("key", key);
		//					session.getAttribute("key")
		//								session.getAttribute("key")
		// session을 구하는 방법 (JSP에서는 기본 내장객체이기 때문에 구할 필요가 없다. 'JAVA'에서만 구하는것!)
		// 1. request에서 구한다.
		// 2. HttpSession session = request.getSession();
		HttpSession session = request.getSession();
		session.setAttribute("key", key);	// LoginValidation.java에서 필요함 
				
		// 2. 네이버에 캡차 이미지 요청하기
		CaptchaImage.getCaptchaImage(request, key);		// 원하면 뒤에 key값은 전달안해도 된다. 왜냐하면 위에 session에서 꺼내쓰면 되니까. 대신 코드가 길어짐.
		
		// CaptchaImage.java에게 request를 전달하고,
		// captchaImage.java가 그 request에 데이터를 저장했다.
		// 따라서 request에 저장된 데이터를 꺼내서 사용할 수 있다.
		// String dir = (String)request.getAttribute("DIR");
		// String filename = (String)request.getAttribute("filename");
		// response.getWriter().write(dir + "/" + filename);
		
		// 3. 로그인 페이지(login.jsp)로 이동하기
		// "캡차 이미지가 저장된 디렉터리 + 캡차 이미지 파일명"을 가지고 login.jsp로 이동 
		// 즉, request의 정보를 유지한 상태로 이동한다.	 -> forward 
		request.getRequestDispatcher("login.jsp").forward(request, response);	// 폴더를 안만들어서 바로 파일명을 적어준다. (폴더가 있으면 앞에 폴더/파일이름)	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
