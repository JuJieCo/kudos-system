package com.jujie.task;

import java.util.Date;

import com.jujie.user.User;

public class WorkProgress {
	private Integer uuid;
	private WorksTask task;
	private String content;
	private String progress;
	private Date inputTime;
	private User inputUser;
    private String hold1;
	private String hold2;
	private String remark;
	
	public WorkProgress(){
		task = new WorksTask();
		inputUser = new User();
	}
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	 
	public WorksTask getTask() {
		return task;
	}
	public void setTask(WorksTask task) {
		this.task = task;
	}
	 
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	 
	 
	public User getInputUser() {
		return inputUser;
	}
	public void setInputUser(User inputUser) {
		this.inputUser = inputUser;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	 
 
}
