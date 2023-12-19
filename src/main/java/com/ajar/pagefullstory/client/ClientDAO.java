package com.ajar.pagefullstory.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
public class ClientDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ClientService csv;
	
	@Autowired
	private SqlSession sqlSession;
	
	public String checkId(String reqId, String reqPwd) {
		Map<String, String> params = new HashMap<>();
		params.put("reqId", reqId);
		params.put("reqPwd", reqPwd);
		int result = sqlSession.selectOne("mapper.clientMap.checkId", params);
		if(result==0) {
			return "0";
		}else {
			int result2 = sqlSession.selectOne("mapper.clientMap.checkPwd", params);
			return (result2 > 0) ?  "1" : "2" ;
		}
		
	}

	

	public void signUp(String reqId, String reqPwd) {
		Map<String, String> params = new HashMap<>();
		params.put("reqId", reqId);
		params.put("reqPwd", reqPwd);
		sqlSession.insert("mapper.clientMap.signUp", params);
		
	}



	public List<ClientVO> tochar(String lId) {
		List<ClientVO> mav;
		try {
		    mav = sqlSession.selectList("mapper.clientMap.tochar", lId);

		    
		    if (mav == null || mav.isEmpty()) {
		        mav = new ArrayList<>();
		    }
		} catch (Exception e) {
		 
		    mav = new ArrayList<>();
		    e.printStackTrace();
		}

		return mav;
	}



	public String checkName(String reqName) {
		int result = sqlSession.selectOne("mapper.clientMap.checkName", reqName);
		return String.valueOf(result);
	}



	public void postPlayer(ClientVO datas) {
		Double p_maxHp = csv.getMaxHpWithStr(datas.getP_str());
		datas.setP_maxHp(p_maxHp);
		datas.setP_curHp(p_maxHp);
		sqlSession.insert("mapper.clientMap.postPlayer", datas);
		
	}



	public String goodbye(String reqName) {
		int result = sqlSession.delete("mapper.clientMap.goodbye", reqName);
		return result > 0 ? "q" : "n"; 
	}



	public List<ClientVO> getUserInfo(String p_name) {
		List<ClientVO> mav = new ArrayList<>();
		if(sqlSession.selectList("mapper.clientMap.getUserInfo",p_name) != null) {
			mav= sqlSession.selectList("mapper.clientMap.getUserInfo",p_name);
		}
		return mav;
	}



	public Double getMaxHpWithStr(Integer p_str) {
		return 50 + p_str + p_str * Math.log10(p_str); 
	}
	
	public Double getExpBonus(Integer p_luk) {
		return 1+ p_luk * Math.log10(p_luk);
	}


	public long giveDamage(Integer p_dex) {
		
		return Math.round(1+p_dex+p_dex*Math.log10(p_dex)*Math.log10(p_dex));
	}



	public Double takeDamage(long damage, String p_name) {
		List<ClientVO> mav = new ArrayList<>();
		if(sqlSession.selectList("mapper.clientMap.getUserInfo",p_name) != null) {
			mav= sqlSession.selectList("mapper.clientMap.getUserInfo",p_name);
		}
		int p_int = mav.get(0).getP_int();
		double p_curHp = mav.get(0).getP_curHp();
		long redDam = p_int >= damage ? 0 : damage - p_int;	
		Double result  =p_curHp > redDam ?(double) p_curHp - redDam : 0; 
		Map<String, Object> newMap = new HashMap<>();
		newMap.put("p_name", p_name);
		newMap.put("p_curHp", result);
		sqlSession.update("mapper.clientMap.updateHp",newMap);
		return result ;
	}



	public void youWhat(String p_name, String string) {
		Map<String, Object> nav = new HashMap<>();
		nav.put("p_name", p_name);
		nav.put("string", string);
		sqlSession.update("mapper.gameMap.updateScore",nav);
		if(string.equals("r_win")) {
			int p_luk = sqlSession.selectOne("mapper.clientMap.getLuck",p_name);
			Double EXP = getExpBonus(p_luk);
			nav.put("p_exp", EXP);
			sqlSession.update("mapper.gameMap.updateExp",nav);
		}
	}



	public void removePlayer(String p_name) {
		sqlSession.delete("mapper.clientMap.goodbye",p_name);
		
	}
	



	
	
	
	
	
	
}
