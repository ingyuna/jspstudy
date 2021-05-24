package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class RectangleCommand implements ShapeCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int width = 0;
		int height = 0;
		try {
			width = Integer.parseInt(request.getParameter("width"));
			height = Integer.parseInt(request.getParameter("height"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 결과값을 request에 저장
		request.setAttribute("area", width * height);
				
		return new ModelAndView("views/output.jsp", false);		// false -> redirect가 아니다. forward 할꺼임.
	}

}
