package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class InsertMemberCommand implements MemberCommand {

	@Override
	public ModelAndView exectue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strMember = request.getParameter("member");
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(strMember);
		
		// 데이터베이스로 보낼 member로 만들어주기
		Member member = new Member();
		member.setId( obj.get("id").toString() );
		member.setName( obj.get("name").toString() );
		member.setGender( obj.get("gender").toString() );
		member.setAddress( obj.get("address").toString() );
		
		// 데이터 베이스로 보내주고 결과 받기
		
		// + 추가로 아이디 중복체크를 해주는 작업이 여기서 필요할 수 있다(지금 여기서는 안 함)
		int result = MemberDAO.getInstance().insertMember(member);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("isSuccess", result > 0);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj2);
		
		return null;
	}

}
