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
		var name = $("input[name=functions.funName]").val();
		var url = $("input[name=functions.funUrl]").val();
		var sign = $("input[name=functions.funSign]").val();
		
		if(jQuery.trim(name)==""){
			alert("名称不能为空！");
			return false;
		}
		if(jQuery.trim(url)!=""&&jQuery.trim(sign)==""){
			alert("标志不能为空！");
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
	<form action="${pageContext.request.contextPath}/login/functionsAction!${type=='edit'?'editFunctions':'saveFunctions'}" name="" method="post" onsubmit="return valitForm();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <input type="hidden" name="functions.uuid" value="${functions.uuid}" />
        <input type="hidden" name="functions.funPID" value="${functions.funPID}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">功能名称：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="functions.funName" type="text" value="${functions.funName}"/></td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">功能URL：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="functions.funUrl" type="text" value="${functions.funUrl}"/></td>
          </tr>
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">功能标志：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="functions.funSign" type="text" value="${functions.funSign}"/></td>
          	<td></td><td></td>
          </tr>
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td>
              <table border="0" align="center" cellpadding="0" cellspacing="0" >
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