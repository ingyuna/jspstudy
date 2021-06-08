package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.InsertStudentCommand;
import command.SelectStudentListCommand;
import command.StudentCommand;


@WebServlet("*.do")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1];		// request가 array 마지막 요소에 담겨있다.
		
		StudentCommand command = null;
		ModelAndView mav = null;
		switch (cmd) {
		case "selectStudentList.do":
			command = new SelectStudentListCommand();
			break;
		case "insertStudent.do":
			command = new InsertStudentCommand();
			break;
			
		}
		
		if (command != null) {		// 실행할 때 try~catch가 필요하다. 위에서 throws를 던지기 때문에.
			try {
				mav = command.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (mav != null) {
			if (mav.isRedirect()) {
				response.sendRedirect(mav.getView());
			} else {
				request.getRequestDispatcher(mav.getView()).forward(request, response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
