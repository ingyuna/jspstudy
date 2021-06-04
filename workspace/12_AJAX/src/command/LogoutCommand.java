package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;

public class LogoutCommand implements MemberCommand {

	@Override
	public ModelAndView exectue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그아웃에서는 세션에 올라와있는거 초기화만 하면 된다.
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();
		}
		return new ModelAndView("/12_AJAX/index.jsp", true) ;
	}

}
