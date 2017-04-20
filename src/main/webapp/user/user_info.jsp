<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link href="${pageContext.request.contextPath}/resource/css/date/date.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/css/css.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/jquery.datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/datepicker_lang_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/loading.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<script type="text/javascript">
function valitForm(){
	var lname = $("input[name=login.loginName]").val();
	var pwd = $("input[name=login.loginPwd]").val();
	var uname = $("input[name=user.executeUserName]").val();
	
	if(jQuery.trim(lname)==""){
		alert("登陆名称不能为空！");
		return false;
	}
	if(jQuery.trim(pwd)==""){
		alert("登陆密码不能为空！");
		return false;
	}
	if(jQuery.trim(uname)==""){
		alert("用户姓名不能为空！");
		return false;
	}
	return true;
}
</script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="../common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td>
	<form action="${pageContext.request.contextPath}/user/userAction!${type=='edit'?'editOneUser':'saveOneUser'}" name="" method="post" onsubmit="return valitForm();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <input type="hidden" name="user.uuid" value="${user.uuid}" />
        <input type="hidden" name="login.uuid" value="${login.uuid}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">登陆名称：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="login.loginName" type="text" value="${login.loginName}"/></td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">登陆密码：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="login.loginPwd" type="password" value="${login.loginPwd}"/></td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">姓名：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input  name="user.executeUserName" type="text" value="${user.executeUserName}"/> </td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">部门：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
            	<select name="user.executeDept">
            	<option value="零售事业部" ${user.executeDept=='零售事业部'?'selected':''}>零售事业部</option>
	            <option value="零售事业部信贷中心" ${user.executeDept=='零售事业部信贷中心'?'selected':''}>零售事业部信贷中心</option>
	            <option value="零售事业部产品营销中心" ${user.executeDept=='零售事业部产品营销中心'?'selected':''}>零售事业部产品营销中心</option>
	            <option value="零售事业部财富管理部" ${user.executeDept=='零售事业部财富管理部'?'selected':''}>零售事业部财富管理部</option>
	            <option value="零售事业部银行卡与渠道管理部" ${user.executeDept=='零售事业部银行卡与渠道管理部'?'selected':''}>零售事业部银行卡与渠道管理部</option>
	            <option value="零售事业部综合管理部" ${user.executeDept=='零售事业部综合管理部'?'selected':''}>零售事业部综合管理部</option>
		    	</select>
            </td>
          </tr>
		  <tr>
           
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">级别：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
            	<select name="user.executeRank">
            		<option value="1" ${user.executeRank==1?'selected':''}>A</option>
            		<option value="2" ${user.executeRank==2?'selected':''}>B</option>
            		<option value="3" ${user.executeRank==3?'selected':''}>C</option>
            	</select>
           </td>
           <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">编号：</td>
           <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">
           		<input type="hidden" name="user.executeUserCode" value="${user.executeUserCode}">
           		&nbsp;${user.executeUserCode}
           </td>
          </tr>
        </table>

		
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> <td >
				<input  type="submit"  value="提交" id="form_oper"/>
				<input  type="button"  value="返回" onclick="history.back()"/>
				</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>

<script type="text/javascript">
	if('show'=='${type}'){
		$("#form_oper").hide();
		$("input").attr("readonly",true);
		$("input").css({"background-color":"#e7e9ea"});
	}
</script>

</body>
</html>