package com.ajar.pagefullstory.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.ajar.pagefullstory.log.*;

@Component
public class GameDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GameVO> getScroeRSP(String lId) {
		sqlSession.insert("mapper.gameMap.checkScoreRSP", lId);
		
		return sqlSession.selectList("mapper.gameMap.getScoreRSP",lId);
	}

	public String geResultRSP(GameVO leftright) {
		String leftHand = leftright.getH_l();
		String rightHand = leftright.getH_r();
		
		 if (leftHand.equals(rightHand)) {
		        return "draw";
		    } else if ((leftHand.equals("R") && rightHand.equals("P")) ||
		               (leftHand.equals("P") && rightHand.equals("S")) ||
		               (leftHand.equals("S") && rightHand.equals("R"))) {
		        return "rw";
		    } else if ((leftHand.equals("R") && rightHand.equals("S")) ||
		               (leftHand.equals("P") && rightHand.equals("R")) ||
		               (leftHand.equals("S") && rightHand.equals("P"))) {
		        return "lw";
		    } else {
		        return "fail";
		    }	
				
	}

	public List<GameVO> giveHand(GameVO sources) {
		
		return sqlSession.selectList("mapper.gameMap.giveHand",sources);
		
	}

	public void insertHand(GameVO sources) {
		sqlSession.insert("mapper.gameMap.insertHand", sources);		
	}
	public void deleteHand(String sources) {
		sqlSession.delete("mapper.gameMap.deleteHand", sources);		
	}

	public List<LogVO> getCoonectedUser(LogVO sources) {
		
		return sqlSession.selectList("mapper.gameMap.getCoonectedUser", sources);
	}

}
