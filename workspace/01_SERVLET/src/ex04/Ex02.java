package ex04;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex02
 */
@WebServlet("/Ex02")
public class Ex02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 1. <a> 태그를 이용해서 넘어오면 100% get 방식이다.
		// 2. 함께 넘어오는 파라미터는 request가 처리한다.
		
		// request에서 파라미터 꺼내는 방법 
		// 1. getParameter() 메소드를 이용한다.
		// 2. String getParameter(String parameter) { } 형식이다. 한 마디로 String을 반환한다.
		
		String name = request.getParameter("name");
		if (name != null && !name.isEmpty()) {
			System.out.println(name);
		}				
		
		String strHeight = request.getParameter("height");
		int height = 0;
		if (strHeight != null && !strHeight.isEmpty()) {
			height = Integer.parseInt(strHeight);			
		}
		
		String strWeight = request.getParameter("weight");
		double weight = 0.0;
		if (strWeight != null && !strWeight.isEmpty()) {
			weight = Double.parseDouble(strWeight);			
		}
		
		System.out.println("키는 " + height + "cm이고, 몸무게는 " + weight + "kg이다.");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
