package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ModelAndView;
import dao.StudentDAO;

public class SelectStudentListCommand implements StudentCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("list", StudentDAO.getInstance().selectStudentList());
		return new ModelAndView("/student/listPage.jsp", false);	// 포워드 (-> request에 들어있는 정보를 보내주기 위해서)
	}

}
