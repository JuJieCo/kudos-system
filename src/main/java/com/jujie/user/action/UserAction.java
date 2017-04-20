package com.jujie.user.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.Page;
import com.jujie.login.Login;
import com.jujie.login.Role;
import com.jujie.login.server.LoginRoleFunServerImpl;
import com.jujie.login.util.CreatUserCode;
import com.jujie.user.User;
import com.jujie.user.server.UserServerImpl;

public class UserAction extends BaseActionSupper {
	
 
	private static Log log = LogFactory.getLog(UserAction.class);
	
	private User user;
	private List<User> userList;
	private Login login;
	private List<Login> loginList;
	
	private Page page;
	
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getLoginList() {
		return loginList;
	}
	public void setLoginList(List<Login> loginList) {
		this.loginList = loginList;
	}
	public String getOneUser() {
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			if(user!=null){
				user = server.queryOneUser(user.getUuid());
				login  = lrfServer.getOneLoginByUserID(user.getUuid());
				log.info("获取用户"+user.getUuid()+"-"+user.getExecuteUserName()+" 成功！");
			}
			this.getCxt().put("deptList", server.queryAllDept());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getCxt().put("type", DataUtil.getStringK(request.getParameter("type")));
		return "userinfo";
	}
	
	public String getAllUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			if(page==null){
				page = new Page(1);
			}
			if(user==null){
				userList = server.queryAllUser(null,page);
			}else{
				userList = server.queryAllUser(new Object[]{user.getExecuteDept()},page);
			}
			loginList = new ArrayList<Login>();
			for (User user : userList) {
				Login login  = lrfServer.getOneLoginByUserID(user.getUuid());
				if(login!=null){
					loginList.add(login);
				}
			}
			this.getCxt().put("deptList", server.queryAllDept());
			log.info("获取全部用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userlist";
	}
	
	public String saveOneUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			user.setExecuteUserCode(CreatUserCode.creatCode(user.getExecuteRank()));
			user.setUuid(server.saveOneUser(user));
			login.getUser().setUuid(user.getUuid());
			lrfServer.saveLogin(login);
			log.info("保持用户"+user.getUuid()+" "+user.getExecuteUserName()+" 成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = null;
		return getAllUser();
	}
	
	public String editOneUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			server.editOneUser(user);
			login.setUser(user);
			if(login.getUuid()==null){
				if(!"".equals(login.getLoginName())){
					lrfServer.saveLogin(login);
				}
			}else{
				lrfServer.editLogin(login);
			}
			log.info("修改用户"+user.getUuid()+" "+user.getExecuteUserName()+" 成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = null;
		return getAllUser();
	}
	
	public String deleteUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		String[] ids = request.getParameterValues("checkbox_uuid");
		try {
			if(ids!=null){
				for (String id : ids) {
					lrfServer.deleteLoginByUserID(DataUtil.getInt(id));
					server.deleteOneUser(DataUtil.getInt(id));
				}
			}else{
				lrfServer.deleteLoginByUserID(user.getUuid());
				server.deleteOneUser(user.getUuid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllUser();
	}
	
	public String toAuthorUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			user = server.queryOneUser(user.getUuid());
			login  = lrfServer.getOneLoginByUserID(user.getUuid());
			if(login==null){
				user = null;
				return getAllUser();
			}
			this.getCxt().put("roleList", lrfServer.queryAllRoles(null,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userauthor";
	}
	
	public String authorUser(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			String[] roleUuids = request.getParameterValues("role_uuid");
			List<Role> roleList = new ArrayList<Role>();
			for (String roleUuid : roleUuids) {
				Role role = new Role();
				role.setUuid(DataUtil.getInt(roleUuid));
				roleList.add(role);
			}
			login.setRoleList(roleList);
			lrfServer.authorizeLogin(login);
		}catch(Exception e){
			e.printStackTrace();
		}
		return getAllUser();
	}
}
