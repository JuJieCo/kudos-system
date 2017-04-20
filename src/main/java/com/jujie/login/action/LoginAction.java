package com.jujie.login.action;

import java.io.IOException;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.ValidateCodeImageUtils;
import com.jujie.login.Login;
import com.jujie.login.server.LoginRoleFunServerImpl;
import com.jujie.user.User;
import com.jujie.user.server.UserServerImpl;

public class LoginAction extends BaseActionSupper {

	private Login login ;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public String getVailteCode(){
		try {
			String code = ValidateCodeImageUtils.createImage(response.getOutputStream(), 4);
			request.getSession().setAttribute("sessionCode", code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String editPwd(){
		String newPwd = request.getParameter("newpwd");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		Login login = (Login)request.getSession().getAttribute("sessionLogin");
		response.setCharacterEncoding("UTF-8");
		if(lrfServer.editPwd(login.getUuid(), newPwd)){
			try {
				response.getWriter().write("密码修改成功！");
				login.setLoginPwd(newPwd);
				request.getSession().setAttribute("sessionLogin", login);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("密码修改失败！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String login(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		response.setCharacterEncoding("UTF-8");
		try{
			if(!DataUtil.getStringK(request.getParameter("code")).equals((String)request.getSession().getAttribute("sessionCode"))){
				//this.getCxt().put("mesg", "验证码错误！");
				request.setAttribute("mesg", "验证码错误！");
				return "login";
			}
			login = lrfServer.queryLoginByNameAndPwd(login.getLoginName(), login.getLoginPwd());
			login.setTreeRole(lrfServer.getTreeRoleToLogin(login.getUuid()));
			if(login!=null){
				User user = server.queryOneUser(login.getUser().getUuid());
				if(user.getUuid()==null){
					this.getCxt().put("mesg", "用户不存在！");
					return "login";
				}else{
					request.getSession().setAttribute("sessionUser", user);
				}
				request.getSession().setAttribute("sessionLogin", login);
			}else{
				this.getCxt().put("mesg", "登录名或密码有误！");
				return "login";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "login";
		}
		return "index";
	}
	
	public String logout(){
		request.getSession().removeAttribute("sessionLogin");
		request.getSession().removeAttribute("sessionUser");
		return "login";
	}
	
}
