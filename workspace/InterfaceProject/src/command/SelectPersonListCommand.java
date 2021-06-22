package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

@WebServlet("/selectPersonList.do")
public class SelectPersonListCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SelectPersonListCommand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> list = PersonDAO.getInstance().selectPersonList();
		// list -> Person -> JSONObject -> JSONArray 추가 (리스트에 있는걸 하나 빼면 Person이고, 요걸 JSONOBJECT로 바꿔서 JSONArray를 추가)
		JSONArray arr = new JSONArray();
		for (Person person : list) {	// 리스트에 있는걸 빼주고,
			JSONObject obj = new JSONObject();			// JSONObject로 바꿔준다.
			obj.put("sno", person.getSno());
			obj.put("name", person.getName());
			obj.put("age", person.getAge());
			obj.put("birthday", person.getBirthday());
			obj.put("regdate", person.getRegdate().toString());
			arr.add(obj);			// JSONArray 추가
		}
		// System.out.println(arr);
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(arr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
