package com.ajar.pagefullstory.game;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajar.pagefullstory.log.*;

@Service
public class GameService {

	@Autowired
	private GameDAO gdao;
	
	public List<GameVO> getScoreRSP(String lId) {
		// TODO Auto-generated method stub
		return gdao.getScroeRSP(lId);
	}
	public String getResultRSP(GameVO leftright) {
		return gdao.geResultRSP(leftright);
	}
	public List<GameVO> giveHand(GameVO sources) {
		return gdao.giveHand(sources);
	}
	public void insertHand(GameVO sources) {
		gdao.insertHand(sources);
		
	}
	public void deleteHand(String man) {
		gdao.deleteHand(man);
	}
	public List<LogVO> getCoonectedUser(LogVO sources) {
		return gdao.getCoonectedUser(sources);
	}
	
}
