package com.ajar.pagefullstory.log;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajar.pagefullstory.board.BoardVO;

@Controller
@RequestMapping("/lg")
public class LogController {
	
	@Autowired
	private LogService lsv;
	
	@RequestMapping(value="/rcin", method = {RequestMethod.GET, RequestMethod.POST})
	public void recordIn(@RequestBody LogVO loginfo) {
		String test = loginfo.getP_name();
		if(test != null && test != "") {
			
			lsv.recordIn(loginfo);
		}
	}
	@RequestMapping(value="/rcout", method = {RequestMethod.GET, RequestMethod.POST})
	public void recordOut(@RequestBody LogVO loginfo) {
		String test = loginfo.getP_name();
		if(test != null && test != "") {			
			lsv.recordOut(loginfo);
		}
	}
	@RequestMapping(value="/roomjoin", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void joinRoom(@RequestBody LogVO loginfo, HttpSession session) {
		String test = loginfo.getP_name();
		if(test != null && test != "") {			
			lsv.joinRoom(loginfo, session);
		}
	}
	@RequestMapping(value="/readySitu", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void readySitu(@RequestBody LogVO loginfo) {
		
		String test = loginfo.getP_name();
		
		if(test != null && test != "") {			
			lsv.readySitu(loginfo);
		}
	}
	@RequestMapping(value="/loadReadyPlayer", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void loadReadyPlayer(@RequestParam("emptyS") String empty) {
		
		try {			
			lsv.loadReadyPlayer();
		}
		catch (Exception e) {
        
    	e.printStackTrace();
    	
		}
		
		
	}
	
	@RequestMapping(value="/updateReady", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<LogVO> updateReady(@RequestBody LogVO sources) {
		List<LogVO> nav = new ArrayList<>();
		try {			
			nav = lsv.updateReady(sources);
		}
		catch (Exception e) {
        
    	e.printStackTrace();
    	
		}
		return nav;
		
	}
	
	
}
