package com.ajar.pagefullstory.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ajar.pagefullstory.log.LogVO;

@Component
public class BoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@Autowired
	private SqlSession sqlSession;



	public List<BoardVO> getBoard(int reqN) {
		return sqlSession.selectList("mapper.boardMap.getBoard",reqN);
	}



	public void updateBoard(BoardVO sources) {
		int results =0;
		if (sqlSession.selectOne("mapper.boardMap.checkSevenBoardCount") != null) {
			results = sqlSession.selectOne("mapper.boardMap.checkSevenBoardCount");
			sqlSession.delete("mapper.boardMap.checkSevenBoard",results);
		}
		
		sqlSession.insert("mapper.boardMap.updateBoard", sources);
		
	}



	public void leaveBoard(LogVO sources) {
		sqlSession.delete("mapper.logMap.clearRoom",sources);
		int results = 0;
		if(sqlSession.selectOne("mapper.logMap.checkRecentN", sources) != null) {
			results= sqlSession.selectOne("mapper.logMap.checkRecentN",sources);
			
		}
		if(results != 1) {
			sqlSession.delete("mapper.boardMap.leaveBoard", sources);	
		}else if(results == 1) {
			String rt =(String) sqlSession.selectOne("mapper.logMap.checkRecentName",sources);
			Map<String, Object> mav = new HashMap<>();
			mav.put("l_room", sources.getL_room());
			mav.put("p_name",rt);			
			sqlSession.update("mapper.boardMap.leaveGiveBoard", mav);
		}
		
	}
}
