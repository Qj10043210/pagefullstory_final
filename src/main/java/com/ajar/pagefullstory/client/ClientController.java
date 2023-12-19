package com.ajar.pagefullstory.client;

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
import org.springframework.web.servlet.ModelAndView;
import com.ajar.pagefullstory.*;


@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService csv;
	
	@Autowired
	private AllBehold allbehold;
	
	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String logIn(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd) {
		try {
	        return csv.checkId(reqId, reqPwd);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        return "3"; // Indicates an error
	    }
		//0:no id, 1 : login success, 2 : login fail, 3 : trouble
		
	}
	@RequestMapping(value="/signin", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void signIn(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd, HttpSession session) {
		try {
			session.setMaxInactiveInterval(60 * 60);
			session.setAttribute("loginedId", reqId);
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    }

		
	}
	@RequestMapping(value="/signup", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void signUp(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd) {
		try {
	        csv.signUp(reqId, reqPwd);
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    }

	}
	
	@RequestMapping(value="/tochar", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ClientVO> tochar(HttpSession session) {
		List<ClientVO> mav = new ArrayList<>();
		try {
			if(session.getAttribute("loginedId")!=null){
				String lId = (String) session.getAttribute("loginedId");
				mav = csv.tochar(lId);
				
			}else {
				
			}
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    	
	    }
		return mav;
		
	}
	
	@RequestMapping(value="/checkName", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String checkName(@RequestParam("reqName") String reqName) {
		try {
	        return csv.checkName(reqName);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        return "2"; // Indicates an error
	    }
		//0:no id, 1:there is duplicated id 2 : trouble
		
	}
	
	@RequestMapping(value="/postPlayer", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void postPlayer(@RequestBody ClientVO datas, HttpSession session) {
		try {
			String lId = (String) session.getAttribute("loginedId");
			datas.setName(lId);
	        csv.postPlayer(datas);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		
	}
	
	@RequestMapping(value="/goodbye", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String goodbye(@RequestParam("reqName") String reqName) {
		try {
	        return csv.goodbye(reqName);
	        
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		return "n";
	}
	
	@RequestMapping(value="/gameopen", method = {RequestMethod.GET, RequestMethod.POST})
	public String gameopen(@RequestParam("data") String data, @RequestParam("names") String names , HttpSession session, Model model) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		try {
			if(session.getAttribute("loginedId")!=null){
				session.setAttribute("joinedName", names);
				return "redirect:/" + data;
			}
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "redirect:/index";
	        
	    }
		return "redirect:/index";
	}
	@RequestMapping(value="/index", method = {RequestMethod.GET, RequestMethod.POST})
	public String gotoindex(HttpSession session) {
		try {
			
				session.invalidate();
				return "redirect:/index";
			
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "redirect:/index";
	        
	    }
		
	}
	@RequestMapping(value="/dead", method = {RequestMethod.GET, RequestMethod.POST})
	public String gotodead(HttpSession session) {
		try {
			
				session.invalidate();
				return "redirect:/dead";
			
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "redirect:/dead";
	        
	    }
		
	}
	@RequestMapping(value = "/room", method = RequestMethod.GET)
	public String room(Locale locale, Model model, HttpSession session, @RequestParam("num") String num) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		try {
				String tt = "redirect:/room" + num;
				
					return tt;
				
			
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "redirect:/index";
	        
	    }
		
	}
	
	@RequestMapping(value="/getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ClientVO> bringFaceHair(@RequestParam("p_name") String p_name){
		
		List<ClientVO> mav = new ArrayList<>();
		try {
			mav = csv.getUserInfo(p_name);
		
		} catch (Exception e) {
	        
	        e.printStackTrace();
	     }
		
		return mav;
	}
	
	@RequestMapping(value="/removePlayer", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void removePlayer(@RequestParam("p_name") String p_name){
		
		
		try {
			csv.removePlayer(p_name);
		
		} catch (Exception e) {
	        
	        e.printStackTrace();
	     }
		
		
	}
	
	@RequestMapping(value="/youLose", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void youLose(@RequestParam("p_name") String p_name){
		csv.youWhat(p_name,"r_lose");
	}
	@RequestMapping(value="/youWin", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void youWin(@RequestParam("p_name") String p_name){
		csv.youWhat(p_name,"r_win");
	}
	@RequestMapping(value="/youDraw", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void youDraw(@RequestParam("p_name") String p_name){
		csv.youWhat(p_name,"r_draw");
	}
}
