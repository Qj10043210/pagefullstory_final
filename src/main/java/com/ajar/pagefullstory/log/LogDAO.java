package com.ajar.pagefullstory.log;

import java.sql.Timestamp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Component
public class LogDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private LogService lsv;
	
	
	public void recordIn(LogVO loginfo) {		
		try {
			
			sqlSession.insert("mapper.logMap.recordIn",loginfo);
		}catch (Exception e) {
			
		}
	}
	public String checkRecent(LogVO sources) {
		sqlSession.delete("mapper.logMap.clearLog",sources);
		int cf = sqlSession.selectOne("mapper.logMap.checkRecentN",sources);		
		String result = "";
		if(cf>0) {
			String cs =  sqlSession.selectOne("mapper.logMap.checkRecent",sources);			
			if(cs.equals("EXIT")) {
				result = "Y";
			}else if(cs.equals("ENTER")) {				
				LogVO temps = sqlSession.selectOne("mapper.logMap.checkTRecent",sources);
				Timestamp ct = (Timestamp) temps.getL_createdat();
				int cn = (int) temps.getL_id();
				
				 Instant loggedInstant = ct.toInstant();
				 Instant currentInstant = Instant.now();
				 Duration duration = Duration.between(loggedInstant, currentInstant);
				  
				 
				 if (duration.toMinutes() > 1) {
					 sqlSession.delete("mapper.logMap.clearLogAll",sources);
					 sqlSession.update("mapper.logMap.updateType", cn);
					 result = "Y";
			        } 
			         else {
			        	result = "S";
			        }
				
			}else {
				result = "E";
			}
			
		}else {
			result = "Y";
		}
		return result;
	}
	public void joinRoom(LogVO loginfo) {
		sqlSession.delete("mapper.logMap.clearRoom",loginfo);
		List<LogVO> result = new ArrayList<>();
		if(sqlSession.selectList("mapper.logMap.checkLR",loginfo) != null) {
			result=	sqlSession.selectList("mapper.logMap.checkLR",loginfo);	
		}
		if(result != null && result.size()==1) {
			LogVO temps = result.get(0); 
			switch(temps.getRo_lr()) {
			case "L" :
				loginfo.setRo_lr("R");
				lsv.sessionUpdate("newbiew", "R");
				break;
			case "R" :
				loginfo.setRo_lr("L");
				lsv.sessionUpdate("newbiew", "L");
				break;
			default:
			break;
			};
			
		}
		sqlSession.insert("mapper.logMap.joinRoom",loginfo);
		
		
	}
	public void leaveRoom(LogVO loginfo) {
		sqlSession.delete("mapper.logMap.clearLogPlayer",loginfo);
		sqlSession.delete("mapper.logMap.clearRoom",loginfo);		
	}
	public void readySitu(LogVO loginfo) {
		sqlSession.update("mapper.logMap.readySitu",loginfo);
		
	}
	public void loadReadyPlayer() {
		List<LogVO> mav = new ArrayList<>();
		if(sqlSession.selectList("mapper.logMap.loadReadyPlayer")!=null) {
			mav = sqlSession.selectList("mapper.logMap.loadReadyPlayer");			
		}
		
		for(LogVO log : mav) {
			String p_name = log.getP_name();			
			sqlSession.update("mapper.boardMap.readyBoard",p_name);
		}
		
		
	}
	public List<LogVO> updateReady(LogVO sources) {
		List<LogVO> mav = new ArrayList<>();
		if (sqlSession.selectList("mapper.logMap.updatedReady", sources) != null) {
			mav = sqlSession.selectList("mapper.logMap.updatedReady", sources);
		}
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void defaultingAll() {
		String value1 = "CREATE TABLE alt.logged ("
				+ "  l_id INT NOT NULL AUTO_INCREMENT, "
				+ "  b_type INT NULL, "
				+ "  l_room INT NULL, "
				+ "  p_name VARCHAR(45) NULL, "
				+ "  l_type ENUM('ENTER', 'EXIT') NULL, "
				+ "  l_createdat TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, "
				+ "  PRIMARY KEY (l_id))";
		String value2 = "CREATE TABLE alt.rooms ("
				+ "  ro_id int NOT NULL AUTO_INCREMENT, "
				+ "  l_room int DEFAULT NULL, "
				+ " ro_lr varchar(1) DEFAULT NULL, "
				+ "  ro_re tinyint(1) DEFAULT '0', "
				+ "  b_type int DEFAULT NULL, "
				+ "  p_name varchar(45) DEFAULT NULL, "
				+ "  PRIMARY KEY (ro_id) "
				+ ") ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
		String value3 = "CREATE TABLE alt.board ( "
				+ "  b_id INT NOT NULL AUTO_INCREMENT, "
				+ "  p_name VARCHAR(45) NULL, "
				+ "  b_type int NULL, "
				+ "  b_title VARCHAR(100) NULL, "
				+ "  b_cotext VARCHAR(200) NULL, "
				+ "  b_createdat TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, "
				+ "  b_playing tinyint(1) DEFAULT '0', "
				+ "  l_room int DEFAULT NULL, "
				+ "  PRIMARY KEY (b_id))";
		String value4 = "CREATE TABLE `alt`.`game` ( "
				+ "  `g_id` INT NOT NULL AUTO_INCREMENT, "
				+ "  `p_name` VARCHAR(45) NULL, "
				+ "  `b_type` INT NULL, "
				+ "  `ro_lr` VARCHAR(1) NULL, "
				+ "  `g_hand` VARCHAR(45) NULL, "
				+ "  `g_num` INT NULL, "
				+ "  PRIMARY KEY (`g_id`));";
		sqlSession.update("mapper.defaulting.defaulted1");
		sqlSession.update("mapper.defaulting.defaulted2");
		sqlSession.update("mapper.defaulting.defaulted3");
		sqlSession.update("mapper.defaulting.defaulted4");
		String value = value1;
		sqlSession.update("mapper.defaulting.defaulted1-1",value);
		value = value2;
		sqlSession.update("mapper.defaulting.defaulted1-1",value);
		value = value3;
		sqlSession.update("mapper.defaulting.defaulted1-1",value);
		value = value4;
		sqlSession.update("mapper.defaulting.defaulted1-1",value);
		
	}


}
