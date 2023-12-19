package com.ajar.pagefullstory.board;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajar.pagefullstory.client.*;
import com.ajar.pagefullstory.game.*;
import com.ajar.pagefullstory.log.*;
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bsv;
	
	@Autowired
	private GameService gsv;
	
	@RequestMapping(value="/getBoard", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<BoardVO> getBoardRSP(@RequestParam("reqN") String reqNs) {
		List<BoardVO> mav = new ArrayList<>();
		try {
			int reqN = Integer.parseInt(reqNs);
			mav = bsv.getBoard(reqN);
		}
		catch (Exception e) {
        
    	e.printStackTrace();
    	
    }
	return mav;
	}
	
	@RequestMapping(value="/updateBoard", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void updateBoard(@RequestBody BoardVO sources) {
		try {			
	        bsv.updateBoard(sources);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
	}
	
	@RequestMapping(value="/getCoonectedUser", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<LogVO> getCoonectedUser(@RequestBody LogVO sources) {
		List<LogVO> nav = new ArrayList<>();
		try {			
	        nav = gsv.getCoonectedUser(sources);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		return nav;
	}
	
}
