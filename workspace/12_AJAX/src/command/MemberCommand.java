package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public interface MemberCommand {
	public ModelAndView exectue(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
