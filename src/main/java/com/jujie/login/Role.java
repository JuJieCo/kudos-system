package com.jujie.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jujie.global.BaseBean;

public class Role extends BaseBean {

	private Integer uuid;
	private String roleName;
	private List<Functions> funList;
	
	public Role(){
		this.funList = new ArrayList<Functions>();
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Functions> getFunList() {
		return funList;
	}

	public void setFunList(List<Functions> funList) {
		this.funList = funList;
	}

	@Override
	public Role mapRow(ResultSet rs, int rownum) throws SQLException {
		Role role = new Role();
		role.setUuid(rs.getInt("role_uuid"));
		role.setRoleName(rs.getString("role_name"));
		return role;
	}
	
	
}
