package com.ajar.pagefullstory.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajar.pagefullstory.client.ClientDAO;
import com.ajar.pagefullstory.log.LogVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;

	public List<BoardVO> getBoard(int reqN) {		
		return bdao.getBoard(reqN);
	}

	public void updateBoard(BoardVO sources) {
		bdao.updateBoard(sources);
	}

	public void leaveBoard(LogVO sources) {
		bdao.leaveBoard(sources);
		
	}
	
}
