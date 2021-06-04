package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class LoginCommand implements MemberCommand {

	@Override
	public ModelAndView exectue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = new Member(id, pw, null, null, null);
		
		Member loginUser = MemberDAO.getInstance().login(member);
		
		if (loginUser != null) {	// 로그인 성공했을 때 -> 세션에 올려둔다
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		} else {	// 실패했을 때
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// ajax 처리하는 것이 아니다. MVC 패턴 처리하는것.
		return new ModelAndView("/member/myPage.jsp", false);	// 로그인은 forward, redirect 상관없다.
	}

}
