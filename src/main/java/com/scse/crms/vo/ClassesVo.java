package com.scse.crms.vo;

import com.scse.crms.po.Classes;

public class ClassesVo extends Classes {
	String sid;
	String sname;
	String cid;
	String classroom;
	String seat;
	String usual_performance;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getUsual_performance() {
		return usual_performance;
	}
	public void setUsual_performance(String usual_performance) {
		this.usual_performance = usual_performance;
	}
	
	
}