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

</head>

<body>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">登陆名称：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;${sessionLogin.loginName}</td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">姓名：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${sessionUser.executeUserName}</td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">部门：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${sessionUser.executeDept}</td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">级别：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
		${sessionUser.executeRank==1?'A':''}
		${sessionUser.executeRank==2?'B':''}
		${sessionUser.executeRank==3?'C':''}
		</td>
	</tr>
	<tr>
		<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">编号：</td>
		<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;${sessionUser.executeUserCode}</td>
	</tr>
</table>

		
		
 
<table border="0" align="center" cellpadding="0" cellspacing="0" height="100">
	<tr><td><input  type="button"  value="关闭" onclick="window.close()"/></td></tr>
</table>
         
</body>
</html>