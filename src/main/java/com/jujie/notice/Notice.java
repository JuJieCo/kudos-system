package com.jujie.notice;

import java.util.Date;

public class Notice {

	private int uuid;
	private String title;
	private String content;
	private int public_uuid;
	private Date public_time;
	private Date start_time;
	private Date end_time;
	private int status;
	private int rank;
	private String hold1;
	private String hold2;
	private String remark;	
	private String execute_userName;
	private String execute_dept;	
	public String getExecute_dept() {
		return execute_dept;
	}
	public void setExecute_dept(String execute_dept) {
		this.execute_dept = execute_dept;
	}
	public String getExecute_userName() {
		return execute_userName;
	}
	public void setExecute_userName(String execute_userName) {
		this.execute_userName = execute_userName;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPublic_uuid() {
		return public_uuid;
	}
	public void setPublic_uuid(int public_uuid) {
		this.public_uuid = public_uuid;
	}
	public Date getPublic_time() {
		return public_time;
	}
	public void setPublic_time(Date public_time) {
		this.public_time = public_time;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getHold1() {
		return hold1;
	}
	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}
	public String getHold2() {
		return hold2;
	}
	public void setHold2(String hold2) {
		this.hold2 = hold2;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
