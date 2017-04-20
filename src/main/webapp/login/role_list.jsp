<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link href="${pageContext.request.contextPath}/resource/css/date/date.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/jquery.datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/datepicker_lang_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/loading.js"type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<script type="text/javascript">

	function selectPage(currentPage){
		var form = document.forms.form1;
		form.action = form.action+"?page.currentPage="+currentPage;
		form.submit();
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
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <form action="${pageContext.request.contextPath}/login/roleAction!queryAllRoles" name="form1" method="post">
	  <td width="58%"  class="STYLE4" >&nbsp;</td>
	  </form>
	  <td width="24%">&nbsp;</td>
	  <td width="18%"  class="STYLE4">  
	  		<input type="button"  value="新增角色" class="btn_mouseout" onclick="location.href='${pageContext.request.contextPath}/login/roleAction!showRole?type=add'"/>	
	 </td>
	  </tr>  
		</table></td>
	
				
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">序号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">角色名称</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">操作</span></td>
          </tr>
		    <!-- 迭代  第一层-->
		    <s:iterator value="roleList" var="one">
		    <tr>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.uuid}</span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.roleName}</span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center">
	            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/login/roleAction!showRole?role.uuid=${one.uuid}&type=show" >查看</a></span>
	            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/login/roleAction!showRole?role.uuid=${one.uuid}&type=edit" >修改</a></span>
	            	<span class="STYLE1"><a href="javascript:if(confirm('确认要删除吗?')) {location.href='${pageContext.request.contextPath}/login/roleAction!deleteRole?role.uuid=${one.uuid}'}" >删除</a></span>
	           		<span class="STYLE1"><a href="${pageContext.request.contextPath}/login/roleAction!showRole?role.uuid=${one.uuid}&type=authorize" >授权</a></span>
	            </td>
            </tr> 
            </s:iterator>
		 	<!--迭代 end  -->
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
        <td><%@include file="../common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>