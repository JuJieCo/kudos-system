package com.jujie.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class User extends BaseBean{

	private Integer uuid;
	private String executeDept;
	private String executeUserCode;
	private String executeUserName;
	private Integer executeRank;
	private Integer executeStatus;
	
	public Integer getExecuteStatus() {
		return executeStatus;
	}
	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getExecuteDept() {
		return executeDept;
	}
	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
	}
	public String getExecuteUserCode() {
		return executeUserCode;
	}
	public void setExecuteUserCode(String executeUserCode) {
		this.executeUserCode = executeUserCode;
	}
	public String getExecuteUserName() {
		return executeUserName;
	}
	public void setExecuteUserName(String executeUserName) {
		this.executeUserName = executeUserName;
	}
	public Integer getExecuteRank() {
		return executeRank;
	}
	public void setExecuteRank(Integer executeRank) {
		this.executeRank = executeRank;
	}
	@Override
	public User mapRow(ResultSet rs, int rownum) throws SQLException {
		User user = new User();
		user.setExecuteDept(rs.getString("execute_dept"));
		user.setExecuteRank(rs.getInt("execute_rank"));
		user.setExecuteUserCode(rs.getString("execute_userCode"));
		user.setExecuteUserName(rs.getString("execute_userName"));
		user.setExecuteStatus(rs.getInt("execute_status"));
		user.setUuid(rs.getInt("execute_uuid"));
		return user;
	}
	
	
}
