package com.jujie.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jujie.global.BaseBean;
import com.jujie.user.User;

public class Login extends BaseBean {
	private Integer uuid;
	private String loginName;
	private String loginPwd;
	private User user;
	private Role treeRole;
	private List<Role> roleList;
	
	public Login(){
		this.user = new User();
		this.roleList = new ArrayList<Role>();
		this.treeRole = new Role();
	}

	public Role getTreeRole() {
		return treeRole;
	}

	public void setTreeRole(Role treeRole) {
		this.treeRole = treeRole;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public Login mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.setLoginName(rs.getString("login_name"));
		login.setLoginPwd(rs.getString("login_pwd"));
		login.setUuid(rs.getInt("login_uuid"));
		login.getUser().setUuid(rs.getInt("execute_user_uuid"));
		return login;
	}
	
	
}
