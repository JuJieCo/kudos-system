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
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="../common/table_top.jsp" %>
    </td>
  </tr>
  <form action="${pageContext.request.contextPath}/login/roleAction!authorRole" name="" method="post">
  <tr>
    <td><input type="hidden" name="role.uuid" value="${role.uuid}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0">  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
             <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">功能名称</span></td>
			 <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">功能URL</span></td>
			 <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">功能标志</span></td>
          </tr>
	      <!-- 迭代  第一层-->
	      <s:iterator value="functionsList" var="one">
	      	<s:set value="false" name="flag"></s:set>
		    <tr>
	            <td height="20" bgcolor="#FFFFFF" align="left" style="padding-left:20px">
	            	<s:iterator value="role.funList" var="rl">
	            		<s:if test="#one.uuid==#rl.uuid">
	            			<s:set value="true" name="flag"></s:set>
	            		</s:if>
	            	</s:iterator>
	            	<s:if test="#flag==true">
	            		<input type="checkbox" name="fun_uuid" value="${one.uuid}" checked/>
	            	</s:if>
	            	<s:else>
	            		<input type="checkbox" name="fun_uuid" value="${one.uuid}"/>
	            	</s:else>
	            	<span class="STYLE1">${one.funName}</span>
	            </td>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.funUrl}</span></td>
          		<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.funSign}</span></td>
           </tr> 
          <!-- 迭代  第二层-->
		  <s:iterator value="#one.funList" var="two">
		  	<s:set value="false" name="flag2"></s:set>
		    <tr>
                <td height="20" bgcolor="#FFFFFF" align="left" style="padding-left:30px">
	            	<s:iterator value="role.funList" var="rl">
	            		<s:if test="#two.uuid==#rl.uuid">
	            			<s:set value="true" name="flag2"></s:set>
	            		</s:if>
	            	</s:iterator>
	            	<s:if test="#flag2==true">
	            		<input type="checkbox" name="fun_uuid" value="${two.uuid}" checked/>
	            	</s:if>
	            	<s:else>
	            		<input type="checkbox" name="fun_uuid" value="${two.uuid}"/>
	            	</s:else>
	            	<span class="STYLE1">${two.funName}</span>
	            </td>
                <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${two.funUrl}</span></td>
            	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${two.funSign}</span></td>
            </tr> 
          <!-- 迭代  第三层-->
		  <s:iterator value="#two.funList" var="three">
		  	<s:set value="false" name="flag3"></s:set>
		    <tr>
                <td height="20" bgcolor="#FFFFFF" align="left" style="padding-left:40px">
	            	<s:iterator value="role.funList" var="rl">
	            		<s:if test="#three.uuid==#rl.uuid">
	            			<s:set value="true" name="flag3"></s:set>
	            		</s:if>
	            	</s:iterator>
	            	<s:if test="#flag3==true">
	            		<input type="checkbox" name="fun_uuid" value="${three.uuid}" checked/>
	            	</s:if>
	            	<s:else>
	            		<input type="checkbox" name="fun_uuid" value="${three.uuid}"/>
	            	</s:else>
	            	<span class="STYLE1">${three.funName}</span>
	            </td>
            	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${three.funUrl}</span></td>
            	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${three.funSign}</span></td>
            </tr> 
          <!-- 迭代  第四层-->
	  	  <s:iterator value="#three.funList" var="four">
	  	  	<s:set value="false" name="flag4"></s:set>
		    <tr>
                <td height="20" bgcolor="#FFFFFF" align="left" style="padding-left:20px">
	            	<s:iterator value="role.funList" var="rl">
	            		<s:if test="#four.uuid==#rl.uuid">
	            			<s:set value="true" name="flag4"></s:set>
	            		</s:if>
	            	</s:iterator>
	            	<s:if test="#flag4==true">
	            		<input type="checkbox" name="fun_uuid" value="${four.uuid}" checked/>
	            	</s:if>
	            	<s:else>
	            		<input type="checkbox" name="fun_uuid" value="${four.uuid}"/>
	            	</s:else>
	            	<span class="STYLE1">${four.funName}</span>
	            </td>
                <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${four.funUrl}</span></td>
            	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${four.funSign}</span></td>
            </tr> 
           </s:iterator>
           </s:iterator>
           </s:iterator>
           </s:iterator>
	 	   <!--迭代 end  -->
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
  	<td>
  		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="40">
	  		<tr>
			  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
			  <td align="center">
			  		<input type="submit" name="" value="提交">
			  		<input type="button" name="" value="返回" onclick="history.back()">
			  </td>		
		  	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
		  	</tr>
		</table>
    </td>
  </tr>
  </form>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
	        <td></td>
	        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
	      </tr>
        </table>
    </td>
  </tr>
</table>
</body>
</html>