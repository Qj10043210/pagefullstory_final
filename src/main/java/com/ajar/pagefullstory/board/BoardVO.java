package com.ajar.pagefullstory.board;

import java.sql.Timestamp;

public class BoardVO {
	private Integer b_id;
	private String p_name;
	private Integer b_type;
	private String b_title;
	private String b_context;
	private Timestamp b_createdat;
	private Integer b_playing;
	private Integer l_room;
	
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getB_type() {
		return b_type;
	}
	public void setB_type(Integer b_type) {
		this.b_type = b_type;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_context() {
		return b_context;
	}
	public void setB_context(String b_context) {
		this.b_context = b_context;
	}
	public Timestamp getB_createdat() {
		return b_createdat;
	}
	public void setB_createdat(Timestamp b_createdat) {
		this.b_createdat = b_createdat;
	}
	public Integer getB_playing() {
		return b_playing;
	}
	public void setB_playing(Integer b_playing) {
		this.b_playing = b_playing;
	}
	public Integer getL_room() {
		return l_room;
	}
	public void setL_room(Integer l_room) {
		this.l_room = l_room;
	}
	
	
	
}
