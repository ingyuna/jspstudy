package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConnector;
import dto.BoardDTO;
import dto.PageVO;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		con = DBConnector.getinstance().getConnection();
	}
	public static BoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	/* 1. 게시글 삽입 */
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
	}
	
	/* 2. 전체 게시글 반환 */
	public List<BoardDTO> selectAll(PageVO pageVO) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			/*******************************************************
			SELECT b.rn, b.employee_id, b.first_name
			  FROM (SELECT rownum as rn, a.employee_id, a.first_name
			          FROM (SELECT employee_id, first_name
			                  FROM employees
			                 ORDER BY hire_date) a) b
			 WHERE b.rn between 11 and 20;

			-- a : 정렬한 테이블
			-- b : a 테이블에 rn을 추가한 테이블 (a에서 정렬하고 -> b에서 행번호 붙임)
			**********************************************************/
			
			sql = "SELECT b.IDX, b.AUTHOR, b.TITLE, b.CONTENT, b.HIT, b.POSTDATE" +
				  " FROM (SELECT ROWNUM AS rn, a.IDX, a.AUTHOR, a.TITLE, a.CONTENT, a.HIT, a.POSTDATE" +
				  "         FROM (SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, POSTDATE" +
				  "                  FROM BOARD" +
				  "        			ORDER BY POSTDATE DESC) a ) b" +
				  " WHERE b.rn BETWEEN ? AND ?";			// -> 전체가 아닌 일부만 가져올 수 있는 (3개씩만)
			ps = con.prepareStatement(sql);
			ps.setInt(1, pageVO.getBeginRecord());
			ps.setInt(2, pageVO.getEndRecord());
			rs = ps.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
				list.add(dto);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);	
		}		
		return list;
	}
	
	/* 3. 조회수 증가 */
	public void updateHit(long idx) {	// 조회수가 늘었습니다. 이런걸 하지않으니까 반환타입은 없다.
		try {
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}	
	}
	
	
	/* 4. 게시글 반환 */
	public BoardDTO selectByIdx(long idx) {		
		BoardDTO dto = null;		// 페이지에는 있는데 DB에는 없을 수도 있으니까 이렇게 구현.
		try {
			sql = "SELECT AUTHOR, TITLE, CONTENT, HIT, POSTDATE FROM BOARD WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			rs = ps.executeQuery();
			if (rs.next()) {	// rs가 존재한다면
				dto = new BoardDTO();		// 만든다.
				dto.setIdx(idx);
				dto.setAuthor(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setHit(rs.getInt(4));
				dto.setPostdate(rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);			
		}
		return dto;
	}
	
	/* 5. 게시글 삭제 */
	public int deleteBoard(long idx) {
		int result = 0;
		try {
			sql = "DELETE FROM BOARD WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
	}
	
	/* 6. 게시글 수정 */
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setLong(3, dto.getIdx());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, null);
		}
		return result;
		
	}
	
	/* 7. 전체 게시글의 개수 반환 */
	public int getTotalRecord() {
		int totalRecord = 0;
		try {
			sql = "SELECT COUNT(IDX) AS TOTAL_RECORD FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);		// totalRecord = rs.getInt("TOTAL_RECORD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getinstance().close(ps, rs);
		}
		return totalRecord;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	 
}
