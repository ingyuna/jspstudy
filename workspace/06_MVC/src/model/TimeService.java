package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TimeService 클래스는
// 타입이 2개이다.
// TimeService, HomeService

public class TimeService implements HomeService {

	@Override			// "나 오버라이드 했다"라고 알려주는게 좋다.
	public String execute(HttpServletRequest request, HttpServletResponse response) { // 사용 안하더라도 HttpServletResponse response를 추가 해준다.
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
		request.setAttribute("time", sdf.format(date));
		
		return "views/output.jsp";	
		
	}
	
}
