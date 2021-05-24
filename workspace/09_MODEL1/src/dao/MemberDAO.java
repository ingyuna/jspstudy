package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.util.DBConnector;
import dto.MemberDTO;

public class MemberDAO {

	// field
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	// singleton
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	
	
	/* 1. 회원가입 */
	public int save(MemberDTO dto) {	// join.jsp가 전달한 dto
		int result = 0;
		try {
			con = DBConnector.getinstance().getConnection();
			sql = "INSERT INTO MEMBER VALUES (MEMBER.SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());		// 물음표 위치 => 1, 2, 3, 4
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getEmail());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
		
	}
	
	/* 2. 아이디 중복체크 */
	public boolean isUsableId(String id) {		// idCheck.jsp가 전달한 id
		boolean result = false;
		try {
			con = DBConnector.getinstance().getConnection();
			sql = "SELECT ID FROM MEMBER WHERE ID = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			result = !rs.next(); 	// 검색결과 rs가 없으면 중복이 없으므로 가입이 가능하다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);
		}
		return result;
	}
	
	
	
}
