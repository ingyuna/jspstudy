package model;

import javax.servlet.http.HttpServletRequest;

public class BeerService {

	public String exectue(HttpServletRequest request) {
		
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));	// 정수가 아닌 값이 "age"로 와서 오류가 날 상황을 대비해서 try~catch를 해준다.
		} catch (Exception e) {
			
		}
		
		request.setAttribute("result", age < 20 ? "아가는 포카리나 드세요." : "여기 있습니다~!");
		
		return "views/output.jsp";
	}
	
}
