package com.jujie.login.server;

import java.util.List;

import com.jujie.global.util.Page;
import com.jujie.login.Functions;
import com.jujie.login.Login;
import com.jujie.login.Role;
import com.jujie.login.dao.FunctionsDaoImpl;
import com.jujie.login.dao.LoginDaoImpl;
import com.jujie.login.dao.RoleDaoImpl;

public class LoginRoleFunServerImpl {
	
	private LoginDaoImpl loginDao;
	private RoleDaoImpl roleDao;
	private FunctionsDaoImpl functionsDao;
	
	public void setLoginDao(LoginDaoImpl loginDao) {
		this.loginDao = loginDao;
	}
	public void setRoleDao(RoleDaoImpl roleDao) {
		this.roleDao = roleDao;
	}
	public void setFunctionsDao(FunctionsDaoImpl functionsDao) {
		this.functionsDao = functionsDao;
	}

	
	public List<Functions> standardizedFunctionsList(List<Functions> functionsList){
		int root = 0;
		for (Functions functions : functionsList) {
			if(functions.getFunPID()==null||functions.getFunPID()==0){
				root++;
				continue;
			}
			for(int i = 0 ; i < functionsList.size() ; i++){
				if(functions.getFunPID()==functionsList.get(i).getUuid()){
					functionsList.get(i).getFunList().add(functions);
				}
			}
		}
		while(functionsList.size()!=root){
			int i = 0 ;
			for(; i < functionsList.size() ; i++){
				if(functionsList.get(i).getFunPID()!=null&&functionsList.get(i).getFunPID()!=0){
					functionsList.remove(i);
				}
			}
		}
		return functionsList;
	}
	
	public List<Functions> queryAllFunctions() throws Exception{
		return functionsDao.getAllFunctions();
	}
	
	public Functions queryOneFunctions(int uuid) throws Exception{
		return functionsDao.getOneFunctions(uuid);
	}
	
	public int saveFunctions(Functions functions) throws Exception{
		return functionsDao.saveFunctions(functions);
	}
	
	public void editFunctions(Functions functions) throws Exception{
		functionsDao.editFunctions(functions);
	}
	
	public void deleteFunctions(int uuid) throws Exception{
		functionsDao.deleteFunctions(uuid);
	}
	
	public List<Role> queryAllRoles(Object[] objs,Page page) throws Exception{
		return roleDao.getAllRoles(objs,page);
	}
	
	public Role queryOneRole(int uuid) throws Exception{
		Role role = roleDao.getOneRole(uuid);
		role.setFunList(functionsDao.getFunctionssByRole(uuid));
		return role;
	}
	
	public int saveRole(Role role) throws Exception{
		return roleDao.saveRole(role);
	}
	
	public void editRole(Role role) throws Exception{
		roleDao.editRole(role);
	}
	
	public void deleteRole(int uuid) throws Exception{
		roleDao.deleteRole(uuid);
	}
	
	public void authorizeRole(Role role) throws Exception{
		roleDao.authorizeRole(role);
	}
	
	public List<Login> queryAllLogins(Object[] objs) throws Exception{
		return loginDao.getAllLogins(objs);
	}
	
	public Login queryOneLogin(int uuid) throws Exception{
		return loginDao.getOneLogin(uuid);
	}
	
	public Login queryLoginByNameAndPwd(String name, String pwd) throws Exception{
		Login login = loginDao.getOneLogin(name, pwd);
		if(login!=null){
			List<Role> roleList = roleDao.getRolesByLogin(login.getUuid());
			for (Role role : roleList) {
				role.setFunList(functionsDao.getFunctionssByRole(role.getUuid()));
			}
			login.setRoleList(roleList);
		}
		return login;
	}
	
	public Login getOneLoginByUserID(int userUuid) throws Exception{
		Login login = loginDao.getOneLoginByUserID(userUuid);
		if(login!=null){
			login.setRoleList(roleDao.getRolesByLogin(login.getUuid()));
		}
		return login;
	}
	
	public int saveLogin(Login login) throws Exception{
		return loginDao.saveLogin(login);
	}
	
	public void editLogin(Login login) throws Exception{
		loginDao.editLogin(login);
	}
	
	public void deleteLogin(int uuid) throws Exception{
		loginDao.deleteLogin(uuid);
	} 
	
	public void deleteLoginByUserID(int UserUuid) throws Exception{
		loginDao.deleteLoginByUserID(UserUuid);
	} 
	
	public void authorizeLogin(Login login) throws Exception{
		loginDao.authorizeLogin(login);
	}
	
	public Role getTreeRoleToLogin(int loginUuid) throws Exception{
		Role role = new Role();
		List<Functions> funList = functionsDao.getAllFunctionsByLogin(loginUuid);
		funList = standardizedFunctionsList(funList);
		role.setFunList(funList);
		return role;
	}
	
	public boolean editPwd(int loginUuid,String pwd){
		return loginDao.editPwd(loginUuid, pwd);
	}
	
}
