package com.jujie.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jujie.global.BaseBean;

public class Functions extends BaseBean {
	
	private Integer uuid;
	private String funName;
	private String funUrl;
	private Integer funPID;
	private String funSign;
	private List<Functions> funList;
	
	public Functions(){
		this.funList = new ArrayList<Functions>();
	}

	public Integer getFunPID() {
		return funPID;
	}

	public void setFunPID(Integer funPID) {
		this.funPID = funPID;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public List<Functions> getFunList() {
		return funList;
	}

	public void setFunList(List<Functions> funList) {
		this.funList = funList;
	}
	
	

	public String getFunSign() {
		return funSign;
	}

	public void setFunSign(String funSign) {
		this.funSign = funSign;
	}

	@Override
	public Functions mapRow(ResultSet rs, int rownum) throws SQLException {
		Functions functions = new Functions();
		functions.setFunName(rs.getString("fun_name"));
		functions.setFunPID(rs.getInt("fun_pid"));
		functions.setFunUrl(rs.getString("fun_url"));
		functions.setUuid(rs.getInt("fun_uuid"));
		functions.setFunSign(rs.getString("fun_sign"));
		return functions;
	}
	
	
}
