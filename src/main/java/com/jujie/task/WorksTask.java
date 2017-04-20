package com.jujie.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jujie.user.User;

public class WorksTask {

	private List<WorkProgress> taskProgressList;
	private List<WorksAccessory> worksAccessoryList;
	private Integer uuid;
	private String taskCode;
	private String title;
	private String taskContent;
	private List<User> safferList;
	private Integer taskLevel;
	private Date startTime;
	private Date finishTime;
	private User leader;
	private Date issuedTime;
	private Integer status;
	private String hold1;
	private String hold2;
	private String remark;
	
	public WorksTask(){
		taskProgressList=new ArrayList<WorkProgress>();
		worksAccessoryList=new ArrayList<WorksAccessory>();
		safferList=new ArrayList<User>();
		leader=new User();
	}
	
	public List<WorkProgress> getTaskProgressList() {
		return taskProgressList;
	}
	public void setTaskProgressList(List<WorkProgress> taskProgressList) {
		this.taskProgressList = taskProgressList;
	}
	public List<WorksAccessory> getWorksAccessoryList() {
		return worksAccessoryList;
	}
	public void setWorksAccessoryList(List<WorksAccessory> worksAccessoryList) {
		this.worksAccessoryList = worksAccessoryList;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	 
	 
	 
	public Integer getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(Integer taskLevel) {
		this.taskLevel = taskLevel;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
 
	public Date getIssuedTime() {
		return issuedTime;
	}
	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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

	public List<User> getSafferList() {
		return safferList;
	}

	public void setSafferList(List<User> safferList) {
		this.safferList = safferList;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

 
 

	
}
