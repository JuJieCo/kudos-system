package com.jujie.global.web;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext ctx = invocation.getInvocationContext();
		Map<String,Object> session = ctx.getSession();
		if(session.get("sessionUser")!=null&&session.get("sessionLogin")!=null){
			return invocation.invoke();
		}
		ctx.put("mesg", "session过期，请重新登录！");
		return Action.LOGIN;
	}

}
