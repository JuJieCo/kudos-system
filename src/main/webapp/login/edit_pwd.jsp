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
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

.btn_mouseout {
 BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#AAC8F0); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
input     {
           background-color: #ffffff;
           border: 1px solid #669ACC;
           font-size: 12px;
           font-style: normal;
           font-variant: normal;
           line-height: normal;
          }
SELECT{
           font-size: 12px;     
}

-->
</style>



<link href="${pageContext.request.contextPath}/resource/css/date/date.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/css/css.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/jquery.datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/datepicker_lang_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/loading.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<script type="text/javascript">
	function editPwd(){
		var oldpwd = $("input[name=oldpwd]").val();
		var newpwd = $("input[name=newpwd]").val();
		var newpwd2 = $("input[name=newpwd2]").val();
		if(oldpwd==''||newpwd==''||newpwd2==''){
			alert("密码不能为空！");
			return ;
		}
		if(newpwd!=newpwd2){
			alert("两次密码不一致！");
			return ;
		}
		if(oldpwd!='${sessionLogin.loginPwd}'){
			alert("原密码输入不对！");
			return ;
		}
		$.post("${pageContext.request.contextPath}/login/loginAction!editPwd",{oldpwd:oldpwd,newpwd:newpwd,newpwd2:newpwd2},function(data){
			alert(data);
			window.close();
		});
	}
	
</script>

</head>

<body>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">旧密码：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input type="password" name="oldpwd" value="" /></td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">新密码：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input type="password" name="newpwd" value="" /></td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">再次输入：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input type="password" name="newpwd2" value="" /></td>
	</tr>
</table>

		
		
 
<table border="0" align="center" cellpadding="0" cellspacing="0" height="100">
	<tr>
		<td>
			<input  type="button"  value="提交" onclick="editPwd()"/>
			<input  type="button"  value="关闭" onclick="window.close()"/>
		</td>
	</tr>
</table>
         
</body>
</html>