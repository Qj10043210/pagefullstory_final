package com.ajar.pagefullstory.game;


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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajar.pagefullstory.client.*;
import com.ajar.pagefullstory.board.*;
import com.ajar.pagefullstory.log.*;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private LogService lsv;
	
	@Autowired
	private GameService gsv;
	
	@Autowired
	private BoardService bsv;
	
	@Autowired
	private ClientService csv;
	
	public long delayInMillis = 1500;
	
	@RequestMapping(value="/getScoreRSP", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<GameVO> getScoreRSP(HttpSession session) {
		List<GameVO> mav = new ArrayList<>();
		try {
			if(session.getAttribute("joinedName")!=null){
				String lId = (String) session.getAttribute("joinedName");
				mav = gsv.getScoreRSP(lId);
				
			}
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    	
	    }
		return mav;
		
	};
	
	@RequestMapping(value="/tryRoom", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String tryRoom(@RequestBody LogVO sources, HttpSession session) {
		String result="";
		try {			
	       result = lsv.checkRecent(sources);
	       if(result.equals("Y")) {
	    	   session.setAttribute("newbie", "L");
	       }else if(result.equals("S")) {
	    	   session.setAttribute("newbie", "R");
	       }
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		return result;
	};
	
	@RequestMapping(value="/enterRoom", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String enterRoom(@RequestBody LogVO sources, HttpSession session) {
		
		String result ="n";
		
		try {
		   session.setAttribute("loc", sources.getL_room());
		   session.setAttribute("typ", sources.getB_type());
	       lsv.recordIn(sources);
	       lsv.joinRoom(sources, session);
	       result ="w";
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		return result;
	}
	@RequestMapping(value="/leaveRoom", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void leaveRoom(@RequestBody LogVO sources, HttpSession session) {
		try {
			
			lsv.recordOut(sources);			
			bsv.leaveBoard(sources);
		}catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
	}
	@RequestMapping(value="/outTimer", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void outTimer(@RequestBody LogVO sources, HttpSession session) {
		try {
			
			lsv.recordOut(sources);			
			bsv.leaveBoard(sources);
			session.invalidate();
		}catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
	}
	@RequestMapping(value="/insertHand", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	
	public void insertHand(@RequestBody GameVO sources) {
		try {
			gsv.insertHand(sources);
		}catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
	}
	@RequestMapping(value="/giveHand", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	@Async
	public Map<String, Object> giveHand(@RequestBody GameVO sources) {
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
		try {
			Thread.sleep(delayInMillis);
			List<GameVO> temp = gsv.giveHand(sources);			
			if(temp.size() > 1) {
				GameVO temp2 = new GameVO();
				if(temp.get(0).getRo_lr().equals("L")) {
					temp2.setH_l(temp.get(0).getG_hand());
					temp2.setH_r(temp.get(1).getG_hand());
					resultMap.put("leftHand", temp.get(0).getG_hand());
					resultMap.put("rightHand", temp.get(1).getG_hand());
					resultMap.put("leftMan", temp.get(0).getP_name());
					resultMap.put("rightMan", temp.get(1).getP_name());
				}else if(temp.get(0).getRo_lr().equals("R")) {
					temp2.setH_l(temp.get(1).getG_hand());
					temp2.setH_r(temp.get(0).getG_hand());
					resultMap.put("leftHand", temp.get(1).getG_hand());
					resultMap.put("rightHand", temp.get(0).getG_hand());
					resultMap.put("leftMan", temp.get(1).getP_name());
					resultMap.put("rightMan", temp.get(0).getP_name());
				}
				result = gsv.getResultRSP(temp2);
				resultMap.put("result", result);
				
			}else {
				resultMap.put("result", "fail");
			}
			
		}catch (InterruptedException e) {
            Thread.currentThread().interrupt();

	        
	        
	    }
		return resultMap;
	}
	@RequestMapping(value="/returnValueDamage", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	@Transactional
	public long returnValueDamaage(@RequestParam("p_dex") int p_dex) {
		return csv.giveDamage(p_dex);
	}
	
	@RequestMapping(value="/returnValueCurHp", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	@Transactional
	public Double returnValueCurHp(@RequestParam("p_name") String p_name, @RequestParam("damage") long damage) {
		Double result = csv.takeDamage(damage, p_name);		
		return result; 
	}
}
