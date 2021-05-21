package model;

import javax.servlet.http.HttpServletRequest;

public class GugudanService {

	public String execute(HttpServletRequest request) {
		
		String strDan = request.getParameter("dan");
		int dan = 1;
		if (!strDan.isEmpty()) {
			dan = Integer.parseInt(strDan);
		}
		StringBuilder sb = new StringBuilder();
		for (int n = 1; n <= 9; n++) {
			sb.append(dan).append("x").append(n).append("=").append(dan * n).append("<br>");
		}
		request.setAttribute("gugudan", sb.toString());  // StringBuilder는 그냥 담으면 안되고 String으로 형변환을 해서 담아줘야 한다.
		
		return "views/output.jsp";
	}
	
}
