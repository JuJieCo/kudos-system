package com.jujie.login.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DataUtil;
import com.jujie.login.Functions;
import com.jujie.login.server.LoginRoleFunServerImpl;

public class FunctionsAction extends BaseActionSupper {

	private Functions functions ;
	private List<Functions> functionsList;
	
	
	
	public Functions getFunctions() {
		return functions;
	}



	public void setFunctions(Functions functions) {
		this.functions = functions;
	}



	public List<Functions> getFunctionsList() {
		return functionsList;
	}



	public void setFunctionsList(List<Functions> functionsList) {
		this.functionsList = functionsList;
	}



	public String queryAllFunctions(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			functionsList = lrfServer.queryAllFunctions();
			functionsList = lrfServer.standardizedFunctionsList(functionsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "funlist";
	}
	
	public String saveFunctions(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.saveFunctions(functions);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllFunctions();
	}
	
	public String editFunctions(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.editFunctions(functions);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllFunctions();
	}
	
	public String showFunctions(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			if(functions!=null&&functions.getUuid()!=null){
				functions = lrfServer.queryOneFunctions(functions.getUuid());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getCxt().put("type", DataUtil.getStringK(request.getParameter("type")));
		return "funaddmod";
	}
	
	public String deleteFunctions(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			lrfServer.deleteFunctions(functions.getUuid());
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllFunctions();
	}
}
