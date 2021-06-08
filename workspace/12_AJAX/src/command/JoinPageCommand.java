package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class JoinPageCommand implements MemberCommand {

	@Override
	public ModelAndView exectue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("/member/joinPage.jsp", false);
	}

}