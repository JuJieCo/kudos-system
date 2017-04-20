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
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/jquery.datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/datepicker_lang_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/date/loading.js"type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<script language=JavaScript>
	jQuery(function($) {
	  	var checkboxAll = $("input[name=checkboxAll][type=checkbox]");
	  	checkboxAll.unbind().click(function(){
	  		if($(this).attr("checked")==true){
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",true);
	  		}else{
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",false);
	  		}
	  	});
	  	var checkboxUUid = $("input[name=checkbox_uuid][type=checkbox]");
	  	checkboxUUid.unbind().click(function(){
	  		var checked_num = $("input[name=checkbox_uuid][type=checkbox]:checked").size();
	  		var checkbox_num = $("input[name=checkbox_uuid][type=checkbox]").size();
	  		if(checked_num==checkbox_num){
	  			checkboxAll.attr("checked",true);
	  		}else{
	  			checkboxAll.attr("checked",false);
	  		}
	  	});
	}); 
</script>

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
	  <form action="${pageContext.request.contextPath}/user/userAction!getAllUser" name="form1" method="post">
	  <td width="58%"  class="STYLE4" >按部门查询：
	  	<select name="user.executeDept">
	  		<option value="">全部</option>
            <option value="零售事业部" ${user.executeDept=='零售事业部'?'selected':''}>零售事业部</option>
            <option value="零售事业部信贷中心" ${user.executeDept=='零售事业部信贷中心'?'selected':''}>零售事业部信贷中心</option>
            <option value="零售事业部产品营销中心" ${user.executeDept=='零售事业部产品营销中心'?'selected':''}>零售事业部产品营销中心</option>
            <option value="零售事业部财富管理部" ${user.executeDept=='零售事业部财富管理部'?'selected':''}>零售事业部财富管理部</option>
            <option value="零售事业部银行卡与渠道管理部" ${user.executeDept=='零售事业部银行卡与渠道管理部'?'selected':''}>零售事业部银行卡与渠道管理部</option>
            <option value="零售事业部综合管理部" ${user.executeDept=='零售事业部综合管理部'?'selected':''}>零售事业部综合管理部</option>
   		</select>
	  &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">&nbsp;</td>
	  </form>
	  <td width="24%">&nbsp;</td>
	  <td width="18%"  class="STYLE4">  
	  		<input type="button"  value="新增" class="btn_mouseout" onclick="location.href='${pageContext.request.contextPath}/user/userAction!getOneUser?&type=add'"/>	
            <input type="button"  value="删除" class="btn_mouseout" onclick="if(confirm('确定要删除吗？')){document.forms.form2.submit()}"/>
	 </td>
	  </tr>  
		</table></td>
	
				
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <form action="${pageContext.request.contextPath}/user/userAction!deleteUser" name="form2" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="3%" height="22" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" align="center">
				<input type="checkbox" name="checkboxAll" value="all" />
            </td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">序号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">用户编号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">部门</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">级别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">登陆名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		 <s:iterator value="userList" var="one">
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center">
              <input type="checkbox" name="checkbox_uuid" value="${one.uuid}" />
           	</td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.uuid}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.executeUserCode}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.executeUserName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.executeDept}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><script>document.write(TranslationUserRank('${one.executeRank}'));</script></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1">
            		<s:iterator value="loginList" var="lot">
            			<s:if test="#lot.user.uuid==#one.uuid">${lot.loginName}</s:if>
            		</s:iterator>
            	</span>
            </td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!getOneUser?user.uuid=${one.uuid}&type=show" >查看</a></span>
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!getOneUser?user.uuid=${one.uuid}&type=edit" >修改</a></span>
            	<c:if test="${sessionUser.uuid!=one.uuid}">
            	<span class="STYLE1"><a href="javascript:if(confirm('确定要删除吗？')){location.href='${pageContext.request.contextPath}/user/userAction!deleteUser?user.uuid=${one.uuid}'}" >删除</a></span>
           		<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!toAuthorUser?user.uuid=${one.uuid}" >授权</a></span>
            	</c:if>
            </td>
          </tr> 
          </s:iterator>
		 <!--迭代 end  -->
        </table>
        </form>
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