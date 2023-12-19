package com.ajar.pagefullstory.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
	
	@Autowired
    private HttpSession httpSession;
	
	@Autowired
	private LogDAO ldao;
	
	public void defaultingAll() {
		ldao.defaultingAll();
		
	}
	
	public void recordIn(LogVO loginfo) {
		loginfo.setL_type(CustomType.ENTER);
		ldao.recordIn(loginfo);
	}

	public String checkRecent(LogVO sources) {
		return ldao.checkRecent(sources);
	}

	public void recordOut(LogVO loginfo) {
		loginfo.setL_type(CustomType.EXIT);
		ldao.recordIn(loginfo);
		ldao.leaveRoom(loginfo);
	}

	public void joinRoom(LogVO loginfo, HttpSession session) {
		String temp = (String) session.getAttribute("newbie");
		loginfo.setRo_lr(temp);
		ldao.joinRoom(loginfo);
		
	}
	public void leaveRoom(LogVO loginfo) {
		ldao.leaveRoom(loginfo);
	}

	public void readySitu(LogVO loginfo) {
		ldao.readySitu(loginfo);
		
	}

	public void loadReadyPlayer() {
		
		ldao.loadReadyPlayer();
	}

	public List<LogVO> updateReady(LogVO sources) {
		return ldao.updateReady(sources);
	}

	public void sessionUpdate(String sessionName, String sessionValue) {
		httpSession.setAttribute(sessionName, sessionValue);
		
	}

	
	
}
