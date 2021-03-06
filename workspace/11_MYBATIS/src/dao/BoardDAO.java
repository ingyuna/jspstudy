package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import mybatis.config.DBService;

public class BoardDAO {

	private SqlSessionFactory factory;
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	final String NAMESPACE = "mybatis.mapper.board";
		
	/* 1. 작성 */
	public int insert(BoardDTO dto) {
		SqlSession ss = factory.openSession(false);		// insert 후 수동커밋하겠다.
		int result = ss.insert(NAMESPACE + ".insertBoard", dto);		// ss.insert("SQL's id", "인수")
		if (result > 0) {	// ss.insert() 성공하면
			ss.commit(); 	// 커밋하겠다.
		}
		ss.close();
		return result;
	}
	
	/* 2. 전체 레코드 개수 */
	public int getTotalRecord() {
		SqlSession ss = factory.openSession();		// 커밋이 필요 없는 SELECT문
		int count = ss.selectOne("mybatis.mapper.board.getTotalRecord");  			// selectOne의 검색 결과가 1개라서 selectOne이 필요하다.
		ss.close();
		return count;
	}
	
	/* 3. 목록 */
	public List<BoardDTO> selectList(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.selectList", map);
		ss.close();
		return list;
	}
		
	/* 4. 같은 그룹 기존 댓글들의 groupord 증가 */
	public int increaseGroupordPreviousReply(long groupno) {
		SqlSession ss = factory.openSession(false);		// 직접 커밋하겠다.
		int result = ss.update("mybatis.mapper.board.increaseGroupordPreviousReply", groupno);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	/* 5. 댓글 삽입하기 */
	public int insertReply(BoardDTO replyDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(NAMESPACE + ".insertReply", replyDTO); // 위에 변수랑 이름 똑같이 하는건 그냥 우리끼리 약속해서 정한것. 이렇게 안해도 됨.
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	/* 6. 검색 결과 개수 반환 */
	public int getFindRecordCount(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NAMESPACE + ".getFindRecordCount", map);
		ss.close();
		return count;
	}
	
	/* 7. 검색 결과 반환 */
	public List<BoardDTO> findList(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList(NAMESPACE + ".findList", map);
		ss.close();
		return list;
	}
	
	/* 8. 삭제 */
	public int delete(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(NAMESPACE + ".delete", no);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 9. 대댓글 목록 */
	public List<BoardDTO> selectList3(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.selectList3", map);
		ss.close();
		return list;
	}
	
	/* 10. 원글 가져오기 */
	public BoardDTO selectBoard(long no) {
		SqlSession ss = factory.openSession();
		BoardDTO boardDTO = ss.selectOne("mybatis.mapper.board.selectBoard", no);
		ss.close();
		return boardDTO;			
	}
	
	/* 11. 원글의 groupord보다 큰 groupord를 가진 댓글의 groupord 증가 */
	public int increaseGroupordOtherReply(BoardDTO boardDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.board.increaseGroupordOtherReply", boardDTO);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}

	
}
