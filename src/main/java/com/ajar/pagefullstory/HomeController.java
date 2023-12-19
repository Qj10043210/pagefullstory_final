package com.ajar.pagefullstory;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajar.pagefullstory.*;
import com.ajar.pagefullstory.log.LogService;


@Controller
public class HomeController {
	
	@Autowired
	private AllBehold allbehold;
	
	@Autowired
	private LogService lsv;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		 String sampleDirectoryPath = "C:/pagefull";
	        File sampleDirectory = new File(sampleDirectoryPath);
	        
	        if (sampleDirectory.exists() && sampleDirectory.isDirectory()) {
	            File[] files = sampleDirectory.listFiles();
	            if (files != null && files.length > 0) {
	                allbehold.setfileEnabled(true);
	            }
	        } else {
	            // C:\sample directory does not exist or is not a directory
	            // You can log a warning or perform other actions
	        	allbehold.setfileEnabled(false);
	        }		
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());		
		return "home";
	}
	@RequestMapping(value="/def", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void def() {
		lsv.defaultingAll();
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		return "index";
	}
	@RequestMapping(value = "/dead", method = RequestMethod.GET)
	public String dead(Locale locale, Model model) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		return "dead";
	}
	@RequestMapping(value = "/rsp", method = RequestMethod.GET)
	public String rsp(Locale locale, Model model, HttpSession session) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		try {
			
			if(session.getAttribute("loginedId")!=null){
				return "rsp";
			}
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "index";
	        
	    }
		return "index";
	}
	@RequestMapping(value = "/sdoku", method = RequestMethod.GET)
	public String sdoku(Locale locale, Model model, HttpSession session) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		try {
			
			if(session.getAttribute("loginedId")!=null){
				return "sdoku";
			}
			
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        return "index";
	        
	    }
		return "index";
	}
	@RequestMapping(value = "/room1", method = RequestMethod.GET)
	public String room1(Model model) {
		model.addAttribute("fileEnabled", allbehold.isfileEnabled());
		return "room1";
	}
	@RequestMapping(value = "/room2", method = RequestMethod.GET)
	public String room2(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room2";
	}
	@RequestMapping(value = "/room3", method = RequestMethod.GET)
	public String room3(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room3";
	}
	@RequestMapping(value = "/room4", method = RequestMethod.GET)
	public String room4(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room4";
	}
	@RequestMapping(value = "/room5", method = RequestMethod.GET)
	public String room5(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room5";
	}
	@RequestMapping(value = "/room6", method = RequestMethod.GET)
	public String room6(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room6";
	}
	@RequestMapping(value = "/room7", method = RequestMethod.GET)
	public String room7(Model model) {
	    model.addAttribute("fileEnabled", allbehold.isfileEnabled());
	    return "room7";
	}
	
}
