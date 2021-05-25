package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	private MemberDAO() {
		con = DBConnector.getinstance().getConnection();
	}
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
			sql = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
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
	
	/* 3. 로그인 */
	public MemberDTO login(MemberDTO dto) {		// login.jsp에서 받아온 dto
		MemberDTO logindtDto = null;
		try {
			sql = "SELECT NO, ID, PW, NAME, EMAIL, REGDATE FROM MEMBER WHERE ID = ? AND PW = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs = ps.executeQuery();
			if (rs.next()) {
				logindtDto = new MemberDTO();
				logindtDto.setNo(rs.getLong(1));
				logindtDto.setId(rs.getString(2));
				logindtDto.setPw(rs.getString(3));
				logindtDto.setName(rs.getString(4));
				logindtDto.setEmail(rs.getString(5));
				logindtDto.setRegdate(rs.getDate(6));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);
		}
		return logindtDto;
	}
	
	/* 4. 비밀번호 변경 */
	public int updatePw(MemberDTO dto) {	// pwChange.jsp에서 받아 온 dto
		int result = 0;
		try {
			sql = "UPDATE MEMBER SET PW = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setLong(2, dto.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
	}
	
	/* 5. 회원정보 변경 */
	public int updateMember(MemberDTO dto) {	// myPage.jsp에서 받아 온 dto
		int result = 0;		// 결과가 실패할 수 있으니까 미리 0으로 셋팅한다.
		try {
			sql = "UPDATE MEMBER SET NAME = ?, EMAIL = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getEmail());
			ps.setLong(3, dto.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
	}
	
	/* 6. 회원 탈퇴 */
	public int deleteMember(long no) {	// leave.jsp에서 받아 온 no
		int result = 0;
		try {
			sql = "DELETE FROM MEMBER WHERE NO = ?";	// = sql = "DELETE FROM MEMBER WHERE NO = " + no; 이것도 되지만 ?가 좋다.
			ps = con.prepareStatement(sql);				// 이렇게 하면 ps가 필요 없고 그냥 statement로 해야하는데, 이건 보완성이 떨어진다.
			ps.setLong(1, no);
			result = ps.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}	
		return result;	
	}
	
	/* 7. 전체 회원 */
	public List<MemberDTO> selectALL() {		// 전체 회원 가져오는 selectALL
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			sql = "SELECT NO, ID, PW, NAME, EMAIL, REGDATE FROM MEMBER";	// 칼럼 순서는 여기서 결정해서 아래 번호가 1,2...가 된다.
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getLong(1));
				dto.setId(rs.getString(2));
				dto.setPw(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);
		}
		return list;
	}
	
	
}
