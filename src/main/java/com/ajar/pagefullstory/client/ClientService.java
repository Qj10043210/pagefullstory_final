package com.ajar.pagefullstory.client;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private ClientDAO cdao;
	
	
	public String checkId(String reqId, String reqPwd) {
		
		return cdao.checkId(reqId, reqPwd);
	}

	
	public void signUp(String reqId, String reqPwd) {
		cdao.signUp(reqId,reqPwd);
		
	}


	public List<ClientVO> tochar(String lId) {
		return cdao.tochar(lId);
	}


	public String checkName(String reqName) {		
		return cdao.checkName(reqName);
	}


	public void postPlayer(ClientVO datas) {
		cdao.postPlayer(datas);
		
	}


	public String goodbye(String reqName) {
		return cdao.goodbye(reqName);
		
	}


	public List<ClientVO> getUserInfo(String p_name) {		
		return cdao.getUserInfo(p_name);
	}
	
	public Double getMaxHpWithStr(Integer p_str) {
		return cdao.getMaxHpWithStr(p_str);
	}
	public long giveDamage(Integer p_dex) {
		return cdao.giveDamage(p_dex);
	}
	public Double takeDamage(long damage, String p_name) {
		return cdao.takeDamage(damage, p_name);
	}


	public void youWhat(String p_name, String string) {
		cdao.youWhat(p_name, string);
		
	}


	public void removePlayer(String p_name) {
		cdao.removePlayer(p_name);
		
	}
}
