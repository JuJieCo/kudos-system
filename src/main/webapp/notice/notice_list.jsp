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


-->
</style>

<script>

</script>

<script language=JavaScript>
today=new Date();
function initArray(){
 this.length=initArray.arguments.length
 for(var i=0;i<this.length;i++)
 this[i+1]=initArray.arguments[i]  }
 var d=new initArray(" 星期日"," 星期一",
 " 星期二"," 星期三", " 星期四"," 星期五"," 星期六"); 
</script>
<script type="text/javascript">

	function selectPage(currentPage){
		var form = document.forms.form1;
		form.action = form.action+"&page.currentPage="+currentPage;
		form.submit();
	}

</script>
<link href="${pageContext.request.contextPath}/resource/css/date/date.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="${pageContext.request.contextPath}/resource/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%"  valign="middle"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.request.contextPath}/resource/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：公告管理-
                	<c:if test="${noticeType==1 }">发布公告</c:if><c:if test="${noticeType==2 }">审批公告</c:if><c:if test="${noticeType==3 }">收看公告</c:if>
                </td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"> 
						<script language=JavaScript>
					 		document.write("", today.getYear(),"年", today.getMonth()+1,"月",today.getDate(),"日 ",d[today.getDay()+1], "" ); 
						</script>
					</td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <form action="${pageContext.request.contextPath}/notice/noticeAction!getQuery?tag=${tag }" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" align="left"> 
	  &nbsp;起止时间：<input name="notice.start_time" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${stime }"/>  
	  &nbsp;至 <input name="notice.end_time" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${etime }"/>
	  &nbsp;标题 ：<input name="notice.title" type="text"  value="${titl }"/>  
	  &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">&nbsp;
	  </td>
	  <td align="right">
	  <c:if test="${noticeType==1 }">
	  <input name="button11" type="button" value="发布公告" class="btn_mouseout" onclick="location.href='${pageContext.request.contextPath}/notice/noticeAction!showEditNotice?method=isAdd'">&nbsp;&nbsp;
	  </c:if>
	  </td>
	  </tr> 
		</table>
		</form></td>		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <form action="" name="form2" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">公告标题</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">发布人</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">发布时间</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">起始时间</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">结束时间</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">审批状态</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">收看级别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="15%"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		 <s:iterator value="noticeList" var="list" >		
		 <tr>   
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.title }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${execute_userName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.public_time }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.start_time }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.end_time }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            <span class="STYLE1">
            <c:if test="${list.status==0 }">公告已录入</c:if>
            <c:if test="${list.status==1 }">公告已送审</c:if>
            <c:if test="${list.status==2 }">审批已通过</c:if>
            <c:if test="${list.status==3 }">审批未通过</c:if>
            </span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            <span class="STYLE1">
            <c:if test="${list.rank==0 }">所有部门</c:if>
            <c:if test="${list.rank==1 }">${list.execute_dept }</c:if>
            </span>
            </td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!showNotice?uuid=${list.uuid }&tag=${tag }">查看</a></span>&nbsp;
            	<c:if test="${list.status==0}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!upRatify?uuid=${list.uuid }" onclick="return(confirm('确认要送审吗?'))">送审</a></span>&nbsp;
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!showEditNotice?method=isUpdate&uuid=${list.uuid }">修改</a></span>&nbsp;
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!deleteNotice?uuid=${list.uuid }" onclick="return(confirm('确认要删除吗?'))">删除</a></span>
          		</c:if>
            	<c:if test="${list.status==1 && tag == 't'}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!showRatifyNotice?uuid=${list.uuid }&tag=${tag }">审批</a></span>&nbsp;
            	</c:if> 
				<c:if test="${list.status==2 && tag == 't'}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!deleteNotice?uuid=${list.uuid }&tag=${tag }" onclick="return(confirm('确认要删除吗?'))">删除</a></span>
            	</c:if>
				<c:if test="${list.status==3 && tag != 't'}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!showEditNotice?method=isUpdate&uuid=${list.uuid }">修改</a></span>&nbsp;
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/notice/noticeAction!deleteNotice?uuid=${list.uuid }" onclick="return(confirm('确认要删除吗?'))">删除</a></span>
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