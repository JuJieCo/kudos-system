package com.jujie.login.action;

import java.util.ArrayList;
import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.Page;
import com.jujie.login.Functions;
import com.jujie.login.Role;
import com.jujie.login.server.LoginRoleFunServerImpl;

public class RoleAction extends BaseActionSupper {

	private Role role ;
	private List<Role> roleList;
	private Page page;
	
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String queryAllRoles(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			if(page==null){
				page = new Page(1);
			}
			roleList = lrfServer.queryAllRoles(null,page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "rolelist";
	}
	
	public String saveRole(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.saveRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllRoles();
	}
	
	public String editRole(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.editRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllRoles();
	}
	
	public String showRole(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			if(role!=null){
				role = lrfServer.queryOneRole(role.getUuid());
				
			}
			if("authorize".equals(request.getParameter("type"))){
				List<Functions> functionsList = lrfServer.standardizedFunctionsList(lrfServer.queryAllFunctions());
				this.getCxt().put("functionsList", functionsList);
				return "roleauthorize";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getCxt().put("type", DataUtil.getStringK(request.getParameter("type")));
		return "roleaddmod";
	}
	
	public String deleteRole(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.deleteRole(role.getUuid());
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllRoles();
	}
	
	public String authorRole(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			String[] funUuids = request.getParameterValues("fun_uuid");
			List<Functions> functionsList = new ArrayList<Functions>();
			for (String funUuid : funUuids) {
				Functions functions = new Functions();
				functions.setUuid(DataUtil.getInt(funUuid));
				functionsList.add(functions);
			}
			role.setFunList(functionsList);
			lrfServer.authorizeRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllRoles();
	}
}
